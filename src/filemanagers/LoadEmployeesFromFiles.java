package filemanagers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import employees.ContractEmployee;
import employees.Employee;
import employees.FullTimeEmployee;
import employees.PartTimeEmployee;
import enums.Gender;
import enums.Status;

public class LoadEmployeesFromFiles {
    private FilenameFinder fileFinder;

    public LoadEmployeesFromFiles(FilenameFinder fileFinder) {
        this.fileFinder = fileFinder;
    }

    public List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 

        for (String file : fileFinder.files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                boolean isFirstLine = true;

                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) { 
                        isFirstLine = false; 
                        continue;
                    }

                    String[] parts = line.split("\t"); 

                    if (parts.length < 14) continue; 

                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    Date dob = sdf.parse(parts[2].trim());
                    Gender gender = Gender.valueOf(parts[3].trim().toUpperCase());
                    String email = parts[4].trim();
                    String phone = parts[5].trim();
                    Date hireDate = sdf.parse(parts[6].trim());
                    String jobTitle = parts[7].trim();
                    String employmentType = parts[8].trim();
                    int deptId = Integer.parseInt(parts[9].trim());
                    Status status = Status.valueOf(parts[10].trim().toUpperCase().replace(" ", "_"));
                    Date lastPromotion = sdf.parse(parts[11].trim());
                    String password = parts[12].trim();

                    if (file.equals("employees_full_time.txt")) {
                        double salary = Double.parseDouble(parts[13].trim());
                        Employee emp = new FullTimeEmployee(id, name, dob, gender, email, phone,
                                hireDate, jobTitle, deptId, status, lastPromotion, password, salary);
                        employees.add(emp);

                    } else if (file.equals("employees_part_time.txt")) {
                        int working_hours_per_month = Integer.parseInt(parts[13].trim());
                        int rate_per_hour = Integer.parseInt(parts[14].trim());
                        Employee emp = new PartTimeEmployee(id, name, dob, gender, email, phone,
                                hireDate, jobTitle, deptId, status, lastPromotion, password, working_hours_per_month, rate_per_hour);
                        employees.add(emp);

                    } else if (file.equals("employees_contract.txt")) {
                        int duration = Integer.parseInt(parts[13].trim());
                        double overall_payment = Double.parseDouble(parts[14].trim());
                        Employee emp = new ContractEmployee(id, name, dob, gender, email, phone,
                                hireDate, jobTitle, deptId, status, lastPromotion, password, duration, overall_payment);
                        employees.add(emp);
                    } 
                }

            } catch (Exception f) {
                System.out.println("Error reading " + file + ": " + f.getMessage());
            }
        }

        System.out.println("Total employees loaded: " + employees.size());
        return employees;
    }
}
