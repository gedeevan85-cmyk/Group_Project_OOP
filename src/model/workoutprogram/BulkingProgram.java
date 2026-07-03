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
public class BulkingProgram extends WorkoutProgram{
    public BulkingProgram() {
        super("Bulking");
    }

    @Override
    public double calculateDailyCalories(Member member) {

        double weight = member.getWeight();

        return weight * 35 + 300;

    }
}
