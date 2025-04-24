
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public interface CommonGUI {
    default JFrame createJFrame(String text, int w, int h) {
        JFrame page = new JFrame(text);
        page.setLayout(new BorderLayout());
        page.setSize(w, h);
        page.setLocationRelativeTo(null);
        return page;
    }

    default JLabel createPageLabel(String text) {
        JLabel pageLabel = new JLabel(text);
        pageLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pageLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return pageLabel;
    }

    default JPanel createPanel(JPanel panel) {
        panel.setBackground(new Color(255, 255, 255));
        panel.setLayout(new BorderLayout());
        return panel;
    }

    default JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Times New Roman", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(120, 60));
        button.setFocusPainted(false);
        button.setBackground(new Color(220, 220, 220));
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        return button;
    }

    default JTable createJTable(String[] columns, DefaultTableModel tableModel) {
        if (tableModel == null) {
            tableModel = new DefaultTableModel(columns, 0);
        }
        JTable table = new JTable(tableModel);
        table.setEnabled(true);
        table.setDefaultEditor(Object.class, null);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Times New Roman", Font.BOLD, 14));
        tableHeader.setReorderingAllowed(false);
        table.setRowHeight(25);
        return table;
    }
}

