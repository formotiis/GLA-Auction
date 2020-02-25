package persistence;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless(name = "BidManager")
@LocalBean
public class BidManagerBean implements BidManager {
}
