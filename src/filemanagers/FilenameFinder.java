package filemanagers;

import employees.ContractEmployee;
import employees.Employee;
import employees.FullTimeEmployee;
import employees.PartTimeEmployee;

public class FilenameFinder {
    String fileName;
    public final String[] files = {
        "employees_full_time.txt",
        "employees_part_time.txt",
        "employees_contract.txt"
    };


    public String findFileForEmployee(Employee emp) {
        if (emp instanceof FullTimeEmployee) {
            this.fileName = "employees_full_time.txt";
        } else if (emp instanceof PartTimeEmployee) {
            this.fileName = "employees_part_time.txt";
        } else if (emp instanceof ContractEmployee) {
            this.fileName = "employees_contract.txt";
        } 
        
        return fileName;
    }


    public String getFileName() {
        return fileName;
    }
}
