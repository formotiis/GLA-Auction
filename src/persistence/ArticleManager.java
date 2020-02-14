package persistence;

import javax.ejb.Local;
import java.io.Serializable;

@Local
public interface ArticleManager extends Serializable {
    public String addArticle(Article a);
    public Article getArticleById(int id);
    public Article getArticleByName(String name);

}
