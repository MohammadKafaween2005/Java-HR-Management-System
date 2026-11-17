package strategy;

import employees.Employee;
import decorators.*;
public class PartTimePayrollStrategy implements PayrollStrategy {
    @Override
    public double calculateSalary(Employee emp, double bonuses) {
        SalaryComponent base = new BaseSalary(emp);
        SalaryComponent decorated = new BonusDecorator(base, bonuses);
        return decorated.calculateSalary();
    }
}