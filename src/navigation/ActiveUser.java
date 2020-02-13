package navigation;

import persistence.Person;

import javax.ejb.SessionBean;
// import javax.faces.bean.SessionScoped;

public interface ActiveUser extends SessionBean {
    Person getActiveUser();
    boolean login();
    void logout();
    String postLoginRedirect();
}
