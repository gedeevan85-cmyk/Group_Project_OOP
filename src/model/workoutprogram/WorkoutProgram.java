/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.workoutprogram;

import model.Member;
/**
 *
 * @author gedee
 */
public abstract class WorkoutProgram {
    private String programName;

    public WorkoutProgram(String programName) {
        this.programName = programName;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public abstract double calculateDailyCalories(Member member);

    @Override
    public String toString() {
        return programName;
    }
}
