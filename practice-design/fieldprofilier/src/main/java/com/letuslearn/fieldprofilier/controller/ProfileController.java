package com.letuslearn.fieldprofilier.controller;

import com.letuslearn.fieldprofilier.model.OutputEntity;
import com.letuslearn.fieldprofilier.model.Person;
import com.letuslearn.fieldprofilier.config.email.EmailService;
import com.letuslearn.fieldprofilier.service.fieldprocessor.AgeProcessor;
import com.letuslearn.fieldprofilier.service.fieldprocessor.SalaryProcessor;
import com.letuslearn.fieldprofilier.utils.JsonUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api")
public class ProfileController {
    private final Logger logger = Logger.getLogger(ProfileController.class.getName());
    private EmailService emailService;

    @Value("${report.mail.to}")
    private String emailTo;

    AgeProcessor ageProcessor = new AgeProcessor();
    SalaryProcessor salaryProcessor = new SalaryProcessor();


    public ProfileController(EmailService theEmailService){
        this.emailService = theEmailService;
    }

    @GetMapping("/profile")
    public void personRecordProcessor() throws IOException, MessagingException {
        List<OutputEntity> outputEntities = new ArrayList<OutputEntity>();
        String fileInputJson = "src/main/resources/input.json";
        String fileOutputJson = "src/main/resources/output_"+ System.currentTimeMillis() +".json";
        List<Person> personList = JsonUtil.getPersonList(fileInputJson);
        outputEntities.addAll(ageProcessor.groupByAge(personList));
        outputEntities.add(salaryProcessor.processSalary(personList));
        JsonUtil.toJson(fileOutputJson,outputEntities);

        // email service to send JSON to receiver
        emailService.sendReport(
                emailTo,
                "Output Field Profilier Json",
                "Find Attached JSON for the reference",
                new File(fileOutputJson)
        );
        logger.info("file written to the Output Json successfully on path: "+ fileOutputJson);

    }
}
