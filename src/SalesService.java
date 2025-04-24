
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SalesService {
    private List<Sales> allSales;

    public SalesService(List<Sales> allSales) {
        this.allSales = allSales;
    }

    public void viewSales(String type, DefaultTableModel tableModel, JLabel summaryLabel) {
        tableModel.setRowCount(0);
        Map<String, Double> aggregatedSales = new TreeMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();

        for (Sales sale : allSales) {
            try {
                Date saleDate = sdf.parse(sale.getDate());
                cal.setTime(currentDate);

                boolean includeInFilter = false;
                String key = "";

                switch (type) {
                    case "WEEKLY":
                        cal.add(Calendar.DAY_OF_MONTH, -7);
                        if (saleDate.after(cal.getTime()) && (saleDate.before(currentDate) || saleDate.equals(currentDate))) {
                            includeInFilter = true;
                            key = sale.getDate();
                        }
                        break;
                    case "MONTHLY":
                        key = new SimpleDateFormat("yyyy-MM").format(saleDate);
                        includeInFilter = true;
                        break;
                    case "LASTMONTH":
                        cal.add(Calendar.MONTH, -1);
                        if (saleDate.after(cal.getTime()) && (saleDate.before(currentDate) || saleDate.equals(currentDate))) {
                            includeInFilter = true;
                            key = sale.getDate();
                        }
                        break;
                    case "YEARLY":
                        key = new SimpleDateFormat("yyyy").format(saleDate);
                        includeInFilter = true;
                        break;
                    default: // ALL
                        key = sale.getDate();
                        includeInFilter = true;
                }

                if (includeInFilter) {
                    aggregatedSales.put(key, aggregatedSales.getOrDefault(key, 0.0) + sale.getSales());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        for (Map.Entry<String, Double> entry : aggregatedSales.entrySet()) {
            tableModel.addRow(new Object[]{
                    entry.getKey(), String.format("%.2f", entry.getValue())
            });
        }

        summarizeSales(aggregatedSales, summaryLabel);
    }

    private void summarizeSales(Map<String, Double> aggregatedSales, JLabel label) {
        if (aggregatedSales.isEmpty()) {
            label.setText("No sales data available for the selected period.");
            return;
        }

        String period = aggregatedSales.keySet().stream().min(String::compareTo).get() + " to " +
                aggregatedSales.keySet().stream().max(String::compareTo).get();
        double totalSales = aggregatedSales.values().stream().mapToDouble(Double::doubleValue).sum();

        label.setText(String.format("Period: %s, Total Sales: RM %.2f", period, totalSales));
    }
}

