package employees;

import java.util.Date;

import enums.*;
public class PartTimeEmployee extends Employee {
private int working_hrs_per_month;
private double hourly_rate;
public PartTimeEmployee(String employee_id, String full_name, Date date_of_birth, Gender gender,
        String email, String phone_number, Date hire_date, String job_title,
        int department_id, Status status, Date last_promotion_date, String password,
        double hourly_rate, int working_hrs_per_month) {
super(employee_id, full_name, date_of_birth, gender, email, phone_number,
hire_date, job_title, department_id, status, last_promotion_date, password);
this.working_hrs_per_month= working_hrs_per_month;
this.hourly_rate=hourly_rate;
}

public int getWorking_hrs_per_month() {
	return working_hrs_per_month;
}
public void setWorking_hrs_per_month(int working_hrs_per_month) {
	this.working_hrs_per_month = working_hrs_per_month;
}
public double getHourly_rate() {
	return hourly_rate;
}
public void setHourly_rate(double hourly_rate) {
	this.hourly_rate = hourly_rate;
}
@Override
public double calculatemonthlyincome() {
	double Salary= working_hrs_per_month* hourly_rate;
	return Salary;
}

}
