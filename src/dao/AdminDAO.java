/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Admin;

/**
 *
 * @author gedee
 */
public class AdminDAO {
    private Connection connection;

    public AdminDAO() {
        connection = DBConnection.getConnection();
    }
    
    public void register(Admin admin) {

        String sql = "INSERT INTO admin (name, email, password) VALUES (?, ?, ?)";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, admin.getName());
            statement.setString(2, admin.getEmail());
            statement.setString(3, admin.getPassword());

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }
    
    public Admin findByEmail(String email) {

        String sql = "SELECT * FROM admin WHERE email = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Admin admin = new Admin();

                admin.setAdminId(resultSet.getInt("admin_id"));
                admin.setName(resultSet.getString("name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setCreatedAt(resultSet.getTimestamp("created_at"));
                admin.setUpdatedAt(resultSet.getTimestamp("updated_at"));

                return admin;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }
    
    public void updatePassword(String email, String newPassword) {

        String sql = "UPDATE admin SET password = ? WHERE email = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, newPassword);
            statement.setString(2, email);

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }
    
    public Admin login(String email) {

        return findByEmail(email);

    }
}
