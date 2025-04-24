
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SalesDashboard implements CommonGUI, Navigation{
    private JFrame salesPage;
    private JButton viewAll, filterWeekly, filterLastMonth, filterMonthly, filterYearly;
    private String[] salesColumns = {"Period", "Total Sales (RM)"};
    public static JLabel summaryLabel;
    private JButton returnHome;
    private DefaultTableModel salesTableModel;
    private SalesService salesService;
    private JTable salesTable;
    private JScrollPane scrollPane;

    public SalesDashboard(SalesService salesService) {
        this.salesService = salesService;
        this.salesTableModel = new DefaultTableModel(salesColumns, 0); // Initialize issuesTableModel
    }

    public void openDashboard() {
        createSalesDashboardGUI();
    }

    public void createSalesDashboardGUI() {
        salesPage = createJFrame("Sales Dashboard", 800, 550);
        JPanel mainPanel = createPanel(new JPanel());
        JLabel salesReportLabel = createPageLabel("Sales Report");
        mainPanel.add(salesReportLabel, BorderLayout.NORTH);

        salesTable = createJTable(salesColumns, salesTableModel);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        for (int i = 0; i < salesTable.getColumnCount(); i++) {
            salesTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }
        scrollPane = new JScrollPane(salesTable);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout(0, 10));
        JPanel upperButtonPanel = new JPanel(new GridLayout(5, 1, 0, 0));

        viewAll = createButton("View All");
        filterWeekly = createButton("Filter Weekly");
        filterLastMonth = createButton("Filter Last Month");
        filterMonthly = createButton("Filter Monthly");
        filterYearly = createButton("Filter Yearly");
        returnHome = createButton("Return Home");

        viewAll.addActionListener(e -> salesService.viewSales("ALL", salesTableModel, summaryLabel));
        filterWeekly.addActionListener(e -> salesService.viewSales("WEEKLY", salesTableModel, summaryLabel));
        filterLastMonth.addActionListener(e -> salesService.viewSales("LASTMONTH", salesTableModel, summaryLabel));
        filterMonthly.addActionListener(e -> salesService.viewSales("MONTHLY", salesTableModel, summaryLabel));
        filterYearly.addActionListener(e -> salesService.viewSales("YEARLY", salesTableModel, summaryLabel));
        returnHome.addActionListener(e -> returnHome(salesPage));

        upperButtonPanel.add(viewAll);
        upperButtonPanel.add(filterWeekly);
        upperButtonPanel.add(filterLastMonth);
        upperButtonPanel.add(filterMonthly);
        upperButtonPanel.add(filterYearly);

        buttonPanel.add(upperButtonPanel, BorderLayout.NORTH);
        buttonPanel.add(returnHome, BorderLayout.SOUTH);

        mainPanel.add(buttonPanel, BorderLayout.WEST);

        summaryLabel = new JLabel();
        summaryLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        summaryLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        summaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(summaryLabel, BorderLayout.SOUTH);

        salesPage.add(mainPanel);
        salesPage.setVisible(true);
    }


    @Override
    public JFrame createJFrame(String text, int w, int h) {
        return CommonGUI.super.createJFrame(text, w, h);
    }

    @Override
    public JLabel createPageLabel(String text) {
        return CommonGUI.super.createPageLabel(text);
    }

    @Override
    public JPanel createPanel(JPanel panel) {
        return CommonGUI.super.createPanel(panel);
    }

    @Override
    public JButton createButton(String text) {
        return CommonGUI.super.createButton(text);
    }

    @Override
    public JTable createJTable(String[] columns, DefaultTableModel tableModel) {
        return CommonGUI.super.createJTable(columns, tableModel);
    }

    @Override
    public void returnHome(JFrame currentPage) {
        Navigation.super.returnHome(currentPage);
    }

}
