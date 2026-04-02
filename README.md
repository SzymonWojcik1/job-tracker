# Job Tracker

A simple console-based Java application to track job applications.
The application allows users to manage job applications, sort them, and store data in CSV and JSON formats.

---

## Features

* Add a job application
* View all job applications
* Update application status
* Delete a job application
* Sort applications (date → status → company → position)
* Load data from CSV file
* Save data to CSV and JSON

---

## Technologies

* Java
* Object-Oriented Programming (OOP)
* File I/O
* CSV
* JSON (Gson)
* Git

---

## Project Structure

```
jobtracker
│
├── model
│   ├── JobApplication.java
│   └── Status.java
│
├── service
│   └── JobService.java
│
├── repository
│   ├── JobRepository.java
│   ├── CsvRepository.java
│   ├── JsonRepository.java
│   └── LocalDateAdapter.java
│
├── comparator
│   └── JobApplicationComparator.java
│
└── Main.java
```

---

## How the Application Works

1. When the application starts, job applications are loaded from a CSV file.
2. The user can add, update, delete, or sort job applications.
3. When the application exits, data is saved to:

   * CSV file
   * JSON file

The JSON file is mainly used for data export and future extensibility.

---

## CSV Format

The CSV file must follow this format:

```
COMPANY;POSITION;STATUS;DATE_APPLIED;NOTES
Google;Junior Dev;INTERVIEW;2026-04-02;Interview in two days
Microsoft;Backend Dev;APPLIED;2026-03-28;No news
```

Separator used: `;`

---

## JSON Format

Example JSON object:

```json
{
  "company": "Google",
  "position": "Junior Dev",
  "status": "INTERVIEW",
  "dateApplied": "2026-04-02",
  "notes": "Interview in two days (2026-04-04)"
}
```

The JSON file contains a list of job applications.

---

## How to Run

1. Clone the repository:

```
git clone https://github.com/your-username/job-tracker.git
```

2. Open the project in IntelliJ IDEA

3. Make sure the following folder exists:

```
data/
```

4. Run:

```
Main.java
```

---

## Future Improvements

* Search job applications
* Filter by status
* Multiple sorting options

---

## Author

Szymon Wojcik 
Personal project for learning, portfolio, and job search preparation.

---
