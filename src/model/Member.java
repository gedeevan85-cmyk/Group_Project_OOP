/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;
/**
 *
 * @author gedee
 */
public class Member extends Person{
    private int memberId;
    private Date birthDate;
    private double weight;
    private double height;
    private MembershipPackage membershipPackage;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Member() {
    }

    public Member(int memberId, String name, String gender, Date birthDate,
            double weight, double height, String phone, String email,
            MembershipPackage membershipPackage,
            Timestamp createdAt, Timestamp updatedAt) {

        super(name, gender, phone, email);

        this.memberId = memberId;
        this.birthDate = birthDate;
        this.weight = weight;
        this.height = height;
        this.membershipPackage = membershipPackage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public MembershipPackage getMembershipPackage() {
        return membershipPackage;
    }

    public void setMembershipPackage(MembershipPackage membershipPackage) {
        this.membershipPackage = membershipPackage;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
