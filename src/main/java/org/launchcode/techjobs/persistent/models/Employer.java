package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Employer extends AbstractEntity {

    @NotBlank (message = "Please enter a location")
    @Size (max = 100, message = "location must be less then 100 characters")
    private String location;

    public Employer() {}

    public Employer(String location) {
        this();
        this.location = location;
    }

    public @NotBlank(message = "Please enter a location") @Size(max = 100, message = "location must be less then 100 characters") String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank(message = "Please enter a location") @Size(max = 100, message = "location must be less then 100 characters") String location) {
        this.location = location;
    }
}
