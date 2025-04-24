import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Manager extends UserData {
    private JTable issuesTable;
    private ArrayList<Issue> issues;

    // Add a no-argument constructor
    public Manager() {
        super("", "", new char[0]);
        this.issues = (ArrayList<Issue>) DataIO.loadIssues();
    }

    public Manager(String userid, String username, char[] password) {
        super(userid, username, password);
        this.issues = (ArrayList<Issue>) DataIO.loadIssues(); // Assuming DataIO has a method to load issues
    }

    private void loadIssues() {
        this.issues = DataIO.loadIssues();
    }

    public void setIssuesTable(JTable issuesTable) {
        this.issuesTable = issuesTable;
        MaintenanceOperations.updateIssuesTable();
    }

    public void respondToIssue() {
        int selectedRow = issuesTable.getSelectedRow();
        if (selectedRow != -1) {
            Issue issue = issues.get(selectedRow);
            JDialog respondDialog = new JDialog(MaintenanceOperations.operationsPage, "Respond to Issue", true);
            respondDialog.setSize(400, 200);
            respondDialog.setLocationRelativeTo(MaintenanceOperations.operationsPage);

            JPanel contentPane = new JPanel(new BorderLayout());
            JTextArea respondTextArea = new JTextArea();
            respondTextArea.setRows(3);
            respondTextArea.setLineWrap(true);  // Enable line wrapping
            respondTextArea.setWrapStyleWord(true);  // Wrap at word boundaries
            JScrollPane scrollPane = new JScrollPane(respondTextArea);  // Add scroll pane for vertical scrolling if needed

            JLabel RespondLabel = new JLabel("Respond:", SwingConstants.LEFT);
            RespondLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
            contentPane.add(RespondLabel, BorderLayout.NORTH);
            contentPane.add(scrollPane, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
            JButton saveButton = new JButton("Save");
            JButton cleanUpButton = new JButton("Clean up");
            JButton cancelButton = new JButton("Cancel");

            saveButton.addActionListener(e -> {
                String response = respondTextArea.getText().trim();
                if (!response.isEmpty()) {
                    issue.setRespond(response); //setter to access and add respond
                    MaintenanceOperations.updateIssuesTable();
                    DataIO.updateIssues();  //write to file
                    JOptionPane.showMessageDialog(respondDialog, "Response saved successfully.");
                    respondDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(respondDialog, "Response failed to saved. \nPlease try again!");
                }
            });

            cleanUpButton.addActionListener(e -> respondTextArea.setText(""));

            cancelButton.addActionListener(e -> respondDialog.dispose());

            buttonPanel.add(saveButton);
            buttonPanel.add(cleanUpButton);
            buttonPanel.add(cancelButton);

            contentPane.add(buttonPanel, BorderLayout.SOUTH);
            respondDialog.setContentPane(contentPane);
            respondDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(MaintenanceOperations.operationsPage, "Please select an issue first.");
        }
    }

    public void assignStaff() {
        int selectedRow = issuesTable.getSelectedRow();     //row select to edit
        if (selectedRow != -1) {
            Issue issue = issues.get(selectedRow);
            //Pop out Dialog for Staff list
            JDialog assignDialog = new JDialog(MaintenanceOperations.operationsPage, "Assign Staff", true);
            assignDialog.setSize(400, 200);
            assignDialog.setLocationRelativeTo(MaintenanceOperations.operationsPage);

            JPanel contentPane = new JPanel(new BorderLayout());
            ArrayList<Scheduler> allScheduler = DataIO.laodScheduler();
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (Scheduler scheduler : allScheduler) {
                listModel.addElement(scheduler.getUsername());
            }
            JList<String> staffList = new JList<>(listModel);
            contentPane.add(new JScrollPane(staffList), BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
            JButton saveButton = new JButton("Save");
            JButton cancelButton = new JButton("Cancel");

            saveButton.addActionListener(e -> {
                int selectedIndex = staffList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedStaff = allScheduler.get(selectedIndex).getUsername();
                    issue.setStaff(selectedStaff);
                    MaintenanceOperations.updateIssuesTable();
                    DataIO.updateIssues();
                    JOptionPane.showMessageDialog(assignDialog, "Staff assigned successfully.");
                    assignDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(assignDialog, "Please select a staff!");
                }
            });

            cancelButton.addActionListener(e -> assignDialog.dispose());

            buttonPanel.add(saveButton);
            buttonPanel.add(cancelButton);

            contentPane.add(buttonPanel, BorderLayout.SOUTH);
            assignDialog.setContentPane(contentPane);
            assignDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(MaintenanceOperations.operationsPage, "Please select an issue first.");
        }
    }

    public void changeStatus() {
        int selectedRow = issuesTable.getSelectedRow();
        if (selectedRow != -1) {
            Issue issue = issues.get(selectedRow);
            JDialog statusDialog = new JDialog(MaintenanceOperations.operationsPage, "Change Status", true);
            statusDialog.setSize(400, 200);
            statusDialog.setLocationRelativeTo(MaintenanceOperations.operationsPage);

            JPanel contentPane = new JPanel(new BorderLayout());
            JPanel radioButtonPanel = new JPanel(new GridLayout(4, 1));
            ButtonGroup statusGroup = new ButtonGroup();
            JRadioButton inProgressRadio = new JRadioButton("In progress");
            JRadioButton doneRadio = new JRadioButton("Done");
            JRadioButton closedRadio = new JRadioButton("Closed");
            JRadioButton cancelledRadio = new JRadioButton("Cancelled");
            statusGroup.add(inProgressRadio);
            statusGroup.add(doneRadio);
            statusGroup.add(closedRadio);
            statusGroup.add(cancelledRadio);
            radioButtonPanel.add(inProgressRadio);
            radioButtonPanel.add(doneRadio);
            radioButtonPanel.add(closedRadio);
            radioButtonPanel.add(cancelledRadio);

            contentPane.add(radioButtonPanel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
            JButton saveButton = new JButton("Save");
            JButton cancelButton = new JButton("Cancel");

            saveButton.addActionListener(e -> {
                if (!inProgressRadio.isSelected() && !doneRadio.isSelected() && !closedRadio.isSelected() && !cancelledRadio.isSelected()) {
                    JOptionPane.showMessageDialog(statusDialog, "Please choose a status.");
                } else {
                    if (inProgressRadio.isSelected()) {
                        issue.setStatus("In progress");
                    } else if (doneRadio.isSelected()) {
                        issue.setStatus("Done");
                    } else if (closedRadio.isSelected()) {
                        issue.setStatus("Closed");
                    } else if (cancelledRadio.isSelected()) {
                        issue.setStatus("Cancelled");
                    }
                    MaintenanceOperations.updateIssuesTable();
                    DataIO.updateIssues();
                    JOptionPane.showMessageDialog(statusDialog, "Status changed successfully.");
                    statusDialog.dispose();
                }
            });

            cancelButton.addActionListener(e -> statusDialog.dispose());

            buttonPanel.add(saveButton);
            buttonPanel.add(cancelButton);

            contentPane.add(buttonPanel, BorderLayout.SOUTH);
            statusDialog.setContentPane(contentPane);
            statusDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(MaintenanceOperations.operationsPage, "Please select an issue first.");
        }

    }
}