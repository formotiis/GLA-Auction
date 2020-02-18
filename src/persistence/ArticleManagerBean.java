package persistence;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.Date;


@Stateless(name = "ArticleManager")
@LocalBean
public class ArticleManagerBean implements ArticleManager{

    @Resource(lookup = "jdbc/auctions")
    private DataSource dataSource;
    private Connection connection;
    private PersonManager personManager;

    @PostConstruct
    public void initialize() {
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

    // TODO: Implements all Methods below

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

            sb.append("INSERT INTO ARTICLES(OWNER, NAME, ");

            if(article.getDescription() != null)
                sb.append("DESCRIPTION, ");

            sb.append("MINPRICE, CATEGORIES, TIME) VALUES(" + "\'");
            sb.append(article.getOwner())
                    .append("\',\'")
                    .append(article.getName())
                    .append("\',\'");

            if (article.getDescription() != null){
                sb.append(article.getDescription())
                        .append("\',\'");
            }

            sb.append(article.getPrice())
                    .append("\',\'")
                    .append(article.getCategories())
                    .append("\',\'")
                    .append(article.getEnd()).append('\'');
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
    public Article getById(int id) {
        try {
            String query = "SELECT * FROM ARTICLES WHERE ID=?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setInt(1,id);

            s.execute();
            s.execute();
            return getArticle(s.getResultSet());
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
            String query = "SELECT * FROM ARTICLES WHERE USER=?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setInt(1,id.intValue());

            s.execute();
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
        return null;
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
        return null;
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
            String query = "SELECT * FROM ARTICLES WHERE TIME > ?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setDate(7, java.sql.Date.valueOf(date));
            s.execute();
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
        return null;
    }

    public Article getArticle(ResultSet rs) throws SQLException {
        Article a = new Article (
                personManager.getUserByID(rs.getInt(2)),
                rs.getString(3),
                rs.getDouble(5),
                rs.getDate(7),
                rs.getString(6),
                rs.getString(4)
        );
        return a;
    }
}
