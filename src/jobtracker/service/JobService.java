package jobtracker.service;

import jobtracker.comparator.JobApplicationComparator;
import jobtracker.model.JobApplication;
import jobtracker.model.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JobService {
    private List<JobApplication> applications = new ArrayList<JobApplication>();

    public JobService(List<JobApplication> applications) {
        this.applications = new ArrayList<>(applications);
    }

    public JobService() {
        this.applications = new ArrayList<>();
    }

    public void add(JobApplication app){
        if(app==null){
            System.out.println("Can't add a null job application");
        } else {applications.add(app);}
    }

    public List<JobApplication> getAll(){return new ArrayList<>(applications);}

    public void delete(int index){
        if (isIndexValid(index)) {applications.remove(index);}
        else {
            System.out.println("Index is out of bound");
        }
    }

    public void updateStatus(int index, Status status){
        if (status==null) {
            System.out.println("Status can't be null");
            return;
        }
        if (isIndexValid(index)) {applications.get(index).setStatus(status);}
        else {
            System.out.println("Index is out of bound");
        }
    }

    public void sortApplications() {
        applications.sort(new JobApplicationComparator());
    }

    private boolean isIndexValid(int index) {
        if (applications.isEmpty()) {
            return false;
        } else if (index >= applications.size() || index < 0) {
            return false;
        } else {return true;}
    }
}
