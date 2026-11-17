package finalassignment;
import java.util.Scanner;

import employees.Employee;
import filemanagers.FindEmployee;
public class Login {
    private HRFunctions hr;
    public Login(HRFunctions hr) {
        this.hr = hr;
}
    
    public Employee signIn() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your Employee ID: ");
        String id = sc.nextLine().trim();

        System.out.print("Enter your Password: ");
        String password = sc.nextLine().trim();
        sc.close();
        FindEmployee finder = new FindEmployee(hr.employees);
        Employee emp = finder.findEmployeeById(id);

        if (emp == null) {
            System.out.println("Employee not found!");
            return null;
        }

        if (!emp.getPassword().equals(password)) {
            System.out.println("Incorrect password!");
            return null;
        }

        System.out.println("Welcome " + emp.getFull_name() + "! Job Title: " + emp.getJob_title());
        return emp;
    }

}
