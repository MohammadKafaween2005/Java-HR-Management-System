package junit_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import employees.*;
import enums.*;
import filemanagers.FilenameFinder;
import filemanagers.FindEmployee;
import strategy.*;
import finalassignment.EmployeeFunctions;
import finalassignment.HRFunctions;

public class SystemTestCases {

	private HRFunctions hr;
	private EmployeeFunctions ER;
	private FilenameFinder fileFinder;
	private Date sampleDate;

	@BeforeEach
	public void setUp() {
	    fileFinder = new FilenameFinder();
	    hr = new HRFunctions();
	    List<Employee> employees = new ArrayList<>();
	    hr.employees = employees;
	    hr.employeeFinder = new FindEmployee(employees); 
	    ER = new EmployeeFunctions(employees, fileFinder);
	    sampleDate = new Date();
	}


    @Test
    public void testAddFullTimeEmployee() {
        Employee emp = new FullTimeEmployee("E001", "John Doe", sampleDate, Gender.MALE,"john@example.com", "12345", sampleDate,
        		"Developer", 1, Status.ACTIVE, sampleDate, "pass123",3000);
        hr.insertEmployee(emp);
        assertTrue(hr.employees.contains(emp));
    }
    @Test
    public void testAddPartTimeEmployee() {
        Employee emp = new PartTimeEmployee("E002", "Jane Smith", sampleDate, Gender.FEMALE,"jane@example.com", "54321",
        		sampleDate,"Assistant", 2, Status.ACTIVE, sampleDate, "pass456",20, 40);
        hr.insertEmployee(emp);
        assertEquals(1, hr.employees.size());
    }
    @Test
    public void testFullTimePayrollStrategy() {
        Employee emp = new FullTimeEmployee("E003", "Alice", sampleDate, Gender.FEMALE,"alice@example.com", "11111", sampleDate,
                "Analyst", 1, Status.ACTIVE, sampleDate, "pwd",2000);
        PayrollStrategy strategy = new FullTimePayrollStrategy();
        PayrollProcessor processor = new PayrollProcessor(strategy);
        double netSalary = processor.process(emp, 200,0);
        assertTrue(netSalary > 0);
    }
    @Test
    public void testContractPayrollStrategy() {
        Employee emp = new ContractEmployee("E004", "Bob", sampleDate, Gender.MALE,"bob@example.com", "22222", sampleDate,
        		"Consultant", 3, Status.ACTIVE, sampleDate, "pwd",6, 6000);
        PayrollStrategy strategy = new ContractPayrollStrategy();
        PayrollProcessor processor = new PayrollProcessor(strategy);
        double netSalary = processor.process(emp, 0,0);
        double expectedNetSalary = 820;
        assertEquals(expectedNetSalary, netSalary, 0.01);
    }
   

    @Test
    public void testPromoteEmployee() {
        Employee emp = new FullTimeEmployee(
            "E009", "George", sampleDate, Gender.MALE,"george@example.com", "77777",
            sampleDate,"Developer", 1, Status.ACTIVE, sampleDate, "pwd",2800
        );
        hr.insertEmployee(emp);
        boolean promoted = hr.PromoteEmployee("E009", "Manager");
        assertTrue(promoted); 
    }


    @Test
    public void testPromoteEmployeeInvalidTitle() {
        Employee emp = new FullTimeEmployee(
                "E010", "Helen", sampleDate, Gender.FEMALE,"helen@example.com", "88888", sampleDate,"Developer", 1,
                Status.ACTIVE, sampleDate, "pwd",2900);
        hr.insertEmployee(emp);
        boolean promoted = hr.PromoteEmployee("E010", "wrongtitle");
        assertFalse(promoted);
    }




       
        @Test
        public void testUpdateEmployeeEmail() {
            Employee emp = new FullTimeEmployee("E003", "Alice", sampleDate, Gender.FEMALE,"alice@example.com", "11111",
                    sampleDate, "Analyst", 1, Status.ACTIVE, sampleDate, "pwd",2000);
            hr.insertEmployee(emp);
            emp.setEmail("alice_new@example.com");
            assertEquals("alice_new@example.com", emp.getEmail());
        }

        @Test
        public void testUpdateEmployeePhone() {
            Employee emp = new FullTimeEmployee("E004", "Bob", sampleDate, Gender.MALE,"bob@example.com", "22222",
                    sampleDate, "Consultant", 2, Status.ACTIVE, sampleDate, "pwd",2200);
            hr.insertEmployee(emp);
            emp.setPhone_number("555555");
            assertEquals("555555", emp.getPhone_number());
        }
        @Test
        public void testRequestLeaveValid() {
            Employee emp = new FullTimeEmployee("E005", "Charlie", sampleDate, Gender.MALE,"charlie@example.com", "33333",
                    sampleDate,"Engineer", 1, Status.ACTIVE, sampleDate, "pwd",3000);
            hr.insertEmployee(emp);

            boolean leaveRequested = ER.requestLeave("E005", Leavetype.VACATION, sampleDate,sampleDate
            );
            assertTrue(leaveRequested);
        }

        @Test
        public void testRequestLeaveOverlap() {
            Employee emp = new FullTimeEmployee("E006", "David", sampleDate, Gender.MALE,
                    "david@example.com", "44444",
                    sampleDate,"Technician", 2, Status.ACTIVE, sampleDate, "pwd",3000);
            hr.insertEmployee(emp);

            ER.requestLeave("E006", Leavetype.VACATION, sampleDate,sampleDate);

            boolean leaveRequested = ER.requestLeave("E0", Leavetype.VACATION, sampleDate,sampleDate);


            assertFalse(leaveRequested);
        }
}
