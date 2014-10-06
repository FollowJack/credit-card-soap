package validator;

import org.junit.Test;

import main.entities.CreditCard;
import main.infrastructure.validator.CardValidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Deniel on 03.10.2014.
 */
public class CardValidatorTest {

    @Test
    public void shouldCheckCreditCardValidity(){

        // Arrange - create validator
        CardValidator cardValidator = new CardValidator();
        // Arrange - create credit card
        CreditCard creditCard = new CreditCard("12341234","10/10",1234,"VISA");

        //Assert - validate credit card
        assertTrue("Credit card should be valid",cardValidator.validate(creditCard));
        creditCard.setNumber("2");
        assertTrue("Credit card should be valid", cardValidator.validate(creditCard));
        creditCard.setNumber("12341233");
        assertFalse("Credit card should not be valid", cardValidator.validate(creditCard));
        creditCard.setNumber("1");
        assertFalse("Credit card should not be valid", cardValidator.validate(creditCard));

    }
}
