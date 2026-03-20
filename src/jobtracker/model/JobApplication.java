package jobtracker.model;

import java.time.LocalDate;
import java.util.Objects;

public class JobApplication {
    private String company;
    private String position;
    private Status status;
    private LocalDate dateApplied;
    private String notes;

    public JobApplication(String company, String position, Status status, LocalDate dateApplied, String notes) {
        this.company = company;
        this.position = position;
        this.status = status;
        this.dateApplied = dateApplied;
        this.notes = notes;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(LocalDate dateApplied) {
        this.dateApplied = dateApplied;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JobApplication that = (JobApplication) o;
        return Objects.equals(company, that.company) && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, position);
    }

    @Override
    public String toString() {
        return company + " - " + position + " ["+status+"]";
    }

    public String detailToString() {
        return toString()+ " Applied the : "+ dateApplied + " Notes : " + notes;
    }
}
