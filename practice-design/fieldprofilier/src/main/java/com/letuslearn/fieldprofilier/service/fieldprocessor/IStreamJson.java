package com.letuslearn.fieldprofilier.service.fieldprocessor;

import com.letuslearn.fieldprofilier.model.Person;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IStreamJson {
    List<Person> streamJson(File inputFile) throws IOException;
}
