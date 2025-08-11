package com.letuslearn.fieldprofilier.controller;

import com.letuslearn.fieldprofilier.model.OutputEntity;
import com.letuslearn.fieldprofilier.model.Person;
import com.letuslearn.fieldprofilier.service.fieldprocessor.IStreamJson;
import com.letuslearn.fieldprofilier.service.fieldprocessor.JsonProcessor;
import com.letuslearn.fieldprofilier.service.fieldprocessor.PersonStreamJsonImpl;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api")
public class ProfileController {
    private final Logger logger = Logger.getLogger(ProfileController.class.getName());

    private JsonProcessor jsonProcessor;
    private IStreamJson streamJson;

    public ProfileController(JsonProcessor theJsonProcessor, PersonStreamJsonImpl personStreamJson){
        this.jsonProcessor = theJsonProcessor;
        this.streamJson = personStreamJson;
    }

    @GetMapping("/profile")
    public List<OutputEntity>  personRecordProcessor() throws IOException, MessagingException {
        List<OutputEntity> outputEntities = jsonProcessor.process();
        return outputEntities;
    }

    @GetMapping("/stream")
    public List<Person> streamPersonJson() throws IOException {
        File file = new File("src/main/resources/input.json");
        return streamJson.streamJson(file);
    }
}
