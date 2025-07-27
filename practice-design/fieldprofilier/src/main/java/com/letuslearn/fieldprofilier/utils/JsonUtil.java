package com.letuslearn.fieldprofilier.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.letuslearn.fieldprofilier.model.OutputEntity;
import com.letuslearn.fieldprofilier.model.Person;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class JsonUtil {

    private static final Logger logger = Logger.getLogger(JsonUtil.class.getName());
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<Person> getPersonList(String filePath) throws IOException {
        try{
            List<Person> personList = mapper.readValue(new File(filePath),
                    new TypeReference<List<Person>>(){});
            return personList;
        }catch (IOException e){
            e.getLocalizedMessage();
            throw new RuntimeException("failed to read input JSON", e);
        }
    }

    public static void toJson(String outPutFilePath, List<OutputEntity> listOutputEntity){
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outPutFilePath), listOutputEntity);
        } catch (IOException e) {
            logger.severe("not able to save file");
            throw new RuntimeException("failed to convert to JSON",e);
        }
    }


}
