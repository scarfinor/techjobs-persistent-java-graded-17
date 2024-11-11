package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @NotBlank (message = "Please enter a location")
    @Size (max = 100, message = "location must be less then 100 characters")
    private String location;

    @OneToMany
    @JoinColumn(name = "employer_id", referencedColumnName = "id")
    private List<Job> jobs = new ArrayList<>();

    public Employer() {}

    public Employer(String name, String location) {
        this();
        this.setName(name);
        this.location = location;
    }

    public @NotBlank(message = "Please enter a location") @Size(max = 100, message = "location must be less then 100 characters") String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank(message = "Please enter a location") @Size(max = 100, message = "location must be less then 100 characters") String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
