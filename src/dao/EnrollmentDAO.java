/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import model.Enrollment;
import model.Member;
import model.FitnessClass;
import model.workoutprogram.WorkoutProgram;
import model.workoutprogram.BulkingProgram;
import model.workoutprogram.CuttingProgram;
import model.workoutprogram.WeightLossProgram;
import model.workoutprogram.MuscleGainProgram;



/**
/**
 *
 * @author asus
 */
public class EnrollmentDAO implements InterfaceDAO<Enrollment>{
    private Connection connection;
    private MemberDAO memberDAO;
    private FitnessClassDAO fitnessClassDAO;

    public EnrollmentDAO() {
        
    connection = DBConnection.getConnection();
    memberDAO = new MemberDAO();
    fitnessClassDAO = new FitnessClassDAO();

    
    }
    private String getGoal(WorkoutProgram workoutProgram) {

    if (workoutProgram instanceof BulkingProgram) {

        return "Bulking";

    } else if (workoutProgram instanceof CuttingProgram) {

        return "Cutting";

    } else if (workoutProgram instanceof WeightLossProgram) {

        return "Weight Loss";

    } else if (workoutProgram instanceof MuscleGainProgram) {

        return "Muscle Gain";

    }

    return null;
}
    private WorkoutProgram createWorkoutProgram(String goal) {

    switch (goal) {

        case "Bulking":
            return new BulkingProgram();

        case "Cutting":
            return new CuttingProgram();

        case "Weight Loss":
            return new WeightLossProgram();

        case "Muscle Gain":
            return new MuscleGainProgram();

        default:
            return null;

    }

}
    @Override
public void save(Enrollment object) {

    String sql = "INSERT INTO enrollment "
            + "(member_id, class_id, goal, target_calories, enrollment_date, status) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, object.getMember().getMemberId());
        statement.setInt(2, object.getFitnessClass().getClassId());
        statement.setString(3, getGoal(object.getWorkoutProgram()));
        statement.setDouble(4, object.getTargetCalories());
        statement.setDate(5, object.getEnrollmentDate());
        statement.setString(6, object.getStatus());

        statement.executeUpdate();

    } catch (SQLException e) {

        e.printStackTrace();

    }

} 
    @Override
public void update(Enrollment object) {

    String sql = "UPDATE enrollment SET "
            + "member_id = ?, "
            + "class_id = ?, "
            + "goal = ?, "
            + "target_calories = ?, "
            + "enrollment_date = ?, "
            + "status = ? "
            + "WHERE enrollment_id = ?";

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, object.getMember().getMemberId());
        statement.setInt(2, object.getFitnessClass().getClassId());
        statement.setString(3, getGoal(object.getWorkoutProgram()));
        statement.setDouble(4, object.getTargetCalories());
        statement.setDate(5, object.getEnrollmentDate());
        statement.setString(6, object.getStatus());
        statement.setInt(7, object.getEnrollmentId());

        statement.executeUpdate();

    } catch (SQLException e) {

        e.printStackTrace();

    }

}
    @Override
public void delete(int id) {

    String sql = "DELETE FROM enrollment WHERE enrollment_id = ?";

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, id);

        statement.executeUpdate();

    } catch (SQLException e) {

        e.printStackTrace();

    }

}
    @Override
public Enrollment findById(int id) {

    String sql = "SELECT * FROM enrollment WHERE enrollment_id = ?";

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {

            Enrollment enrollment = new Enrollment();

            enrollment.setEnrollmentId(resultSet.getInt("enrollment_id"));

            int memberId = resultSet.getInt("member_id");
            Member member = memberDAO.findById(memberId);
            enrollment.setMember(member);

            int classId = resultSet.getInt("class_id");
            FitnessClass fitnessClass = fitnessClassDAO.findById(classId);
            enrollment.setFitnessClass(fitnessClass);

            String goal = resultSet.getString("goal");
            enrollment.setWorkoutProgram(createWorkoutProgram(goal));

            enrollment.setTargetCalories(resultSet.getDouble("target_calories"));
            enrollment.setEnrollmentDate(resultSet.getDate("enrollment_date"));
            enrollment.setStatus(resultSet.getString("status"));

            enrollment.setCreatedAt(resultSet.getTimestamp("created_at"));
            enrollment.setUpdatedAt(resultSet.getTimestamp("updated_at"));

            return enrollment;

        }

    } catch (SQLException e) {

        e.printStackTrace();

    }

    return null;

}
    @Override
public List<Enrollment> findAll() {

    List<Enrollment> enrollments = new ArrayList<>();

    String sql = "SELECT * FROM enrollment";

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {

            Enrollment enrollment = new Enrollment();

            enrollment.setEnrollmentId(resultSet.getInt("enrollment_id"));

            int memberId = resultSet.getInt("member_id");
            Member member = memberDAO.findById(memberId);
            enrollment.setMember(member);

            int classId = resultSet.getInt("class_id");
            FitnessClass fitnessClass = fitnessClassDAO.findById(classId);
            enrollment.setFitnessClass(fitnessClass);

            String goal = resultSet.getString("goal");
            enrollment.setWorkoutProgram(createWorkoutProgram(goal));

            enrollment.setTargetCalories(resultSet.getDouble("target_calories"));
            enrollment.setEnrollmentDate(resultSet.getDate("enrollment_date"));
            enrollment.setStatus(resultSet.getString("status"));

            enrollment.setCreatedAt(resultSet.getTimestamp("created_at"));
            enrollment.setUpdatedAt(resultSet.getTimestamp("updated_at"));

            enrollments.add(enrollment);

        }

    } catch (SQLException e) {

        e.printStackTrace();

    }

    return enrollments;

}
}
