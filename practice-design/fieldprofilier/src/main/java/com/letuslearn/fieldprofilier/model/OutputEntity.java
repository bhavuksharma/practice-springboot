package com.letuslearn.fieldprofilier.model;

public class OutputEntity {
    private String groupName;
    private int valueCount;
    private int totalCount;

    public OutputEntity(String theGroupName, int theValueCount, int theTotalCount){
        this.groupName = theGroupName;
        this.valueCount = theValueCount;
        this.totalCount = theTotalCount;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getValueCount() {
        return valueCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setValueCount(int valueCount) {
        this.valueCount = valueCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "OutputGroup{" +
                "groupName='" + groupName + '\'' +
                ", valueCount=" + valueCount +
                ", totalCount=" + totalCount +
                '}';
    }
}
