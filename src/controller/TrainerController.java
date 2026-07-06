/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TrainerDAO;
import java.util.List;
import model.Trainer;
/**
 *
 * @author gedee
 */
public class TrainerController {
     private TrainerDAO trainerDAO;

    public TrainerController() {
        trainerDAO = new TrainerDAO();
    }

    public void save(Trainer trainer) {

        validateTrainer(trainer);

        trainerDAO.save(trainer);

    }

    public void update(Trainer trainer) {

        validateTrainer(trainer);

        trainerDAO.update(trainer);

    }

    public void delete(int id) {
        trainerDAO.delete(id);
    }

    public Trainer findById(int id) {
        return trainerDAO.findById(id);
    }

    public List<Trainer> findAll() {
        return trainerDAO.findAll();
    }

    private void validateTrainer(Trainer trainer) {

        if (trainer.getName() == null || trainer.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama trainer tidak boleh kosong.");
        }

        if (trainer.getSpecialization() == null || trainer.getSpecialization().trim().isEmpty()) {
            throw new IllegalArgumentException("Spesialisasi trainer harus diisi.");
        }

        if (trainer.getExperienceYear() < 0) {
            throw new IllegalArgumentException("Pengalaman trainer tidak boleh bernilai negatif.");
        }
          validateEmail(trainer.getEmail());
          validatePhone(trainer.getPhone());

    }
    private void validateEmail(String email) {

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email tidak boleh kosong.");
        }

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (!email.matches(regex)) {
            throw new IllegalArgumentException("Format email tidak valid.");
        }

    }
    private void validatePhone(String phone) {

    if (phone == null || phone.trim().isEmpty()) {
        throw new IllegalArgumentException("Nomor HP tidak boleh kosong.");
    }

    if (!phone.matches("^\\d{10,13}$")) {
        throw new IllegalArgumentException("Nomor HP hanya boleh berisi angka.");
    }

}
    
    public List<Trainer> search(String keyword) {

        return trainerDAO.search(keyword);
    }
    
    public int count() {

        return trainerDAO.count();
    }
}
