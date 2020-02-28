package persistence;


import navigation.ActiveUser;
import navigation.Router;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
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
@RequestScoped
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
    List<Article> display;
    String cat;
    private String description;
    private String query;

    public ArticleBean(){
    }

    @PostConstruct
    private void postConstruct(){
        setDisplay(getAvailable());
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

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(String cat) {
        String[] splitCat = cat.split(",");
        this.categories = Arrays.asList(splitCat);
    }

    public List<Article> getDisplay() {
        return display;
    }

    public void setDisplay(List<Article> display) {
        this.display = display;
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

    public List<Article> getOwn(Long id, List<Bid> bids){
        ArrayList<Article> al = (ArrayList<Article>) articleManager.getUserListArticles(id);
        for (Bid b: bids){
            Article a = getById(b.getArticleId());
            if (!al.contains(a)){
                al.add(a);
            }
        }
        return al;
    }

    public String delete(Boolean b,Long id){
        if(b)
            articleManager.delete(id);
        restoreDefault();
        return "home";
    }

    public String addArticle(){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.setCategories(cat);
        try{
            end = formatter.parse(endDate);
            Article a = new Article(activeUser.getActiveUser(), name, price, end, getCategoriesAsCSV(), description);
            articleManager.addNew(a);
            restoreDefault();
            return "home";
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public String updateDisplay(boolean b){
        if (b) { // b search by category
            setDisplay(articleManager.getArticlesByCategory(query));
        } else { // !b search by name
            setDisplay(articleManager.getArticlesByName(query));
        }
        return "home";
    }

    public String restoreDefault(){
        postConstruct();
        return "home";
    }

    public List<Article> getSpecialOffers(){
        return articleManager.getSpecialOffers();
    }
}
