package Repositories;

import model.employeeProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class employeeProjectRepositoryImpl implements employeeProjectRepository{
    private List<employeeProject> employeeProjects;

    public employeeProjectRepositoryImpl() {
        this.employeeProjects = new ArrayList<>();
    }

    @Override
    public void saveAllEmployeeProjects(List<employeeProject> employeeProjects) {
        this.employeeProjects = employeeProjects;
    }

    @Override
    public List<employeeProject> getAllEmployeeProject() {
        return Collections.unmodifiableList(employeeProjects);
    }
}
