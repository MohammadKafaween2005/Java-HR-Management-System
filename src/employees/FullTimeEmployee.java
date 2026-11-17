package employees;
import java.util.Date;

import enums.*;
public class FullTimeEmployee extends Employee {
private double Salary;


public FullTimeEmployee(String employee_id, String full_name, Date date_of_birth, Gender gender,
        String email, String phone_number, Date hire_date, String job_title,
        int department_id, Status status, Date last_promotion_date, String password,
        double salary) {
super(employee_id, full_name, date_of_birth, gender, email, phone_number,
hire_date, job_title, department_id, status, last_promotion_date, password);
this.Salary = salary;
}

public double getSalary() {
	return Salary;
}

public void setSalary(double salary) {
	Salary = salary;
}
@Override
public double calculatemonthlyincome() {
	return Salary;
}



}

