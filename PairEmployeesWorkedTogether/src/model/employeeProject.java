package model;

import java.time.LocalDate;
import java.util.Objects;

public class employeeProject {
    private int empId;
    private int projectId;
    private LocalDate startDate;
    private LocalDate endDate;

    public employeeProject() {
    }

    public employeeProject(int empId, int projectId, LocalDate startDate, LocalDate endDate) {
        setEmpId(empId);
        setProjectId(projectId);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        employeeProject that = (employeeProject) o;
        return empId == that.empId && projectId == that.projectId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, projectId);
    }
}
