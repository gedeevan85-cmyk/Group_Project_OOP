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
public class MuscleGainProgram extends WorkoutProgram {

    public MuscleGainProgram() {
        super("Muscle Gain");
    }

    @Override
    public double calculateDailyCalories(Member member) {

        double weight = member.getWeight();

        return weight * 36 + 250;

    }
}
