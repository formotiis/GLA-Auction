package persistence;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name="BidBean")
@SessionScoped
public class BidBean implements Serializable {

    @Inject
    BidManager bidManager;

    private Long id;
    private Long ownerId;
    private Long itemId;
    private Double bidValue;

    public BidBean(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Double getBidValue() {
        return bidValue;
    }

    public void setBidValue(Double bidValue) {
        this.bidValue = bidValue;
    }
    
    public String newBid(Long oID, Long aID, Double val){
        bidManager.addBid(new Bid(-1L, oID, aID, val));
        return "home";
    }

    public List<Bid> getBidsByArticleId(Long articleId) {
        return bidManager.getBidsByArticleId(articleId);
    }

    public Bid getArticleHighestBid(Long id){
        return bidManager.getArticleHighestBid(id);
    }

    public Boolean isTheOwnerOfHighestBit(Long id, Long ownId){
        if(id!=null){
            Bid b = getArticleHighestBid(id);
            if (ownId!=null&&b!=null) {
                return ownId.equals(b.getBidderId());
            }
        }
        return false;
    }
}
