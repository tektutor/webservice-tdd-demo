package org.tektutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class RPNCalculatorEndpoint {
	private static final String NAMESPACE_URI = "http://tektutor.org/rpncalculator-web-service";

	private RPNCalculator rpnCalculator;

	@Autowired
	public RPNCalculatorEndpoint( RPNCalculator rpnCalculator ) {
		this.rpnCalculator = rpnCalculator;
	}

	@PayloadRoot( namespace = NAMESPACE_URI, localPart = "RPNCalculatorRequest" )
	@ResponsePayload
	public RPNCalculatorResponse evaluate( @RequestPayload RPNCalculatorRequest request ) {
		RPNCalculatorResponse response = new RPNCalculatorResponse();
		response.setResult ( rpnCalculator.evaluate ( request.getRpnExpression() ) );
	
		return response;
	}

}
