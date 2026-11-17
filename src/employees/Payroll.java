package employees;
import java.util.Date;
import java.time.Month;  
import enums.*;
public class Payroll {
private String payroll_id;
private Employee employee_id;
private Month month;
private double monthly_payment;
private double bonuses;
private double deductions;
private double tax_withheld;
private double net_salary;
private Date processed_date;
public String getPayroll_id() {
	return payroll_id;
}

public void setPayroll_id(String payroll_id) {
	this.payroll_id = payroll_id;
}

public Employee getEmployee_id() {
	return employee_id;
}

public void setEmployee_id(Employee employee_id) {
	this.employee_id = employee_id;
}

public Month getMonth() {
	return month;
}

public void setMonth(Month month) {
	this.month = month;
}

public double getMonthly_payment() {
	return monthly_payment;
}

public void setMonthly_payment(double monthly_payment) {
	this.monthly_payment = monthly_payment;
}

public double getBonuses() {
	return bonuses;
}

public void setBonuses(double bonuses) {
	this.bonuses = bonuses;
}

public double getDeductions() {
	return deductions;
}

public void setDeductions(double deductions) {
	this.deductions = deductions;
}

public double getTax_withheld() {
	return tax_withheld;
}

public void setTax_withheld(double tax_withheld) {
	this.tax_withheld = tax_withheld;
}

public double getNet_salary() {
	return net_salary;
}

public void setNet_salary(double net_salary) {
	this.net_salary = net_salary;
}

public Date getProcessed_date() {
	return processed_date;
}

public void setProcessed_date(Date processed_date) {
	this.processed_date = processed_date;
}


}
