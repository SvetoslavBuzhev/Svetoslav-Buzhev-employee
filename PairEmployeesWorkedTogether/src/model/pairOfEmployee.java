package model;

import java.util.Objects;

public class pairOfEmployee {
    private employeeProject firstEmployeeRecord;
    private employeeProject secondEmployeeRecord;
    private long dayWorkedTogether;

    public pairOfEmployee(employeeProject firstEmployeeRecord, employeeProject secondEmployeeRecord) {
        this.firstEmployeeRecord = firstEmployeeRecord;
        this.secondEmployeeRecord = secondEmployeeRecord;
    }

    public long getDayWorkedTogether() {
        return dayWorkedTogether;
    }
    public void increaseDaysWorkedTogether(long days){
        this.dayWorkedTogether+=days;
    }
    public employeeProject getFirstEmployeeRecord() {
        return firstEmployeeRecord;
    }

    public void setFirstEmployeeRecord(employeeProject firstEmployeeRecord) {
        this.firstEmployeeRecord = firstEmployeeRecord;
    }

    public employeeProject getSecondEmployeeRecord() {
        return secondEmployeeRecord;
    }

    public void setSecondEmployeeRecord(employeeProject secondEmployeeRecord) {
        this.secondEmployeeRecord = secondEmployeeRecord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        pairOfEmployee that = (pairOfEmployee) o;
        return (Objects.equals(firstEmployeeRecord, that.firstEmployeeRecord) && Objects.equals(secondEmployeeRecord, that.secondEmployeeRecord))
                ||(Objects.equals(firstEmployeeRecord,that.secondEmployeeRecord)&&Objects.equals(secondEmployeeRecord,that.firstEmployeeRecord));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstEmployeeRecord, secondEmployeeRecord);
    }

    @Override
    public String toString() {
        return "Pair of Employees Record { " +
                "firstEmployeeId - " + firstEmployeeRecord.getEmpId() +
                ", secondEmployeeId - " + secondEmployeeRecord.getEmpId() +
                ", projectId - " + firstEmployeeRecord.getProjectId() +
                ", dayWorkedTogether - " + dayWorkedTogether +
                " }";
    }
}
