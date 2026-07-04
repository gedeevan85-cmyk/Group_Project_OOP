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

        if (admin.getName() == null || admin.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama admin tidak boleh kosong.");
        }

        if (admin.getEmail() == null || admin.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email tidak boleh kosong.");
        }

        if (admin.getPassword() == null || admin.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password tidak boleh kosong.");
        }

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

        if (newPassword == null || newPassword.isEmpty()) {
            throw new IllegalArgumentException("Password baru tidak boleh kosong.");
        }

        String hashedPassword = BCrypt.hashpw(newPassword,BCrypt.gensalt());

        adminDAO.updatePassword(email, hashedPassword);

    }
}
