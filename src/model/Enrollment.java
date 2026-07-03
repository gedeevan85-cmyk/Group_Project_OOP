/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;
import model.workoutprogram.WorkoutProgram;
/**
 *
 * @author gedee
 */
public class Enrollment {
    private int enrollmentId;
    private Member member;
    private FitnessClass fitnessClass;
    private WorkoutProgram workoutProgram;
    private double targetCalories;
    private Date enrollmentDate;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Enrollment() {
    }

    public Enrollment(int enrollmentId, Member member,
            FitnessClass fitnessClass, WorkoutProgram workoutProgram,
            double targetCalories, Date enrollmentDate,
            String status, Timestamp createdAt,
            Timestamp updatedAt) {

        this.enrollmentId = enrollmentId;
        this.member = member;
        this.fitnessClass = fitnessClass;
        this.workoutProgram = workoutProgram;
        this.targetCalories = targetCalories;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public FitnessClass getFitnessClass() {
        return fitnessClass;
    }

    public void setFitnessClass(FitnessClass fitnessClass) {
        this.fitnessClass = fitnessClass;
    }

    public WorkoutProgram getWorkoutProgram() {
        return workoutProgram;
    }

    public void setWorkoutProgram(WorkoutProgram workoutProgram) {
        this.workoutProgram = workoutProgram;
    }

    public double getTargetCalories() {
        return targetCalories;
    }

    public void setTargetCalories(double targetCalories) {
        this.targetCalories = targetCalories;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
