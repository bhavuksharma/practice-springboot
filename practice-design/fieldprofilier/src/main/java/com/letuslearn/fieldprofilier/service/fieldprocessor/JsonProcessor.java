package com.letuslearn.fieldprofilier.service.fieldprocessor;

import com.letuslearn.fieldprofilier.config.email.EmailService;
import com.letuslearn.fieldprofilier.model.OutputEntity;
import com.letuslearn.fieldprofilier.model.Person;
import com.letuslearn.fieldprofilier.utils.JsonUtil;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonProcessor {
    private final Logger logger = LoggerFactory.getLogger(JsonProcessor.class);

    private AgeProcessor ageProcessor;
    private SalaryProcessor salaryProcessor;
    private EmailService emailService;

    @Value("${email.report.to}")
    private String emailTo;

    @Value("${email.enabled}")
    private boolean enabled;

    public JsonProcessor(EmailService theEmailService){
        ageProcessor = new AgeProcessor();
        salaryProcessor = new SalaryProcessor();
        this.emailService = theEmailService;
    }

    public List<OutputEntity> process() throws IOException, MessagingException {

        List<OutputEntity> outputEntities = new ArrayList<OutputEntity>();
        String fileInputJson = "src/main/resources/input.json";
        String fileOutputJson = "src/main/resources/output_"+ System.currentTimeMillis() +".json";
        List<Person> personList =  JsonUtil.getPersonList(fileInputJson);
        outputEntities.addAll(ageProcessor.groupByAge(personList));
        outputEntities.add(salaryProcessor.processSalary(personList));
        JsonUtil.toJson(fileOutputJson,outputEntities);

        // email service to send JSON to receiver
        if(enabled) {
            emailService.sendReport(
                    emailTo,
                    "Output Field Profilier Json",
                    "Find Attached JSON for the reference",
                    new File(fileOutputJson)
            );
        }

        logger.info("json parsed successfully and output file at location "+ fileOutputJson);
        return outputEntities;
    }
}
