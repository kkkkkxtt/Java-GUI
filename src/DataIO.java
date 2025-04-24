
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataIO {
    // Use generic types for better type safety
    public static ArrayList<Manager> allManagers = new ArrayList<>();
    public static ArrayList<Scheduler> allSchedulers = new ArrayList<>();
    public static ArrayList<Sales> allSales = new ArrayList<>();
    public static ArrayList<Issue> allIssues = new ArrayList<>();

    // ---------------- Manager Read/Write Logic ------------------
    public static void writeManager(){
        try{
            PrintWriter writer_manager = new PrintWriter("Manager.txt");
            for(Manager x: allManagers){
                writer_manager.println(x.getUserid());
                writer_manager.println(x.getUsername());
                writer_manager.println(x.getPassword());
                writer_manager.println();
            }
            writer_manager.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error in write....");
        }
    }

    public static void laodManager() {
        try {
            File file = new File("Manager.txt");
            Scanner reader_manager = new Scanner(file);
            allManagers.clear(); // Clear the list to avoid duplication

            while (reader_manager.hasNextLine()) {
                String userid = reader_manager.nextLine().trim();
                if (userid.isEmpty()) continue; // Skip empty lines
                String username = reader_manager.nextLine().trim();
                String password = reader_manager.nextLine().trim();

                allManagers.add(new Manager(userid, username, password.toCharArray()));
            }
            reader_manager.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in read: " + e.getMessage());
            e.printStackTrace(); // This will print the stack trace for debugging
        }
    }

    // ---------------- Scheduler Read/Write Logic ------------------

    public static ArrayList<Scheduler> laodScheduler(){
        allSchedulers.clear(); // Clear the list to avoid duplication
        try {
            File file = new File("Scheduler.txt");
            Scanner reader_scheduler = new Scanner(file);
            while (reader_scheduler.hasNextLine()) {
                String userid = reader_scheduler.nextLine().trim();
                if (userid.isEmpty()) continue; // Skip empty lines

                String username = reader_scheduler.nextLine().trim();
                String password = reader_scheduler.nextLine().trim();

                allSchedulers.add(new Scheduler(userid, username, password.toCharArray()));
            }
            reader_scheduler.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in read: " + e.getMessage());
            e.printStackTrace(); // This will print the stack trace for debugging
        }
        return (ArrayList<Scheduler>) allSchedulers;
    }

    // ---------------- Issue Read/Write Logic ------------------
    public static ArrayList<Issue> loadIssues() {
        allIssues.clear(); // Clear the list to avoid duplication
        try {
            File file = new File("issues.txt");
            Scanner reader_issue = new Scanner(file);

            while (reader_issue.hasNextLine()) {
                String issueId = reader_issue.nextLine().trim();
                if (issueId.isEmpty()) continue; // Skip empty lines
                String hallId = reader_issue.nextLine().trim();
                String date = reader_issue.nextLine().trim();
                String issuesDetails = reader_issue.nextLine();
                String staff = reader_issue.nextLine().trim();
                String status = reader_issue.nextLine().trim();
                String respond = reader_issue.nextLine();

                allIssues.add(new Issue(issueId, hallId, date, issuesDetails, staff, status, respond));
            }
            reader_issue.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allIssues;
    }

    public static void updateIssues() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("issues.txt"))) {
            for (Issue issue : allIssues) {
                bw.write(String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n\n",
                        issue.getIssueId(), issue.getHallId(), issue.getDate(), issue.getDetails(),
                        issue.getStaff(), issue.getStatus(), issue.getRespond()));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing issues: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ---------------- Sales Read/Write Logic ------------------
    public static void loadSales() {
        allSales.clear();
        try {
            File file = new File("receipt.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String receiptId = reader.nextLine().trim();
                if (receiptId.isEmpty()) continue; // Skip empty lines
                String bookingId = reader.nextLine().trim();
                String date = reader.nextLine().trim();
                double cost = Double.parseDouble(reader.nextLine().trim());

                allSales.add(new Sales(receiptId, bookingId, date, cost));

                // Skip the empty line between records
                if (reader.hasNextLine()) {
                    reader.nextLine();
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Sales data file not found: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in reading sales data: " + e.getMessage());
            e.printStackTrace();
        }
    }

//    public static void updateSales() {
//        try (PrintWriter writer = new PrintWriter(new FileWriter("receipt.txt"))) {
//            for (Sales sale : allSales) {
//                writer.println(sale.getReceiptid());
//                writer.println(sale.getBookingid());
//                writer.println(sale.getDate());
//                writer.println(String.format("%.2f", sale.getSales()));
//                writer.println(); // Empty line between records
//            }
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Error saving sales data: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }

}

