/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.sql.Date;
import controller.EnrollmentController;
import controller.MemberController;
import controller.FitnessClassController;
import model.Enrollment;
import model.Member;
import model.FitnessClass;
import model.workoutprogram.BulkingProgram;
import model.workoutprogram.CuttingProgram;
import model.workoutprogram.WeightLossProgram;
import model.workoutprogram.MuscleGainProgram;
import model.workoutprogram.WorkoutProgram;

/**
 *
 * @author user
 */
public class EnrollmentForm extends javax.swing.JFrame {
    private EnrollmentController enrollmentController;
    private MemberController memberController;
    private FitnessClassController fitnessClassController;
    private List<Enrollment> enrollmentList;
    private Enrollment selectedEnrollment;
    private int currentPage = 1;
    private final int dataPerPage = 5;
    private int totalPage;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EnrollmentForm.class.getName());

    /**
     * Creates new form TrainerForm
     */
    public EnrollmentForm() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        enrollmentController = new EnrollmentController();

        memberController = new MemberController();

        fitnessClassController = new FitnessClassController();

        loadMember();

        loadFitnessClass();

        loadTable();

        clearForm();
    }
    
    private void loadMember() {

        jComboMember.removeAllItems();

        List<Member> members =
                memberController.findAll();

        for (Member member : members) {

            jComboMember.addItem(member);

        }

    }
    
    private void loadFitnessClass() {

        jComboBoxClass.removeAllItems();

        List<FitnessClass> classes =
                fitnessClassController.findAll();

        for (FitnessClass fitnessClass : classes) {

            jComboBoxClass.addItem(fitnessClass);

        }

    }
    
    private void clearForm() {

        jComboMember.setSelectedIndex(-1);

        jComboBoxClass.setSelectedIndex(-1);

        jComboBoxWorkoutProgram.setSelectedIndex(0);

        jComboBoxStatus.setSelectedIndex(0);

        jTextFieldRegistrationDate.setText("");

        jLabelMembership.setText("");

        jLabelTrainer.setText("");

        jLabelAge.setText("");

        jLabelBMI.setText("");

        jLabelBMR.setText("");

        jLabelCalorieTarget.setText("");
        
        jLabelCalorieTarget.setText("");

        jTableFitnessEnrollment.clearSelection();

        selectedEnrollment = null;

    }
    
    private Enrollment getEnrollmentFromForm() {

        Enrollment enrollment = new Enrollment();

        enrollment.setMember(
                (Member) jComboMember.getSelectedItem());

        enrollment.setFitnessClass(
                (FitnessClass) jComboBoxClass.getSelectedItem());

        String goal =
                jComboBoxWorkoutProgram.getSelectedItem().toString();

        switch (goal) {

            case "Bulking":
                enrollment.setWorkoutProgram(new BulkingProgram());
                break;

            case "Cutting":
                enrollment.setWorkoutProgram(new CuttingProgram());
                break;

            case "Weight Loss":
                enrollment.setWorkoutProgram(new WeightLossProgram());
                break;

            case "Muscle Gain":
                enrollment.setWorkoutProgram(new MuscleGainProgram());
                break;

        }

        enrollment.setEnrollmentDate(
                Date.valueOf(jTextFieldRegistrationDate.getText().trim()));

        enrollment.setStatus(
                jComboBoxStatus.getSelectedItem().toString());

        return enrollment;

    }
    
    private void setEnrollmentToForm(Enrollment enrollment) {

        jComboMember.setSelectedItem(
                enrollment.getMember());

        jComboBoxClass.setSelectedItem(
                enrollment.getFitnessClass());

        String goal = "";

        if (enrollment.getWorkoutProgram() instanceof BulkingProgram) {

            goal = "Bulking";

        } else if (enrollment.getWorkoutProgram() instanceof CuttingProgram) {

            goal = "Cutting";

        } else if (enrollment.getWorkoutProgram() instanceof WeightLossProgram) {

            goal = "Weight Loss";

        } else if (enrollment.getWorkoutProgram() instanceof MuscleGainProgram) {

            goal = "Muscle Gain";

        }

        jComboBoxWorkoutProgram.setSelectedItem(goal);

        jTextFieldRegistrationDate.setText(
                enrollment.getEnrollmentDate().toString());

        jComboBoxStatus.setSelectedItem(
                enrollment.getStatus());

    }
    
    private void loadTable() {

        loadTable(enrollmentController.findAll());

    }
    
    private void loadTable(List<Enrollment> enrollments) {

        DefaultTableModel model =
                (DefaultTableModel) jTableFitnessEnrollment.getModel();

        model.setRowCount(0);

        enrollmentList = enrollments;

        totalPage = (int) Math.ceil(
                (double) enrollmentList.size() / dataPerPage);

        if (totalPage == 0) {

            totalPage = 1;

        }

        if (currentPage > totalPage) {

            currentPage = totalPage;

        }

        int start = (currentPage - 1) * dataPerPage;

        int end = Math.min(
                start + dataPerPage,
                enrollmentList.size());

        int no = start + 1;

        for (int i = start; i < end; i++) {

            Enrollment enrollment = enrollmentList.get(i);

            String goal = "";

            if (enrollment.getWorkoutProgram() instanceof BulkingProgram) {

                goal = "Bulking";

            } else if (enrollment.getWorkoutProgram() instanceof CuttingProgram) {

                goal = "Cutting";

            } else if (enrollment.getWorkoutProgram() instanceof WeightLossProgram) {

                goal = "Weight Loss";

            } else if (enrollment.getWorkoutProgram() instanceof MuscleGainProgram) {

                goal = "Muscle Gain";

            }

            model.addRow(new Object[]{

                no++,

                enrollment.getMember().getName(),

                enrollment.getMember()
                        .getMembershipPackage()
                        .getPackageName(),

                enrollment.getFitnessClass().getClassName(),

                enrollment.getFitnessClass()
                        .getTrainer()
                        .getName(),

                enrollment.getEnrollmentDate(),

                enrollment.getStatus(),

                enrollmentController.calculateAge(
                        enrollment.getMember()),

                String.format("%.2f",
                        enrollmentController.calculateBMI(
                                enrollment.getMember())),

                String.format("%.2f",
                        enrollmentController.calculateBMR(
                                enrollment.getMember())),

                goal,

                String.format("%.2f",
                        enrollment.getTargetCalories())

            });

        }

        jLabelNoPage.setText(
                "Halaman "
                + currentPage
                + " / "
                + totalPage);

    }
    
    private void updateMemberInformation() {

        Member member = (Member) jComboMember.getSelectedItem();
        
        if (member == null) {
            return;
        }

        if (member.getMembershipPackage() == null) {

            System.out.println("Membership NULL");

            return;

        }

        if (member == null) {
            return;
        }

        jLabelMembership.setText(
                member.getMembershipPackage().getPackageName());

        jLabelAge.setText(
                String.valueOf(
                        enrollmentController.calculateAge(member)));

        jLabelBMI.setText(
                String.format("%.2f",
                        enrollmentController.calculateBMI(member)));

        jLabelBMR.setText(
                String.format("%.2f",
                        enrollmentController.calculateBMR(member)));

    }
    
    private void updateFitnessClassInformation() {

        FitnessClass fitnessClass =
                (FitnessClass) jComboBoxClass.getSelectedItem();

        if (fitnessClass == null) {
            return;
        }

        jLabelTrainer.setText(
                fitnessClass.getTrainer().getName());

    }
    
    private void updateTargetCalories() {

        Member member =
                (Member) jComboMember.getSelectedItem();

        if (member == null) {

            jLabelCalorieTarget.setText("");

            return;

        }

        String goal =
                jComboBoxWorkoutProgram.getSelectedItem().toString();

        WorkoutProgram workoutProgram = null;

        switch (goal) {

            case "Bulking":
                workoutProgram = new BulkingProgram();
                break;

            case "Cutting":
                workoutProgram = new CuttingProgram();
                break;

            case "Weight Loss":
                workoutProgram = new WeightLossProgram();
                break;

            case "Muscle Gain":
                workoutProgram = new MuscleGainProgram();
                break;

        }

        double bmr = enrollmentController.calculateBMR(member);
        
        double calories = enrollmentController.calculateTargetCalories(
                    bmr,
                    workoutProgram);

        jLabelCalorieTarget.setText(String.format("%.2f kcal", calories));

    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboMember = new javax.swing.JComboBox<>();
        jComboBoxClass = new javax.swing.JComboBox<>();
        jLabelMembership = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelTrainer = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldRegistrationDate = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jComboBoxStatus = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabelAge = new javax.swing.JLabel();
        jLabelBMI = new javax.swing.JLabel();
        jLabelBMR = new javax.swing.JLabel();
        jLabelCalorieTarget = new javax.swing.JLabel();
        jComboBoxWorkoutProgram = new javax.swing.JComboBox<>();
        jButtonCancel = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButtonUpdate = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFitnessEnrollment = new javax.swing.JTable();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jButtonPrevv = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();
        jLabelNoPage = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("ENROLLMENT");

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Member");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Membership");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Class");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("BMI");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("BMR");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Age");

        jComboMember.setBackground(new java.awt.Color(0, 0, 0));
        jComboMember.setForeground(new java.awt.Color(255, 255, 255));
        jComboMember.addActionListener(this::jComboMemberActionPerformed);

        jComboBoxClass.setBackground(new java.awt.Color(0, 0, 0));
        jComboBoxClass.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxClass.addActionListener(this::jComboBoxClassActionPerformed);

        jLabelMembership.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMembership.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Trainer");

        jLabelTrainer.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTrainer.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Registration Date");

        jTextFieldRegistrationDate.setBackground(new java.awt.Color(0, 0, 0));
        jTextFieldRegistrationDate.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldRegistrationDate.setText("dd/mm/yyyy");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Status");

        jComboBoxStatus.setBackground(new java.awt.Color(0, 0, 0));
        jComboBoxStatus.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Completed", "Cancelled" }));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Workout Program");

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Calorie Target");

        jLabelAge.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAge.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabelBMI.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBMI.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabelBMR.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBMR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabelCalorieTarget.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCalorieTarget.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jComboBoxWorkoutProgram.setBackground(new java.awt.Color(0, 0, 0));
        jComboBoxWorkoutProgram.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bulking", "Cutting", "Weight Loss", "Muscle Gain" }));
        jComboBoxWorkoutProgram.addActionListener(this::jComboBoxWorkoutProgramActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboMember, 0, 221, Short.MAX_VALUE)
                            .addComponent(jComboBoxClass, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelMembership, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTrainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxStatus, 0, 222, Short.MAX_VALUE)
                            .addComponent(jTextFieldRegistrationDate))))
                .addGap(142, 142, 142)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(88, 88, 88)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelBMI, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelBMR, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelAge, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCalorieTarget, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxWorkoutProgram, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAge, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jComboMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabelBMI, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel9))
                            .addComponent(jLabelBMR, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jComboBoxWorkoutProgram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabelMembership, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBoxClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabelTrainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldRegistrationDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelCalorieTarget, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jButtonCancel.setBackground(new java.awt.Color(255, 0, 0));
        jButtonCancel.setText("CANCEL");
        jButtonCancel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonCancel.addActionListener(this::jButtonCancelActionPerformed);

        jButtonSave.setBackground(new java.awt.Color(255, 0, 0));
        jButtonSave.setText("SAVE");
        jButtonSave.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonSave.addActionListener(this::jButtonSaveActionPerformed);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/heart-beating.png"))); // NOI18N
        jLabel14.setText("jLabel14");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/gym rawr.jpg"))); // NOI18N
        jLabel13.setText("jLabel13");

        jButtonUpdate.setBackground(new java.awt.Color(255, 0, 0));
        jButtonUpdate.setText("UPDATE");
        jButtonUpdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonUpdate.addActionListener(this::jButtonUpdateActionPerformed);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jTableFitnessEnrollment.setBackground(new java.awt.Color(0, 0, 0));
        jTableFitnessEnrollment.setForeground(new java.awt.Color(255, 255, 255));
        jTableFitnessEnrollment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Member", "Membership", "Class", "Trainer", "Registration ", "Status", "Age", "BMI", "BMR", "Program", "Calorie"
            }
        ));
        jTableFitnessEnrollment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFitnessEnrollmentMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableFitnessEnrollment);
        if (jTableFitnessEnrollment.getColumnModel().getColumnCount() > 0) {
            jTableFitnessEnrollment.getColumnModel().getColumn(4).setResizable(false);
        }

        jTextFieldSearch.setBackground(new java.awt.Color(255, 0, 0));
        jTextFieldSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonSearch.setBackground(new java.awt.Color(255, 0, 0));
        jButtonSearch.setText("SEARCH");
        jButtonSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonSearch.addActionListener(this::jButtonSearchActionPerformed);

        jButtonPrevv.setBackground(new java.awt.Color(255, 0, 0));
        jButtonPrevv.setText("<<Prev");
        jButtonPrevv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonPrevv.addActionListener(this::jButtonPrevvActionPerformed);

        jButtonNext.setBackground(new java.awt.Color(255, 0, 0));
        jButtonNext.setText("Next>>");
        jButtonNext.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonNext.addActionListener(this::jButtonNextActionPerformed);

        jLabelNoPage.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNoPage.setText("- ");

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setText("BACK");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(355, 355, 355)
                        .addComponent(jButtonPrevv, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabelNoPage)
                        .addGap(30, 30, 30)
                        .addComponent(jButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 901, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPrevv, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNoPage))
                .addGap(34, 34, 34)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jButtonDelete.setBackground(new java.awt.Color(255, 0, 0));
        jButtonDelete.setText("DELETE");
        jButtonDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButtonDelete.addActionListener(this::jButtonDeleteActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        if (selectedEnrollment == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data terlebih dahulu.");

            return;

        }

        int pilihan = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus enrollment ini?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION);

        if (pilihan == JOptionPane.YES_OPTION) {

            enrollmentController.delete(
                    selectedEnrollment.getEnrollmentId());

            loadTable();

            clearForm();

            selectedEnrollment = null;

            JOptionPane.showMessageDialog(
                    this,
                    "Enrollment berhasil dihapus.");

        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        try {

            Enrollment enrollment = getEnrollmentFromForm();

            enrollmentController.save(enrollment);

            currentPage = 1;

            loadTable();

            clearForm();

            JOptionPane.showMessageDialog(
                    this,
                    "Enrollment berhasil disimpan.");

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Validasi Gagal",
                    JOptionPane.WARNING_MESSAGE);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Terjadi kesalahan : " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
       jTextFieldSearch.setText("");

        clearForm();

        currentPage = 1;

        loadTable();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        if (currentPage < totalPage) {

            currentPage++;

            loadTable(enrollmentList);

        }
    }//GEN-LAST:event_jButtonNextActionPerformed

    private void jTableFitnessEnrollmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFitnessEnrollmentMouseClicked
        int row =
            jTableFitnessEnrollment.getSelectedRow();

        if (row >= 0) {

            int index =
                    (currentPage - 1)
                    * dataPerPage
                    + row;

            selectedEnrollment =
                    enrollmentList.get(index);

            setEnrollmentToForm(
                    selectedEnrollment);
            
            updateTargetCalories();

        }
    }//GEN-LAST:event_jTableFitnessEnrollmentMouseClicked

    private void jButtonPrevvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrevvActionPerformed
        if (currentPage > 1) {

            currentPage--;

            loadTable(enrollmentList);

        }
    }//GEN-LAST:event_jButtonPrevvActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        if (selectedEnrollment == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data yang akan diperbarui.");

            return;

        }

        try {

            Enrollment enrollment = getEnrollmentFromForm();

            enrollment.setEnrollmentId(
                    selectedEnrollment.getEnrollmentId());

            enrollmentController.update(enrollment);

            loadTable();

            clearForm();

            selectedEnrollment = null;

            JOptionPane.showMessageDialog(
                    this,
                    "Enrollment berhasil diperbarui.");

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Validasi Gagal",
                    JOptionPane.WARNING_MESSAGE);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Terjadi kesalahan : " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jComboMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboMemberActionPerformed
       updateMemberInformation();
       updateMemberInformation();
    }//GEN-LAST:event_jComboMemberActionPerformed

    private void jComboBoxClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxClassActionPerformed
        updateFitnessClassInformation();
    }//GEN-LAST:event_jComboBoxClassActionPerformed

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
      String keyword =
        jTextFieldSearch.getText().trim();

        currentPage = 1;

        if (keyword.isEmpty()) {

            loadTable();

        } else {

            loadTable(
                    enrollmentController.search(keyword));

        }
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jComboBoxWorkoutProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxWorkoutProgramActionPerformed
        updateTargetCalories();
    }//GEN-LAST:event_jComboBoxWorkoutProgramActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new DashboardForm().setVisible(true);

            dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new EnrollmentForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrevv;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<FitnessClass> jComboBoxClass;
    private javax.swing.JComboBox<String> jComboBoxStatus;
    private javax.swing.JComboBox<String> jComboBoxWorkoutProgram;
    private javax.swing.JComboBox<Member> jComboMember;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAge;
    private javax.swing.JLabel jLabelBMI;
    private javax.swing.JLabel jLabelBMR;
    private javax.swing.JLabel jLabelCalorieTarget;
    private javax.swing.JLabel jLabelMembership;
    private javax.swing.JLabel jLabelNoPage;
    private javax.swing.JLabel jLabelTrainer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFitnessEnrollment;
    private javax.swing.JTextField jTextFieldRegistrationDate;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}
