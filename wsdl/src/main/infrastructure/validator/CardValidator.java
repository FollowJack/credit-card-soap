package main.infrastructure.validator;

import main.entities.CreditCard;

import javax.jws.WebService;

/**
 * Created by Deniel on 03.10.2014.
 */
@WebService(endpointInterface = "main.infrastructure.validator.Validator")
public class CardValidator implements Validator{

    @Override
    public boolean validate(CreditCard creditCard) {

        // Arrange - get last digit
        Character lastDigit = creditCard.getNumber().charAt(creditCard.getNumber().length()-1);

        // Assert - check if last digit is even
        if(Integer.parseInt(lastDigit.toString()) % 2 == 0)
            return true;
        else
            return false;
    }
}
