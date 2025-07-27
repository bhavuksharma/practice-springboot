package com.example.demo.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestApiRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		// configure REST API
		restConfiguration() 
		        .component("servlet")// REST component
		        .contextPath("/app")
		        .dataFormatProperty("prettyPrint", "true");
		
		// Define REST endpoints 
		rest("/api")
		   	.get("/hello")    // GET /api/hello
		   	.to("direct:hello")
		   	
		   	// POST /api/uppercase
		   	.post("/uppercase")
		   	.consumes("application/json")
		   	.produces("application/json")
		   	.to("direct:uppercase")
		   	
		   	// GET /api/external
		   	.get("/external")
		   	.to("direct:external");
		
		
		// Route for GET /hello
		from("direct:hello")
			.setBody(constant("Hello from Apache Camel!"));
		
		// Route for POST /uppercase
		from("direct:uppercase")
			.process(exchange -> {
					String input = exchange.getIn().getBody(String.class);
					exchange.getIn().setBody(input.toUpperCase());
			});
		
		// Route for GET /external 
		from("direct:external")
			.toD("https://jsonplaceholder.typicode.com/posts/1?bridgeEndpoint=true") // External API
			.log("Response headers: ${headers}")
			.convertBodyTo(String.class);
	}

}
