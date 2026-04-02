package jobtracker.repository;

import jobtracker.model.JobApplication;
import jobtracker.model.Status;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvRepository implements JobRepository{
    private static final String FILE_PATH = "data/jobs.csv";

    @Override
    public void saveToFile(List<JobApplication> jobApplications) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bw.write("COMPANY;POSITION;STATUS;DATE_APPLIED;NOTES");
            bw.newLine();
            for (JobApplication job : jobApplications) {
                bw.write(job.getCompany()+";"+job.getPosition()+";"+job.getStatus()+";"+job.getDateApplied()+";"+job.getNotes());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing file");
        }

    }

    @Override
    public List<JobApplication> loadFromFile() {
        String company;
        String position;
        Status status;
        LocalDate date;
        String notes;
        List<JobApplication> jobs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            br.readLine(); // Skip first line since its a header

            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] parts = line.split(";");
                if (parts.length < 5) {
                    System.err.println("Invalid line in file :" + line);
                    continue;
                }

                company = parts[0];
                position = parts[1];
                status = Status.valueOf(parts[2]);
                date = LocalDate.parse(parts[3]);
                notes = parts[4];

                jobs.add(new JobApplication(company, position, status, date, notes));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found creating a file");
            try {
                new FileWriter(FILE_PATH).close();
            } catch (IOException ex) {
                System.err.println("Error creating file.");
            }
        } catch (IOException e) {
            System.err.println("Error reading file.");
        }
        return jobs;
    }
}