/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.MembershipPackageDAO;
import java.util.List;
import model.MembershipPackage;
/**
 *
 * @author gedee
 */
public class MembershipPackageController {
     private MembershipPackageDAO membershipPackageDAO;

    public MembershipPackageController() {
        membershipPackageDAO = new MembershipPackageDAO();
    }

    public void save(MembershipPackage membershipPackage) {
        membershipPackageDAO.save(membershipPackage);
    }

    public void update(MembershipPackage membershipPackage) {
        membershipPackageDAO.update(membershipPackage);
    }

    public void delete(int id) {
        membershipPackageDAO.delete(id);
    }

    public MembershipPackage findById(int id) {
        return membershipPackageDAO.findById(id);
    }

    public List<MembershipPackage> findAll() {
        return membershipPackageDAO.findAll();
    }
}
