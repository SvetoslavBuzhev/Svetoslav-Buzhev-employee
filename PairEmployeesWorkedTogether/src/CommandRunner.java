import Services.Impl.employeeProjectServiceImpl;
import model.pairOfEmployee;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CommandRunner implements Runnable {
    private final employeeProjectServiceImpl employeeProjectService;

    public CommandRunner(employeeProjectServiceImpl employeeProjectService) {
        this.employeeProjectService = employeeProjectService;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            employeeProjectService.saveAllEmployeeProjects();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        List<pairOfEmployee> pairWithMostOverlapDaysOnSameProject = employeeProjectService
                .getPairWithMostOverlapDaysOnSameProject().stream()
                .sorted((f1, f2) -> Long.compare(f2.getDayWorkedTogether(), f1.getDayWorkedTogether()))
                .toList();
        System.out.printf("Choose:%n1.All teams%n2.Best team%nEnter your choice: ");
        try {
            printing(scanner, pairWithMostOverlapDaysOnSameProject);
        } catch (Exception e) {
            System.out.println("Type a number not string");
        }
    }

    private static void printing(Scanner scanner, List<pairOfEmployee> pairWithMostOverlapDaysOnSameProject) {
        int input = Integer.parseInt(scanner.nextLine());
        String choose = "Default";
        if (input==1){choose = "All teams";}
        else if(input==2){choose = "Best team";}
        switch (choose) {
            case "All teams":
                System.out.println(pairWithMostOverlapDaysOnSameProject
                        .stream()
                        .map(a -> String.format(a + "%n"))
                        .collect(Collectors.joining()).trim());
                break;
            case "Best team":
                if (pairWithMostOverlapDaysOnSameProject.size() > 0) {
                    pairOfEmployee bestTeam = pairWithMostOverlapDaysOnSameProject.get(0);
                    System.out.println(bestTeam);
                } else {
                    System.out.println("There aren't any pair of employees working on same project at same period.");
                }
                break;
            default:
                System.out.println("Invalid command");
        }
    }
}

