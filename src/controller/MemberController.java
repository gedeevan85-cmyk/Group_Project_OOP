/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.MemberDAO;
import java.util.List;
import model.Member;
/**
 *
 * @author gedee
 */
public class MemberController {
     private MemberDAO memberDAO;

    public MemberController() {
        memberDAO = new MemberDAO();
    }

    public void save(Member member) {

        validateMember(member);

        memberDAO.save(member);

    }

    public void update(Member member) {

        validateMember(member);

        memberDAO.update(member);

    }

    public void delete(int id) {
        memberDAO.delete(id);
    }

    public Member findById(int id) {
        return memberDAO.findById(id);
    }

    public List<Member> findAll() {
        return memberDAO.findAll();
    }

    private void validateMember(Member member) {

        if (member.getName() == null || member.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama member tidak boleh kosong.");
        }

        if (member.getWeight() <= 0) {
            throw new IllegalArgumentException("Berat badan harus lebih dari 0.");
        }

        if (member.getHeight() <= 0) {
            throw new IllegalArgumentException("Tinggi badan harus lebih dari 0.");
        }

        if (member.getMembershipPackage() == null) {
            throw new IllegalArgumentException("Paket membership harus dipilih.");
        }

    }
}
