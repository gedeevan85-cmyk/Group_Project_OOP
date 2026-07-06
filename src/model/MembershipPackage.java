/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.Objects;
import java.sql.Timestamp;
/**
 *
 * @author gedee
 */
public class MembershipPackage {
    private int packageId;
    private String packageName;
    private int durationMonth;
    private double price;
    private String description;
    private Timestamp created_at;
    private Timestamp updated_at;

    public MembershipPackage() {
    }

    public MembershipPackage(int packageId, String packageName, int durationMonth,
            double price, String description, Timestamp created_at, Timestamp updated_at) {

        this.packageId = packageId;
        this.packageName = packageName;
        this.durationMonth = durationMonth;
        this.price = price;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    @Override
    public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    MembershipPackage other = (MembershipPackage) obj;
    return this.packageId == other.packageId;
}
    

    @Override
    public int hashCode() {
    return Objects.hash(packageId);
}

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getDurationMonth() {
        return durationMonth;
    }

    public void setDurationMonth(int durationMonth) {
        this.durationMonth = durationMonth;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return packageName;
    }
}
