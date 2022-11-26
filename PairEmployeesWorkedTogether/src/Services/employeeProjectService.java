package Services;

import model.employeeProject;
import model.pairOfEmployee;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface employeeProjectService {
    void saveAllEmployeeProjects() throws IOException, ParseException;
    List<employeeProject> getAllEmployeeProjects();
    List<pairOfEmployee> getPairWithMostOverlapDaysOnSameProject();
}
