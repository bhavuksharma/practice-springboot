package com.example.demo.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.component.UpperCaseProcessor;

@Component
public class CamelRestExample extends RouteBuilder {
	
	private UpperCaseProcessor upperCaseProcessor;
	
	@Autowired
	public CamelRestExample(UpperCaseProcessor upperCaseProcessor) {
		this.upperCaseProcessor = upperCaseProcessor;
	}

	@Override
	public void configure() throws Exception {

        // Define a REST configuration
        restConfiguration()
                .component("servlet") // Use the servlet component for REST
                .contextPath("/api") // Base path for the API
                .dataFormatProperty("prettyPrint", "true"); // Optional: Pretty print JSON output

        // Define a REST endpoint
        rest("/hello") // Set the base path for this REST endpoint
                .get() // Handle GET requests
                .to("direct:helloRoute"); // Direct the request to a Camel route
        
        // Post /api/upper
        rest("/upper")
        		.post()
        		.consumes("application/json")
        		.produces("application/json")
        		.to("direct:upper");

        // Define the "direct:helloRoute" route
        from("direct:helloRoute")
                .setBody(constant("Hello, World!")); // Set a static response body
        
        // Route for POST /upper
        from("direct:upper")
        		.bean(upperCaseProcessor, "toUpperCase"); // calling bean method with exchange object
        
	}

}
