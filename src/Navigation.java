import javax.swing.*;

public interface Navigation {
    default void returnHome(JFrame currentPage) {
        currentPage.setVisible(false);
        MenuManager menuManager = new MenuManager();
        menuManager.getFrame().setVisible(true);
    }

    default void logout(JFrame currentPage) {
        int respond = JOptionPane.showConfirmDialog(currentPage, "Do you want to proceed?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
        if (respond == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "You have successfully logged out");
            System.exit(0);
        }
    }
}
