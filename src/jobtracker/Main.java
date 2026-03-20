package jobtracker;

import jobtracker.model.JobApplication;
import jobtracker.model.Status;
import jobtracker.service.JobService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Scanner scanner = new Scanner(System.in);
        JobService jobService = new JobService();

        userInput(scanner, jobService);
    }

    public static void userInput(Scanner scanner, JobService js){
        boolean end = true;
        int userChoice;
        System.out.println("Welcome to the job application thingy app");
        System.out.println("What do you want to do ?");
        while (end){
            System.out.println("0 - exit");
            System.out.println("1 - show all jobs");
            System.out.println("2 - add");
            System.out.println("3 - update");
            System.out.println("4 - delete");
            userChoice = Integer.parseInt(scanner.nextLine());

            switch (userChoice){
                case 0:
                    end = false;
                    break;
                case 1:
                    showAllJobs(js);
                    break;
                case 2:
                    createJob(scanner, js);
                    break;
                case 3:
                    updateStatus(scanner, js);
                    break;
                case 4:
                    deleteJob(scanner, js);
                    break;
            }
        }
    }

    public static void showAllJobs(JobService js){
        List<JobApplication> lsJA = js.getAll();
        for (int i=0; i<lsJA.size();i++){
            System.out.println(i+" - "+lsJA.get(i).detailToString());
        }
    }

    public static void createJob(Scanner application, JobService jobService){
        String company;
        String position;
        String dateApplied;
        LocalDate dateAppliedParse;

        System.out.println("Company name");
        company = application.nextLine();

        System.out.println("Position");
        position = application.nextLine();

        System.out.println("Day of application (YYYY-MM-DD) press enter if today");
        dateApplied = application.nextLine();
        if (dateApplied.isBlank()){
            dateAppliedParse = LocalDate.now();
        }else {
            dateAppliedParse = LocalDate.parse(dateApplied);
        }

        System.out.println("Notes");
        String notes = application.nextLine();

        JobApplication jobApplication = new JobApplication(company, position, Status.APPLIED, dateAppliedParse, notes);
        System.out.println(jobApplication);
        jobService.add(jobApplication);
    }

    public static void updateStatus(Scanner scanner, JobService jobService){
        int numJob;
        int numStatus;
        Status status = Status.APPLIED;

        System.out.println("Lequel voudriez vous changer ?");
        List<JobApplication> jobApplications = jobService.getAll();
        for (int i=0 ;i<jobApplications.size();i++){
            System.out.println(i+" - "+ jobApplications.get(i));
        }
        numJob = Integer.parseInt(scanner.nextLine());

        System.out.println("What's the new status ?");
        System.out.println("0 - Interview");
        System.out.println("1 - Rejected");
        System.out.println("2 - Offer");
        numStatus = Integer.parseInt((scanner.nextLine()));

        status = switch (numStatus) {
            case 0 -> Status.INTERVIEW;
            case 1 -> Status.REJECTED;
            case 2 -> Status.OFFER;
            default -> status;
        };

        jobService.updateStatus(numJob, status);
    }

    public static void deleteJob(Scanner scanner, JobService js) {
        String numJob;
        int numJobParse;

        System.out.println("What job app to delete ? enter if none");
        showAllJobs(js);
        numJob = scanner.nextLine();
        if (numJob.isBlank()){
            return;
        }
        numJobParse = Integer.parseInt(numJob);
        js.delete(numJobParse);
    }
}