/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import config.DBConnection;
import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.FitnessClass;
import model.Trainer;

/**
 *
 * @author asus
 */
public class FitnessClassDAO implements InterfaceDAO<FitnessClass>{
    private Connection connection;
    private TrainerDAO trainerDAO;

    public FitnessClassDAO() {
        connection = DBConnection.getConnection();
        trainerDAO = new TrainerDAO();
    }
    @Override
    public void save(FitnessClass object) {

    String sql = "INSERT INTO fitness_class "
            + "(trainer_id, class_name, schedule_day, schedule_time, duration_minute, capacity) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, object.getTrainer().getTrainerId());
        statement.setString(2, object.getClassName());
        statement.setString(3, object.getScheduleDay());
        statement.setTime(4, object.getScheduleTime());
        statement.setInt(5, object.getDurationMinute());
        statement.setInt(6, object.getCapacity());

        statement.executeUpdate();

    } catch (SQLException e) {

        e.printStackTrace();

    }

}
    @Override
public void update(FitnessClass object) {

    String sql = "UPDATE fitness_class SET "
            + "trainer_id = ?, "
            + "class_name = ?, "
            + "schedule_day = ?, "
            + "schedule_time = ?, "
            + "duration_minute = ?, "
            + "capacity = ? "
            + "WHERE class_id = ?";

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, object.getTrainer().getTrainerId());
        statement.setString(2, object.getClassName());
        statement.setString(3, object.getScheduleDay());
        statement.setTime(4, object.getScheduleTime());
        statement.setInt(5, object.getDurationMinute());
        statement.setInt(6, object.getCapacity());
        statement.setInt(7, object.getClassId());

        statement.executeUpdate();

    } catch (SQLException e) {

        e.printStackTrace();

    }

}
@Override
public void delete(int id) {

    String sql = "DELETE FROM fitness_class WHERE class_id = ?";

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, id);

        statement.executeUpdate();

    } catch (SQLException e) {

        e.printStackTrace();

    }

}
@Override
public FitnessClass findById(int id) {

    String sql = "SELECT * FROM fitness_class WHERE class_id = ?";

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {

            FitnessClass fitnessClass = new FitnessClass();

            fitnessClass.setClassId(resultSet.getInt("class_id"));

            int trainerId = resultSet.getInt("trainer_id");

            Trainer trainer = trainerDAO.findById(trainerId);

            fitnessClass.setTrainer(trainer);

            fitnessClass.setClassName(resultSet.getString("class_name"));
            fitnessClass.setScheduleDay(resultSet.getString("schedule_day"));
            fitnessClass.setScheduleTime(resultSet.getTime("schedule_time"));
            fitnessClass.setDurationMinute(resultSet.getInt("duration_minute"));
            fitnessClass.setCapacity(resultSet.getInt("capacity"));

            fitnessClass.setCreatedAt(resultSet.getTimestamp("created_at"));
            fitnessClass.setUpdatedAt(resultSet.getTimestamp("updated_at"));

            return fitnessClass;

        }

    } catch (SQLException e) {

        e.printStackTrace();

    }

    return null;
}
@Override
public List<FitnessClass> findAll() {

    List<FitnessClass> fitnessClasses = new ArrayList<>();

    String sql = "SELECT * FROM fitness_class";

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {

            FitnessClass fitnessClass = new FitnessClass();

            fitnessClass.setClassId(resultSet.getInt("class_id"));

            int trainerId = resultSet.getInt("trainer_id");

            Trainer trainer = trainerDAO.findById(trainerId);

            fitnessClass.setTrainer(trainer);

            fitnessClass.setClassName(resultSet.getString("class_name"));
            fitnessClass.setScheduleDay(resultSet.getString("schedule_day"));
            fitnessClass.setScheduleTime(resultSet.getTime("schedule_time"));
            fitnessClass.setDurationMinute(resultSet.getInt("duration_minute"));
            fitnessClass.setCapacity(resultSet.getInt("capacity"));

            fitnessClass.setCreatedAt(resultSet.getTimestamp("created_at"));
            fitnessClass.setUpdatedAt(resultSet.getTimestamp("updated_at"));

            fitnessClasses.add(fitnessClass);

        }

    } catch (SQLException e) {

        e.printStackTrace();

    }

    return fitnessClasses;
}

public List<FitnessClass> search(String keyword) {

    List<FitnessClass> fitnessClasses = new ArrayList<>();

    String sql = "SELECT * FROM fitness_class "
            + "WHERE class_name LIKE ? "
            + "OR schedule_day LIKE ?";

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, "%" + keyword + "%");
        statement.setString(2, "%" + keyword + "%");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {

            FitnessClass fitnessClass = new FitnessClass();

            fitnessClass.setClassId(resultSet.getInt("class_id"));

            Trainer trainer =
                    trainerDAO.findById(
                            resultSet.getInt("trainer_id"));

            fitnessClass.setTrainer(trainer);

            fitnessClass.setClassName(
                    resultSet.getString("class_name"));

            fitnessClass.setScheduleDay(
                    resultSet.getString("schedule_day"));

            fitnessClass.setScheduleTime(
                    resultSet.getTime("schedule_time"));

            fitnessClass.setDurationMinute(
                    resultSet.getInt("duration_minute"));

            fitnessClass.setCapacity(
                    resultSet.getInt("capacity"));

            fitnessClass.setCreatedAt(
                    resultSet.getTimestamp("created_at"));

            fitnessClass.setUpdatedAt(
                    resultSet.getTimestamp("updated_at"));

            fitnessClasses.add(fitnessClass);

        }

    } catch (SQLException e) {

        e.printStackTrace();

    }

    return fitnessClasses;

}

public int count() {

    String sql = "SELECT COUNT(*) FROM fitness_class";

    try {

        PreparedStatement statement =
                connection.prepareStatement(sql);

        ResultSet resultSet =
                statement.executeQuery();

        if (resultSet.next()) {

            return resultSet.getInt(1);

        }

    } catch (SQLException e) {

        e.printStackTrace();

    }

    return 0;

}
}
