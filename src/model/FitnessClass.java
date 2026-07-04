/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Time;
import java.sql.Timestamp;
/**
 *
 * @author gedee
 */
public class FitnessClass {
    private int classId;
    private Trainer trainer;
    private String className;
    private String scheduleDay;
    private Time scheduleTime;
    private int durationMinute;
    private int capacity;
    private Timestamp created_at;
    private Timestamp updated_at;

    public FitnessClass() {
    }

    public FitnessClass(int classId, Trainer trainer, String className,
            String scheduleDay, Time scheduleTime,
            int durationMinute, int capacity,
            Timestamp created_at, Timestamp updated_at) {

        this.classId = classId;
        this.trainer = trainer;
        this.className = className;
        this.scheduleDay = scheduleDay;
        this.scheduleTime = scheduleTime;
        this.durationMinute = durationMinute;
        this.capacity = capacity;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getScheduleDay() {
        return scheduleDay;
    }

    public void setScheduleDay(String scheduleDay) {
        this.scheduleDay = scheduleDay;
    }

    public Time getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Time scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public int getDurationMinute() {
        return durationMinute;
    }

    public void setDurationMinute(int durationMinute) {
        this.durationMinute = durationMinute;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
        return className;
    }
}
