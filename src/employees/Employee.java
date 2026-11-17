package employees;
import enums.*;
import java.util.Date;

public abstract class  Employee{
private String employee_id;
private String full_name;
private Date date_of_birth;
private Gender gender;
private String email;
private String phone_number;
private Date hire_date;
private String job_title;
private int department_id;
private Status status;
private Date last_promotion_date;
private String password;



public Employee(String employee_id, String full_name, Date date_of_birth, Gender gender, String email, String phone_number,
		Date hire_date, String job_title, int department_id, Status status, Date last_promotion_date, String password) {
	super();
	this.employee_id = employee_id;
	this.full_name = full_name;
	this.date_of_birth = date_of_birth;
	this.gender = gender;
	this.email = email;
	this.phone_number = phone_number;
	this.hire_date = hire_date;
	this.job_title = job_title;
	this.department_id = department_id;
	this.status = status;
	this.last_promotion_date = last_promotion_date;
	this.password = password;
}

public String getFull_name() {
	return full_name;
}
public void setFull_name(String full_name) {
	this.full_name = full_name;
}
public String getEmployee_id() {
	return employee_id;
}
public void setEmployee_id(String employee_id) {
	this.employee_id = employee_id;
}
public Date getDate_of_birth() {
	return date_of_birth;
}
public void setDate_of_birth(Date date_of_birth) {
	this.date_of_birth = date_of_birth;
}
public Gender getGender() {
	return gender;
}
public void setGender(Gender gender) {
	this.gender = gender;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone_number() {
	return phone_number;
}
public void setPhone_number(String phone_number) {
	this.phone_number = phone_number;
}
public Date getHire_date() {
	return hire_date;
}
public void setHire_date(Date hire_date) {
	this.hire_date = hire_date;
}
public String getJob_title() {
	return job_title;
}
public void setJob_title(String job_title) {
	this.job_title = job_title;
}
public int getDepartment_id() {
	return department_id;
}
public void setDepartment_id(int department_id) {
	this.department_id = department_id;
}
public Status getStatus() {
	return status;
}
public void setStatus(Status status) {
	this.status = status;
}
public Date getLast_promotion_date() {
	return last_promotion_date;
}
public void setLast_promotion_date(Date last_promotion_date) {
	this.last_promotion_date = last_promotion_date;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public abstract double calculatemonthlyincome();
}
