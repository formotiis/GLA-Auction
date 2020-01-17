package registration;

import persistence.Person;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless (name = "NameHandler")
@LocalBean
public class NameHandlerBean implements NameHandler {
    public NameHandlerBean() {
    }

    @Override
    public String greetingsMessage(Person person) {
        return "Hello " + person.getFirstName() +' '+ person.getLastName()
                + " (" + person.getUserName() + ") !";
    }


}
