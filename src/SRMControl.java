import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Scanner;

public class SRMControl {

    // Menu ---------------------------------------------------------------------
    // Main menu
    public static void mainmenu() {
        System.out.println("----------------------------------------");
        System.out.println("Welcome to the Student Management System");
        System.out.println("----------------------------------------");
        System.out.println("1 - Student Login");
        System.out.println("2 - Admin Login");
        System.out.println("9 - Exit");
        System.out.println("Please enter your choice: ");
    }

    // Student page menu
    public static void studentloginmenu() {
        System.out.println("----------------------------------------");
        System.out.println("Welcome to the Student Management System");
        System.out.println("----------------------------------------");
        System.out.println("1 - View Student Record");
        System.out.println("2 - Find Student Record");
        System.out.println("9 - Go back to main menu");
        System.out.println("Please enter your choice: ");
    }

    // Admin page menu
    public static void adminloginmenu() {
        System.out.println("----------------------------------------");
        System.out.println("Welcome to the Student Management System");
        System.out.println("----------------------------------------");
        System.out.println("1 - View Student Record");
        System.out.println("2 - Find Student Record");
        System.out.println("3 - Add Student Record");
        System.out.println("4 - Delete Student Record");
        System.out.println("5 - Admin Management");
        System.out.println("9 - Go back to main menu");
        System.out.println("Please enter your choice: ");
    }

    // Admin management page menu
    public static void adminmanagementmenu() {
        System.out.println("--------------------------------------");
        System.out.println("Welcome to the Admin Management System");
        System.out.println("--------------------------------------");
        System.out.println("1 - View Admin Record");
        System.out.println("2 - Find Admin Record");
        System.out.println("3 - Add Admin Record");
        System.out.println("4 - Delete Admin Record");
        System.out.println("9 - Go back to main menu");
        System.out.println("Please enter your choice: ");
    }
    // --------------------------------------------------------------------------


    // Main method --------------------------------------------------------------
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {
        Scanner scanner = new Scanner(System.in); // Create scanner object

        // Create StudentRecordManagement and AdminRecordManagement object
        StudentRecordManagement SRM = new StudentRecordManagement();
        AdminRecordManagement ARM = new AdminRecordManagement();


        // Create default admin ------------------------------------------------
        boolean checkdefaultadmin; // Check if default admin is created

        if (ARM.verifyAdmin("Admin", 0)) {
            checkdefaultadmin = true;  // Default admin is created
        } else {
            checkdefaultadmin = false;  // Default admin is not created
        }

        if (checkdefaultadmin == false) {  // If default admin is not created
            ARM.add("Admin", 0);  // Create default admin with username "Admin" and password "0"
        }
        // ---------------------------------------------------------------------


        // Run main menu -------------------------------------------------------
        mainmenu();  // Display main menu
        int choice = scanner.nextInt();  // Get user input

        switch (choice) {
            // Student login
            case 1:
                System.out.println("Welcome to the Student Login");
                System.out.println("Please enter your Student ID: ");
                int idNumber = scanner.nextInt();

                if (SRM.find(idNumber)) {  // If student is found
                    System.out.println("Student found");
                    studentPageAdmin();
                } else {  // If student is not found
                    do {  // Loop until student is found or user exits
                        System.out.println("Student not found");
                        System.out.println("1 - Try again");
                        System.out.println("2 - Go back to main menu");
                        System.out.println("Please enter your choice: ");
                        int choice2 = scanner.nextInt();  // Get user input between try again and go back to main menu
                        switch (choice2) {
                            // Try again
                            case 1:
                                System.out.println("Please enter your Student ID: ");
                                int idNumber2 = scanner.nextInt();
                                if (SRM.find(idNumber2)) {
                                    System.out.println("Student found");
                                    do {
                                        studentPageAdmin();
                                    } while (true);
                                } else {
                                    System.out.println("Student not found");
                                }
                                break;

                            // Go back to main menu
                            case 2:
                                System.out.println("Going back to main menu");
                                main(null);
                        }

                    } while (choice != 2);  // Loop until user exits
                }

            // Admin login
            case 2:
                System.out.println("Welcome to the Admin Login");
                System.out.println("Please enter your Admin Name: ");
                String name = scanner.next();
                System.out.println("Please enter your Admin Password: ");
                int password = scanner.nextInt();

                // verify admin
                boolean check = ARM.verifyAdmin(name, password);

                if (check) { // If admin is found
                    adminPageAdmin();
                } else { // If admin is not found
                    do {
                        System.out.println("1 - Try again");
                        System.out.println("2 - Go back to main menu");
                        System.out.println("Please enter your choice: ");
                        int choice2 = scanner.nextInt();
                        switch (choice2) {
                            // Try again
                            case 1:
                                System.out.println("Please enter your Admin Name: ");
                                String name2 = scanner.next();
                                System.out.println("Please enter your Admin Password: ");
                                int password2 = scanner.nextInt();

                                // verify admin
                                boolean check2 = ARM.verifyAdmin(name2, password2);

                                if (check2) { // If admin is found
                                    do {
                                        adminPageAdmin();
                                    } while (true);
                                }
                                break;

                            // Go back to main menu
                            case 2:
                                System.out.println("Go back to main menu");
                                main(null);
                        }

                    } while (choice != 2);
                }

            // Exit
            case 9:
                System.out.println("Thank you for using the Student Management System");
                System.out.println("-------------------------------------------------");
                System.exit(0);  // Exit program
                break;
        }
    }

    // Student page
    public static void studentPageAdmin() throws ParserConfigurationException, TransformerException, IOException, SAXException {

        Scanner scanner = new Scanner(System.in);  // Create scanner object
        StudentRecordManagement srm = new StudentRecordManagement();  // Create StudentRecordManagement object

        do {  // Loop until user exits
            studentloginmenu();  // Display student login menu
            int choice = scanner.nextInt();
            switch (choice) {  // Get user input between view student record, find student record and go back to main menu
                // View student record
                case 1:
                    System.out.println("View Student Record --------------------------");
                    for (Student student : srm.getStudentList()) {
                        System.out.println(student.toString());
                    }
                    break;

                // Find student record
                case 2:
                    System.out.println("Find Student Record --------------------------");
                    System.out.println("Please enter the Student ID: ");

                    int idNumber = scanner.nextInt();

                    if (srm.find(idNumber)) {  // If student is found
                        System.out.println("Student found . . .");
                        System.out.println(srm.getonestudent(idNumber).toString());  // Display student record
                        studentPageAdmin();  // Go to student page
                    } else {  // If student is not found
                        do {  // Loop until student is found or user exits
                            System.out.println("Student not found");
                            System.out.println("Try again . . .");
                            System.out.println("Please enter the Student ID: ");
                            idNumber = scanner.nextInt();
                            if (srm.find(idNumber)) {
                                System.out.println("Student found . . .");
                                System.out.println(srm.getonestudent(idNumber).toString());
                                studentPageAdmin();
                            } else {
                                System.out.println("Student not found");
                                System.out.println("1 - Try again");
                                System.out.println("2 - Go back to main menu");
                                System.out.println("Please enter your choice: ");
                                int choice2 = scanner.nextInt();

                                switch (choice2) {
                                    case 1:
                                        System.out.println("Please enter the Student ID: ");
                                        idNumber = scanner.nextInt();
                                        if (srm.find(idNumber)) {
                                            System.out.println("Student found . . .");
                                            System.out.println(srm.getonestudent(idNumber).toString());
                                            studentPageAdmin();
                                        } else {
                                            System.out.println("Student not found");
                                        }
                                        break;

                                    case 2:
                                        System.out.println("Go back to main menu");
                                        main(null);
                                        break;
                                }
                            }
                        } while (!srm.find(idNumber));

                    }
                    break;

                case 9:
                    System.out.println("Go back to main menu . . .");
                    main(null);
                    break;
            }
        } while (true);
    }

    // Admin page
    public static void adminPageAdmin() throws ParserConfigurationException, TransformerException, IOException, SAXException {
        Scanner scanner = new Scanner(System.in);
        StudentRecordManagement srm = new StudentRecordManagement();
        adminloginmenu();

        int choice = scanner.nextInt();

        switch (choice) {

            // Add student record
            case 1:
                System.out.println("View Student Record --------------------------");
                for (Student student : srm.getStudentList()) {
                    System.out.println(student.toString());
                }
                adminPageAdmin();
                break;

            // Find student record
            case 2:
                System.out.println("Find Student Record --------------------------");
                System.out.println("Please enter the Student ID: ");
                int idNumber = scanner.nextInt();
                if (srm.find(idNumber)) {
                    System.out.println("Student found . . .");
                    System.out.println(srm.getonestudent(idNumber).toString());
                    adminPageAdmin();
                } else {
                    do {
                        System.out.println("Student not found");
                        System.out.println("Please enter the Student ID: ");
                        idNumber = scanner.nextInt();
                        if (srm.find(idNumber)) {
                            System.out.println("Student found . . .");
                            System.out.println(srm.getonestudent(idNumber).toString());
                            adminPageAdmin();
                        }
                    } while (!srm.find(idNumber));
                    adminPageAdmin();

                }
                break;

            // Add student record
            case 3:
                System.out.println("Add Student Record --------------------------");
                StudentRecordManagement SRM = new StudentRecordManagement();
                System.out.println("Add Student");
                System.out.println("Please enter the Student ID: ");
                int id = scanner.nextInt();
                System.out.println("Please enter the Student Name: ");
                String name = scanner.next();
                System.out.println("Please enter the Student contact number: ");
                String contactNumber = scanner.next();

                System.out.println("Name: " + name);
                System.out.println("ID: " + id);
                System.out.println("Contact Number: " + contactNumber);
                System.out.println("--------------");
                System.out.println("1 - Confirm");
                System.out.println("2 - Re-enter");
                System.out.println("--------------");

                System.out.println("Please enter your choice: ");
                int choice2 = scanner.nextInt();

                switch (choice2) {
                    case 1:
                        SRM.add(id, name, contactNumber);
                        System.out.println("Student added");
                        adminPageAdmin();
                        break;
                    case 2:
                        do {
                            System.out.println("Re-enter");
                            System.out.println(". . .");
                            System.out.println("Please enter the Student ID: ");
                            id = scanner.nextInt();
                            System.out.println("Please enter the Student Name: ");
                            name = scanner.next();
                            System.out.println("Please enter the Student contact number: ");
                            contactNumber = scanner.next();
                            System.out.println("Name: " + name + " ID: " + id + " Contact Number: " + contactNumber);
                            System.out.println("1 - Confirm");
                            System.out.println("2 - Re-enter");
                            choice2 = scanner.nextInt();
                            switch (choice2) {
                                case 1:
                                    SRM.add(id, name, contactNumber);
                                    System.out.println("Student added");
                                    adminPageAdmin();
                                    break;
                                case 2:
                                    System.out.println("Re-enter");
                                    break;
                            }
                        } while (choice2 != 1);
                        adminPageAdmin();

                        break;
                }
                break;

            // Edit student record
            case 4:
                System.out.println("Delete Student --------------------------");
                System.out.println("Please enter the Student ID: ");
                int id2 = scanner.nextInt();
                srm.delete(id2);

                adminPageAdmin();
                break;

            // Admin management`
            case 5:
                AdminRecordManagement ARM = new AdminRecordManagement();
                System.out.println("Admin Management ---------------------");
                adminmanagementmenu();
                int choice3 = scanner.nextInt();
                switch (choice3) {
                    case 1:
                        System.out.println("View Admin Record ----------------------");
                        for (Admin admin : ARM.getAdminList()) {
                            System.out.println(admin.toString());
                        }
                        adminPageAdmin();
                        break;

                    case 2:
                        System.out.println("Find Admin Record --------------------------");
                        System.out.println("Please enter the Admin name: ");
                        String name2 = scanner.next();

                        boolean check = ARM.checkonlyname(name2);

                        if (check) {
                            System.out.println(ARM.findpassbyname(name2));
                            adminPageAdmin();

                        } else {
                            System.out.println("Admin not found");
                            adminPageAdmin();
                        }

                        case 3:
                            System.out.println("Add Admin Record --------------------------");

                            System.out.println("Please enter the Admin name: ");
                            String name3 = scanner.next();
                            System.out.println("Please enter the Admin password: ");
                            int password3 = Integer.parseInt(scanner.next());

                            System.out.println("Name: " + name3);
                            System.out.println("Password: " + password3);

                            System.out.println("1 - Confirm");
                            System.out.println("2 - Re-enter");
                            System.out.println("--------------");

                            System.out.println("Please enter your choice: ");
                            int choice4 = scanner.nextInt();

                            switch (choice4) {
                                case 1:
                                    ARM.add(name3, password3);
                                    System.out.println("Admin added");
                                    adminPageAdmin();
                                    break;
                                case 2:
                                    do {
                                        System.out.println("Re-enter");
                                        System.out.println(". . .");
                                        System.out.println("Please enter the Admin name: ");
                                        name3 = scanner.next();
                                        System.out.println("Please enter the Admin password: ");
                                        password3 = Integer.parseInt(scanner.next());
                                        System.out.println("Name: " + name3 + " Password: " + password3);
                                        System.out.println("1 - Confirm");
                                        System.out.println("2 - Re-enter");
                                        choice4 = scanner.nextInt();
                                        switch (choice4) {
                                            case 1:
                                                ARM.add(name3, password3);
                                                System.out.println("Admin added");
                                                adminPageAdmin();
                                                break;
                                            case 2:
                                                System.out.println("Re-enter");
                                                break;
                                        }
                                    } while (choice4 != 1);
                                    adminPageAdmin();
                                    break;

                            }
                            break;

                        case 4:
                            System.out.println("Delete Admin --------------------------");
                            System.out.println("Please enter the Admin name: ");
                            String name4 = scanner.next();
                            ARM.delete(name4);
                            adminPageAdmin();
                            break;

                    // go back to admin page
                    case 9:
                        System.out.println("Go back to main menu . . .");
                        main(null);
                        break;
                }
        }
    }
}