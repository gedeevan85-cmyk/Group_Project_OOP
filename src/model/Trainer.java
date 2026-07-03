/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
/**
 *
 * @author gedee
 */
public class Trainer extends Person{
     private int trainerId;
    private String specialization;
    private int experienceYear;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Trainer() {
    }

    public Trainer(int trainerId, String name, String gender,
            String phone, String email,
            String specialization, int experienceYear,
            Timestamp createdAt, Timestamp updatedAt) {

        super(name, gender, phone, email);

        this.trainerId = trainerId;
        this.specialization = specialization;
        this.experienceYear = experienceYear;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return getName();
    }
}
