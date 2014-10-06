package client.consumer;

import main.infrastructure.validator.CardValidatorService;
import main.infrastructure.validator.CreditCard;
import main.infrastructure.validator.Validator;

import javax.xml.ws.WebServiceRef;

/**
 * Created by Deniel on 06.10.2014.
 */
public class WebServiceConsumer  {

    @WebServiceRef
    public static CardValidatorService cardValidatorService;

    public static void main(String[] args){
        CreditCard creditCard = new CreditCard();
        creditCard.setNumber("1234134");
        creditCard.setExpiryDate("10/12");
        creditCard.setType("VISA");
        creditCard.setControlNumber(1234);

        Validator cardValidator = cardValidatorService.getCardValidatorPort();

        System.out.println(cardValidator.validate(creditCard));
    }

}
