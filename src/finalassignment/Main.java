package finalassignment;

import java.text.ParseException;
import strategy.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import employees.ContractEmployee;
import employees.Employee;
import employees.EmployeeFactory;
import employees.FullTimeEmployee;
import employees.PartTimeEmployee;
import enums.Gender;
import enums.Leavetype;
import enums.Status;
import filemanagers.FilenameFinder;
import filemanagers.FindEmployee;
import filemanagers.LoadEmployeesFromFiles;
import strategy.PayrollProcessor;
public class Main {

    public static void main(String[] args) {

        FilenameFinder fileFinder = new FilenameFinder();
        LoadEmployeesFromFiles loader = new LoadEmployeesFromFiles(fileFinder);
        List<Employee> allEmployees = loader.loadEmployees();

        FindEmployee finder = new FindEmployee(allEmployees);

        HRFunctions hr = new HRFunctions(); 
        ManagerFunctions Mr = new ManagerFunctions(allEmployees);
        EmployeeFunctions Er = new EmployeeFunctions(allEmployees, fileFinder);

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        loader.loadEmployees();
        hr.employees = allEmployees;
        hr.employeeFinder = new FindEmployee(allEmployees);

        Employee loggedIn = null;
        String loginId = null;
        while (loggedIn == null) {
            System.out.print("Enter Employee ID: ");
             loginId = sc.nextLine();
            System.out.print("Enter Password: ");
            String loginPass = sc.nextLine();

            Employee emp = finder.findEmployeeById(loginId);
            if (emp != null && emp.getPassword().equals(loginPass)) {
                loggedIn = emp;
                System.out.println("Login successful! Welcome, " + loggedIn.getFull_name());
            } else {
                System.out.println("Invalid ID or Password. Please try again.");
            }
        }
        while (true) {
        	System.out.println("1. Insert Employee");
        	System.out.println("2. Update Employees info");
        	System.out.println("3. Process Monthly Payroll");
        	System.out.println("4. Promote Employee");
        	System.out.println("5.Generate Salary and Tax reports ");
        	System.out.println("6. Approve, decline or delay leave request");
        	System.out.println("7. Request Leave");
        	System.out.println("8. Update personal Data");
        	System.out.println("9. Exit");
            int mainChoice = sc.nextInt();
            sc.nextLine();
            if (mainChoice >= 1 && mainChoice <= 4 &&
                    !loggedIn.getJob_title().equalsIgnoreCase("HR Specialist")) {
                    System.out.println("Access denied. Only HR Specialists can perform this action.\n");
                    continue;
                }
            if (mainChoice >= 5 && mainChoice <= 6 &&
                    !loggedIn.getJob_title().equalsIgnoreCase("Manager")) {
                    System.out.println("Access denied. Only Managers can perform this action.\n");
                    continue;
                }
            if ((mainChoice >= 7 && mainChoice <= 8) && 
            	    (!loggedIn.getJob_title().equalsIgnoreCase("Developer") 
            	     && !loggedIn.getJob_title().equalsIgnoreCase("Consultant"))) {
            	    System.out.println("Access denied. Only Employees can perform this action.\n");
            	    continue;
            	}

            if (mainChoice == 1) {
                try {
                    System.out.print("ID: ");
                    String id = sc.nextLine(); 
                    System.out.print("Full Name: ");
                    String name = sc.nextLine();
                    System.out.print("Date of Birth (yyyy-MM-dd): ");
                    Date dob = sdf.parse(sc.nextLine());
                    System.out.print("Gender (MALE/FEMALE): ");
                    Gender gender = Gender.valueOf(sc.nextLine().toUpperCase());
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Hire Date (yyyy-MM-dd): ");
                    Date hireDate = sdf.parse(sc.nextLine());
                    System.out.print("Job Title: ");
                    String jobTitle = sc.nextLine();
                    System.out.print("Department ID (1-5): ");
                    int deptId = sc.nextInt(); sc.nextLine();
                    System.out.print("Status (ACTIVE/INACTIVE): ");
                    Status status = Status.valueOf(sc.nextLine().toUpperCase());
                    System.out.print("Last Promotion Date (yyyy-MM-dd): ");
                    Date promoDate = sdf.parse(sc.nextLine());
                    System.out.print("Password: ");
                    String password = sc.nextLine();

                    System.out.println("Choose Employee Type:");
                    System.out.println("1. Full-Time");
                    System.out.println("2. Part-Time");
                    System.out.println("3. Contract");
                    int choice = sc.nextInt(); sc.nextLine();

                  
                    double salaryOrRate = 0;
                    int hoursOrMonths = 0;
                    double overallPayment = 0;

                    switch (choice) {
                        case 1: 
                            System.out.print("Salary: ");
                            salaryOrRate = sc.nextDouble();
                            sc.nextLine();
                            break;
                        case 2: 
                            System.out.print("Hourly Rate: ");
                            salaryOrRate = sc.nextDouble();
                            System.out.print("Working Hours: ");
                            hoursOrMonths = sc.nextInt();
                            sc.nextLine();
                            break;
                        case 3:
                            System.out.print("Number of Months: ");
                            hoursOrMonths = sc.nextInt();
                            System.out.print("Overall Payment: ");
                            overallPayment = sc.nextDouble();
                            sc.nextLine();
                            break;
                        default:
                            System.out.println("Invalid employee type.");
                            continue;
                    }

                  
                    Employee emp = EmployeeFactory.createEmployee(
                            choice, id, name, dob, gender, email, phone, hireDate,
                            jobTitle, deptId, status, promoDate, password,
                            salaryOrRate, hoursOrMonths, overallPayment
                    );

                    hr.insertEmployee(emp);
                    System.out.println("Employee added successfully!");

                } catch (ParseException e) {
                    System.out.println("Invalid date format! Please use yyyy-MM-dd.");
                }
            }
            else if (mainChoice == 2) {
                System.out.print("Enter employee ID to update: "); 
                String id = sc.next(); 

                boolean success = hr.UpdateEmployeeInfo(id);

                if (success) {
                    System.out.println("Employee updated successfully!");
                } else {
                    System.out.println("Failed to update employee.");
                }
            }

            else if (mainChoice == 3) {
                for (int i = 0; i < finder.getEmployeesCount(); i++) {
                    Employee emp = finder.getEmployeeByIndex(i);

                    System.out.println("\nProcessing payroll for: " + emp.getFull_name());
                    System.out.print("Enter bonuses: ");
                    double bonuses = sc.nextDouble();

                    PayrollStrategy strategy;

                    if (emp instanceof FullTimeEmployee) {
                        strategy = new FullTimePayrollStrategy();
                    } else if (emp instanceof PartTimeEmployee) {
                        strategy = new PartTimePayrollStrategy();
                    } else if (emp instanceof ContractEmployee) {
                        strategy = new ContractPayrollStrategy();
                    } else {
                        throw new IllegalArgumentException("Unknown employee type");
                    }

                    PayrollProcessor processor = new PayrollProcessor(strategy);
                    double netSalary = processor.process(emp, bonuses, 3);

                    System.out.println("Net Salary for " + emp.getFull_name() + ": " + netSalary);
                }
            }
            	else if (mainChoice == 4) {
            		System.out.print("Enter employee ID to promote: "); 
            		String id = sc.nextLine();

            		System.out.print("Enter new job title (HR Specialist / Consultant / Developer / Manager): ");
            		String newTitle = sc.nextLine();

            		boolean success = hr.PromoteEmployee(id, newTitle);
            		if (!success) {
            		    System.out.println("Promotion failed: employee not found.");
            		}

            		}
            	else if (mainChoice == 5) {
            	   
            	    ManagerFunctions manager = new ManagerFunctions(hr); 

           
            	    manager.generateDepartmentReports();
            	}

            	else if (mainChoice == 6) { 
            		Mr.processleaverequest(loggedIn);

            	}


            	else if (mainChoice == 7) {

            	    System.out.print("Enter LeaveType (SICK, VACATION, MATERNITY, PATERNITY, UNPAID): ");
            	    Leavetype leaveType = Leavetype.valueOf(sc.nextLine().toUpperCase());
            	    try {
            	        System.out.print("Enter Start Date (yyyy-MM-dd): ");
            	        Date start = sdf.parse(sc.nextLine());

            	        System.out.print("Enter End Date (yyyy-MM-dd): ");
            	        Date end = sdf.parse(sc.nextLine());

            	    Er.requestLeave(loginId, leaveType, start, end);
            	    System.out.println("Leave requested successfully!");
            	}
            catch (ParseException e) {
    	        System.out.println("Invalid date format! Please use yyyy-MM-dd.");
    	    }}
            	else if(mainChoice==8) {
            		boolean success =Er.UpdateEmployeepersonalInfo(loginId);

                    if (success) {
                        System.out.println("Employee updated successfully!");
                    } else {
                        System.out.println("Failed to update employee.");
                    }
            	}
            else if (mainChoice == 9) {
                System.out.println("Exiting...");
                break;

            } else {
                System.out.println("Invalid option!");
            }
        }

    }

}
