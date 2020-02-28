package persistence;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
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
    public Bid getBidById(Long id) {
        try {
            String query = "SELECT * FROM BIDDING WHERE ID=?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setLong(1,id);
            s.execute();
            ResultSet rs = s.getResultSet();
            if (rs.next())
                return getBid(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Bid> getBidsByArticleId(Long articleId) {
        try {
            String query = "SELECT * FROM BIDDING WHERE ITEM = ?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setLong(1,articleId);
            s.execute();
            ResultSet rs = s.getResultSet();
            List<Bid> bidList = new ArrayList<>();
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
    public List<Bid> getBidsByOwnerId(Long ownerId) {
        try {
            String query = "SELECT * FROM BIDDING WHERE BIDDER = ?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setLong(1,ownerId);
            s.execute();
            ResultSet rs = s.getResultSet();
            List<Bid> bidList = new ArrayList<>();
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

            sb.append("INSERT INTO BIDDING(BIDDER, ITEM, BID) VALUES(");
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

    @Override
    public void deleteBid(Long id) {
        try {
            Bid b = getBidById(id);
            if (b!= null){
            Long itemID = b.getArticleId();
            String query = "DELETE FROM BIDDING WHERE ID = ?";
            PreparedStatement s = connection.prepareStatement(query);
            s.setLong(1,id);
            s.execute();
            Article a = articleManager.getById(itemID);
            // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            if (a.getEnd().before(new Date())) {
                personManager.incrementCanceledBid(b.getBidderId());
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bid getArticleHighestBid(Long id) {
        try {
            String query = "SELECT * FROM BIDDING WHERE BID = (SELECT MAX(BID) FROM BIDDING WHERE ITEM = ?)";
            PreparedStatement s = connection.prepareStatement(query);
            s.setLong(1,id);
            s.execute();
            ResultSet rs = s.getResultSet();
            if (rs.next())
                return getBid(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Bid getArticleHighestBidByOwner(Long itemId, Long ownerId) {
        try {
            String query = "SELECT * FROM BIDDING WHERE BID = (SELECT MAX(BID) FROM BIDDING WHERE ITEM = ? AND BIDDER = ?)";
            PreparedStatement s = connection.prepareStatement(query);
            s.setLong(1, itemId);
            s.setLong(2, ownerId);
            s.execute();
            ResultSet rs = s.getResultSet();
            if (rs.next())
                return getBid(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Bid getBid(ResultSet rs) throws SQLException {
        Bid b = new Bid(rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getDouble(4)
            );
        return b;
    }
}
