
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuManager implements ActionListener, CommonGUI, Navigation {
    private JFrame page;
    private JButton sales, issues, logout;

    public MenuManager() {
        page = createJFrame("Manager Menu", 400, 180);

        JLabel managerMenuLabel = createPageLabel("Manager Menu");
        JPanel labelPanel = createPanel(new JPanel());
        labelPanel.add(managerMenuLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        sales = createButton("Sales Report");
        issues = createButton("Issues Report");
        logout = createButton("Logout");

        sales.addActionListener(this);
        issues.addActionListener(this);
        logout.addActionListener(this);

        buttonPanel.add(sales);
        buttonPanel.add(issues);
        buttonPanel.add(logout);
        buttonPanel.setBackground(new Color(255, 255, 255));

        page.add(labelPanel, BorderLayout.NORTH);
        page.add(buttonPanel, BorderLayout.CENTER);

        page.setVisible(true);
    }

    public JFrame getFrame() {
        return page;
    }

    @Override
    public JButton createButton(String text) {
        JButton button = CommonGUI.super.createButton(text);
        button.setPreferredSize(new Dimension(100, 40));
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().equals(logout)) {
                logout(page);
            } else if (e.getSource().equals(sales)) {
                page.setVisible(false);
                openSalesDashboard();
            } else if (e.getSource().equals(issues)) {
                page.setVisible(false);
                openMaintenanceoperations();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(page, "An error occurred: " + ex.getMessage());
        }
    }

    private void openSalesDashboard() {
        DataIO.loadSales();
        SalesService salesService = new SalesService(DataIO.allSales);
        SalesDashboard dashboard = new SalesDashboard(salesService);
        dashboard.openDashboard();
    }

    private void openMaintenanceoperations() {
        try {
            DataIO.loadIssues();  // Load issues data
            Manager manager = new Manager();  // Create Manager instance using the new no-arg constructor
            MaintenanceOperations maintenanceOps = new MaintenanceOperations(manager);
            maintenanceOps.openDashboard();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error opening Maintenance Operations: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
