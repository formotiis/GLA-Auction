package persistence;

import javax.ejb.Local;
import java.io.Serializable;
import java.util.List;

@Local
public interface ArticleManager extends Serializable {
    String addNew(Article article);
    Article getById(int id);
    List<Article> getUserListArticles(Long id); // Articles appartenant à un utilisateur
    List<Article> getAllArticles(); // Tous les articles
    List<Article> getAllAvailableArticles(); // Articles encore à la vente

}
