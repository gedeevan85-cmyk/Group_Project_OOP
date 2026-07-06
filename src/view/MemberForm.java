/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import controller.MemberController;
import controller.MembershipPackageController;
import model.Member;
import model.MembershipPackage;


/**
 *
 * @author user
 */
public class MemberForm extends javax.swing.JFrame {
    private MemberController memberController;
    private MembershipPackageController membershipPackageController;  
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MemberForm.class.getName());
    private List<Member> memberList;
    private Member selectedMember;
    private int currentPage = 1;
    private final int dataPerPage = 2;
    private int totalPage;

    /**
     * Creates new form TrainerForm
     */
    public MemberForm() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        memberController = new MemberController();
        membershipPackageController = new MembershipPackageController();

        loadMembershipPackage();
        loadTable();
        clearForm();
    }
    
    private void loadMembershipPackage() {

       jComboBoxMembershipPackage.removeAllItems();

        List<MembershipPackage> packages = membershipPackageController.findAll();

        for (MembershipPackage membershipPackage : packages) {

            jComboBoxMembershipPackage.addItem(membershipPackage);

        }

    }
    
    private void clearForm() {

        jTextFieldName.setText("");
        jTextFieldBirthDate.setText("");
        jTextFieldNomber.setText("");
        jTextFieldEmail.setText("");
        jTextFieldHeight.setText("");
        jTextFieldWeight.setText("");

        buttonGroup1.clearSelection();

        if (jComboBoxMembershipPackage.getItemCount() > 0) {
            jComboBoxMembershipPackage.setSelectedIndex(0);
        }

    }
    
    private void loadTable() {

         loadTable(memberController.findAll());

    }
    
    private void loadTable(List<Member> members) {

        DefaultTableModel model = (DefaultTableModel) jTableMember.getModel();

        model.setRowCount(0);

        memberList = members;
        
        totalPage = (int) Math.ceil((double) memberList.size() / dataPerPage);

        if (totalPage == 0) {
            totalPage = 1;
        }

        if (currentPage > totalPage) {
            currentPage = totalPage;
        }

        int start = (currentPage - 1) * dataPerPage;
        int end = Math.min(start + dataPerPage, memberList.size());

        int no = start + 1;

        for (int i = start; i < end; i++) {

            Member member = memberList.get(i);

            model.addRow(new Object[]{
                no++,
                member.getName(),
                member.getGender(),
                member.getBirthDate(),
                member.getPhone(),
                member.getEmail(),
                member.getHeight(),
                member.getWeight(),
                member.getMembershipPackage().getPackageName()
            });

        }
        
        jLabelNoPage.setText(" Halaman " + currentPage + " / " + totalPage);

    }
    
    private Member getMemberFromForm() {

        Member member = new Member();
        
        member.setName(jTextFieldName.getText());
        if (jRadioButtonMale.isSelected()) {

            member.setGender("Male");

        } else {

            member.setGender("Female");

        }
        member.setBirthDate(java.sql.Date.valueOf(jTextFieldBirthDate.getText()));
        member.setWeight(Double.parseDouble(jTextFieldWeight.getText()));
        member.setHeight(
        Double.parseDouble(jTextFieldHeight.getText()));
        member.setPhone(jTextFieldNomber.getText());
        member.setEmail(jTextFieldEmail.getText());
        MembershipPackage membershipPackage =(MembershipPackage)jComboBoxMembershipPackage.getSelectedItem();
        member.setMembershipPackage(membershipPackage);

        return member;

    }
    
    
    
    private void setMemberToForm(Member member) {
        jTextFieldName.setText(member.getName());
        jTextFieldBirthDate.setText(member.getBirthDate().toString());
        jTextFieldWeight.setText(String.valueOf(member.getWeight()));
        jTextFieldHeight.setText(String.valueOf(member.getHeight()));
        jTextFieldNomber.setText(member.getPhone());
        jTextFieldEmail.setText(member.getEmail());
        if (member.getGender().equals("Male")) {

            jRadioButtonMale.setSelected(true);

        } else {

            jRadioButtonFemale.setSelected(true);

        }
        jComboBoxMembershipPackage.setSelectedItem(member.getMembershipPackage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jRadioButtonMale = new javax.swing.JRadioButton();
        jRadioButtonFemale = new javax.swing.JRadioButton();
        jTextFieldBirthDate = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldHeight = new javax.swing.JTextField();
        jTextFieldWeight = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNomber = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxMembershipPackage = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMember = new javax.swing.JTable();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jButtonPrevv = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();
        jLabelNoPage = new javax.swing.JLabel();
        jButtonBack = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("MEMBER");

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Name");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Gender");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Birth Date");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Email");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Height (Cm)");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));

        jTextFieldName.setBackground(new java.awt.Color(0, 0, 0));
        jTextFieldName.setForeground(new java.awt.Color(255, 255, 255));

        buttonGroup1.add(jRadioButtonMale);
        jRadioButtonMale.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButtonMale.setText("Male");
        jRadioButtonMale.addActionListener(this::jRadioButtonMaleActionPerformed);

        buttonGroup1.add(jRadioButtonFemale);
        jRadioButtonFemale.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButtonFemale.setText("Female");
        jRadioButtonFemale.addActionListener(this::jRadioButtonFemaleActionPerformed);

        jTextFieldBirthDate.setBackground(new java.awt.Color(0, 0, 0));
        jTextFieldBirthDate.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldBirthDate.setText("yyyy-mm-dd");
        jTextFieldBirthDate.addActionListener(this::jTextFieldBirthDateActionPerformed);

        jTextFieldEmail.setBackground(new java.awt.Color(0, 0, 0));
        jTextFieldEmail.setForeground(new java.awt.Color(255, 255, 255));

        jTextFieldHeight.setBackground(new java.awt.Color(0, 0, 0));
        jTextFieldHeight.setForeground(new java.awt.Color(255, 255, 255));

        jTextFieldWeight.setBackground(new java.awt.Color(0, 0, 0));
        jTextFieldWeight.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldWeight.addActionListener(this::jTextFieldWeightActionPerformed);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Phone");

        jTextFieldNomber.setBackground(new java.awt.Color(0, 0, 0));
        jTextFieldNomber.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Weight (Kg)");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Membership package");

        jComboBoxMembershipPackage.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBirthDate, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jRadioButtonMale)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonFemale))
                    .addComponent(jTextFieldNomber))
                .addGap(119, 119, 119)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                        .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldWeight, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(jTextFieldHeight, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(jComboBoxMembershipPackage, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(68, 68, 68))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jRadioButtonMale))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jRadioButtonFemale))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(jTextFieldBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNomber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBoxMembershipPackage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/heart-beating.png"))); // NOI18N
        jLabel14.setText("jLabel14");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/gym rawr.jpg"))); // NOI18N
        jLabel13.setText("jLabel13");

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jTableMember.setBackground(new java.awt.Color(0, 0, 0));
        jTableMember.setForeground(new java.awt.Color(255, 255, 255));
        jTableMember.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Name", "Gender", "Birth Date", "Phone", "Email", "Height", "Weight", "Membership"
            }
        ));
        jTableMember.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMemberMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMember);
        if (jTableMember.getColumnModel().getColumnCount() > 0) {
            jTableMember.getColumnModel().getColumn(4).setResizable(false);
        }

        jTextFieldSearch.setBackground(new java.awt.Color(255, 0, 0));
        jTextFieldSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonSearch.setBackground(new java.awt.Color(255, 0, 0));
        jButtonSearch.setText("SEARCH");
        jButtonSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonSearch.addActionListener(this::jButtonSearchActionPerformed);

        jButtonPrevv.setBackground(new java.awt.Color(255, 0, 0));
        jButtonPrevv.setText("<<Prevv");
        jButtonPrevv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonPrevv.addActionListener(this::jButtonPrevvActionPerformed);

        jButtonNext.setBackground(new java.awt.Color(255, 0, 0));
        jButtonNext.setText("Next>>");
        jButtonNext.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonNext.addActionListener(this::jButtonNextActionPerformed);

        jLabelNoPage.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNoPage.setText("-");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(355, 355, 355)
                        .addComponent(jButtonPrevv, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabelNoPage)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 30, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jTextFieldSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPrevv, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNoPage)
                    .addComponent(jButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        jButtonBack.setBackground(new java.awt.Color(255, 0, 0));
        jButtonBack.setText("BACK");
        jButtonBack.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonBack.addActionListener(this::jButtonBackActionPerformed);

        jButtonUpdate.setBackground(new java.awt.Color(255, 0, 0));
        jButtonUpdate.setText("UPDATE");
        jButtonUpdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonUpdate.addActionListener(this::jButtonUpdateActionPerformed);

        jButtonDelete.setBackground(new java.awt.Color(255, 0, 0));
        jButtonDelete.setText("DELETE");
        jButtonDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonDelete.addActionListener(this::jButtonDeleteActionPerformed);

        jButtonSave.setBackground(new java.awt.Color(255, 0, 0));
        jButtonSave.setText("SAVE");
        jButtonSave.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonSave.addActionListener(this::jButtonSaveActionPerformed);

        jButtonCancel.setBackground(new java.awt.Color(255, 0, 0));
        jButtonCancel.setText("CANCEL");
        jButtonCancel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonCancel.addActionListener(this::jButtonCancelActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        if (selectedMember == null) {

            JOptionPane.showMessageDialog(this,"Silakan pilih data terlebih dahulu.");

            return;

        }

        int pilihan = JOptionPane.showConfirmDialog(this,"Yakin ingin menghapus data ini?","Konfirmasi",JOptionPane.YES_NO_OPTION);

        if (pilihan == JOptionPane.YES_OPTION) {

            memberController.delete(selectedMember.getMemberId());

            loadTable();

            clearForm();

            selectedMember = null;

            JOptionPane.showMessageDialog(this,"Data berhasil dihapus.");

        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
            try {

            Member member = getMemberFromForm();

            memberController.save(member);
            
            currentPage = 1;

            loadTable();

            clearForm();

            JOptionPane.showMessageDialog(this,"Data member berhasil disimpan.");

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(this,e.getMessage(),"Validasi Gagal",JOptionPane.WARNING_MESSAGE);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,"Terjadi kesalahan : " + e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        clearForm();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jTextFieldWeightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldWeightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldWeightActionPerformed

    private void jRadioButtonFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonFemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonFemaleActionPerformed

    private void jRadioButtonMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonMaleActionPerformed

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        if (currentPage < totalPage) {

            currentPage++;

            loadTable(memberList);

        }
    }//GEN-LAST:event_jButtonNextActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        if (selectedMember == null) {

            JOptionPane.showMessageDialog(this,"Silakan pilih data yang akan diperbarui.");

            return;

        }

        try {

            Member member = getMemberFromForm();

            member.setMemberId(selectedMember.getMemberId());

            memberController.update(member);

            loadTable();

            clearForm();

            selectedMember = null;

            JOptionPane.showMessageDialog(this,"Data berhasil diperbarui.");

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(this,e.getMessage(),"Validasi Gagal",JOptionPane.WARNING_MESSAGE);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,"Terjadi kesalahan : " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

         }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jTableMemberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMemberMouseClicked
        int row = jTableMember.getSelectedRow();

        if (row >= 0) {

            int index = (currentPage - 1) * dataPerPage + row;

            selectedMember = memberList.get(index);

            setMemberToForm(selectedMember);

        }
    }//GEN-LAST:event_jTableMemberMouseClicked

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
       String keyword =
        jTextFieldSearch.getText().trim();

        currentPage = 1;

        if (keyword.isEmpty()) {

            loadTable();

        } else {

            loadTable(
                    memberController.search(keyword));

        }
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jButtonPrevvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrevvActionPerformed
         if (currentPage > 1) {

            currentPage--;

            loadTable(memberList);

        }
    }//GEN-LAST:event_jButtonPrevvActionPerformed

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        new DashboardForm().setVisible(true);

            dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jTextFieldBirthDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBirthDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBirthDateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MemberForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrevv;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<MembershipPackage> jComboBoxMembershipPackage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelNoPage;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButtonFemale;
    private javax.swing.JRadioButton jRadioButtonMale;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMember;
    private javax.swing.JTextField jTextFieldBirthDate;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldHeight;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldNomber;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JTextField jTextFieldWeight;
    // End of variables declaration//GEN-END:variables
}
