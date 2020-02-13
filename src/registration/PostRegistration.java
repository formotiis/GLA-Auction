package registration;

import javax.ejb.Local;

@Local
public interface PostRegistration {
    public String welcome();
}
