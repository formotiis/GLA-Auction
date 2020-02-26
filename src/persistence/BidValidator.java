package persistence;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;


@FacesValidator("persistence.BidValidator")
public class BidValidator implements Validator {

    @Inject
    ArticleManager articleManager;

    @Inject
    BidManager bidManager;

    public BidValidator(){}

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Double val = (Double) value;
        Long id = Long.parseLong((String)component.getAttributes().get("validatorId"));
        Article a = articleManager.getById(id);

        if(val <= bidManager.getArticleHighestBid(id).getBidValue() || val <= a.getPrice()){
            FacesMessage msg = new FacesMessage("The value of your bid must be greater than the last bid or the first price");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
