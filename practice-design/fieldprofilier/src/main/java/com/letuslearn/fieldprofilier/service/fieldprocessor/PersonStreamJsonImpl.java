package com.letuslearn.fieldprofilier.service.fieldprocessor;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.letuslearn.fieldprofilier.model.Person;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonStreamJsonImpl implements IStreamJson{

    @Override
    public List<Person> streamJson(File inputFile) throws IOException {
        // create object mapper for reading json
        ObjectMapper mapper = new ObjectMapper();

        // create json factory for creating JsonParser
        JsonFactory factory = mapper.getFactory();

        List<Person> personList = new ArrayList<Person>();

        // create jsonparser with file
        try(JsonParser parser = factory.createParser(inputFile)){
            if (parser.nextToken() == JsonToken.START_ARRAY){
                while (parser.nextToken() == JsonToken.START_OBJECT){
                    Person person = mapper.readValue(parser, Person.class);
                    personList.add(person);
                    System.out.println(person);
                }
            }
        }
        return personList;
    }
}
