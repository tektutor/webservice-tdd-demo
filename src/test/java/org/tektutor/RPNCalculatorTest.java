package org.tektutor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RPNCalculatorTest {
    	private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

    	@LocalServerPort
    	private int port = 8080;

    	@Before
    	public void init() throws Exception {
        	marshaller.setPackagesToScan(ClassUtils.getPackageName(RPNCalculatorRequest.class));
        	marshaller.afterPropertiesSet();
    	}

	@Test
	public void testSimpleAddition( ) {

        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
       	RPNCalculatorRequest request = new RPNCalculatorRequest();
        request.setRpnExpression("10 15 +");

        RPNCalculatorResponse response 
            = (RPNCalculatorResponse) ws.marshalSendAndReceive("http://localhost:" + port + "/ws", request);

		assertEquals ( 25.0, response.getResult(), 0.001 );
        
	}
}