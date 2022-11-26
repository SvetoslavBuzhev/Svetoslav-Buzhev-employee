package Services.Impl;

import Repositories.employeeProjectRepository;
import Services.employeeProjectService;
import model.employeeProject;
import model.pairOfEmployee;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Constants.Paths.PATH_TO_CSV_FILE;
import static Util.ReadCSVFiles.readEmployeeProjectCSV;
import static Validations.EmployeeValidation.*;
import static java.time.temporal.ChronoUnit.DAYS;

public class employeeProjectServiceImpl implements employeeProjectService {
    final employeeProjectRepository employeeProjectsRecords;

    public employeeProjectServiceImpl(employeeProjectRepository employeeProjectsRecords) {
        this.employeeProjectsRecords = employeeProjectsRecords;
    }


    @Override
    public void saveAllEmployeeProjects() throws IOException, ParseException {
        this.employeeProjectsRecords.saveAllEmployeeProjects(readEmployeeProjectCSV(PATH_TO_CSV_FILE));
    }

    @Override
    public List<employeeProject> getAllEmployeeProjects() {
        return Collections.unmodifiableList(employeeProjectsRecords.getAllEmployeeProject());
    }

    @Override
    public List<pairOfEmployee> getPairWithMostOverlapDaysOnSameProject() {
        List<employeeProject> employeeProjects = employeeProjectsRecords.getAllEmployeeProject();
        List<pairOfEmployee> pairOfEmployees = new ArrayList<>();
        for (int i = 0; i < employeeProjects.size(); i++) {
            for (int k = i + 1; k < employeeProjects.size(); k++) {
                employeeProject firstRecordOfEmployee = employeeProjects.get(i);
                employeeProject secondRecordOfEmployee = employeeProjects.get(k);
                insertRecords(firstRecordOfEmployee, secondRecordOfEmployee, pairOfEmployees);
            }
        }
        return pairOfEmployees;
    }
    private void insertRecords(employeeProject firstRecordOfEmployee, employeeProject secondRecordOfEmployee
            , List<pairOfEmployee> pairOfEmployees) {
        if (isDateOverlaps(firstRecordOfEmployee, secondRecordOfEmployee)
                && (firstRecordOfEmployee.getEmpId() != secondRecordOfEmployee.getEmpId())) {
            long days = calculateOverlappingDays(firstRecordOfEmployee, secondRecordOfEmployee);
            pairOfEmployee pairOfEmployee = new pairOfEmployee(firstRecordOfEmployee, secondRecordOfEmployee);
            if (pairOfEmployees.size() > 0) {
                notEmptyPairsList(days, pairOfEmployee, pairOfEmployees);
            } else {
                pairOfEmployee.increaseDaysWorkedTogether(days);
                pairOfEmployees.add(pairOfEmployee);
            }
        }
    }
    private long calculateOverlappingDays(employeeProject firstRecordOfEmployee, employeeProject secondRecordOfEmployee) {
        LocalDate startDate = getDate(firstRecordOfEmployee.getStartDate(), secondRecordOfEmployee.getStartDate());
        LocalDate endDate = getDateTo(firstRecordOfEmployee.getEndDate(), secondRecordOfEmployee.getEndDate());
        return DAYS.between(startDate, endDate);
    }
    private void notEmptyPairsList(long days, pairOfEmployee pairOfEmployee, List<pairOfEmployee> pairOfEmployees) {
        boolean isExisting = false;
        pairOfEmployee pair = null;
        for (pairOfEmployee pOE : pairOfEmployees) {
            if (pOE.equals(pairOfEmployee)) {
                pair = pOE;
                isExisting = true;
                break;
            }
        }
        if (isExisting) {
            pair.increaseDaysWorkedTogether(days);
        } else {
            pairOfEmployee.increaseDaysWorkedTogether(days);
            pairOfEmployees.add(pairOfEmployee);
        }
    }
}
