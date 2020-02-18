package persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Article implements Serializable {

    private Person owner;
    private String name;
    private Long id;
    private Double price;
    private Date end;
    List<String> categories;
    private String description;

    public Article(Person owner, String name, Long id, Double price, Date end, String categories, String desc) {
        this.owner = owner;
        this.name = name;
        this.id = id;
        this.price = price;
        this.end = end;
        this.categories = fromCSV(categories);
        this.description = desc;
    }

    public Article(Person owner, String name, Double price, Date end, String categories, String desc) {
        this.owner = owner;
        this.name = name;
        this.price = price;
        this.end = end;
        this.categories = fromCSV(categories);
        this.description = desc;
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

    private List<String> fromCSV(String entry){
        String[] csv = entry.split(",");
        List<String> list = new ArrayList<>(csv.length);
        for (String c: csv){
            if (!"".equals(c)){
                list.add(c);
            }
        }
        return list;
    }
}
