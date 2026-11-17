package finalassignment;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import employees.Employee;
import employees.LeaveRequest;
import enums.Approvalstatus;
import enums.Leavetype;
import filemanagers.EmployeeFileManager;
import filemanagers.EmployeeFileUpdater;
import filemanagers.FilenameFinder;
import filemanagers.FindEmployee;
import filemanagers.LeavesFileManager;
public class EmployeeFunctions {
    public List<Employee> employees;
    EmployeeFileManager empFM = new EmployeeFileManager();
    LeavesFileManager Lfm = new LeavesFileManager();
    FilenameFinder fileFinder;
    EmployeeFileUpdater updater;
    FindEmployee employeeFinder;
    public List<String> Leaves = new ArrayList<>();

    public EmployeeFunctions(List<Employee> allEmployees, FilenameFinder finder) {
        this.employees = allEmployees;
        this.fileFinder = finder;
        this.updater = new EmployeeFileUpdater(finder);
        this.employeeFinder = new FindEmployee(employees);
    }

    public boolean requestLeave(String employee_id, Leavetype leaveType, Date start, Date end) {
        Employee emp = employeeFinder.findEmployeeById(employee_id);
        if (emp == null) return false;
        LeaveRequest leave = new LeaveRequest(emp, leaveType, start, end, Approvalstatus.Pending, null);
        Lfm.OpenLeavesandwrite(leave);
        return true;
    }



    public boolean UpdateEmployeepersonalInfo(String employee_id) {
        Employee emp = employeeFinder.findEmployeeById(employee_id);
        if (emp == null) {
            System.out.println("Employee not found!");
            return false;
        }

        System.out.println("Which Attribute do you want to change?\n1-Full Name\n2-Date of Birth\n3-Gender\n4-Email\n5-Phone Number\n6-Password");

        Scanner sc1 = new Scanner(System.in);
        int Choice = sc1.nextInt();
        updater.updatepersonaldata(emp, Choice);
        sc1.close();
        return true;
    }
}
