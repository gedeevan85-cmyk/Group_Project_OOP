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
import java.util.List;
import java.util.ArrayList;
import model.MembershipPackage;
import model.Member;
/**
 *
 * @author gedee
 */
public class MemberDAO implements InterfaceDAO<Member>{

    private Connection connection;
    private MembershipPackageDAO membershipPackageDAO;

    public MemberDAO() {
        connection = DBConnection.getConnection();
        membershipPackageDAO = new MembershipPackageDAO();
    }

    @Override
    public void save(Member object) {
        String sql = "INSERT INTO member "
            + "(name, gender, birth_date, weight, height, phone, email, package_id) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, object.getName());
            statement.setString(2, object.getGender());
            statement.setDate(3, object.getBirthDate());
            statement.setDouble(4, object.getWeight());
            statement.setDouble(5, object.getHeight());
            statement.setString(6, object.getPhone());
            statement.setString(7, object.getEmail());
            statement.setInt(8, object.getMembershipPackage().getPackageId());

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }
    }

    @Override
    public void update(Member object) {
       String sql = "UPDATE member SET "
            + "name = ?, "
            + "gender = ?, "
            + "birth_date = ?, "
            + "weight = ?, "
            + "height = ?, "
            + "phone = ?, "
            + "email = ?, "
            + "package_id = ? "
            + "WHERE member_id = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, object.getName());
            statement.setString(2, object.getGender());
            statement.setDate(3, object.getBirthDate());
            statement.setDouble(4, object.getWeight());
            statement.setDouble(5, object.getHeight());
            statement.setString(6, object.getPhone());
            statement.setString(7, object.getEmail());
            statement.setInt(8, object.getMembershipPackage().getPackageId());
            statement.setInt(9, object.getMemberId());

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM member WHERE member_id = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    @Override
    public Member findById(int id) {
         String sql = "SELECT * FROM member WHERE member_id = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Member member = new Member();

                member.setMemberId(resultSet.getInt("member_id"));
                member.setName(resultSet.getString("name"));
                member.setGender(resultSet.getString("gender"));
                member.setBirthDate(resultSet.getDate("birth_date"));
                member.setWeight(resultSet.getDouble("weight"));
                member.setHeight(resultSet.getDouble("height"));
                member.setPhone(resultSet.getString("phone"));
                member.setEmail(resultSet.getString("email"));

                int packageId = resultSet.getInt("package_id");

                MembershipPackage membershipPackage = membershipPackageDAO.findById(packageId);

                member.setMembershipPackage(membershipPackage);

                member.setCreatedAt(resultSet.getTimestamp("created_at"));
                member.setUpdatedAt(resultSet.getTimestamp("updated_at"));

                return member;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();

        String sql = "SELECT * FROM member";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Member member = new Member();

                member.setMemberId(resultSet.getInt("member_id"));
                member.setName(resultSet.getString("name"));
                member.setGender(resultSet.getString("gender"));
                member.setBirthDate(resultSet.getDate("birth_date"));
                member.setWeight(resultSet.getDouble("weight"));
                member.setHeight(resultSet.getDouble("height"));
                member.setPhone(resultSet.getString("phone"));
                member.setEmail(resultSet.getString("email"));

                int packageId = resultSet.getInt("package_id");

                MembershipPackage membershipPackage = membershipPackageDAO.findById(packageId);

                member.setMembershipPackage(membershipPackage);

                member.setCreatedAt(resultSet.getTimestamp("created_at"));
                member.setUpdatedAt(resultSet.getTimestamp("updated_at"));

                members.add(member);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return members;
    }
}
