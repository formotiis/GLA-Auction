package persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Article implements Serializable {

    private Person owner;
    private Long id;
    private Double price;
    private Date end;
    List<String> categories;

    public Article(Person owner, Long id, Double price, Date end, String categories) {
        this.owner = owner;
        this.id = id;
        this.price = price;
        this.end = end;
        this.categories = fromCSV(categories);
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
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
