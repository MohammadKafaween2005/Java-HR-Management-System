package employees;

import java.util.Date;

import enums.Gender;
import enums.Status;

public class EmployeeFactory {

    public static Employee createEmployee(
            int type,             
            String id,
            String name,
            Date dob,
            Gender gender,
            String email,
            String phone,
            Date hireDate,
            String jobTitle,
            int deptId,
            Status status,
            Date promoDate,
            String password,
            double salaryOrRate, 
            int hoursOrMonths,   
            double overallPayment 
    ) {
        switch(type) {
            case 1: 
                return new FullTimeEmployee(id, name, dob, gender, email, phone,
                        hireDate, jobTitle, deptId, status, promoDate, password, salaryOrRate);
            case 2: 
                return new PartTimeEmployee(id, name, dob, gender, email, phone,
                        hireDate, jobTitle, deptId, status, promoDate, password, salaryOrRate, hoursOrMonths);
            case 3: 
                return new ContractEmployee(id, name, dob, gender, email, phone,
                        hireDate, jobTitle, deptId, status, promoDate, password, hoursOrMonths, overallPayment);
            default:
                throw new IllegalArgumentException("Invalid employee type");
        }
    }
}
