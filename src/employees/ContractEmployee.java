package employees;

import java.util.Date;

import enums.Gender;
import enums.Status;

public class ContractEmployee extends Employee {
private double no_of_months;
private double overall_payment;
public ContractEmployee(String employee_id, String full_name, Date date_of_birth, Gender gender,
        String email, String phone_number, Date hire_date, String job_title,
        int department_id, Status status, Date last_promotion_date, String password,
        double no_of_months, double overall_payment) {
super(employee_id, full_name, date_of_birth, gender, email, phone_number,
hire_date, job_title, department_id, status, last_promotion_date, password);
this.no_of_months= no_of_months;
this.overall_payment=overall_payment;
}

public double getNo_of_months() {
	return no_of_months;
}
public void setNo_of_months(double no_of_months) {
	this.no_of_months = no_of_months;
}
public double getOverall_payment() {
	return overall_payment;
}
public void setOverall_payment(double overall_payment) {
	this.overall_payment = overall_payment;
}
@Override
public double calculatemonthlyincome() {
	return overall_payment/no_of_months;
}



}
