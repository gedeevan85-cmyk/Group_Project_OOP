/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import javax.swing.JFrame;
import controller.FitnessClassController;
import controller.TrainerController;
import java.sql.Time;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.FitnessClass;
import model.Trainer;
/**
 *
 * @author user
 */
public class FitnessClassForm extends javax.swing.JFrame {
    private FitnessClassController fitnessClassController;
    private TrainerController trainerController;
    private List<FitnessClass> fitnessClassList;
    private FitnessClass selectedFitnessClass;
    private int currentPage = 1;
    private final int dataPerPage = 5;
    private int totalPage;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FitnessClassForm.class.getName());

    /**
     * Creates new form TrainerForm
     */
    public FitnessClassForm() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        fitnessClassController = new FitnessClassController();
        trainerController = new TrainerController();
        loadTrainer();
        loadTable();
        clearForm();
    }
    
    private void loadTrainer() {

        jComboBoxTrainer.removeAllItems();

        List<Trainer> trainers = trainerController.findAll();

        for (Trainer trainer : trainers) {

            jComboBoxTrainer.addItem(trainer);

        }

    }
    
    private void clearForm() {

        jComboBoxTrainer.setSelectedIndex(-1);

        jTextFieldClass.setText("");

        jComboBoxDay.setSelectedIndex(0);

        jTextFieldTime.setText("00:00:00");

        jTextFielDuration.setText("");

        jTextFieldCapasity.setText("");

        jTextFieldSearch.setText("");

        jTableFitnessClass.clearSelection();

        selectedFitnessClass = null;

    }
    
    private FitnessClass getFitnessClassFromForm() {

        FitnessClass fitnessClass = new FitnessClass();

        fitnessClass.setTrainer(
                (Trainer) jComboBoxTrainer.getSelectedItem());

        fitnessClass.setClassName(
                jTextFieldClass.getText().trim());

        fitnessClass.setScheduleDay(
                jComboBoxDay.getSelectedItem().toString());

        fitnessClass.setScheduleTime(
                Time.valueOf(jTextFieldTime.getText().trim()));

        fitnessClass.setDurationMinute(
                Integer.parseInt(jTextFielDuration.getText()));

        fitnessClass.setCapacity(
                Integer.parseInt(jTextFieldCapasity.getText()));

        return fitnessClass;

    }
    
    private void setFitnessClassToForm(FitnessClass fitnessClass) {

        jComboBoxTrainer.setSelectedItem(
                fitnessClass.getTrainer());

        jTextFieldClass.setText(
                fitnessClass.getClassName());

        jComboBoxDay.setSelectedItem(
                fitnessClass.getScheduleDay());

        jTextFieldTime.setText(
                fitnessClass.getScheduleTime().toString());

        jTextFielDuration.setText(
                String.valueOf(
                        fitnessClass.getDurationMinute()));

        jTextFieldCapasity.setText(
                String.valueOf(
                        fitnessClass.getCapacity()));

    }
    
    private void loadTable() {

        loadTable(fitnessClassController.findAll());

    }
    
    private void loadTable(List<FitnessClass> fitnessClasses) {

        DefaultTableModel model =
                (DefaultTableModel) jTableFitnessClass.getModel();

        model.setRowCount(0);

        fitnessClassList = fitnessClasses;

        totalPage = (int) Math.ceil(
                (double) fitnessClassList.size() / dataPerPage);

        if (totalPage == 0) {

            totalPage = 1;

        }

        if (currentPage > totalPage) {

            currentPage = totalPage;

        }

        int start = (currentPage - 1) * dataPerPage;

        int end = Math.min(
                start + dataPerPage,
                fitnessClassList.size());

        int no = start + 1;

        for (int i = start; i < end; i++) {

            FitnessClass fitnessClass =
                    fitnessClassList.get(i);

            model.addRow(new Object[]{

                no++,
                fitnessClass.getTrainer().getName(),
                fitnessClass.getClassName(),
                fitnessClass.getScheduleDay(),
                fitnessClass.getScheduleTime(),
                fitnessClass.getDurationMinute(),
                fitnessClass.getCapacity()

            });

        }

        jLabelNoPage.setText(
                "Halaman "
                + currentPage
                + " / "
                + totalPage);

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
        jTextFielDuration = new javax.swing.JTextField();
        jTextFieldCapasity = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldTime = new javax.swing.JTextField();
        jComboBoxTrainer = new javax.swing.JComboBox<>();
        jTextFieldClass = new javax.swing.JTextField();
        jComboBoxDay = new javax.swing.JComboBox<>();
        jButtonCancel = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButtonUpdate = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFitnessClass = new javax.swing.JTable();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jButtonPrevv = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();
        jLabelNoPage = new javax.swing.JLabel();
        jButtonBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("FITNESS CLASS");

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Trainer");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Class");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Day");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Duration (Minute)");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Capasity");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));

        jTextFielDuration.setBackground(new java.awt.Color(0, 0, 0));
        jTextFielDuration.setForeground(new java.awt.Color(255, 255, 255));

        jTextFieldCapasity.setBackground(new java.awt.Color(0, 0, 0));
        jTextFieldCapasity.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Time");

        jTextFieldTime.setBackground(new java.awt.Color(0, 0, 0));
        jTextFieldTime.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldTime.setText("00:00");

        jComboBoxTrainer.setBackground(new java.awt.Color(0, 0, 0));
        jComboBoxTrainer.setForeground(new java.awt.Color(255, 255, 255));

        jTextFieldClass.setBackground(new java.awt.Color(0, 0, 0));
        jTextFieldClass.setForeground(new java.awt.Color(255, 255, 255));

        jComboBoxDay.setBackground(new java.awt.Color(0, 0, 0));
        jComboBoxDay.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxTrainer, 0, 221, Short.MAX_VALUE)
                    .addComponent(jTextFieldClass)
                    .addComponent(jComboBoxDay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(142, 142, 142)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9))
                            .addGap(68, 68, 68)
                            .addComponent(jTextFieldCapasity, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(18, 18, 18)
                            .addComponent(jTextFielDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldTime, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxTrainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFielDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(15, 15, 15)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jTextFieldCapasity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jButtonCancel.setBackground(new java.awt.Color(255, 0, 0));
        jButtonCancel.setText("CANCEL");
        jButtonCancel.setBorder(null);
        jButtonCancel.addActionListener(this::jButtonCancelActionPerformed);

        jButtonDelete.setBackground(new java.awt.Color(255, 0, 0));
        jButtonDelete.setText("DELETE");
        jButtonDelete.setBorder(null);
        jButtonDelete.addActionListener(this::jButtonDeleteActionPerformed);

        jButtonSave.setBackground(new java.awt.Color(255, 0, 0));
        jButtonSave.setText("SAVE");
        jButtonSave.setBorder(null);
        jButtonSave.addActionListener(this::jButtonSaveActionPerformed);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/heart-beating.png"))); // NOI18N
        jLabel14.setText("jLabel14");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/gym rawr.jpg"))); // NOI18N
        jLabel13.setText("jLabel13");

        jButtonUpdate.setBackground(new java.awt.Color(255, 0, 0));
        jButtonUpdate.setText("UPDATE");
        jButtonUpdate.setBorder(null);
        jButtonUpdate.addActionListener(this::jButtonUpdateActionPerformed);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jTableFitnessClass.setBackground(new java.awt.Color(0, 0, 0));
        jTableFitnessClass.setForeground(new java.awt.Color(255, 255, 255));
        jTableFitnessClass.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Trainer", "Class", "Day", "Time", "Duration", "Capasity"
            }
        ));
        jTableFitnessClass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFitnessClassMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableFitnessClass);
        if (jTableFitnessClass.getColumnModel().getColumnCount() > 0) {
            jTableFitnessClass.getColumnModel().getColumn(4).setResizable(false);
        }

        jTextFieldSearch.setBackground(new java.awt.Color(255, 0, 0));
        jTextFieldSearch.setText("Search");
        jTextFieldSearch.setBorder(null);

        jButtonSearch.setBackground(new java.awt.Color(255, 0, 0));
        jButtonSearch.setText("SEARCH");
        jButtonSearch.setBorder(null);
        jButtonSearch.addActionListener(this::jButtonSearchActionPerformed);

        jButtonPrevv.setBackground(new java.awt.Color(255, 0, 0));
        jButtonPrevv.setText("<<Prev");
        jButtonPrevv.setBorder(null);
        jButtonPrevv.addActionListener(this::jButtonPrevvActionPerformed);

        jButtonNext.setBackground(new java.awt.Color(255, 0, 0));
        jButtonNext.setText("Next>>");
        jButtonNext.setBorder(null);
        jButtonNext.addActionListener(this::jButtonNextActionPerformed);

        jLabelNoPage.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNoPage.setText("jLabel1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(355, 355, 355)
                        .addComponent(jButtonPrevv, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabelNoPage)
                        .addGap(30, 30, 30)
                        .addComponent(jButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 902, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPrevv)
                    .addComponent(jButtonNext)
                    .addComponent(jLabelNoPage))
                .addGap(33, 33, 33))
        );

        jButtonBack.setBackground(new java.awt.Color(255, 0, 0));
        jButtonBack.setText("BACK");
        jButtonBack.setBorder(null);
        jButtonBack.addActionListener(this::jButtonBackActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(35, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(225, 225, 225))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonUpdate)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonSave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBack)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        if (selectedFitnessClass == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Silakan pilih data terlebih dahulu.");

            return;

        }

        int pilihan = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus data ini?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION);

        if (pilihan == JOptionPane.YES_OPTION) {

            fitnessClassController.delete(
                    selectedFitnessClass.getClassId());

            loadTable();

            clearForm();

            selectedFitnessClass = null;

            JOptionPane.showMessageDialog(
                    this,
                    "Data kelas fitness berhasil dihapus.");

        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
       try {

            FitnessClass fitnessClass = getFitnessClassFromForm();

            fitnessClassController.save(fitnessClass);

            currentPage = 1;

            loadTable();

            clearForm();

            JOptionPane.showMessageDialog(
                    this,
                    "Data kelas fitness berhasil disimpan.");

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
       clearForm();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        if (currentPage < totalPage) {

            currentPage++;

            loadTable(fitnessClassList);

        }
    }//GEN-LAST:event_jButtonNextActionPerformed

    private void jTableFitnessClassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFitnessClassMouseClicked
        int row = jTableFitnessClass.getSelectedRow();

        if (row >= 0) {

            int index =
                    (currentPage - 1) * dataPerPage + row;

            selectedFitnessClass =
                    fitnessClassList.get(index);

            setFitnessClassToForm(
                    selectedFitnessClass);

        }
    }//GEN-LAST:event_jTableFitnessClassMouseClicked

    private void jButtonPrevvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrevvActionPerformed
        if (currentPage > 1) {

            currentPage--;

            loadTable(fitnessClassList);

        }
    }//GEN-LAST:event_jButtonPrevvActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        if (selectedFitnessClass == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Silakan pilih data yang akan diperbarui.");

            return;

        }

        try {

            FitnessClass fitnessClass = getFitnessClassFromForm();

            fitnessClass.setClassId(
                    selectedFitnessClass.getClassId());

            fitnessClassController.update(fitnessClass);

            loadTable();

            clearForm();

            selectedFitnessClass = null;

            JOptionPane.showMessageDialog(
                    this,
                    "Data kelas fitness berhasil diperbarui.");

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

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
       String keyword =
        jTextFieldSearch.getText().trim();

        currentPage = 1;

        if (keyword.isEmpty()) {

            loadTable();

        } else {

            loadTable(
                    fitnessClassController.search(keyword));

        }
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        new DashboardForm().setVisible(true);

            dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new FitnessClassForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrevv;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<String> jComboBoxDay;
    private javax.swing.JComboBox<Trainer> jComboBoxTrainer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelNoPage;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFitnessClass;
    private javax.swing.JTextField jTextFielDuration;
    private javax.swing.JTextField jTextFieldCapasity;
    private javax.swing.JTextField jTextFieldClass;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JTextField jTextFieldTime;
    // End of variables declaration//GEN-END:variables
}
