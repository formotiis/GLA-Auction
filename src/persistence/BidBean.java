package persistence;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean(name="BidBean")
@SessionScoped
public class BidBean implements Serializable {

    @Inject
    BidManager bidManager;

}
