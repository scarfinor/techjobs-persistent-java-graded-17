package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;

@Entity
public class Skill extends AbstractEntity {

    @Size(max = 500, message = "Description is to long!")
    public String description;

    public Skill() {
    }

    public Skill(String description) {
        this();
        this.description = description;
    }

    public @Size(max = 500, message = "Description is to long!") String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 500, message = "Description is to long!") String description) {
        this.description = description;
    }
}
