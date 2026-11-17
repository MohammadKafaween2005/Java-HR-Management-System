package filemanagers;

import java.util.List;

import employees.Employee;

public class FindEmployee {

    private  List<Employee> employees;

    public FindEmployee(List<Employee> employees) {
        this.employees = employees;
    }

    public int getEmployeesCount() {
        return employees.size();
    }


    public Employee getEmployeeByIndex(int index) {
        if (index < 0 || index >= employees.size()) {
            return null;
        }
        return employees.get(index);
    }


    public Employee findEmployeeById(String id) {
        for (Employee e : employees) {
            if (e.getEmployee_id().equals(id)) {
                return e;
            }
        }
        return null;
    }
}
