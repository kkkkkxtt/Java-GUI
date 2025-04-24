import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MaintenanceOperations implements CommonGUI, Navigation{
    public static JFrame operationsPage;
    private JButton respond, assignStaff, updateStatus, returnHome;
    private String[] issuesColumns = {"Issue ID", "Hall ID", "Report Date", "Report Details", "Responsible Staff", "Status", "Respond"};
    public static DefaultTableModel issuesTableModel;
    private static JTable issuesTable;
    private JScrollPane scrollPane;

    // Use Manager to handle issue operations
    private Manager manager;

    public MaintenanceOperations(Manager manager) {
        this.manager = manager;  // Pass Manager instance for handling operations
        this.issuesTableModel = new DefaultTableModel(issuesColumns, 0); // Initialize issuesTableModel
    }

    public void openDashboard() {
        createOperationsDashboardGUI();
    }

    public void createOperationsDashboardGUI() {
        operationsPage = createJFrame("Maintenance Operations", 1000, 500);
        JPanel mainPanel = createPanel(new JPanel());
        JLabel issuesReportLabel = createPageLabel("Issues Report");
        mainPanel.add(issuesReportLabel, BorderLayout.NORTH);

        issuesTable = createJTable(issuesColumns, issuesTableModel);
        manager.setIssuesTable(issuesTable); // Pass the issuesTable to Manager
        issuesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only single selection
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < issuesTable.getColumnCount(); i++) {
            issuesTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }
        // Add mouse listener to the table
        issuesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = issuesTable.rowAtPoint(e.getPoint());
                int col = issuesTable.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    String columnName = issuesTable.getColumnName(col);
                    if (columnName.equals("Report Details") || columnName.equals("Respond")) {
                        String content = issuesTable.getValueAt(row, col).toString();
                        showFullContentDialog(columnName, content);
                    }
                }
            }
        });
        scrollPane = new JScrollPane(issuesTable);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout(0, 10));
        JPanel upperButtonPanel = new JPanel(new GridLayout(5, 1, 0, 0));

        respond = createButton("Respond");
        assignStaff = createButton("Assign Staff");
        updateStatus = createButton("Change Status");
        returnHome = createButton("Return Home");

        respond.addActionListener(e -> manager.respondToIssue());
        assignStaff.addActionListener(e -> manager.assignStaff());
        updateStatus.addActionListener(e -> manager.changeStatus());
        returnHome.addActionListener(e -> returnHome(operationsPage));

        upperButtonPanel.add(respond);
        upperButtonPanel.add(assignStaff);
        upperButtonPanel.add(updateStatus);

        buttonPanel.add(upperButtonPanel, BorderLayout.NORTH);
        buttonPanel.add(returnHome, BorderLayout.SOUTH);

        mainPanel.add(buttonPanel, BorderLayout.WEST);

        operationsPage.add(mainPanel);
        operationsPage.setVisible(true);
    }

    // Method to load issues into the table
    public static void updateIssuesTable() {
        issuesTableModel.setRowCount(0); // Clear the table
        for (Issue issue : DataIO.allIssues) {
            Object[] rowData = {issue.getIssueId(), issue.getHallId(), issue.getDate(),
                    issue.getDetails(), issue.getStaff(), issue.getStatus(), issue.getRespond()};
            issuesTableModel.addRow(rowData);
        }
    }

    private void showFullContentDialog(String columnName, String content) {
        JDialog dialog = new JDialog(operationsPage, columnName, true);
        JTextArea textArea = new JTextArea(content);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        dialog.add(scrollPane);
        dialog.pack();
        dialog.setLocationRelativeTo(operationsPage);
        dialog.setVisible(true);
    }

}