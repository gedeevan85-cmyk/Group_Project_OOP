/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DBConnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.MembershipPackage;

/**
 *
 * @author gedee
 */
public class MembershipPackageDAO implements InterfaceDAO<MembershipPackage> {

    private Connection connection;

    public MembershipPackageDAO() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void save(MembershipPackage object) {
        String sql = "INSERT INTO membership_package "
            + "(package_name, duration_month, price, description) "
            + "VALUES (?, ?, ?, ?)";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, object.getPackageName());
            statement.setInt(2, object.getDurationMonth());
            statement.setDouble(3, object.getPrice());
            statement.setString(4, object.getDescription());

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }
    }

    @Override
    public void update(MembershipPackage object) {
        String sql = "UPDATE membership_package "
            + "SET package_name = ?, "
            + "duration_month = ?, "
            + "price = ?, "
            + "description = ? "
            + "WHERE package_id = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, object.getPackageName());
            statement.setInt(2, object.getDurationMonth());
            statement.setDouble(3, object.getPrice());
            statement.setString(4, object.getDescription());
            statement.setInt(5, object.getPackageId());

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM membership_package WHERE package_id = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }
    }

    @Override
    public MembershipPackage findById(int id) {
        String sql = "SELECT * FROM membership_package WHERE package_id = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                MembershipPackage membershipPackage = new MembershipPackage();

                membershipPackage.setPackageId(resultSet.getInt("package_id"));
                membershipPackage.setPackageName(resultSet.getString("package_name"));
                membershipPackage.setDurationMonth(resultSet.getInt("duration_month"));
                membershipPackage.setPrice(resultSet.getDouble("price"));
                membershipPackage.setDescription(resultSet.getString("description"));
                membershipPackage.setCreatedAt(resultSet.getTimestamp("created_at"));
                membershipPackage.setUpdatedAt(resultSet.getTimestamp("updated_at"));

                return membershipPackage;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public List<MembershipPackage> findAll() {
        List<MembershipPackage> membershipPackages = new ArrayList<>();

    String sql = "SELECT * FROM membership_package";

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();

        System.out.println("Query membership_package dijalankan");

        while (resultSet.next()) {

            System.out.println("Data ditemukan : "
                    + resultSet.getString("package_name"));

            MembershipPackage membershipPackage = new MembershipPackage();

            membershipPackage.setPackageId(resultSet.getInt("package_id"));
            membershipPackage.setPackageName(resultSet.getString("package_name"));
            membershipPackage.setDurationMonth(resultSet.getInt("duration_month"));
            membershipPackage.setPrice(resultSet.getDouble("price"));
            membershipPackage.setDescription(resultSet.getString("description"));

            membershipPackages.add(membershipPackage);

        }

        System.out.println("Total List = " + membershipPackages.size());

    } catch (SQLException e) {

        e.printStackTrace();

    }

    return membershipPackages;
    }
    
    public List<MembershipPackage> search(String keyword) {

        List<MembershipPackage> membershipPackages = new ArrayList<>();

        String sql = "SELECT * FROM membership_package "
                + "WHERE package_name LIKE ? "
                + "OR description LIKE ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                MembershipPackage membershipPackage =
                        new MembershipPackage();

                membershipPackage.setPackageId(
                        resultSet.getInt("package_id"));

                membershipPackage.setPackageName(
                        resultSet.getString("package_name"));

                membershipPackage.setDurationMonth(
                        resultSet.getInt("duration_month"));

                membershipPackage.setPrice(
                        resultSet.getDouble("price"));

                membershipPackage.setDescription(
                        resultSet.getString("description"));

                membershipPackage.setCreatedAt(
                        resultSet.getTimestamp("created_at"));

                membershipPackage.setUpdatedAt(
                        resultSet.getTimestamp("updated_at"));

                membershipPackages.add(membershipPackage);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return membershipPackages;

    }
}
