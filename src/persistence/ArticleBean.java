package persistence;


import navigation.ActiveUser;
import navigation.Router;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ManagedBean(name="articleBean")
@SessionScoped
public class ArticleBean implements Serializable {

    @Inject
    ArticleManager articleManager;
    @Inject
    ActiveUser activeUser;

    private Person owner;
    private String name;
    private Long id;
    private Double price;
    private String endDate;
    private Date end;
    List<String> categories;
    String cat;
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

    public void setEnd(Date end) {this.end = end;}

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(String cat) {
        String[] splitCat = cat.split(" ");
        this.categories = Arrays.asList(splitCat);
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
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

    public String addArticle(){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        try{
            end = formatter.parse(endDate);
            Article a = new Article(activeUser.getActiveUser(), name, price, end, getCategoriesAsCSV(), description);
            articleManager.addNew(a);
            return "index";
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
