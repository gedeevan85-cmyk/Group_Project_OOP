/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.Objects;
import java.sql.Timestamp;
/**
 *
 * @author gedee
 */
public class Trainer extends Person{
     private int trainerId;
    private String specialization;
    private int experienceYear;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Trainer() {
    }

    public Trainer(int trainerId, String name, String gender,
            String phone, String email,
            String specialization, int experienceYear,
            Timestamp created_at, Timestamp updated_at) {

        super(name, gender, phone, email);

        this.trainerId = trainerId;
        this.specialization = specialization;
        this.experienceYear = experienceYear;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    @Override
    public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    Trainer other = (Trainer) obj;
    return trainerId == other.trainerId;
}

    @Override
    public int hashCode() {
    return Integer.hashCode(trainerId);
}

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperienceYear() {
        return experienceYear;
    }

    public void setExperienceYear(int experienceYear) {
        this.experienceYear = experienceYear;
    }

    public Timestamp getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return getName();
    }
}
