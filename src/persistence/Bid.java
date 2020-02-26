package persistence;

import java.io.Serializable;

public class Bid implements Serializable {

    private int id;
    private int bidderId;
    private int articleId;
    private float bidValue;

    public Bid(int id, int bidderID, int articleId, float bidValue){
        this.id = id;
        this.bidderId = bidderID;
        this.articleId = articleId;
        this.bidValue = bidValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBidderId() {
        return bidderId;
    }

    public void setBidderId(int bidderId) {
        this.bidderId = bidderId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public float getBidValue() {
        return bidValue;
    }

    public void setBidValue(float bidValue) {
        this.bidValue = bidValue;
    }
}
