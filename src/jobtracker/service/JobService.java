package jobtracker.service;

import jobtracker.model.JobApplication;
import jobtracker.model.Status;

import java.util.ArrayList;
import java.util.List;

public class JobService {
    private List<JobApplication> applications = new ArrayList<JobApplication>();

    public void add(JobApplication app){
        applications.add(app);
    }

    public List<JobApplication> getAll(){
        return applications;
    }

    public void delete(int index){
        applications.remove(index);
    }

    public void updateStatus(int index, Status status){
        applications.get(index).setStatus(status);
    }
}
