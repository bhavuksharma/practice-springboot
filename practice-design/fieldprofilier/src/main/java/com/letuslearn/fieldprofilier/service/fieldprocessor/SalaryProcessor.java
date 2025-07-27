package com.letuslearn.fieldprofilier.service.fieldprocessor;

import com.letuslearn.fieldprofilier.model.OutputEntity;
import com.letuslearn.fieldprofilier.model.Person;

import java.util.List;

public class SalaryProcessor {

    public OutputEntity processSalary(List<Person> personList){
        int total = personList.size();
        long max = personList.stream().mapToLong(e -> e.getSalary()).max().orElse(0);
        long count = personList.stream().filter(person -> person.getSalary() == max).count();
        return new OutputEntity("maxSalary",(int)count, total);
    }
}
