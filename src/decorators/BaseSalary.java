package decorators;

import employees.Employee;

public class BaseSalary implements SalaryComponent {
    private Employee employee;

    public BaseSalary(Employee employee) {
        this.employee = employee;
    }

    @Override
    public double calculateSalary() {
        return employee.calculatemonthlyincome(); 
    }
}
