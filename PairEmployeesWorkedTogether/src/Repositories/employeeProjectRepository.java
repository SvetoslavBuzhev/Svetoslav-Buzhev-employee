package Repositories;

import model.employeeProject;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface employeeProjectRepository {
    void saveAllEmployeeProjects(List<employeeProject> employeeProjects) throws IOException, ParseException;
    List<employeeProject> getAllEmployeeProject();
}
