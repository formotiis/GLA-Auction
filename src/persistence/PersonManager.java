package persistence;

import javax.ejb.Local;
import java.io.Serializable;
import java.util.List;

@Local
public interface PersonManager extends Serializable {
    public String addPerson(Person person, String password);
    public Person getUserByID(int id);
    public Person getUserByCreditentials(String user,String password);
}
