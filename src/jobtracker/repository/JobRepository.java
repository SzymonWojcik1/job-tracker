package jobtracker.repository;

import jobtracker.model.JobApplication;

import java.util.List;

public interface JobRepository {
    public void saveToFile(List<JobApplication> jobApplications);
    public List<JobApplication> loadFromFile();
}
