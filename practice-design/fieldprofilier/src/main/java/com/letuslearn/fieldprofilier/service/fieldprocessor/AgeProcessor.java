package com.letuslearn.fieldprofilier.service.fieldprocessor;

import com.letuslearn.fieldprofilier.model.OutputEntity;
import com.letuslearn.fieldprofilier.model.Person;

import java.util.ArrayList;
import java.util.List;

public class AgeProcessor {

    public List<OutputEntity> groupByAge(List<Person> personList){
        int total = personList.size();
        int agelessThan16 = 0, ageBetween16_20 = 0, ageBetween20_30 = 0;

        for (Person person : personList){
            int personAge = person.getAge();
            if (personAge < 16){
                agelessThan16++;
            } else if (personAge < 20) {
                ageBetween16_20++;
            } else if (personAge < 30) {
                ageBetween20_30++;
            }
        }

        List<OutputEntity> outputEntities = new ArrayList<OutputEntity>();
        outputEntities.add(new OutputEntity("Age<16",agelessThan16, total));
        outputEntities.add(new OutputEntity("ageBetween16And20", ageBetween16_20, total));
        outputEntities.add(new OutputEntity("ageBetween20And30", ageBetween20_30, total));

        return outputEntities;
    }
}
