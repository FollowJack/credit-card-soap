package integration;

import main.entities.CreditCard;
import main.infrastructure.validator.CardValidator;
import main.infrastructure.validator.Validator;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by Deniel on 03.10.2014.
 */
public class CardValidatorIntegrationTest {

    @Test
    public void shouldCheckCreditCardValidity() throws MalformedURLException{

        // Arrange - publish the SOAP web service
        Endpoint endpoint = Endpoint.publish("http://localhost:8080/cardValidator",new CardValidator());

        assertTrue(endpoint.isPublished());
        assertEquals("http://schemas.xmlsoap.org/wsdl/soap/http",endpoint.getBinding().getBindingID());

        // Arrange - Needed properties to access the web service
        URL wsdlDocumentLocation = new URL("http://localhost:8080/cardValidator?wsdl");

        String namespaceURI = "http://validator.infrastructure.main/";
        String servicePart = "CardValidatorService";
        String portName = "CardValidatorPort";

        QName serviceQN = new QName(namespaceURI, servicePart);
        QName portQN = new QName(namespaceURI, portName);

        // Arrange - Creates a service instance
        Service service = Service.create(wsdlDocumentLocation, serviceQN);
        Validator cardValidator = service.getPort(portQN, Validator.class);

        // Act - Invokes the web service
        CreditCard creditCard = new CreditCard("12341234", "10/10", 1234, "VISA");

        // Assert - card is valid
        assertTrue("Credit card should be valid", cardValidator.validate(creditCard));

        // Assert - card is not valid
        creditCard.setNumber("12341233");
        assertFalse("Credit card should not be valid", cardValidator.validate(creditCard));

        // Act - Unpublishes the SOAP Web Service
        endpoint.stop();
        assertFalse(endpoint.isPublished());
    }
}
