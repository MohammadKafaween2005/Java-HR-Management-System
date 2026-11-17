package finalassignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import employees.ContractEmployee;
import employees.Employee;
import employees.FullTimeEmployee;
import employees.PartTimeEmployee;
import employees.Payroll;
import filemanagers.EmployeeFileManager;
import filemanagers.EmployeeFileUpdater;
import filemanagers.FilenameFinder;
import filemanagers.FindEmployee;
import filemanagers.PayrollFileManager;

public class HRFunctions {
    public List<Employee> employees = new ArrayList<>();
    FilenameFinder fileFinder = new FilenameFinder();
    EmployeeFileUpdater updater = new EmployeeFileUpdater(fileFinder);
    EmployeeFileManager empFM = new EmployeeFileManager();
    Payroll payrollemp = new Payroll();
    public FindEmployee employeeFinder = new FindEmployee(employees);
    public PayrollFileManager payrollFM = new PayrollFileManager(); 

    
    
    public void insertEmployee(Employee emp) {
        employees.add(emp);
        fileFinder.findFileForEmployee(emp);
        empFM.Fileopenerandwriter(emp);

    }
    public void ShowAllEmployees() {

        for (String file : fileFinder.files) {
            System.out.println("=== Employees from " + file + " ===");
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("No data found in " + file + " (maybe empty).");
            }
            System.out.println("--------------------------");
        }
    }
    public double processDeductions() {
        double insurance = 20;
        return insurance;
    }

    public double processTax(double monthlyPayment) {
        return monthlyPayment * 0.16;
    }
   

 
 
public boolean UpdateEmployeeInfo(String employee_id) {
	 Employee emp = employeeFinder.findEmployeeById(employee_id);
	 System.out.println(employeeFinder.findEmployeeById(employee_id));
     if (emp == null) {
         System.out.println("Employee not found!");
         return false;
     }
System.out.println("Which Attribute do you want to change?\n1-Employee ID\n2-Full Name\n3-Date of Birth\n4-Gender\n5-Email\n6-Phone Number"
		+ "\n7-Hire_date\n8-Job_title\n9-Department ID\n10-Status\n11-Last Promotion Date\n12-Password");
if (emp instanceof FullTimeEmployee) {
    System.out.println("13-Salary");
} else if (emp instanceof PartTimeEmployee) {
    System.out.println("13-Hourly Rate\n14-Hours Worked");
} else if (emp instanceof ContractEmployee) {
    System.out.println("13-No of Months\n14-Overall Payment");
}
	 Scanner sc1 = new Scanner(System.in);
int Choice= sc1.nextInt(); 
updater.updateEmployee(emp, Choice);
     return true;
}
public boolean PromoteEmployee(String id, String newTitle) {
    List<String> validTitles = Arrays.asList("HR Specialist", "Consultant", "Developer", "Manager");
    Employee emp = employeeFinder.findEmployeeById(id);
    if (emp != null && validTitles.contains(newTitle)) {
        emp.setJob_title(newTitle);
        emp.setLast_promotion_date(new Date());
        fileFinder.findFileForEmployee(emp);
        updater.updateJobTitleAndPromotion(emp);
        System.out.println("Employee promoted successfully.");
        return true;
    }
    return false;
}


}

