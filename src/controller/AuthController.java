/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.AdminDAO;
import model.Admin;
import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author gedee
 */
public class AuthController {
    private AdminDAO adminDAO;

    public AuthController() {
        adminDAO = new AdminDAO();
    }
    
    public void register(Admin admin) {

        validateName(admin.getName());

        validateEmail(admin.getEmail());

        validatePassword(admin.getPassword());

        if (adminDAO.findByEmail(admin.getEmail()) != null) {
            throw new IllegalArgumentException("Email sudah terdaftar.");
        }

        String hashedPassword = BCrypt.hashpw(admin.getPassword(),BCrypt.gensalt()
        );

        admin.setPassword(hashedPassword);

        adminDAO.register(admin);

    }
    
    public Admin login(String email, String password) {

        Admin admin = adminDAO.findByEmail(email);

        if (admin == null) {
            throw new IllegalArgumentException("Email atau password salah.");
        }

        boolean cocok = BCrypt.checkpw(password,admin.getPassword());

        if (!cocok) {
            throw new IllegalArgumentException("Email atau password salah.");
        }

        return admin;

    }
    
    public void forgotPassword(String email, String newPassword) {

        Admin admin = adminDAO.findByEmail(email);

        if (admin == null) {
            throw new IllegalArgumentException("Email tidak ditemukan.");
        }

        validatePassword(newPassword);

        String hashedPassword = BCrypt.hashpw(newPassword,BCrypt.gensalt());

        adminDAO.updatePassword(email, hashedPassword);

    }
    
    private void validateName(String name) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama admin tidak boleh kosong.");
        }

        if (name.trim().length() < 3) {
            throw new IllegalArgumentException("Nama minimal 3 karakter.");
        }

        if (!name.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Nama hanya boleh berisi huruf.");
        }

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
    
    private void validatePassword(String password) {

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password tidak boleh kosong.");
        }

        if (password.length() < 8) {
            throw new IllegalArgumentException("Password minimal 8 karakter.");
        }

    }
}
