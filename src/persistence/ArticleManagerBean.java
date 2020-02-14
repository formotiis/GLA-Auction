package persistence;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Stateless(name="ArticleManager")
@LocalBean
public class ArticleManagerBean implements ArticleManager {

    @Resource(lookup = "jdbc/auctions")
    private DataSource dataSource;
    private Connection connection;
    public ArticleManagerBean() {
    }

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

    @Override
    public String addArticle(Article a){
        try{
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
