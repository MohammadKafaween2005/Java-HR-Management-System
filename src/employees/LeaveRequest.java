package employees;
import java.util.Date;

import enums.*;
public class LeaveRequest {
private int leave_id;
private Employee employee_id;
private Leavetype leave_type;
private Date start_date;
private Date end_date;
private Approvalstatus approval_status;
private String approver_id;
public LeaveRequest(Employee employee, Leavetype leave_type, Date start_date, Date end_date, Approvalstatus approval_status,String approver) {
    this.employee_id = employee;
    this.leave_type = leave_type;
    this.start_date = start_date;
    this.end_date = end_date;
    this.approval_status = approval_status;
    this.approver_id=approver;
}

public int getLeave_id() {
	return leave_id;
}
public void setLeave_id(int leave_id) {
	this.leave_id = leave_id;
}
public Employee getEmployee_id() {
	return employee_id;
}
public void setEmployee_int(Employee employee_id) {
	this.employee_id = employee_id;
}
public Leavetype getLeave_type() {
	return leave_type;
}
public void setLeave_type(Leavetype leave_type) {
	this.leave_type = leave_type;
}
public Date getStart_date() {
	return start_date;
}
public void setStart_date(Date start_date) {
	this.start_date = start_date;
}
public Date getEnd_date() {
	return end_date;
}
public void setEnd_date(Date end_date) {
	this.end_date = end_date;
}
public Approvalstatus getApproval_status() {
	return approval_status;
}
public void setApproval_status(Approvalstatus approval_status) {
	this.approval_status = approval_status;
}

public String getApprover_id() {
	return approver_id;
}

public void setApprover_id(String approver_id) {
	this.approver_id = approver_id;
}

}
