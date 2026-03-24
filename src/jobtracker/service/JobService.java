package jobtracker.service;

import jobtracker.model.JobApplication;
import jobtracker.model.Status;

import java.util.ArrayList;
import java.util.List;

public class JobService {
    private List<JobApplication> applications = new ArrayList<JobApplication>();

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

    private boolean isIndexValid(int index) {
        if (applications.isEmpty()) {
            return false;
        } else if (index >= applications.size() || index < 0) {
            return false;
        } else {return true;}
    }
}
