/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.FitnessClassDAO;
import java.util.List;
import model.FitnessClass;
/**
 *
 * @author gedee
 */
public class FitnessClassController {
    private FitnessClassDAO fitnessClassDAO;

    public FitnessClassController() {
        fitnessClassDAO = new FitnessClassDAO();
    }

    public void save(FitnessClass fitnessClass) {

        validateFitnessClass(fitnessClass);

        fitnessClassDAO.save(fitnessClass);

    }

    public void update(FitnessClass fitnessClass) {

        validateFitnessClass(fitnessClass);

        fitnessClassDAO.update(fitnessClass);

    }

    public void delete(int id) {

        fitnessClassDAO.delete(id);

    }

    public FitnessClass findById(int id) {

        return fitnessClassDAO.findById(id);

    }

    public List<FitnessClass> findAll() {

        return fitnessClassDAO.findAll();

    }

    private void validateFitnessClass(FitnessClass fitnessClass) {

        if (fitnessClass.getTrainer() == null) {
            throw new IllegalArgumentException("Trainer harus dipilih.");
        }

        if (fitnessClass.getClassName() == null
                || fitnessClass.getClassName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama kelas tidak boleh kosong.");
        }

        if (fitnessClass.getScheduleDay() == null
                || fitnessClass.getScheduleDay().trim().isEmpty()) {
            throw new IllegalArgumentException("Hari kelas harus dipilih.");
        }

        if (fitnessClass.getScheduleTime() == null) {
            throw new IllegalArgumentException("Jam kelas harus dipilih.");
        }

        if (fitnessClass.getDurationMinute() <= 0) {
            throw new IllegalArgumentException("Durasi kelas harus lebih dari 0 menit.");
        }

        if (fitnessClass.getCapacity() <= 0) {
            throw new IllegalArgumentException("Kapasitas kelas harus lebih dari 0.");
        }

    }
    
    public List<FitnessClass> search(String keyword) {

        return fitnessClassDAO.search(keyword);

    }
    
    public int count() {

        return fitnessClassDAO.count();
    }
}
