package persistence;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ManagedBean(name="articleBean")
@SessionScoped
public class ArticleBean implements Serializable {

    @Inject
    ArticleManager articleManager;

    private Person owner;
    private String name;
    private Long id;
    private Double price;
    private Date end;
    List<String> categories;
    private String description;

    public ArticleBean(){

    }

    public List<Article> getAvailable(){
        return articleManager.getAllAvailableArticles();
    }

    public String goBack(){
        return "index";
    }


    public String addArticle(){
        Article a = new Article(owner, name, price, end, categories.toString(), description);
        articleManager.addNew(a);
        return "article ajouté";
    }
}
