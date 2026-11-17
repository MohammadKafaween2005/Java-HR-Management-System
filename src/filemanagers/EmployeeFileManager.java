package filemanagers;


import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import employees.ContractEmployee;
import employees.Employee;
import employees.FullTimeEmployee;
import employees.PartTimeEmployee;


public class EmployeeFileManager {
FilenameFinder fileFinder = new FilenameFinder();

   

public void Fileopenerandwriter(Employee emp) {
	 fileFinder.findFileForEmployee(emp);
    String line = writeinfile(emp);

    if (line == null || line.isEmpty()) {
        return; 
    }

    try (FileWriter writer = new FileWriter(fileFinder.fileName, true)) {
        writer.write(line + "\n");  
        System.out.println("Employee inserted");
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }
}

private String writeinfile(Employee e) {


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
    StringBuilder sb = new StringBuilder();
    sb.append(e.getEmployee_id()).append("\t");
    sb.append(e.getFull_name()).append("\t");
    sb.append(sdf.format(e.getDate_of_birth())).append("\t");
    sb.append(e.getGender()).append("\t");
    sb.append(e.getEmail()).append("\t");
    sb.append(e.getPhone_number()).append("\t");
    sb.append(sdf.format(e.getHire_date())).append("\t");
    sb.append(e.getJob_title()).append("\t");
    if (e instanceof FullTimeEmployee ) {
        sb.append("Full-time").append("\t");
    } else if (e instanceof PartTimeEmployee ) {
        sb.append("Part-time").append("\t");
    } else if (e instanceof ContractEmployee ) {
        sb.append("Contractor").append("\t");
    }
    sb.append(e.getDepartment_id()).append("\t");
    sb.append(e.getStatus()).append("\t");
    sb.append(sdf.format(e.getLast_promotion_date())).append("\t");
    sb.append(e.getPassword()).append("\t");

    if (e instanceof FullTimeEmployee fte) {
        sb.append(fte.getSalary());
    } else if (e instanceof PartTimeEmployee pte) {
        sb.append(pte.getHourly_rate()).append("\t");
        sb.append(pte.getWorking_hrs_per_month());
    } else if (e instanceof ContractEmployee ce) {
        sb.append(ce.getNo_of_months()).append("\t");
        sb.append(ce.getOverall_payment());
    }

    return sb.toString();
}



}

