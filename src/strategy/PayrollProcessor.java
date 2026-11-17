package strategy;


import employees.Employee;
import employees.Payroll;
import finalassignment.HRFunctions;
public class PayrollProcessor {
    private PayrollStrategy strategy;

    public PayrollProcessor(PayrollStrategy strategy) {
        this.strategy = strategy;
    }

    public double process(Employee emp, double bonuses, int Choice) {
        HRFunctions hr = new HRFunctions(); 
        Payroll payrollemp = new Payroll();

     
        payrollemp.setEmployee_id(emp);
        payrollemp.setMonth(java.time.Month.SEPTEMBER);
        payrollemp.setBonuses(bonuses);

       
        double grossSalary = strategy.calculateSalary(emp, bonuses);
        payrollemp.setMonthly_payment(emp.calculatemonthlyincome());

       
        double deductions = hr.processDeductions();
        payrollemp.setDeductions(deductions);

        double tax = hr.processTax(emp.calculatemonthlyincome());
        payrollemp.setTax_withheld(tax);

      
        double netSalary = grossSalary - deductions - tax;
        payrollemp.setNet_salary(netSalary);

     if (Choice==3) {
        hr.payrollFM.Fileopenerandwriter(payrollemp);
     }
        return netSalary;
    }
}
