package persistence;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

@ManagedBean(name="personBean")
@SessionScoped
public class PersonBean implements Serializable {

    @Inject
    PersonManager personManager;

    public PersonBean() {

    }

    public Person getUserByID(Long id){
        return personManager.getUserByID(id);
    }
    /*public Person[] getAll(){
        return personManager.allPersons();
    }

    public List<Person> allPersons() {
        return personManager.getAll();
    }

    public Person getRandom(){
        List<Person> a=allPersons();
        return a.get(new Random().nextInt(a.size()));
    }*/
}
