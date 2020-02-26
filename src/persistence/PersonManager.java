package persistence;

import javax.ejb.Local;
import java.io.Serializable;
import java.util.List;

@Local
public interface PersonManager extends Serializable {
    String addPerson(Person person, String password);
    Person getUserByID(Long id);
    Person getUserByCreditentials(String user,String password);
    String incrementCanceledBid(Long id);
}
