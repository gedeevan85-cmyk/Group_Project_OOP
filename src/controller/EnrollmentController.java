/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.EnrollmentDAO;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import model.Enrollment;
import model.Member;
import model.workoutprogram.BulkingProgram;
import model.workoutprogram.CuttingProgram;
import model.workoutprogram.MuscleGainProgram;
import model.workoutprogram.WeightLossProgram;
import model.workoutprogram.WorkoutProgram;
/**
 *
 * @author gedee
 */
public class EnrollmentController {
    private EnrollmentDAO enrollmentDAO;

    public EnrollmentController() {
        enrollmentDAO = new EnrollmentDAO();
    }
    
    public void save(Enrollment enrollment) {
        validateEnrollment(enrollment);

        Member member = enrollment.getMember();

        double bmr = calculateBMR(member);

        double targetCalories = calculateTargetCalories(
                bmr,
                enrollment.getWorkoutProgram()
        );

        enrollment.setTargetCalories(targetCalories);

        enrollmentDAO.save(enrollment);

    }

    public void update(Enrollment enrollment) {
         validateEnrollment(enrollment);

        Member member = enrollment.getMember();

        double bmr = calculateBMR(member);

        double targetCalories = calculateTargetCalories(
                bmr,
                enrollment.getWorkoutProgram()
        );

        enrollment.setTargetCalories(targetCalories);

        enrollmentDAO.update(enrollment);

    }

    public void delete(int id) {

        enrollmentDAO.delete(id);

    }

    public Enrollment findById(int id) {

        return enrollmentDAO.findById(id);

    }

    public List<Enrollment> findAll() {

        return enrollmentDAO.findAll();

    }
    
    private void validateEnrollment(Enrollment enrollment) {

        if (enrollment.getMember() == null) {
            throw new IllegalArgumentException("Member harus dipilih.");
        }

        if (enrollment.getFitnessClass() == null) {
            throw new IllegalArgumentException("Kelas fitness harus dipilih.");
        }

        if (enrollment.getWorkoutProgram() == null) {
            throw new IllegalArgumentException("Program latihan harus dipilih.");
        }

        if (enrollment.getEnrollmentDate() == null) {
            throw new IllegalArgumentException("Tanggal pendaftaran harus diisi.");
        }

    }
    
    public int calculateAge(Member member) {

        LocalDate birthDate = member.getBirthDate().toLocalDate();

        LocalDate today = LocalDate.now();

        return Period.between(birthDate, today).getYears();

    }
    
    public double calculateBMI(Member member) {

        double heightMeter = member.getHeight() / 100.0;

        return member.getWeight() / (heightMeter * heightMeter);

    }
    
    public double calculateBMR(Member member) {

        int age = calculateAge(member);

        if (member.getGender().equalsIgnoreCase("Laki-laki")) {

            return (10 * member.getWeight())
                    + (6.25 * member.getHeight())
                    - (5 * age)
                    + 5;

        } else {

            return (10 * member.getWeight())
                    + (6.25 * member.getHeight())
                    - (5 * age)
                    - 161;

        }

    }
    
    public double calculateTargetCalories(double bmr,
        WorkoutProgram workoutProgram) {

        if (workoutProgram instanceof BulkingProgram) {

            return bmr + 500;

        } else if (workoutProgram instanceof CuttingProgram) {

            return bmr - 500;

        } else if (workoutProgram instanceof WeightLossProgram) {

            return bmr - 700;

        } else if (workoutProgram instanceof MuscleGainProgram) {

            return bmr + 300;

        }

        return bmr;

    }
    
    public List<Enrollment> search(String keyword) {

        return enrollmentDAO.search(keyword);

    }
    
    public int count() {

        return enrollmentDAO.count();
    }
}
