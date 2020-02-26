package persistence;

import javax.ejb.Local;
import java.io.Serializable;
import java.util.List;

@Local
public interface BidManager extends Serializable {

    Bid getBidById(int id);
    List<Bid> getBidsByArticleId(int articleId);
    List<Bid> getBidsByOwnerId(int ownerId);
    String addBid(Bid bid);
    String deleteBid(int id);
    Bid getArticleHighestBid(int id);

}
