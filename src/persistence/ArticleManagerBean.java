package persistence;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Stateless(name = "ArticleManager")
@LocalBean
public class ArticleManagerBean implements ArticleManager{

    @Resource(lookup = "jdbc/auctions")
    private DataSource dataSource;
    private Connection connection;

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
        return null;
    }

    /**
     *
     * @param id de l'article
     * @return L'article
     */
    @Override
    public Article getById(int id) {
        return null;
    }

    /**
     *
     * @param id designe un utilisateur
     * @return Articles appartenant à l'utilisateur
     */
    @Override
    public List<Article> getUserListArticles(Long id) {
        return null;
    }

    /**
     *
     * @return Tous les articles
     */
    @Override
    public List<Article> getAllArticles() {
        return null;
    }

    /**
     * Get all available articles
     * @return Articles encore à la vente
     */
    @Override
    public List<Article> getAllAvailableArticles() {
        return null;
    }
}
