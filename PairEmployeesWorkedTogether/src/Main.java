import Repositories.employeeProjectRepository;
import Repositories.employeeProjectRepositoryImpl;
import Services.Impl.employeeProjectServiceImpl;

public class Main {
    public static void main(String[] args) {
        employeeProjectRepository employeeProjectRepository = new employeeProjectRepositoryImpl();
        employeeProjectServiceImpl employeeProjectService = new employeeProjectServiceImpl(employeeProjectRepository);

        CommandRunner commandRunner = new CommandRunner(employeeProjectService);
        commandRunner.run();
    }
}