package strategy;

import employees.Employee;

public interface PayrollStrategy {
    double calculateSalary(Employee emp, double bonuses);
}
