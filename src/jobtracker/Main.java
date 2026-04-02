package jobtracker;

import jobtracker.model.JobApplication;
import jobtracker.model.Status;
import jobtracker.repository.CsvRepository;
import jobtracker.repository.JobRepository;
import jobtracker.repository.JsonRepository;
import jobtracker.service.JobService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        JobRepository csvRepository = new CsvRepository();
        JobRepository jsonRepository = new JsonRepository();

        List<JobApplication> jobs = csvRepository.loadFromFile();

        JobService jobService = new JobService(jobs);

        userInput(scanner, jobService);

        csvRepository.saveToFile(jobService.getAll());
        jsonRepository.saveToFile(jobService.getAll());

        System.out.println("Goodbye !");
        scanner.close();
    }

    public static void userInput(Scanner scanner, JobService js){
        boolean end = true;
        int userChoice = -1;
        System.out.println("Welcome to the job application thingy app");
        System.out.println("What do you want to do ?");
        while (end){
            System.out.println("---- Job Tracker ----");
            System.out.println("0 - exit");
            System.out.println("1 - show all jobs");
            System.out.println("2 - add");
            System.out.println("3 - update");
            System.out.println("4 - delete");
            System.out.println("5 - sort applications");
            System.out.println("Choice :");
            try{
                userChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                System.err.println("Invalid input, please enter a number");
            }
            switch (userChoice) {
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
                case 5:
                    js.sortApplications();
                    System.out.println("Applications sorted by date -> status -> company name-> position");
                    showAllJobs(js);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public static void showAllJobs(JobService js){
        List<JobApplication> lsJA = js.getAll();
        if (lsJA.isEmpty()) {
            System.out.println("List is empty, no job applications");
            return;
        }
        int size = lsJA.size();
        for (int i=0; i<size;i++){
            System.out.println(i+" - "+lsJA.get(i).detailToString());
        }
    }

    public static void createJob(Scanner application, JobService jobService){
        String company = askNonEmptyString(application, "Name of the company : ");
        String position = askNonEmptyString(application, "Position in the company : "+ company);
        LocalDate date = askDate(application);
        String notes = askNonEmptyString(application, "Notes : ");

        JobApplication jobApplication = new JobApplication(company, position, Status.APPLIED, date, notes);
        System.out.println(jobApplication);
        jobService.add(jobApplication);
    }


    public static void updateStatus(Scanner scanner, JobService jobService){
        int numJob = -1;
        int numStatus = -1;
        Status status;
        List<JobApplication> jobApplications = jobService.getAll();

        while (true) {
            System.out.println("Which job application would you like change");
            for (int i=0 ;i<jobApplications.size();i++){
                System.out.println(i+" - "+ jobApplications.get(i));
            }
            try {
                numJob = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e) {
                System.err.println("Invalid input, please enter a number");
            }

            if (isIndexValid(jobApplications, numJob)){
                break;
            } else {
                System.out.println("Index is out of bound");
            }
        }

        while (true) {
            System.out.println("What's the new status ?");
            System.out.println("0 - Interview");
            System.out.println("1 - Rejected");
            System.out.println("2 - Offer");
            System.out.println("3 - Keep the same status : "+jobApplications.get(numJob).getStatus());
            try {
                numStatus = Integer.parseInt((scanner.nextLine()));
            }catch (NumberFormatException e){
                System.err.println("Invalid input, please enter a number");
            }
            if (numStatus > 3 || numStatus < 0) {
                System.out.println("index out of bound please enter a number between 0 and 3");
            }else {
                break;
            }

        }
        status = switch (numStatus) {
            case 0 -> Status.INTERVIEW;
            case 1 -> Status.REJECTED;
            case 2 -> Status.OFFER;
            case 3 -> jobApplications.get(numJob).getStatus();
            default -> Status.APPLIED;
        };
        jobService.updateStatus(numJob, status);
        System.out.println("Updated job: " + jobApplications.get(numJob).detailToString());
    }

    public static void deleteJob(Scanner scanner, JobService js) {
        List<JobApplication> jobApplications = js.getAll();
        if (jobApplications.isEmpty()) {
            System.out.println("No job applications to delete");
            return;
        }

        for (int i = 0; i < jobApplications.size(); i++) {
            System.out.println(i + " - " + jobApplications.get(i).detailToString());
        }

        int num = askInt(scanner, "Select job to delete", 0, jobApplications.size() - 1);
        if (num == -1) {
            System.out.println("Delete cancelled");
            return;
        }

        js.delete(num);
        System.out.println("Job deleted successfully");
    }

    public static String askNonEmptyString(Scanner scanner, String message){
        String string;
        do {
            System.out.println(message);
            string = scanner.nextLine();
            if (string.isBlank()) {
                System.out.println("Statement is blank");
            }
        } while (string.isBlank());
        return string;
    }

    public static LocalDate askDate(Scanner scanner) {
        while (true) {
            System.out.println("Day of application (YYYY-MM-DD) press enter if today");
            String date = scanner.nextLine();

            if (date.isBlank()) {
                return LocalDate.now();
            }

            try {
                return LocalDate.parse(date);
            } catch (Exception e) {
                System.out.println("Invalid format please use YYYY-MM-DD");
            }
        }
    }

    public static int askInt(Scanner scanner, String message, int min, int max) {
        while (true) {
            System.out.println(message + " (between " + min + " and " + max + ", press Enter if none)");
            String input = scanner.nextLine();

            if (input.isBlank()) {
                return -1;
            }

            try {
                int i = Integer.parseInt(input);
                if (i < min || i > max) {
                    System.out.println("Invalid number. Please select a number between " + min + " and " + max);
                } else {
                    return i;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number");
            }
        }
    }

    public static boolean isIndexValid(List<JobApplication> applications,int index) {
        if (applications.isEmpty()) {
            return false;
        } else if (index >= applications.size() || index < 0) {
            return false;
        } else {return true;}
    }
}