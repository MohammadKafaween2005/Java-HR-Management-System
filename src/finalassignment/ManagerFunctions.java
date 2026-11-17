package finalassignment;

import java.util.ArrayList;
import strategy.*;
import decorators.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import employees.*;
import employees.Employee;
import filemanagers.FindEmployee;
import filemanagers.ManagerFileManager;
import strategy.FullTimePayrollStrategy;
import strategy.PayrollStrategy;

public class ManagerFunctions {
    private List<Employee> employees;
    private FindEmployee employeeFinder;
    ManagerFileManager mfm = new ManagerFileManager();
    private HRFunctions hrFunctions;
    public ManagerFunctions(List<Employee> employees) {
        this.employees = employees;
        this.employeeFinder = new FindEmployee(employees);
    }
    public ManagerFunctions(HRFunctions hrFunctions) {
        if (hrFunctions == null) {
            throw new IllegalArgumentException("HRFunctions cannot be null");
        }
        this.hrFunctions = hrFunctions;
    }
    public void generateDepartmentReports() {
        List<Employee> employees = hrFunctions.employees;

        Map<Integer, List<Employee>> deptMap = new HashMap<>();
        for (Employee emp : employees) {
            int deptId = emp.getDepartment_id();
            deptMap.computeIfAbsent(deptId, k -> new ArrayList<>()).add(emp);
        }

        for (Map.Entry<Integer, List<Employee>> entry : deptMap.entrySet()) {
            int deptId = entry.getKey();
            List<Employee> deptEmployees = entry.getValue();

            System.out.println("\n=== Department " + deptId + " Report ===");
            double deptTotalSalary = 0;
            double deptTotalTax = 0;

            System.out.printf("%-15s | %-10s | %-10s%n", "Employee Name", "Salary", "Tax");

            for (Employee emp : deptEmployees) {
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
            	double salary = processor.process(emp, 0, 5); 

                double tax = hrFunctions.processTax(emp.calculatemonthlyincome());

                salary = Math.round(salary * 100.0) / 100.0;
                tax = Math.round(tax * 100.0) / 100.0;

                System.out.printf("%-15s | %-10.2f | %-10.2f%n", emp.getFull_name(), salary, tax);

                deptTotalSalary += salary;
                deptTotalTax += tax;
            }

            System.out.println("------------------------------");
            System.out.printf("Department Total Salary: %.2f%n", deptTotalSalary);
            System.out.printf("Department Total Tax: %.2f%n", deptTotalTax);
        }
    }

    public void processleaverequest(Employee loggedIn) {
        Scanner sc = new Scanner(System.in);
        
    	mfm.loadrequeststoarray();
		System.out.println("Which employee's request do you want to approve or reject?");
		String empId = sc.nextLine();

		System.out.print("Approve or Reject? ");
		String decision = sc.nextLine().trim().toUpperCase();

		if (!decision.equals("APPROVE") && !decision.equals("REJECT")) {
		    System.out.println("Invalid input! Please type 'Approve' or 'Reject'.");
		} else {
		    mfm.updateRequestStatus(empId, decision.equals("APPROVE") ? "Approved" : "Rejected", loggedIn.getEmployee_id());
		}
    }
}
