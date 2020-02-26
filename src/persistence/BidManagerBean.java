package persistence;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "BidManager")
@LocalBean
public class BidManagerBean implements BidManager {

    @Resource(lookup = "jdbc/auctions")
    private DataSource dataSource;
    private Connection connection;
    @Inject
    private PersonManager personManager;
    @Inject
    private ArticleManager articleManager;

    public BidManagerBean() {
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
    public Bid getBidById(int id) {
        try {
            String query = "SELECT * FROM BIDDING WHERE ID=?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setLong(1,id);
            s.execute();
            return getBid(s.getResultSet());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Bid> getBidsByArticleId(int articleId) {
        try {
            String query = "SELECT * FROM BIDDING WHERE ITEM = ?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setInt(1,articleId);
            s.execute();
            ResultSet rs = s.getResultSet();
            List<Bid> bidList = new ArrayList<Bid>();
            while (rs.next()){
                Bid b = getBid(rs);
                bidList.add(b);
            }
            return bidList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Bid> getBidsByOwnerId(int ownerId) {
        try {
            String query = "SELECT * FROM BIDDING WHERE BIDDER = ?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setInt(1,ownerId);
            s.execute();
            ResultSet rs = s.getResultSet();
            List<Bid> bidList = new ArrayList<Bid>();
            while (rs.next()){
                Bid b = getBid(rs);
                bidList.add(b);
            }
            return bidList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String addBid(Bid bid) {
        try {
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder();

            sb.append("INSERT INTO BIDDING(BIDDER, ITEM, BID VALUES(");
            sb.append(bid.getBidderId())
                    .append(",");
            sb.append(bid.getArticleId())
                    .append(",");
            sb.append(bid.getBidValue());
            sb.append(")");
            statement.execute(sb.toString());
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Ajouter incrémentation du compteur de suppression sur la personne
     */
    @Override
    public String deleteBid(int id) {
        try {
            Bid b = getBidById(id);
            int itemID = b.getArticleId();
            String query = "DELETE FROM BIDDING WHERE ID = ?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setInt(1,id);
            s.execute();
            Article a = articleManager.getById((long) itemID);
            if (a.getEnd().before(new Date(System.currentTimeMillis()))) {
                personManager.incrementCanceledBid((long) id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Bid getArticleHighestBid(int id) {
        try {
            String query = "SELECT * FROM BIDDING WHERE BID = (SELECT MAX(BID) FROM BIDDING WHERE ITEM = ?)";
            PreparedStatement s = connection.prepareStatement(query);
            s.setLong(1,id);
            s.execute();
            return getBid(s.getResultSet());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Bid getBid(ResultSet rs) throws SQLException {
        if(rs.next()){
            Bid b = new Bid(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getFloat(4)
            );
            return b;
        }
        return null;
    }
}
