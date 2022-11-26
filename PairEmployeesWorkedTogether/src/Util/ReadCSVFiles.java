package Util;

import model.employeeProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static Validations.EmployeeValidation.validateRecords;

public class ReadCSVFiles {
    public static List<employeeProject> readEmployeeProjectCSV(Path of) throws IOException, ParseException {
        List<employeeProject> employeeProjectList = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(of)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(", ");
                employeeProject employeeProject = createEmployeeProject(attributes);
                boolean isExisting = false;
                for (employeeProject record : employeeProjectList) {
                    if (validateRecords(employeeProject, record)) {
                        isExisting = true;
                        break;
                    }
                }
                if (!isExisting) {
                    employeeProjectList.add(employeeProject);
                }
                line = br.readLine();
            }
        }
        return employeeProjectList;
    }

    private static employeeProject createEmployeeProject(String[] attributes) throws ParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate endDate;
        int employeeId = Integer.parseInt(attributes[0]);
        int projectId = Integer.parseInt(attributes[1]);
        LocalDate startDate = parseDate(attributes[2], format);
        if (!attributes[3].equals("NULL")) {
            endDate = parseDate(attributes[3], format);
        } else {
            LocalDate now = LocalDate.now();
            endDate = LocalDate.parse(format.format(now));
        }
        return new employeeProject(employeeId, projectId, startDate, endDate);
    }

    private static LocalDate parseDate(String attribute, DateTimeFormatter format) throws ParseException {
        return LocalDate.parse(attribute, format);
    }
}
