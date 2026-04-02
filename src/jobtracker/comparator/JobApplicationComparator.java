package jobtracker.comparator;

import jobtracker.model.JobApplication;

import java.util.Comparator;

public class JobApplicationComparator implements Comparator<JobApplication> {
    public int compare(JobApplication ja1, JobApplication ja2) {
        int dateCompare = ja2.getDateApplied().compareTo(ja1.getDateApplied());
        int statusCompare = ja1.getStatus().compareTo(ja2.getStatus());
        int companyCompare = ja1.getCompany().compareToIgnoreCase(ja2.getCompany());
        int positionCompare = ja1.getPosition().compareToIgnoreCase(ja2.getPosition());

        if (dateCompare!=0) return dateCompare;
        if (statusCompare!=0) return statusCompare;
        if (companyCompare!=0) return companyCompare;
        return positionCompare;
    }
}
