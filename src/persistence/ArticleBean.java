package persistence;


import navigation.Router;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String desc){
        this.description = desc;
    }

    public String getCategoriesAsCSV(){
        StringBuilder sb = new StringBuilder();
        for (String s:categories) {
            sb.append(s).append(',');
        }
        return sb.toString();
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

    public String delete(Boolean b,Long id){
        if(b)
            articleManager.delete(id);
        return "home";
    }
}
