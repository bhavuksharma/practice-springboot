package com.example.demo.component;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class UpperCaseProcessor {

	public void toUpperCase(Exchange exchange) {
		String input = exchange.getIn().getBody(String.class);
		exchange.getIn().setBody(input.toUpperCase());
	}
}
