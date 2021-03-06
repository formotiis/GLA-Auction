package persistence;

import javax.ejb.Local;
import java.io.Serializable;
import java.util.List;

@Local
public interface BidManager extends Serializable {

    Bid getBidById(Long id);
    List<Bid> getBidsByArticleId(Long articleId);
    List<Bid> getBidsByOwnerId(Long ownerId);
    String addBid(Bid bid);
    void deleteBid(Long id);
    Bid getArticleHighestBid(Long id);
    Bid getArticleHighestBidByOwner(Long itemId, Long ownerId);

}
