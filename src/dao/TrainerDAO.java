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
import java.util.ArrayList;
import java.util.List;
import model.Trainer;
/**
 *
 * @author gedee
 */
public class TrainerDAO implements InterfaceDAO<Trainer>{

    private Connection connection;

    public TrainerDAO() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void save(Trainer object) {
         String sql = "INSERT INTO trainer "
            + "(name, gender, specialization, experience_year, phone, email) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, object.getName());
            statement.setString(2, object.getGender());
            statement.setString(3, object.getSpecialization());
            statement.setInt(4, object.getExperienceYear());
            statement.setString(5, object.getPhone());
            statement.setString(6, object.getEmail());

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }
    }

    @Override
    public void update(Trainer object) {
         String sql = "UPDATE trainer SET "
            + "name = ?, "
            + "gender = ?, "
            + "specialization = ?, "
            + "experience_year = ?, "
            + "phone = ?, "
            + "email = ? "
            + "WHERE trainer_id = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, object.getName());
            statement.setString(2, object.getGender());
            statement.setString(3, object.getSpecialization());
            statement.setInt(4, object.getExperienceYear());
            statement.setString(5, object.getPhone());
            statement.setString(6, object.getEmail());
            statement.setInt(7, object.getTrainerId());

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }
    }

    @Override
    public void delete(int id) {
         String sql = "DELETE FROM trainer WHERE trainer_id = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }
    }

    @Override
    public Trainer findById(int id) {
         String sql = "SELECT * FROM trainer WHERE trainer_id = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Trainer trainer = new Trainer();

                trainer.setTrainerId(resultSet.getInt("trainer_id"));
                trainer.setName(resultSet.getString("name"));
                trainer.setGender(resultSet.getString("gender"));
                trainer.setSpecialization(resultSet.getString("specialization"));
                trainer.setExperienceYear(resultSet.getInt("experience_year"));
                trainer.setPhone(resultSet.getString("phone"));
                trainer.setEmail(resultSet.getString("email"));
                trainer.setCreatedAt(resultSet.getTimestamp("created_at"));
                trainer.setUpdatedAt(resultSet.getTimestamp("updated_at"));

                return trainer;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public List<Trainer> findAll() {
        List<Trainer> trainers = new ArrayList<>();

        String sql = "SELECT * FROM trainer";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Trainer trainer = new Trainer();

                trainer.setTrainerId(resultSet.getInt("trainer_id"));
                trainer.setName(resultSet.getString("name"));
                trainer.setGender(resultSet.getString("gender"));
                trainer.setSpecialization(resultSet.getString("specialization"));
                trainer.setExperienceYear(resultSet.getInt("experience_year"));
                trainer.setPhone(resultSet.getString("phone"));
                trainer.setEmail(resultSet.getString("email"));
                trainer.setCreatedAt(resultSet.getTimestamp("created_at"));
                trainer.setUpdatedAt(resultSet.getTimestamp("updated_at"));

                trainers.add(trainer);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return trainers;
    }
    
    public List<Trainer> search(String keyword){
        List<Trainer> trainers = new ArrayList<>();

        String sql = "SELECT * FROM trainer "
                + "WHERE name LIKE ? "
                + "OR phone LIKE ? "
                + "OR email LIKE ? "
                + "OR specialization LIKE ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");
            statement.setString(4, "%" + keyword + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Trainer trainer = new Trainer();

                trainer.setTrainerId(resultSet.getInt("trainer_id"));
                trainer.setName(resultSet.getString("name"));
                trainer.setGender(resultSet.getString("gender"));
                trainer.setPhone(resultSet.getString("phone"));
                trainer.setEmail(resultSet.getString("email"));
                trainer.setSpecialization(resultSet.getString("specialization"));
                trainer.setExperienceYear(resultSet.getInt("experience_year"));
                trainer.setCreatedAt(resultSet.getTimestamp("created_at"));
                trainer.setUpdatedAt(resultSet.getTimestamp("updated_at"));

                trainers.add(trainer);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return trainers;
    }
}
