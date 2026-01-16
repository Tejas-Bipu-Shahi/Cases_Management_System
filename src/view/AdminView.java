/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

/**
 *
 * @author Tejas Shahi
 */
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import model.Case;
import model.CivilCase;
import model.CriminalCase;
import controller.CaseController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Judge;
import controller.JudgeController;
import controller.CaseStack;
import controller.CaseQueue;
import javax.swing.JOptionPane;

public class AdminView extends javax.swing.JFrame {

    //connect with controller
    CaseController controller = new CaseController();

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminView.class.getName());

    /**
     * Creates new form AdminView
     */
    public AdminView() {
        initComponents();
        loadRegisteredCases();
        loadJudgesToComboBox();
        updateDashboardStats();
        loadDeletedTable();
        loadQueueTable();
    }

    public void updateDashboardStats() {
        // 1. Get the lists
        int total = controller.getAllCases().size();
        int pending = 0;
        int closed = 0;

        // 2. Loop to count specific statuses
        for (model.Case c : controller.getAllCases()) {
            if (c.getCaseStatus().equalsIgnoreCase("Closed")) {
                closed++;
            } else {
                pending++; // Assuming anything not closed is "Pending/Active"
            }
        }

        // 3. Update the UI Labels
        lblTotalCount.setText(String.valueOf(total));
        lblPendingCount.setText(String.valueOf(pending));
        lblClosedCount.setText(String.valueOf(closed));
    }

    public void loadQueueTable() {
        // 1. Get the Custom Queue from Controller
        CaseQueue queue = controller.getHearingQueue();

        // 2. Get the Dashboard Table Model
        DefaultTableModel model = (DefaultTableModel) dashboardTable.getModel();
        model.setRowCount(0); // Clear old data

        // 3. Loop through the Custom Queue
        for (int i = 0; i < queue.size(); i++) {

            // Use peek(i) to look at data without removing it
            model.Case c = queue.peek(i);

            if (c != null) {
                model.addRow(new Object[]{
                    c.getCaseId(),
                    c.getRegistrationNumber(),
                    c.getCaseTitle(),
                    c.getCaseType(),
                    c.getAssignedJudge(),
                    "UPCOMING",
                    c.getHearingDate()
                });
            }
        }
    }

    //Load Deleted data in deleted table 
    public void loadDeletedTable() {
        CaseStack deletedStack = controller.getDeletedCases();

        DefaultTableModel model = (DefaultTableModel) tblDeleted.getModel();
        model.setRowCount(0); // Clear old data

        // Iterate BACKWARDS to show the most recently deleted item first
        for (int i = deletedStack.size() - 1; i >= 0; i--) {

            Case c = deletedStack.get(i);

            if (c != null) {
                model.addRow(new Object[]{
                    c.getCaseId(),
                    c.getCaseTitle(),
                    c.getCaseType(),
                    "Deleted"
                });
            }
        }
    }

    //load data in the table
    // Only loads the big "Registered Cases" table
    public void loadRegisteredCases() {
        DefaultTableModel model = (DefaultTableModel) totalRegisteredCasesTable.getModel();
        model.setRowCount(0); // Clear table

        // Get the list of cases from linked list
        LinkedList<Case> allCases = controller.getAllCases();

        for (Case c : allCases) {

            model.addRow(new Object[]{
                c.getCaseId(),
                c.getRegistrationNumber(),
                c.getCaseTitle(),
                c.getCaseType(),
                c.getAssignedJudge(),
                c.getCaseStatus(),
                c.getHearingDate(),
                c.getFilingDate()
            });
        }
    }

    // Method to load judges into the "Assign Judge" dropdown
    public void loadJudgesToComboBox() {
        controller.JudgeController jc = new controller.JudgeController();
        ArrayList<model.Judge> judgeList = jc.getAllJudges();

        // Remove old items
        cmbJudge.removeAllItems();
        rcmbJudge.removeAllItems();
        cmbFilterByJudge.removeAllItems();
        cmbFilterByJudge.addItem("All");

        for (model.Judge j : judgeList) {
            rcmbJudge.addItem(j.getName());
            cmbJudge.addItem(j.getName());
            cmbFilterByJudge.addItem(j.getName());
        }
    }

    // Helper to calculate the next hearing number
    private String getNextHearingStep(String currentStatus) {
        if (currentStatus == null || currentStatus.equalsIgnoreCase("Open")) {
            return "First";
        }
        if (currentStatus.equalsIgnoreCase("First")) {
            return "Second";
        }
        if (currentStatus.equalsIgnoreCase("Second")) {
            return "Third";
        }
        if (currentStatus.equalsIgnoreCase("Third")) {
            return "Fourth";
        }
        if (currentStatus.equalsIgnoreCase("Fourth")) {
            return "Fifth";
        }

        return "Final"; // Stops at Final
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
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lblTotalCount = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblPendingCount = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lblClosedCount = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dashboardTable = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        cmbSortBy = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        cmbFilterByJudge = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        totalRegisteredCasesTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCaseId = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtRegNo = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtCaseTitle = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSubjectMatter = new javax.swing.JTextArea();
        jLabel20 = new javax.swing.JLabel();
        cmbJudge = new javax.swing.JComboBox<>();
        txtHearingDate = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtDisputeType = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtClaimAmount = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtReliefSought = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        rtxtCaseId = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        rtxtRegisterationNumber = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        rtxtCaseTitle = new javax.swing.JTextField();
        jLabel107 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        rtxtSubjectMatter = new javax.swing.JTextArea();
        jLabel108 = new javax.swing.JLabel();
        rcmbJudge = new javax.swing.JComboBox<>();
        rtxtHearingDate = new javax.swing.JTextField();
        jLabel109 = new javax.swing.JLabel();
        registerbutton = new javax.swing.JButton();
        rtxtCrimeType = new javax.swing.JTextField();
        jLabel110 = new javax.swing.JLabel();
        rtxtPoliceStation = new javax.swing.JTextField();
        jLabel111 = new javax.swing.JLabel();
        rtxtFirNumber = new javax.swing.JTextField();
        jLabel112 = new javax.swing.JLabel();
        rchkBailStatus = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtCaseId1 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        cmbJudge1 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        txtRelief1 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtClaimAmount1 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        txtHearing1 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtCaseId3 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        cmbJudge3 = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        txtClaimAmount3 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        rchkBailStatus1 = new javax.swing.JComboBox<>();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDeleted = new javax.swing.JTable();
        btnClear = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel23 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jButton14 = new javax.swing.JButton();
        jLabel68 = new javax.swing.JLabel();
        txtJudgeId = new javax.swing.JTextField();
        txtJudgeFirstName = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        txtJudgeLastName = new javax.swing.JTextField();
        txtJudgeContact = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        txtJudgeUsername = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        txtJudgeConfirmPass = new javax.swing.JPasswordField();
        txtJudgePassword = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1287, 732));
        setMinimumSize(new java.awt.Dimension(1300, 800));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(null);

        jTabbedPane2.setBackground(new java.awt.Color(51, 51, 51));
        jTabbedPane2.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane2.setToolTipText("");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(1120, 550));
        jPanel2.setPreferredSize(new java.awt.Dimension(1120, 550));
        jPanel2.setLayout(null);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 10, true));
        jPanel8.setPreferredSize(new java.awt.Dimension(180, 70));
        jPanel8.setLayout(null);

        lblTotalCount.setFont(new java.awt.Font("Science Gothic", 0, 36)); // NOI18N
        lblTotalCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalCount.setText("00230");
        jPanel8.add(lblTotalCount);
        lblTotalCount.setBounds(60, 50, 100, 47);

        jLabel8.setFont(new java.awt.Font("Science Gothic", 0, 24)); // NOI18N
        jLabel8.setText("Total Cases");
        jPanel8.add(jLabel8);
        jLabel8.setBounds(50, 10, 130, 32);

        jPanel2.add(jPanel8);
        jPanel8.setBounds(220, 80, 220, 110);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 10));
        jPanel9.setPreferredSize(new java.awt.Dimension(180, 70));
        jPanel9.setLayout(null);

        lblPendingCount.setFont(new java.awt.Font("Science Gothic", 0, 36)); // NOI18N
        lblPendingCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPendingCount.setText("00030");
        jPanel9.add(lblPendingCount);
        lblPendingCount.setBounds(60, 50, 100, 47);

        jLabel9.setFont(new java.awt.Font("Science Gothic", 0, 24)); // NOI18N
        jLabel9.setText("Pending Cases");
        jPanel9.add(jLabel9);
        jLabel9.setBounds(30, 10, 170, 32);

        jPanel2.add(jPanel9);
        jPanel9.setBounds(460, 80, 220, 110);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 10));
        jPanel10.setPreferredSize(new java.awt.Dimension(180, 70));
        jPanel10.setLayout(null);

        lblClosedCount.setFont(new java.awt.Font("Science Gothic", 0, 36)); // NOI18N
        lblClosedCount.setText("00200");
        jPanel10.add(lblClosedCount);
        lblClosedCount.setBounds(60, 50, 100, 47);

        jLabel3.setFont(new java.awt.Font("Science Gothic", 0, 24)); // NOI18N
        jLabel3.setText("Closed Cases");
        jPanel10.add(jLabel3);
        jLabel3.setBounds(40, 10, 150, 32);

        jPanel2.add(jPanel10);
        jPanel10.setBounds(700, 80, 220, 110);

        jLabel10.setFont(new java.awt.Font("Science Gothic", 0, 28)); // NOI18N
        jLabel10.setText("CASE DASHBOARD");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(430, 10, 270, 37);

        jPanel25.setBackground(new java.awt.Color(51, 51, 51));
        jPanel25.setForeground(new java.awt.Color(102, 102, 102));
        jPanel25.setLayout(null);

        dashboardTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dashboardTable.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        dashboardTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), "reg-01", "Marriage Devorce", "Civil", "Tek Raj Joshi", "First", "25/10/2025"},
                { new Integer(2), "reg-02", "Murder", "Criminal", "Kalpana Singh", "Second", "25/10/2025"},
                { new Integer(3), null, null, null, null, null, null},
                { new Integer(4), null, null, null, null, null, null},
                { new Integer(5), null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "CaseNo.", "RegNo.", "Title", "Type", "Judge", "Case Status", "Hearing Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dashboardTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dashboardTable.setGridColor(new java.awt.Color(0, 0, 0));
        dashboardTable.setRowHeight(40);
        dashboardTable.setSelectionBackground(new java.awt.Color(0, 0, 102));
        dashboardTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(dashboardTable);

        jPanel25.add(jScrollPane1);
        jScrollPane1.setBounds(20, 50, 1010, 220);

        jButton11.setBackground(new java.awt.Color(0, 0, 0));
        jButton11.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Next Case ->");
        jButton11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jButton11.addActionListener(this::jButton11ActionPerformed);
        jPanel25.add(jButton11);
        jButton11.setBounds(870, 290, 160, 40);

        jLabel7.setFont(new java.awt.Font("Science Gothic", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("UPCOMING CASES");
        jPanel25.add(jLabel7);
        jLabel7.setBounds(370, 0, 340, 50);

        jPanel2.add(jPanel25);
        jPanel25.setBounds(20, 210, 1050, 350);

        jTabbedPane2.addTab("       DASHBOARD      ", jPanel2);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(null);

        jLabel22.setFont(new java.awt.Font("Science Gothic", 0, 36)); // NOI18N
        jLabel22.setText("REGISTERED CASES");
        jPanel7.add(jLabel22);
        jLabel22.setBounds(20, 20, 370, 50);

        txtSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        txtSearch.addActionListener(this::txtSearchActionPerformed);
        jPanel7.add(txtSearch);
        txtSearch.setBounds(660, 90, 300, 36);

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("SEARCH");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jPanel7.add(jButton2);
        jButton2.setBounds(960, 90, 110, 36);

        jLabel23.setFont(new java.awt.Font("Science Gothic", 0, 24)); // NOI18N
        jLabel23.setText("Sort By :");
        jPanel7.add(jLabel23);
        jLabel23.setBounds(20, 90, 93, 32);

        cmbSortBy.setBackground(new java.awt.Color(0, 0, 0));
        cmbSortBy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbSortBy.setForeground(new java.awt.Color(255, 255, 255));
        cmbSortBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Hearing Date", "Filing Date", "Case Status" }));
        jPanel7.add(cmbSortBy);
        cmbSortBy.setBounds(130, 90, 150, 36);

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Sort");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jButton3.addActionListener(this::jButton3ActionPerformed);
        jPanel7.add(jButton3);
        jButton3.setBounds(280, 90, 90, 36);

        jLabel24.setFont(new java.awt.Font("Science Gothic", 0, 24)); // NOI18N
        jLabel24.setText("Filter By Judge");
        jPanel7.add(jLabel24);
        jLabel24.setBounds(660, 50, 167, 32);

        cmbFilterByJudge.setBackground(new java.awt.Color(0, 0, 0));
        cmbFilterByJudge.setForeground(new java.awt.Color(255, 255, 255));
        cmbFilterByJudge.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Kamala Singh", "Tek Raj Joshi" }));
        jPanel7.add(cmbFilterByJudge);
        cmbFilterByJudge.setBounds(830, 50, 150, 36);

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Filter");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jButton4.addActionListener(this::jButton4ActionPerformed);
        jPanel7.add(jButton4);
        jButton4.setBounds(980, 50, 90, 36);

        jButton15.setBackground(new java.awt.Color(0, 0, 0));
        jButton15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("View Detail");
        jButton15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jButton15.addActionListener(this::jButton15ActionPerformed);
        jPanel7.add(jButton15);
        jButton15.setBounds(460, 520, 180, 40);

        totalRegisteredCasesTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        totalRegisteredCasesTable.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        totalRegisteredCasesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), "reg-01", "Marriage Devorce", "Civil", "Tek Raj Joshi", "First", "25/10/2025", "26-02-22"},
                { new Integer(2), "reg-02", "Murder", "Criminal", "Kalpana Singh", "Second", "25/10/2025", null},
                { new Integer(3), null, null, null, null, null, null, null},
                { new Integer(4), null, null, null, null, null, null, null},
                { new Integer(5), null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CaseNo.", "RegNo.", "Title", "Type", "Judge", "Case Status", "Hearing Date", "Filing Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        totalRegisteredCasesTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        totalRegisteredCasesTable.setGridColor(new java.awt.Color(0, 0, 0));
        totalRegisteredCasesTable.setRowHeight(40);
        totalRegisteredCasesTable.setSelectionBackground(new java.awt.Color(0, 0, 102));
        totalRegisteredCasesTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane5.setViewportView(totalRegisteredCasesTable);

        jPanel7.add(jScrollPane5);
        jScrollPane5.setBounds(20, 130, 1050, 380);

        jTabbedPane2.addTab("REGISTERED CASES", jPanel7);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(null);

        jTabbedPane3.setBackground(new java.awt.Color(51, 51, 51));
        jTabbedPane3.setForeground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setLayout(null);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.setLayout(null);

        jLabel13.setFont(new java.awt.Font("Science Gothic", 0, 22)); // NOI18N
        jPanel11.add(jLabel13);
        jLabel13.setBounds(640, 237, 0, 0);

        jLabel14.setFont(new java.awt.Font("Science Gothic", 0, 22)); // NOI18N
        jLabel14.setText("Add Details");
        jPanel11.add(jLabel14);
        jLabel14.setBounds(31, 8, 115, 29);

        jLabel15.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel15.setText("Case Id.");
        jPanel11.add(jLabel15);
        jLabel15.setBounds(37, 49, 70, 21);

        txtCaseId.setBackground(new java.awt.Color(204, 204, 204));
        txtCaseId.setForeground(new java.awt.Color(102, 102, 102));
        txtCaseId.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txtCaseId.addActionListener(this::txtCaseIdActionPerformed);
        jPanel11.add(txtCaseId);
        txtCaseId.setBounds(37, 76, 159, 22);

        jLabel16.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel16.setText("Reg No.");
        jPanel11.add(jLabel16);
        jLabel16.setBounds(214, 49, 80, 21);

        txtRegNo.setBackground(new java.awt.Color(204, 204, 204));
        txtRegNo.setForeground(new java.awt.Color(102, 102, 102));
        txtRegNo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txtRegNo.addActionListener(this::txtRegNoActionPerformed);
        jPanel11.add(txtRegNo);
        txtRegNo.setBounds(214, 76, 159, 22);

        jLabel17.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel17.setText("Dispute Type");
        jPanel11.add(jLabel17);
        jLabel17.setBounds(391, 50, 110, 21);

        jLabel18.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel18.setText("Case Title/Issue");
        jPanel11.add(jLabel18);
        jLabel18.setBounds(37, 116, 181, 21);

        txtCaseTitle.setBackground(new java.awt.Color(204, 204, 204));
        txtCaseTitle.setForeground(new java.awt.Color(102, 102, 102));
        txtCaseTitle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel11.add(txtCaseTitle);
        txtCaseTitle.setBounds(37, 143, 440, 22);

        jLabel19.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel19.setText("Subject Matter");
        jPanel11.add(jLabel19);
        jLabel19.setBounds(37, 183, 181, 21);

        txtSubjectMatter.setBackground(new java.awt.Color(204, 204, 204));
        txtSubjectMatter.setColumns(20);
        txtSubjectMatter.setForeground(new java.awt.Color(102, 102, 102));
        txtSubjectMatter.setRows(5);
        txtSubjectMatter.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jScrollPane2.setViewportView(txtSubjectMatter);

        jPanel11.add(jScrollPane2);
        jScrollPane2.setBounds(37, 210, 597, 35);

        jLabel20.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel20.setText("Assigned Judge");
        jPanel11.add(jLabel20);
        jLabel20.setBounds(37, 321, 115, 21);

        cmbJudge.setBackground(new java.awt.Color(204, 204, 204));
        cmbJudge.setForeground(new java.awt.Color(102, 102, 102));
        cmbJudge.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kamala Singh", "Tek Raj Joshi", "Babu Kaji", " " }));
        jPanel11.add(cmbJudge);
        cmbJudge.setBounds(37, 347, 243, 22);

        txtHearingDate.setBackground(new java.awt.Color(204, 204, 204));
        txtHearingDate.setForeground(new java.awt.Color(102, 102, 102));
        txtHearingDate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txtHearingDate.addActionListener(this::txtHearingDateActionPerformed);
        jPanel11.add(txtHearingDate);
        txtHearingDate.setBounds(298, 348, 159, 22);

        jLabel21.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel21.setText("Hearing Date");
        jPanel11.add(jLabel21);
        jLabel21.setBounds(298, 321, 93, 21);

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Register Case");
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jPanel11.add(jButton1);
        jButton1.setBounds(484, 339, 150, 31);

        txtDisputeType.setBackground(new java.awt.Color(204, 204, 204));
        txtDisputeType.setForeground(new java.awt.Color(102, 102, 102));
        txtDisputeType.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txtDisputeType.addActionListener(this::txtDisputeTypeActionPerformed);
        jPanel11.add(txtDisputeType);
        txtDisputeType.setBounds(391, 76, 159, 22);

        jLabel25.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel25.setText("Claim Amount");
        jPanel11.add(jLabel25);
        jLabel25.setBounds(37, 257, 120, 21);

        txtClaimAmount.setBackground(new java.awt.Color(204, 204, 204));
        txtClaimAmount.setForeground(new java.awt.Color(102, 102, 102));
        txtClaimAmount.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txtClaimAmount.addActionListener(this::txtClaimAmountActionPerformed);
        jPanel11.add(txtClaimAmount);
        txtClaimAmount.setBounds(37, 284, 159, 22);

        jLabel26.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel26.setText("Relief Sought");
        jPanel11.add(jLabel26);
        jLabel26.setBounds(214, 257, 140, 21);

        txtReliefSought.setBackground(new java.awt.Color(204, 204, 204));
        txtReliefSought.setForeground(new java.awt.Color(102, 102, 102));
        txtReliefSought.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txtReliefSought.addActionListener(this::txtReliefSoughtActionPerformed);
        jPanel11.add(txtReliefSought);
        txtReliefSought.setBounds(214, 284, 440, 22);

        jPanel5.add(jPanel11);
        jPanel11.setBounds(215, 45, 680, 400);

        jTabbedPane3.addTab("CIVIL CASE", jPanel5);

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setLayout(null);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel101.setFont(new java.awt.Font("Science Gothic", 0, 22)); // NOI18N

        jLabel102.setFont(new java.awt.Font("Science Gothic", 0, 22)); // NOI18N
        jLabel102.setText("Add Details");

        jLabel103.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel103.setText("Case Id.");

        rtxtCaseId.setBackground(new java.awt.Color(204, 204, 204));
        rtxtCaseId.setForeground(new java.awt.Color(102, 102, 102));
        rtxtCaseId.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        rtxtCaseId.addActionListener(this::rtxtCaseIdActionPerformed);

        jLabel104.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel104.setText("Reg No.");

        rtxtRegisterationNumber.setBackground(new java.awt.Color(204, 204, 204));
        rtxtRegisterationNumber.setForeground(new java.awt.Color(102, 102, 102));
        rtxtRegisterationNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        rtxtRegisterationNumber.addActionListener(this::rtxtRegisterationNumberActionPerformed);

        jLabel105.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel105.setText("Crime Type");

        jLabel106.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel106.setText("Case Title/Issue");

        rtxtCaseTitle.setBackground(new java.awt.Color(204, 204, 204));
        rtxtCaseTitle.setForeground(new java.awt.Color(102, 102, 102));
        rtxtCaseTitle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel107.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel107.setText("Subject Matter");

        rtxtSubjectMatter.setBackground(new java.awt.Color(204, 204, 204));
        rtxtSubjectMatter.setColumns(20);
        rtxtSubjectMatter.setForeground(new java.awt.Color(102, 102, 102));
        rtxtSubjectMatter.setRows(5);
        rtxtSubjectMatter.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jScrollPane10.setViewportView(rtxtSubjectMatter);

        jLabel108.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel108.setText("Assigned Judge");

        rcmbJudge.setBackground(new java.awt.Color(204, 204, 204));
        rcmbJudge.setForeground(new java.awt.Color(102, 102, 102));
        rcmbJudge.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kamala Singh", "Tek Raj Joshi", "Babu Kaji", " " }));

        rtxtHearingDate.setBackground(new java.awt.Color(204, 204, 204));
        rtxtHearingDate.setForeground(new java.awt.Color(102, 102, 102));
        rtxtHearingDate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        rtxtHearingDate.addActionListener(this::rtxtHearingDateActionPerformed);

        jLabel109.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel109.setText("Hearing Date");

        registerbutton.setBackground(new java.awt.Color(51, 51, 51));
        registerbutton.setForeground(new java.awt.Color(255, 255, 255));
        registerbutton.setText("Register Case");
        registerbutton.addActionListener(this::registerbuttonActionPerformed);

        rtxtCrimeType.setBackground(new java.awt.Color(204, 204, 204));
        rtxtCrimeType.setForeground(new java.awt.Color(102, 102, 102));
        rtxtCrimeType.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        rtxtCrimeType.addActionListener(this::rtxtCrimeTypeActionPerformed);

        jLabel110.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel110.setText("Police Station");

        rtxtPoliceStation.setBackground(new java.awt.Color(204, 204, 204));
        rtxtPoliceStation.setForeground(new java.awt.Color(102, 102, 102));
        rtxtPoliceStation.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        rtxtPoliceStation.addActionListener(this::rtxtPoliceStationActionPerformed);

        jLabel111.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel111.setText("Fir Number");

        rtxtFirNumber.setBackground(new java.awt.Color(204, 204, 204));
        rtxtFirNumber.setForeground(new java.awt.Color(102, 102, 102));
        rtxtFirNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        rtxtFirNumber.addActionListener(this::rtxtFirNumberActionPerformed);

        jLabel112.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel112.setText("Bail Status");

        rchkBailStatus.setBackground(new java.awt.Color(204, 204, 204));
        rchkBailStatus.setForeground(new java.awt.Color(102, 102, 102));
        rchkBailStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Granted", "Not Granted" }));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel102)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel103)
                                    .addComponent(rtxtCaseId, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel104)
                                    .addComponent(rtxtRegisterationNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel105)
                                    .addComponent(rtxtCrimeType, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel108)
                                            .addComponent(rcmbJudge, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel109)
                                            .addComponent(rtxtHearingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27)
                                        .addComponent(registerbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel101))
                            .addComponent(rtxtCaseTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel110)
                                    .addComponent(rtxtPoliceStation, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel111)
                                    .addComponent(rtxtFirNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel112)
                                    .addComponent(rchkBailStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel102)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel103)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rtxtCaseId, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel104)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rtxtRegisterationNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rtxtCrimeType, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel105)
                        .addGap(27, 27, 27)))
                .addGap(18, 18, 18)
                .addComponent(jLabel106)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel101)
                        .addGap(161, 161, 161))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rtxtCaseTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel107)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(jLabel110)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rtxtPoliceStation, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(jLabel111)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(rtxtFirNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(rchkBailStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel112)
                                .addGap(28, 28, 28)))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(rcmbJudge, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel17Layout.createSequentialGroup()
                                    .addComponent(jLabel108)
                                    .addGap(27, 27, 27)))
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(registerbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel17Layout.createSequentialGroup()
                                    .addComponent(jLabel109)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rtxtHearingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(27, Short.MAX_VALUE))))
        );

        jPanel6.add(jPanel17);
        jPanel17.setBounds(201, 51, 710, 399);

        jTabbedPane3.addTab("CRIMINAL CASE", jPanel6);

        jPanel12.setBackground(new java.awt.Color(51, 51, 51));
        jPanel12.setLayout(null);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jPanel13.setBackground(new java.awt.Color(51, 51, 51));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel13.setForeground(new java.awt.Color(255, 255, 255));

        jLabel27.setFont(new java.awt.Font("Science Gothic", 0, 22)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Science Gothic", 0, 22)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("CIVIL");

        jLabel29.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Case Id.");

        txtCaseId1.setBackground(new java.awt.Color(204, 204, 204));
        txtCaseId1.setForeground(new java.awt.Color(102, 102, 102));
        txtCaseId1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txtCaseId1.addActionListener(this::txtCaseId1ActionPerformed);

        jLabel34.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Assigned Judge");

        cmbJudge1.setBackground(new java.awt.Color(204, 204, 204));
        cmbJudge1.setForeground(new java.awt.Color(102, 102, 102));
        cmbJudge1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "none", "Kamala Singh", "Tek Raj Joshi", "Babu Kaji", " " }));

        jButton5.setBackground(new java.awt.Color(51, 51, 51));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("UPDATE CASE");
        jButton5.addActionListener(this::jButton5ActionPerformed);

        txtRelief1.setBackground(new java.awt.Color(204, 204, 204));
        txtRelief1.setForeground(new java.awt.Color(102, 102, 102));
        txtRelief1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txtRelief1.addActionListener(this::txtRelief1ActionPerformed);

        jLabel36.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Claim Amount");

        txtClaimAmount1.setBackground(new java.awt.Color(204, 204, 204));
        txtClaimAmount1.setForeground(new java.awt.Color(102, 102, 102));
        txtClaimAmount1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txtClaimAmount1.addActionListener(this::txtClaimAmount1ActionPerformed);

        jButton6.setBackground(new java.awt.Color(51, 51, 51));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("DELETE CASE");
        jButton6.addActionListener(this::jButton6ActionPerformed);

        jLabel45.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Hearing Date");

        txtHearing1.setBackground(new java.awt.Color(204, 204, 204));
        txtHearing1.setForeground(new java.awt.Color(102, 102, 102));
        txtHearing1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txtHearing1.addActionListener(this::txtHearing1ActionPerformed);

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Relief Sought");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel36)
                            .addComponent(jLabel34)
                            .addComponent(jLabel45)
                            .addComponent(jLabel30))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbJudge1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRelief1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtClaimAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHearing1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(txtCaseId1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(149, 149, 149)
                .addComponent(jLabel27))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(jLabel28))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addGap(161, 161, 161))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addGap(46, 46, 46)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCaseId1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRelief1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addComponent(txtClaimAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(cmbJudge1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addComponent(txtHearing1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        jPanel12.add(jPanel14);
        jPanel14.setBounds(6, 6, 541, 493);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jPanel19.setBackground(new java.awt.Color(51, 51, 51));
        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel19.setForeground(new java.awt.Color(255, 255, 255));
        jPanel19.setLayout(null);

        jLabel39.setFont(new java.awt.Font("Science Gothic", 0, 22)); // NOI18N
        jPanel19.add(jLabel39);
        jLabel39.setBounds(640, 209, 0, 0);

        jLabel40.setFont(new java.awt.Font("Science Gothic", 0, 22)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("CRIMINAL");
        jPanel19.add(jLabel40);
        jLabel40.setBounds(213, 8, 107, 29);

        jLabel41.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Case Id.");
        jPanel19.add(jLabel41);
        jLabel41.setBounds(110, 90, 60, 21);

        txtCaseId3.setBackground(new java.awt.Color(204, 204, 204));
        txtCaseId3.setForeground(new java.awt.Color(102, 102, 102));
        txtCaseId3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txtCaseId3.addActionListener(this::txtCaseId3ActionPerformed);
        jPanel19.add(txtCaseId3);
        txtCaseId3.setBounds(230, 90, 159, 22);

        jLabel42.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Bail Status");
        jPanel19.add(jLabel42);
        jLabel42.setBounds(85, 190, 80, 21);

        jLabel43.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Assigned Judge");
        jPanel19.add(jLabel43);
        jLabel43.setBounds(48, 270, 120, 21);

        cmbJudge3.setBackground(new java.awt.Color(204, 204, 204));
        cmbJudge3.setForeground(new java.awt.Color(102, 102, 102));
        cmbJudge3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "none", "Kamala Singh", "Tek Raj Joshi", "Babu Kaji", " " }));
        jPanel19.add(cmbJudge3);
        cmbJudge3.setBounds(230, 270, 243, 22);

        jButton9.setBackground(new java.awt.Color(51, 51, 51));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("UPDATE CASE");
        jButton9.addActionListener(this::jButton9ActionPerformed);
        jPanel19.add(jButton9);
        jButton9.setBounds(50, 130, 150, 31);

        jLabel44.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Hearing Date");
        jPanel19.add(jLabel44);
        jLabel44.setBounds(70, 230, 93, 21);

        txtClaimAmount3.setBackground(new java.awt.Color(204, 204, 204));
        txtClaimAmount3.setForeground(new java.awt.Color(102, 102, 102));
        txtClaimAmount3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txtClaimAmount3.addActionListener(this::txtClaimAmount3ActionPerformed);
        jPanel19.add(txtClaimAmount3);
        txtClaimAmount3.setBounds(230, 230, 159, 22);

        jButton10.setBackground(new java.awt.Color(51, 51, 51));
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("DELETE CASE");
        jButton10.addActionListener(this::jButton10ActionPerformed);
        jPanel19.add(jButton10);
        jButton10.setBounds(300, 130, 150, 31);

        rchkBailStatus1.setBackground(new java.awt.Color(204, 204, 204));
        rchkBailStatus1.setForeground(new java.awt.Color(102, 102, 102));
        rchkBailStatus1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "none", "Granted", "Not Granted" }));
        jPanel19.add(rchkBailStatus1);
        rchkBailStatus1.setBounds(230, 190, 203, 22);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        jPanel12.add(jPanel18);
        jPanel18.setBounds(565, 6, 500, 493);

        jTabbedPane3.addTab("UPDATE CASE", jPanel12);

        jPanel24.setBackground(new java.awt.Color(51, 51, 51));
        jPanel24.setLayout(null);

        tblDeleted.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        tblDeleted.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), "reg-01", "Marriage Devorce", "Civil", "Tek Raj Joshi", "First", "25/10/2025"},
                { new Integer(2), "reg-02", "Murder", "Criminal", "Kalpana Singh", "Second", "25/10/2025"},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "CaseNo.", "RegNo.", "Title", "Type", "Judge", "Case Status", "Hearing Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDeleted.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane4.setViewportView(tblDeleted);

        jPanel24.add(jScrollPane4);
        jScrollPane4.setBounds(10, 130, 1060, 370);

        btnClear.setBackground(new java.awt.Color(51, 51, 51));
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear All ");
        btnClear.addActionListener(this::btnClearActionPerformed);
        jPanel24.add(btnClear);
        btnClear.setBounds(730, 90, 150, 30);

        jLabel47.setFont(new java.awt.Font("Science Gothic", 0, 24)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("RECYCLE BIN");
        jPanel24.add(jLabel47);
        jLabel47.setBounds(20, 20, 170, 32);

        jButton12.setBackground(new java.awt.Color(51, 51, 51));
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Undo Delete");
        jButton12.addActionListener(this::jButton12ActionPerformed);
        jPanel24.add(jButton12);
        jButton12.setBounds(900, 90, 150, 30);

        jTabbedPane3.addTab("RECYCLE BIN", jPanel24);

        jPanel4.add(jTabbedPane3);
        jTabbedPane3.setBounds(6, 6, 1080, 550);

        jTabbedPane2.addTab("CASE MANAGEMENT", jPanel4);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(null);

        jTabbedPane4.setBackground(new java.awt.Color(51, 51, 51));
        jTabbedPane4.setForeground(new java.awt.Color(255, 255, 255));

        jPanel23.setBackground(new java.awt.Color(51, 51, 51));
        jPanel23.setLayout(null);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel22.setLayout(null);

        jButton14.setBackground(new java.awt.Color(0, 0, 0));
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("REGISTER JUDGE");
        jButton14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jButton14.addActionListener(this::jButton14ActionPerformed);
        jPanel22.add(jButton14);
        jButton14.setBounds(430, 400, 170, 50);

        jLabel68.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel68.setText("ID :");
        jPanel22.add(jLabel68);
        jLabel68.setBounds(230, 160, 30, 30);

        txtJudgeId.setBackground(new java.awt.Color(204, 204, 204));
        txtJudgeId.setForeground(new java.awt.Color(102, 102, 102));
        txtJudgeId.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel22.add(txtJudgeId);
        txtJudgeId.setBounds(280, 160, 200, 30);

        txtJudgeFirstName.setBackground(new java.awt.Color(204, 204, 204));
        txtJudgeFirstName.setForeground(new java.awt.Color(102, 102, 102));
        txtJudgeFirstName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel22.add(txtJudgeFirstName);
        txtJudgeFirstName.setBounds(280, 210, 200, 30);

        jLabel69.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel69.setText("First Name :");
        jPanel22.add(jLabel69);
        jLabel69.setBounds(170, 210, 100, 30);

        jLabel70.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel70.setText("Last Name :");
        jPanel22.add(jLabel70);
        jLabel70.setBounds(170, 260, 90, 30);

        txtJudgeLastName.setBackground(new java.awt.Color(204, 204, 204));
        txtJudgeLastName.setForeground(new java.awt.Color(102, 102, 102));
        txtJudgeLastName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel22.add(txtJudgeLastName);
        txtJudgeLastName.setBounds(280, 260, 200, 30);

        txtJudgeContact.setBackground(new java.awt.Color(204, 204, 204));
        txtJudgeContact.setForeground(new java.awt.Color(102, 102, 102));
        txtJudgeContact.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel22.add(txtJudgeContact);
        txtJudgeContact.setBounds(280, 310, 200, 30);

        jLabel71.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel71.setText("Contact :");
        jPanel22.add(jLabel71);
        jLabel71.setBounds(190, 310, 70, 30);

        jLabel53.setFont(new java.awt.Font("Science Gothic", 0, 36)); // NOI18N
        jLabel53.setText("REGISTER JUDGE");
        jPanel22.add(jLabel53);
        jLabel53.setBounds(370, 50, 320, 47);

        jLabel72.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 12)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(153, 153, 153));
        jLabel72.setText("password must be 8 characters long");
        jPanel22.add(jLabel72);
        jLabel72.setBounds(670, 240, 200, 20);

        txtJudgeUsername.setBackground(new java.awt.Color(204, 204, 204));
        txtJudgeUsername.setForeground(new java.awt.Color(102, 102, 102));
        txtJudgeUsername.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel22.add(txtJudgeUsername);
        txtJudgeUsername.setBounds(670, 160, 200, 30);

        jLabel73.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel73.setText("Password :");
        jPanel22.add(jLabel73);
        jLabel73.setBounds(570, 260, 100, 30);

        jLabel74.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel74.setText("Confirm Password :");
        jPanel22.add(jLabel74);
        jLabel74.setBounds(510, 310, 150, 30);

        jLabel75.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel75.setText("Username  :");
        jPanel22.add(jLabel75);
        jLabel75.setBounds(560, 160, 100, 30);

        txtJudgeConfirmPass.setText("jPasswordField1");
        jPanel22.add(txtJudgeConfirmPass);
        txtJudgeConfirmPass.setBounds(670, 310, 200, 30);

        txtJudgePassword.setText("jPasswordField1");
        jPanel22.add(txtJudgePassword);
        txtJudgePassword.setBounds(670, 260, 200, 30);

        jPanel23.add(jPanel22);
        jPanel22.setBounds(10, 16, 1050, 480);

        jTabbedPane4.addTab("ADD JUDGE", jPanel23);

        jPanel15.add(jTabbedPane4);
        jTabbedPane4.setBounds(10, 10, 1080, 550);

        jTabbedPane2.addTab("MANAGE JUDGES", jPanel15);

        jPanel1.add(jTabbedPane2);
        jTabbedPane2.setBounds(19, 180, 1240, 570);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Inter", 0, 48)); // NOI18N
        jLabel1.setText("CASE MANAGEMENT SYSTEM");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(270, 20, 700, 100);
        jPanel3.add(jLabel4);
        jLabel4.setBounds(843, 51, 51, 0);

        jButton7.setBackground(new java.awt.Color(0, 0, 0));
        jButton7.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Log Out");
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jButton7.addActionListener(this::jButton7ActionPerformed);
        jPanel3.add(jButton7);
        jButton7.setBounds(1090, 10, 140, 43);

        jLabel11.setFont(new java.awt.Font("Science Gothic", 0, 24)); // NOI18N
        jLabel11.setText("Welcome Back, Admin");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(10, 10, 290, 32);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(20, 20, 1240, 140);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(2, -4, 1280, 770);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        loadRegisteredCases();
        loadDeletedTable();
        updateDashboardStats();
        loadQueueTable();
    }//GEN-LAST:event_formWindowActivated

    private void txtCaseIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCaseIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCaseIdActionPerformed

    private void txtRegNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRegNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRegNoActionPerformed

    private void txtHearingDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHearingDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHearingDateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // 1. Check for Empty Fields (Basic Validation)
            if (txtCaseId.getText().isEmpty() || txtRegNo.getText().isEmpty()
                    || txtCaseTitle.getText().isEmpty() || txtHearingDate.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all required fields!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // 2. Extract Data from TextFields
            // Use trim() to remove accidental spaces
            int id = Integer.parseInt(txtCaseId.getText().trim()); // Converts String "101" to int 101
            String regNo = txtRegNo.getText().trim();
            String disputeType = txtDisputeType.getText().trim();
            String title = txtCaseTitle.getText().trim();
            String subject = txtSubjectMatter.getText().trim();

            // Handle Claim Amount (Parse String to Double)
            double amount = 0.0;
            try {
                amount = Double.parseDouble(txtClaimAmount.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Claim Amount must be a number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String relief = txtReliefSought.getText().trim();
            String judge = cmbJudge.getSelectedItem().toString(); // Get selected item from dropdown
            String hearingDate = txtHearingDate.getText().trim();
            try {
                // This strictly checks if the date matches yyyy-MM-dd
                java.time.LocalDate.parse(hearingDate, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (java.time.format.DateTimeParseException e) {
                JOptionPane.showMessageDialog(this,
                        "Invalid Date Format! Please use yyyy-MM-dd (e.g., 2026-01-26).",
                        "Date Error", JOptionPane.ERROR_MESSAGE);
                return; // Stop the registration
            }

            // getting current date and time
            LocalDateTime currentDateTime = LocalDateTime.now();

            // 2. FParsing it into String
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedFilingDate = currentDateTime.format(formatter);

            // 3. Create the CivilCase Object
            CivilCase newCase = new CivilCase(
                    id,
                    regNo,
                    title,
                    formattedFilingDate,
                    hearingDate,
                    judge,
                    "Open",
                    disputeType,
                    subject,
                    amount,
                    relief
            );

            boolean isSaved = controller.registerCase(newCase);

            if (isSaved) {
                JOptionPane.showMessageDialog(this, "Success! Case Registered.","Registration Success!",JOptionPane.INFORMATION_MESSAGE);

                // Clear the fields so you can add another one
                txtCaseId.setText("");
                txtCaseTitle.setText("");
                // ... clear others if you want

            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Error: Case ID already exists!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Case ID must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtDisputeTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDisputeTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDisputeTypeActionPerformed

    private void txtClaimAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaimAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaimAmountActionPerformed

    private void txtReliefSoughtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReliefSoughtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReliefSoughtActionPerformed

    private void rtxtCaseIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtxtCaseIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rtxtCaseIdActionPerformed

    private void rtxtRegisterationNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtxtRegisterationNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rtxtRegisterationNumberActionPerformed

    private void rtxtHearingDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtxtHearingDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rtxtHearingDateActionPerformed

    private void registerbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerbuttonActionPerformed

        try {
            // --- 1. VALIDATION ---
            // Check if important fields are empty
            if (rtxtCaseId.getText().trim().isEmpty()
                    || rtxtRegisterationNumber.getText().trim().isEmpty()
                    || rtxtPoliceStation.getText().trim().isEmpty()) {

                javax.swing.JOptionPane.showMessageDialog(this, "Please fill in all required fields.");
                return;
            }

            // --- 2. GET DATA ---
            int id = Integer.parseInt(rtxtCaseId.getText().trim());
            String regNo = rtxtRegisterationNumber.getText().trim();
            String title = rtxtCaseTitle.getText().trim();
            String hearingDate = rtxtHearingDate.getText().trim();
            try {
                // This strictly checks if the date matches yyyy-MM-dd
                java.time.LocalDate.parse(hearingDate, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (java.time.format.DateTimeParseException e) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Invalid Date Format! Please use yyyy-MM-dd (e.g., 2026-01-26).",
                        "Date Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return; // Stop the registration
            }
            String judge = rcmbJudge.getSelectedItem().toString();

            String crimeType = rtxtCrimeType.getText().trim();
            String station = rtxtPoliceStation.getText().trim();
            String firNo = rtxtFirNumber.getText().trim();

            // Bail Status (ComboBox)
            String bailStatus = rchkBailStatus.getSelectedItem().toString();
            LocalDateTime currentDateTime = LocalDateTime.now();

            // 2. FParsing it into String
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedFilingDate = currentDateTime.format(formatter);

            // --- 3. CREATE MODEL OBJECT ---
            // Ensure this matches your CriminalCase Constructor exactly!
            model.CriminalCase newCase = new model.CriminalCase(
                    id,
                    regNo,
                    title,
                    formattedFilingDate,
                    hearingDate,
                    judge,
                    "Open",
                    crimeType,
                    station,
                    firNo,
                    bailStatus
            );

            // CALL CONTROLLER
            boolean isSaved = controller.registerCase(newCase);

            if (isSaved) {
                javax.swing.JOptionPane.showMessageDialog(this, "Criminal Case Registered Successfully!");
                // Clear fields
                rtxtCaseId.setText("");
                rtxtCaseTitle.setText("");
                rtxtFirNumber.setText("");
                rtxtPoliceStation.setText("");

            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Error: Case ID already exists!");
            }
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Case ID must be a valid number.");
        }
    }//GEN-LAST:event_registerbuttonActionPerformed

    private void rtxtCrimeTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtxtCrimeTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rtxtCrimeTypeActionPerformed

    private void rtxtPoliceStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtxtPoliceStationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rtxtPoliceStationActionPerformed

    private void rtxtFirNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtxtFirNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rtxtFirNumberActionPerformed

    private void txtCaseId1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCaseId1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCaseId1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        try {
            // --- 1. VALIDATION: Check Case ID ---
            if (txtCaseId1.getText().trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Please enter a Case ID.");
                return;
            }

            // --- 2. CHECK EMPTY FIELDS ---
            // We check if the user left everything blank/None
            boolean isReliefEmpty = txtRelief1.getText().trim().isEmpty();
            boolean isAmountEmpty = txtClaimAmount1.getText().trim().isEmpty();
            boolean isDateEmpty = txtHearing1.getText().trim().isEmpty();

            String selectedJudge = cmbJudge1.getSelectedItem().toString();
            boolean isJudgeEmpty = selectedJudge.equals("none") || selectedJudge.trim().isEmpty();

            // If EVERYTHING is empty or "None", stop here
            if (isReliefEmpty && isAmountEmpty && isDateEmpty && isJudgeEmpty) {
                javax.swing.JOptionPane.showMessageDialog(this, "Nothing to update. Please change at least one field.");
                return;
            }

            // --- 3. FETCH ORIGINAL CASE ---
            int id = Integer.parseInt(txtCaseId1.getText().trim());
            Case genericCase = controller.findCaseById(id);

            if (genericCase == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "Case ID not found.");
                return;
            }

            if (!(genericCase instanceof CivilCase)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error: This ID belongs to a Criminal Case.");
                return;
            }

            CivilCase original = (CivilCase) genericCase;

            // --- 4. PREPARE data ---
            // A. Relief Sought
            String finalRelief;
            if (isReliefEmpty) {
                finalRelief = original.getReliefSought();
            } else {
                finalRelief = txtRelief1.getText().trim();
            }

            // B. Hearing Date
            String finalDate;
            if (isDateEmpty) {
                finalDate = original.getHearingDate();
            } else {
                finalDate = txtHearing1.getText().trim();

                try {
                    java.time.LocalDate.parse(finalDate, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (java.time.format.DateTimeParseException e) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                            "Invalid Date Format! Please use yyyy-MM-dd (e.g., 2026-01-26).",
                            "Date Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return; // Stop the update
                }
                // --------------------------------------------------------
            }

            // C. Claim Amount
            double finalAmount;
            if (isAmountEmpty) {
                finalAmount = original.getClaimAmount();
            } else {
                try {
                    finalAmount = Double.parseDouble(txtClaimAmount1.getText().trim());
                } catch (NumberFormatException e) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Invalid Amount. Keeping original.");
                    finalAmount = original.getClaimAmount();
                }
            }

            // D. Assigned Judge 
            String finalJudge;
            if (isJudgeEmpty) {
                finalJudge = original.getAssignedJudge(); // Keep Old
            } else {
                finalJudge = selectedJudge; // Use New Selection
            }

            // --- 5. CREATE UPDATED OBJECT ---
            CivilCase updatedCase = new CivilCase(
                    id,
                    original.getRegistrationNumber(),
                    original.getCaseTitle(),
                    original.getFilingDate(),
                    finalDate,
                    finalJudge,
                    original.getCaseStatus(),
                    original.getDisputeType(),
                    original.getSubjectMatter(),
                    finalAmount,
                    finalRelief
            );

            // --- 6. SAVE CHANGES ---
            boolean success = controller.updateCase(updatedCase);

            if (success) {
                javax.swing.JOptionPane.showMessageDialog(this, "Case Updated Successfully.");
                loadRegisteredCases();

                // Clear inputs
                txtCaseId1.setText("");
                txtRelief1.setText("");
                txtClaimAmount1.setText("");
                txtHearing1.setText("");
                cmbJudge1.setSelectedItem("None"); // Reset dropdown
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Update Failed.");
            }

        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Invalid Case ID format.");
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtRelief1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRelief1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRelief1ActionPerformed

    private void txtClaimAmount1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaimAmount1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaimAmount1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        try {
            // 1. VALIDATION: CHECK INPUT
            // Ensure the user has entered a Case ID before attempting to delete.
            if (txtCaseId1.getText().trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Please enter the Case ID to delete.");
                return;
            }

            int id = Integer.parseInt(txtCaseId1.getText().trim());

            // 2. VALIDATION: VERIFY EXISTENCE
            // Check if the case actually exists in the system.
            model.Case caseToDelete = controller.findCaseById(id);

            if (caseToDelete == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "Case ID not found in the system.");
                return;
            }

            // Ensure we are deleting the correct type (Civil vs Criminal)
            if (!(caseToDelete instanceof model.CivilCase)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error: This ID belongs to a Criminal Case. Please use the Criminal tab.");
                return;
            }

            // 3. USER CONFIRMATION
            // Prompt the user to confirm the action. This is a critical safety step.
            int choice = javax.swing.JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to permanently delete Civil Case #" + id + "?",
                    "Delete Confirmation",
                    javax.swing.JOptionPane.YES_NO_OPTION
            );

            // 4. EXECUTE DELETION
            // Only proceed if the user clicked "Yes".
            if (choice == javax.swing.JOptionPane.YES_OPTION) {
                boolean success = controller.deleteCase(id);

                if (success) {
                    // 5. SUCCESS FEEDBACK & REFRESH
                    javax.swing.JOptionPane.showMessageDialog(this, "Case deleted successfully.");

                    // Refresh the main table to reflect the removal
                    loadRegisteredCases();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Error: Deletion failed.");
                }
            }

        } catch (NumberFormatException e) {
            // Handle invalid (non-numeric) ID input
            javax.swing.JOptionPane.showMessageDialog(this, "Invalid Case ID format. Please enter a number.");
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtCaseId3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCaseId3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCaseId3ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        try {
            // 1. Validation: Check Case ID
            if (txtCaseId3.getText().trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Please enter a Case ID.");
                return;
            }

            // 2. Check Empty Fields (Smart Validation)
            boolean isDateEmpty = txtClaimAmount3.getText().trim().isEmpty();

            String selectedJudge = cmbJudge3.getSelectedItem().toString();
            boolean isJudgeEmpty = selectedJudge.equalsIgnoreCase("None") || selectedJudge.trim().isEmpty();

            String selectedBail = rchkBailStatus1.getSelectedItem().toString();
            boolean isBailEmpty = selectedBail.equalsIgnoreCase("None") || selectedBail.trim().isEmpty();

            // Stop if nothing is changed
            if (isDateEmpty && isJudgeEmpty && isBailEmpty) {
                javax.swing.JOptionPane.showMessageDialog(this, "Nothing to update. Please change at least one field.");
                return;
            }

            // 3. Fetch Original Case
            int id = Integer.parseInt(txtCaseId3.getText().trim());
            Case genericCase = controller.findCaseById(id);

            if (genericCase == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "Case ID not found.");
                return;
            }

            if (!(genericCase instanceof model.CriminalCase)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error: This ID belongs to a Civil Case.");
                return;
            }

            CriminalCase original = (CriminalCase) genericCase;

            // 4. Prepare Data
            // B. Hearing Date
            String finalDate;
            if (isDateEmpty) {
                finalDate = original.getHearingDate();
            } else {
                finalDate = txtHearing1.getText().trim();

                // --- NEW VALIDATION: Check the format of the NEW date ---
                try {
                    java.time.LocalDate.parse(finalDate, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (java.time.format.DateTimeParseException e) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                            "Invalid Date Format! Please use yyyy-MM-dd (e.g., 2026-01-26).",
                            "Date Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return; // Stop the update
                }
                // --------------------------------------------------------
            }

            // Assigned Judge
            String finalJudge;
            if (isJudgeEmpty) {
                finalJudge = original.getAssignedJudge();
            } else {
                finalJudge = selectedJudge;
            }

            // Bail Status
            String finalBail;
            if (isBailEmpty) {
                finalBail = original.getBailGranted(); // Ensure getBailStatus() exists in CriminalCase model
            } else {
                finalBail = selectedBail;
            }

            // 5. Create Updated Object
            CriminalCase updatedCase = new CriminalCase(
                    id,
                    original.getRegistrationNumber(),
                    original.getCaseTitle(),
                    original.getFilingDate(),
                    finalDate,
                    finalJudge,
                    original.getCaseStatus(),
                    original.getCrimeType(),
                    original.getPoliceStation(),
                    original.getFirNumber(),
                    finalBail
            );

            // 6. Save Changes
            boolean success = controller.updateCase(updatedCase);

            if (success) {
                javax.swing.JOptionPane.showMessageDialog(this, "Criminal Case Updated Successfully.");
                loadRegisteredCases();

                // Clear inputs
                txtCaseId3.setText("");
                txtClaimAmount3.setText("");
                cmbJudge3.setSelectedItem("None");

                rchkBailStatus1.setSelectedItem("None");

            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Update Failed.");
            }

        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Invalid Case ID format.");
        }

    }//GEN-LAST:event_jButton9ActionPerformed

    private void txtClaimAmount3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaimAmount3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaimAmount3ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            // 1. Validation: Check Input
            if (txtCaseId3.getText().trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Please enter the Case ID to delete.");
                return;
            }

            int id = Integer.parseInt(txtCaseId3.getText().trim());

            // 2. Verification
            model.Case caseToDelete = controller.findCaseById(id);

            if (caseToDelete == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "Case ID not found.");
                return;
            }

            if (!(caseToDelete instanceof model.CriminalCase)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error: This ID belongs to a Civil Case.");
                return;
            }

            // 3. Confirmation
            int choice = javax.swing.JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete Criminal Case #" + id + "?",
                    "Delete Confirmation",
                    javax.swing.JOptionPane.YES_NO_OPTION
            );

            // 4. Execution
            if (choice == javax.swing.JOptionPane.YES_OPTION) {
                boolean success = controller.deleteCase(id);

                if (success) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Case deleted successfully.");
                    loadRegisteredCases();

                    // Clear fields
                    txtCaseId3.setText("");
                    txtClaimAmount3.setText("");

                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Error: Deletion failed.");
                }
            }

        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Invalid Case ID format.");
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void txtHearing1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHearing1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHearing1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int response = javax.swing.JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                javax.swing.JOptionPane.YES_NO_OPTION);

// 2. Check if the user clicked "Yes"
        if (response == javax.swing.JOptionPane.YES_OPTION) {
            // Execute your transition code
            LoginFr login = new LoginFr();
            login.setVisible(true);
            this.dispose();
        }       // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // Check if empty
        if (controller.getDeletedCases().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Recycle Bin is already empty.");
            return;
        }

        // Confirmation
        int choice = javax.swing.JOptionPane.showConfirmDialog(this,
                "Are you sure you want to empty the Recycle Bin?",
                "Clear All", javax.swing.JOptionPane.YES_NO_OPTION);

        if (choice == javax.swing.JOptionPane.YES_OPTION) {
            boolean isCleared = controller.clearDeletedStack();

            if (isCleared) {
                javax.swing.JOptionPane.showMessageDialog(this, "Recycle Bin Cleared.");
                loadDeletedTable();
            }
        }
    }//GEN-LAST:event_btnClearActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        //  Call the RESTORE method
        boolean success = controller.restoreCase();

        if (success) {
            javax.swing.JOptionPane.showMessageDialog(this, "Case Restored Successfully!");

            //  Refresh BOTH tables
            loadDeletedTable();      // Remove from Recycle Bin table
            loadRegisteredCases();   // Add back to Main table

        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Recycle Bin is empty.");
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        // Process the current case (remove from queue)
        Case caseStarting = controller.nextHearing();

        if (caseStarting != null) {
            // Update Status
            caseStarting.setCaseStatus("Closed");
            controller.updateCase(caseStarting);

            // 3. CRITICAL STEP: REGENERATE THE QUEUE
            // This goes back to the list, finds the next waiting case
            // and puts it into the queue to fill the empty spot.
            controller.generateUpcomingQueue();

            // 4. Refresh the GUI Tables
            loadQueueTable();
            loadRegisteredCases();

            javax.swing.JOptionPane.showMessageDialog(this,
                    "Hearing Started for Case #" + caseStarting.getCaseId());

        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "No upcoming hearings.");
        }

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed

        // Get the Selected Row Index
        int selectedRow = totalRegisteredCasesTable.getSelectedRow();

        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a case from the table first.");
            return;
        }

        // Get the Case ID from the Table
        // Note: Assuming Case ID is in the first column (index 0)
        int caseId = (int) totalRegisteredCasesTable.getValueAt(selectedRow, 0);

        // Find the Actual Object using Controller
        model.Case selectedCase = controller.findCaseById(caseId);

        if (selectedCase != null) {
            // 4. Open the Details Frame
            CaseDetail detailsPage = new CaseDetail(selectedCase);
            detailsPage.setVisible(true);
            detailsPage.setLocationRelativeTo(null); // Center the window
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: Case not found in database.");
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String query = txtSearch.getText().trim();

        // Validation: Empty check
        if (query.isEmpty()) {
            loadRegisteredCases(); // Reset table
            return;
        }

        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) totalRegisteredCasesTable.getModel();
        model.setRowCount(0); // Clear table for results

        // CHECK: Is the query a Number? (For Binary Search)
        if (query.matches("\\d+")) {
            // --- USE BINARY SEARCH (Slide 12) ---
            int searchId = Integer.parseInt(query);
            model.Case result = controller.binarySearchById(searchId);

            if (result != null) {
                // Add the single finding to the table
                model.addRow(new Object[]{
                    result.getCaseId(), result.getRegistrationNumber(), result.getCaseTitle(),
                    result.getCaseType(), result.getAssignedJudge(), result.getCaseStatus(),
                    result.getHearingDate()
                });
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Case ID " + searchId + " not found.");
            }

        } else {
            // --- USE LINEAR SEARCH (Slide 6) ---
            java.util.LinkedList<model.Case> results = controller.linearSearch(query);

            if (results.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "No cases found matching: " + query);
            } else {
                for (model.Case c : results) {
                    model.addRow(new Object[]{
                        c.getCaseId(), c.getRegistrationNumber(), c.getCaseTitle(),
                        c.getCaseType(), c.getAssignedJudge(), c.getCaseStatus(),
                        c.getHearingDate()
                    });
                }
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        // 1. Get the selected category from the ComboBox
        // Replace 'cmbSortBy' with your actual variable name!
        String criteria = cmbSortBy.getSelectedItem().toString();

        if (criteria.equalsIgnoreCase("None")) {
            // Optional: Reload original order if you tracked it, or do nothing
            return;
        }

        // 2. Call the Controller to Sort
        controller.sortCases(criteria);

        // 3. Refresh the Table to show the new order
        loadRegisteredCases();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        // 1. Get Selected Judge from Dropdown
        // Note: Make sure your variable name is cmbFilterByJudge (as in your code)
        String selectedJudge = cmbFilterByJudge.getSelectedItem().toString();

        // 2. Check for "None" to reset
        // If "None", we just call your existing method to load everything back
        if (selectedJudge.equalsIgnoreCase("All") || selectedJudge.trim().isEmpty()) {
            loadRegisteredCases();
            return;
        }

        // 3. Get Filtered List from Controller
        java.util.LinkedList<model.Case> judgeCases = controller.filterByJudge(selectedJudge);

        // 4. Update the Table MANUALLY (Since we aren't changing loadRegisteredCases)
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) totalRegisteredCasesTable.getModel();
        model.setRowCount(0); // Clear the table

        if (judgeCases.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "No cases found for Judge: " + selectedJudge);
        } else {
            // Loop through the FILTERED list and add rows
            for (model.Case c : judgeCases) {
                model.addRow(new Object[]{
                    c.getCaseId(),
                    c.getRegistrationNumber(),
                    c.getCaseTitle(),
                    c.getCaseType(),
                    c.getAssignedJudge(),
                    c.getCaseStatus(),
                    c.getHearingDate()
                });
            }
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        try {
            // 1. Gather Input
            int id = Integer.parseInt(txtJudgeId.getText());
            String name = txtJudgeFirstName.getText() + " " + txtJudgeLastName.getText();
            String username = txtJudgeUsername.getText();
            String contact = txtJudgeContact.getText();

            // Handle Passwords
            String password = new String(txtJudgePassword.getPassword());
            String confirmPass = new String(txtJudgeConfirmPass.getPassword());

            // 2. VALIDATION
            // Check A: Empty fields
            if (username.isEmpty() || password.isEmpty() || contact.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            // Check B: Password Length
            if (password.length() < 8) {
                javax.swing.JOptionPane.showMessageDialog(this, "Password must be at least 8 characters.");
                return;
            }

            // Check C: Confirm Password Match (The logic you asked for)
            if (!password.equals(confirmPass)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Passwords do not match! Please try again.");
                return; // Stop here, don't register
            }

            // 3. Create Object & Register
            Judge newJudge = new Judge(id, name, contact, username, password);
            JudgeController jc = new JudgeController();

            if (jc.registerJudge(newJudge)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Judge Registered Successfully!");

                // 4. Clear Fields
                txtJudgeId.setText("");
                txtJudgeFirstName.setText("");
                txtJudgeLastName.setText("");
                txtJudgeUsername.setText("");
                txtJudgePassword.setText("");
                txtJudgeConfirmPass.setText("");
                txtJudgeContact.setText("");

                // 5. Update the Dropdown in the other tab
                loadJudgesToComboBox();

            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Error: Judge ID or Username already exists.");
            }

        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: Judge ID must be a number.");
        }
    }//GEN-LAST:event_jButton14ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new AdminView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JComboBox<String> cmbFilterByJudge;
    private javax.swing.JComboBox<String> cmbJudge;
    private javax.swing.JComboBox<String> cmbJudge1;
    private javax.swing.JComboBox<String> cmbJudge3;
    private javax.swing.JComboBox<String> cmbSortBy;
    private javax.swing.JTable dashboardTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JLabel lblClosedCount;
    private javax.swing.JLabel lblPendingCount;
    private javax.swing.JLabel lblTotalCount;
    private javax.swing.JComboBox<String> rchkBailStatus;
    private javax.swing.JComboBox<String> rchkBailStatus1;
    private javax.swing.JComboBox<String> rcmbJudge;
    private javax.swing.JButton registerbutton;
    private javax.swing.JTextField rtxtCaseId;
    private javax.swing.JTextField rtxtCaseTitle;
    private javax.swing.JTextField rtxtCrimeType;
    private javax.swing.JTextField rtxtFirNumber;
    private javax.swing.JTextField rtxtHearingDate;
    private javax.swing.JTextField rtxtPoliceStation;
    private javax.swing.JTextField rtxtRegisterationNumber;
    private javax.swing.JTextArea rtxtSubjectMatter;
    private javax.swing.JTable tblDeleted;
    private javax.swing.JTable totalRegisteredCasesTable;
    private javax.swing.JTextField txtCaseId;
    private javax.swing.JTextField txtCaseId1;
    private javax.swing.JTextField txtCaseId3;
    private javax.swing.JTextField txtCaseTitle;
    private javax.swing.JTextField txtClaimAmount;
    private javax.swing.JTextField txtClaimAmount1;
    private javax.swing.JTextField txtClaimAmount3;
    private javax.swing.JTextField txtDisputeType;
    private javax.swing.JTextField txtHearing1;
    private javax.swing.JTextField txtHearingDate;
    private javax.swing.JPasswordField txtJudgeConfirmPass;
    private javax.swing.JTextField txtJudgeContact;
    private javax.swing.JTextField txtJudgeFirstName;
    private javax.swing.JTextField txtJudgeId;
    private javax.swing.JTextField txtJudgeLastName;
    private javax.swing.JPasswordField txtJudgePassword;
    private javax.swing.JTextField txtJudgeUsername;
    private javax.swing.JTextField txtRegNo;
    private javax.swing.JTextField txtRelief1;
    private javax.swing.JTextField txtReliefSought;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextArea txtSubjectMatter;
    // End of variables declaration//GEN-END:variables
}
