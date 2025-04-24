# Hall Management System - Manager Module (Issues and Sales Management Role)

![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Swing](https://img.shields.io/badge/GUI-Swing-orange.svg)

A comprehensive Java Swing application for managing hall operations, including maintenance tracking, sales reporting, and user management.

## Features

- **Manager Dashboard**:
  - Access to maintenance operations
  - Sales report viewing with filtering capabilities
  - Issue management system

- **Issue Management**:
  - Track and respond to hall maintenance issues
  - View issue status (Done/Closed)
  - Staff assignment tracking
  - Detailed issue reporting

- **Sales Reporting**:
  - View booking receipts and sales data
  - Generate sales reports with multiple filters:
    - Weekly sales
    - Monthly sales
    - Yearly sales
    - Custom date ranges
  - Sales summary and analytics

- **User Management**:
  - Secure login system for managers and schedulers
  - Role-based access control
  - Password-protected accounts

## System Components

### Core Classes
- **Sales.java**: Data model for sales transactions
- **SalesDashboard.java**: GUI interface for sales reporting
- **SalesService.java**: Business logic for sales data processing
- **UserData.java**: Base class for user account information
- **Scheduler.java**: Extended user class for scheduler accounts
- **Manager.txt**: Database of manager accounts
- **Scheduler.txt**: Database of scheduler accounts

### Data Files
- **Receipt.txt**: Stores all booking receipt data
- **Issues.txt**: Tracks maintenance issues and resolutions
- **Manager.txt**: Manager account credentials
- **Scheduler.txt**: Scheduler account credentials

## Technologies Used

- Java 17
- Java Swing for GUI components
- Object-Oriented programming
- File-based data storage (TXT files)
- Object-oriented design patterns
- Model-View-Controller (MVC) architecture
- Date and time manipulation for reporting

## Getting Started

### Prerequisites
- Java JDK 17 or later
- Basic understanding of Java Swing applications

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/kkkkkxtt/java-gui-hall-management-system.git
   ```
2. Navigate to the project directory
3. Clone the repository:
   ```bash
   javac *.java
   ```
4. Run the main application class

## Usage
- Login: Use credentials from Manager.txt or Scheduler.txt

- Dashboard: Navigate between different modules

- Sales Reporting:

  - View all sales data

  - Filter by week, month, or year

  - View summarized sales information

- Issue Management:

  - Track ongoing maintenance issues

  - Update issue status

  - View resolved issues

## Data Structure
### Sales Data Format
```
ReceiptID
BookingID
Date (YYYY-MM-DD)
Amount
```
### Issue Tracking Format
```
IssueID
HallID
Date (DD-MM-YYYY)
Description
Assigned Staff
Status
Notes
```
### User Account Format
```
UserID
Username
Password
```
