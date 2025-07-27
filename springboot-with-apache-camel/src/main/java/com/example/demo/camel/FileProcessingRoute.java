package com.example.demo.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileProcessingRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {

		from("file://input?noop=true")  // read file from the folder 'input'
		  .log("Processing file: ${file:name}") // log the file name
		  .process(
				  exchange -> {  // custom processor
					  String originalContent = exchange.getIn().getBody(String.class);
					  String upperCaseContent = originalContent.toUpperCase();
					  
					  exchange.getIn().setBody(upperCaseContent);
				  })
		  .to("file://output") // write files to the 'output'
		  .log("file processed and moved to 'output' folder.");
		
	}

}
