package persistence;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.Date;


@Stateless(name = "ArticleManager")
@LocalBean
public class ArticleManagerBean implements ArticleManager{

    @Resource(lookup = "jdbc/auctions")
    private DataSource dataSource;
    private Connection connection;
    @Inject
    private PersonManager personManager;

    private List<Article> specialOffers;

    @PostConstruct
    public void initialize() {
        specialOffers = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @PreDestroy
    public void cleanup() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException sqle) {
            sqle.printStackTrace(); }
    }

    /**
     * Ajoute l'article à la bdd
     * @param article à ajouter
     * @return ?
     */
    @Override
    public String addNew(Article article) {
        try {
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder();

            Instant instant = article.getEnd().toInstant();
            ZoneId zone = ZoneId.of("Europe/Paris");
            ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zone);
            LocalDate localDate = zdt.toLocalDate();


            sb.append("INSERT INTO ARTICLES(OWNER, NAME, ");

            if(article.getDescription() != null)
                sb.append("DESCRIPTION, ");

            sb.append("MINPRICE, CATEGORIES, TIME) VALUES(" + "\'");
            sb.append(article.getOwner().getId())
                    .append("\',\'")
                    .append(article.getName())
                    .append("\',\'");

            if (article.getDescription() != null){
                sb.append(article.getDescription())
                        .append("\',\'");
            }

            sb.append(article.getPrice())
                    .append("\',\'")
                    .append(article.getCategoriesAsCSV())
                    .append("\',\'")
                    .append(localDate).append('\'');
            sb.append(")");
            statement.execute(sb.toString());
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param id de l'article
     * @return L'article
     */
    @Override
    public Article getById(Long id) {
        try {
            String query = "SELECT * FROM ARTICLES WHERE ID=?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setLong(1,id);

            s.execute();
            ResultSet rs = s.getResultSet();
                if(rs.next())
            return getArticle(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param id designe un utilisateur
     * @return Articles appartenant à l'utilisateur
     */
    @Override
    public List<Article> getUserListArticles(Long id) {
        try {
            String query = "SELECT * FROM ARTICLES WHERE OWNER=?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setLong(1,id);
            s.execute();
            ResultSet rs = s.getResultSet();
            List<Article> articleList = new ArrayList<Article>();
            while (rs.next()){
                Article a = getArticle(rs);
                articleList.add(a);
            }
            return articleList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     *
     * @return Tous les articles
     */
    @Override
    public List<Article> getAllArticles() {
        try {
            String query = "SELECT * FROM ARTICLES";
            PreparedStatement s = connection.prepareStatement(query);

            s.execute();
            ResultSet rs = s.getResultSet();
            List<Article> articleList = new ArrayList<Article>();
            while (rs.next()){
                Article a = getArticle(rs);
                articleList.add(a);
            }
            return articleList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Get all available articles
     * @return Articles encore à la vente
     */
    @Override
    public List<Article> getAllAvailableArticles() {
        try {
            Date d = new Date();
            String date = d.toString();
            String query = "CALL  availableArticles()";
            PreparedStatement s = connection.prepareStatement(query);
            //s.setDate(7, java.sql.Date.valueOf(date));
            s.execute();
            ResultSet rs = s.getResultSet();
            List<Article> articleList = new ArrayList<Article>();
            while (rs.next()){
                Article a = getArticle(rs);
                articleList.add(a);
            }
            return articleList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public String delete(Long id) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM ARTICLES WHERE ID=?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setLong(1,id);
            s.execute();
            return "success";
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Article> getArticlesByName(String name) {
        try {
            String query = "CALL availableArticlesByName(?)";
            PreparedStatement s = connection.prepareStatement(query);
            s.setString(1, name);
            s.execute();
            ResultSet rs = s.getResultSet();
            List<Article> articleList = new ArrayList<Article>();
            while (rs.next()){
                Article a = getArticle(rs);
                articleList.add(a);
            }
            return articleList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    @Schedule(second="0", minute="0", hour="0",
              dayOfMonth="*", month="*", year="*")
    public void generateSpecialOffers() {
        try {
            specialOffers.clear();
            String query = "SELECT * FROM ARTICLES ORDER BY RAND() LIMIT 3";
            PreparedStatement s = connection.prepareStatement(query);
            s.execute();
            ResultSet rs = s.getResultSet();
            while (rs.next()){
                Article a = getArticle(rs);
                specialOffers.add(a);
            }
            System.out.println(specialOffers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Article> getArticlesByCategory(String category) {
        try {
            String query = "CALL availableArticlesByCategory(?)";
            PreparedStatement s = connection.prepareStatement(query);
            s.setString(1, "%"+category+"%");
            s.execute();
            ResultSet rs = s.getResultSet();
            List<Article> articleList = new ArrayList<Article>();
            while (rs.next()){
                Article a = getArticle(rs);
                articleList.add(a);
            }
            return articleList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Article getArticle(ResultSet rs) throws SQLException {
        Article a = new Article (
                personManager.getUserByID(rs.getLong(2)),
                rs.getString(3),
                rs.getLong(1),
                rs.getDouble(5),
                rs.getDate(7),
                rs.getString(6),
                rs.getString(4)
        );
        return a;
    }

    public List<Article> getSpecialOffers() {
        return specialOffers;
    }
}
