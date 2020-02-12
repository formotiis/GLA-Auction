package navigation;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "router")
@RequestScoped
public class RouterBean implements Router {

    @Override
    public String navigateByUrl(String input) {
        return "faces/"+input;
    }
}
