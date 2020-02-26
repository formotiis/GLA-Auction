package persistence;

import java.io.Serializable;

public class Bid implements Serializable {

    private Long id;
    private Long bidderId;
    private Long articleId;
    private Double bidValue;

    public Bid(Long id, Long bidderID, Long articleId, Double bidValue){
        this.id = id;
        this.bidderId = bidderID;
        this.articleId = articleId;
        this.bidValue = bidValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBidderId() {
        return bidderId;
    }

    public void setBidderId(Long bidderId) {
        this.bidderId = bidderId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Double getBidValue() {
        return bidValue;
    }

    public void setBidValue(Double bidValue) {
        this.bidValue = bidValue;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", bidderId=" + bidderId +
                ", articleId=" + articleId +
                ", bidValue=" + bidValue +
                '}';
    }
}
