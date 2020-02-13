package registration;

import persistence.Person;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named(value = "postR")
@RequestScoped
public class PostRegistrationBean implements PostRegistration {
    @Inject
    RegistrationBean rb;

    @Override
    public String welcome() {
        return "Registration complete, Welcome "+ rb.getUsername();
    }


}
