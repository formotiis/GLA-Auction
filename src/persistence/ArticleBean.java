package persistence;


import navigation.Router;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name="articleBean")
@SessionScoped
public class ArticleBean implements Serializable {

    @Inject
    ArticleManager articleManager;

    public ArticleBean(){

    }

    public List<Article> getAvailable(){
        return articleManager.getAllAvailableArticles();
    }

    public Article getById(Long id){
        return articleManager.getById(id);
    }

    public List<Article> getOwn(Long id){
        return articleManager.getUserListArticles(id);
    }
}
