package registration;

import persistence.Person;

import javax.ejb.Local;

@Local
public interface NameHandler {
    public String greetingsMessage(Person person);
}
