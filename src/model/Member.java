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
    private Timestamp created_at;
    private Timestamp updated_at;

    public Member() {
    }

    public Member(int memberId, String name, String gender, Date birthDate,
            double weight, double height, String phone, String email,
            MembershipPackage membershipPackage,
            Timestamp created_at, Timestamp updated_at) {

        super(name, gender, phone, email);

        this.memberId = memberId;
        this.birthDate = birthDate;
        this.weight = weight;
        this.height = height;
        this.membershipPackage = membershipPackage;
        this.created_at = created_at;
        this.updated_at = updated_at;
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
        return created_at;
    }

    public void setCreatedAt(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
    
     @Override
    public String toString() {
        return getName();
    }
    
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Member)) {
            return false;
        }

        Member other = (Member) obj;

        return this.memberId == other.memberId;

    }

    @Override
    public int hashCode() {

        return Integer.hashCode(memberId);

    }
}
