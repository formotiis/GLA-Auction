package persistence;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean(name="articleViewer")
@ViewScoped
public class ArticleViewerBean implements Serializable {
    @ManagedProperty(value = "0")
    Long id;

    Double bid;

    Article article;

    Boolean deletion;

    @Inject
    ArticleManager articleManager;

    public ArticleViewerBean() {
        deletion = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Boolean getDeletion() {
        return deletion;
    }

    public void setDeletion(Boolean deletion) {
        this.deletion = deletion;
    }

    @PostConstruct
    public void fetchArticle(){
        ExternalContext exctx = FacesContext.getCurrentInstance().getExternalContext();
        id = Long.parseLong(exctx.getRequestParameterMap().get("id"));
        this.article = this.articleManager.getById(this.id);
    }

    public void updateData(ActionEvent e) {
        deletion = true;
    }
}
