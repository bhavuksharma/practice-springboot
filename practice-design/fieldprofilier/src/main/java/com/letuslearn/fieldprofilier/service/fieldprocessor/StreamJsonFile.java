package com.letuslearn.fieldprofilier.service.fieldprocessor;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.letuslearn.fieldprofilier.model.Person;

import java.io.File;
import java.io.IOException;

public class StreamJsonFile {

    public static void main(String[] args) throws IOException {

        File inputFile = new File("src/main/resources/input.json");
        System.out.println(inputFile.exists());

        // create object mapper
        ObjectMapper mapper = new ObjectMapper();

        // create Json factory
        JsonFactory factory = mapper.getFactory();

        // create Json parser
        try(JsonParser parser = factory.createParser(inputFile)){
            if(parser.nextToken() == JsonToken.START_ARRAY){ // start of an Array
                while(parser.nextToken() == JsonToken.START_OBJECT){
                    Person person = mapper.readValue(parser, Person.class);
                    System.out.println(person);
                }
            }
        }

    }
}
