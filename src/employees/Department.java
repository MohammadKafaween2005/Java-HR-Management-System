package employees;

public class Department {
private int department_id;
private String department_name;
private Employee head_of_department;
private String Location;
public int getDepartment_id() {
	return department_id;
}
public void setDepartment_id(int department_id) {
	this.department_id = department_id;
}
public String getDepartment_name() {
	return department_name;
}
public void setDepartment_name(String department_name) {
	this.department_name = department_name;
}
public Employee getHead_of_department() {
	return head_of_department;
}
public void setHead_of_department(Employee head_of_department) {
	this.head_of_department = head_of_department;
}
public String getLocation() {
	return Location;
}
public void setLocation(String location) {
	Location = location;
}

}
