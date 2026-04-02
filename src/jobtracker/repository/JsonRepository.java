package jobtracker.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jobtracker.model.JobApplication;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

public class JsonRepository implements JobRepository{
    private static final String FILE_PATH = "data/jobs.json";
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    @Override
    public void saveToFile(List<JobApplication> jobApplications) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(jobApplications, writer);
        } catch (IOException e) {
            System.err.println("Problem saving Json file");
        }
    }

    @Override
    public List<JobApplication> loadFromFile() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type type = new TypeToken<List<JobApplication>>() {}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            System.err.println("Problem reading Json file");
            return List.of();
        }
    }
}
