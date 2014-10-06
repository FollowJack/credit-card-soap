package main.infrastructure.validator;

import javax.jws.WebService;
import main.entities.CreditCard;

/**
 * Created by Deniel on 03.10.2014.
 */
@WebService
public interface Validator {

    public boolean validate(CreditCard creditCard);
}
