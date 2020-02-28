package navigation;

import persistence.Person;
import persistence.PersonManager;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.rmi.RemoteException;

@Named("activeUser")
@SessionScoped
public class ActiveUserBean implements ActiveUser {

    public ActiveUserBean() {
        this.user = null;
    }

    @Inject
    PersonManager personManager;


    private Person user;
    private String usn;
    private String pwd;

    @Override
    public Person getActiveUser() {
        return user;
    }

    @Override
    public boolean login() {
        this.user = personManager.getUserByCreditentials(usn,pwd);
        return user!=null;
    }



    @Override
    public String logout() {
        user = null;
        return "index";
    }

    @Override
    public String postLoginRedirect() {
        if (login())
            return "faces/home.xhtml";
        else
            return "faces/index.xhtml";
    }

    @Override
    public boolean isLogged() {
        return user!=null;
    }

    @Override
    public String homeRedirect() {
        if (isLogged())
            return "home";
        else
            return "index";
    }

    @Override
    public void setSessionContext(SessionContext sessionContext) throws EJBException, RemoteException {

    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException {

    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException {

    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {

    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void reloadUser(){
        if (user!=null){
            this.user = personManager.getUserByID(user.getId());
        }
    }
}
