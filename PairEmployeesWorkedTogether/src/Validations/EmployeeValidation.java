package Validations;

import model.employeeProject;

import java.time.LocalDate;

public class EmployeeValidation {
    public static boolean validateRecords(employeeProject employeeProject, employeeProject record) {
        return record.getEmpId() == employeeProject.getEmpId() && record.getProjectId() == employeeProject.getProjectId()
                && record.getEndDate().equals(employeeProject.getEndDate()) && record.getStartDate().equals(employeeProject.getStartDate());
    }
    public static LocalDate getDate(LocalDate firstEmployeeDate, LocalDate secondEmployeeDate) {
        LocalDate date;
        if (firstEmployeeDate.isBefore(secondEmployeeDate)
                || firstEmployeeDate.isEqual(secondEmployeeDate)) {
            date = secondEmployeeDate;
        } else {
            date = firstEmployeeDate;
        }
        return date;
    }
    public static LocalDate getDateTo(LocalDate firstEmployeeDate, LocalDate secondEmployeeDate) {
        LocalDate date;
        if (firstEmployeeDate.isBefore(secondEmployeeDate)
                || firstEmployeeDate.isEqual(secondEmployeeDate)) {
            date = firstEmployeeDate;
        } else {
            date = secondEmployeeDate;
        }
        return date;
    }
    public static boolean isDateOverlaps(employeeProject firstRecordOfEmployee, employeeProject secondRecordOfEmployee) {
        boolean before = firstRecordOfEmployee.getStartDate().isBefore(secondRecordOfEmployee.getEndDate())
                || firstRecordOfEmployee.getStartDate().equals(secondRecordOfEmployee.getEndDate());
        boolean after = firstRecordOfEmployee.getEndDate().isAfter(secondRecordOfEmployee.getStartDate())
                || firstRecordOfEmployee.getEndDate().equals(secondRecordOfEmployee.getStartDate());
        int firstProjectId = firstRecordOfEmployee.getProjectId();
        int secondProjectId = secondRecordOfEmployee.getProjectId();
        return (firstProjectId == secondProjectId) && (before && after);
    }
}
