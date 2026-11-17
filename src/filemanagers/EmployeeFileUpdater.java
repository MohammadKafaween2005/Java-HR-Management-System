package filemanagers;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import employees.ContractEmployee;
import employees.Employee;
import employees.FullTimeEmployee;
import employees.PartTimeEmployee;
import enums.Gender;
import enums.Status;

public class EmployeeFileUpdater {

    private final FilenameFinder fileFinder;

    public EmployeeFileUpdater(FilenameFinder finder) {
        this.fileFinder = finder;
    }

    public void updateJobTitleAndPromotion(Employee updatedEmp) {

        try {
            List<String> lines = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileFinder.fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith(updatedEmp.getEmployee_id() + "\t")) {
                        String[] parts = line.split("\t", -1);
                        parts[7] = updatedEmp.getJob_title();
                        parts[11] = new SimpleDateFormat("yyyy-MM-dd").format(updatedEmp.getLast_promotion_date());
                        line = String.join("\t", parts);
                    }
                    lines.add(line);
                }
            }

            try (FileWriter writer = new FileWriter(fileFinder.fileName, false)) {
                for (String l : lines) writer.write(l + "\n");
            }

            System.out.println("Employee " + updatedEmp.getEmployee_id() + " updated in file.");

        } catch (IOException e) {
            System.out.println("Error updating employee file: " + e.getMessage());
        }
    }

    public void updateEmployeeJobTitleandPromotionInFile(Employee emp) {
        String fileName = fileFinder.findFileForEmployee(emp); 

        try {
            List<String> lines = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith(emp.getEmployee_id() + "\t")) {
                        String[] parts = line.split("\t", -1);
                        parts[7] = emp.getJob_title();
                        parts[11] = new SimpleDateFormat("yyyy-MM-dd").format(emp.getLast_promotion_date());
                        line = String.join("\t", parts);
                    }
                    lines.add(line);
                }
            }

            try (FileWriter fw = new FileWriter(fileName, false)) {
                for (String l : lines) fw.write(l + "\n");
            }

        } catch (IOException e) {
            System.out.println("Error updating employee file: " + e.getMessage());
        }
    }
    public void updateEmployee(Employee emp, int Choice) {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = fileFinder.findFileForEmployee(emp); 

        try {
            List<String> lines = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith(emp.getEmployee_id() + "\t")) {
                        String[] parts = line.split("\t", -1);

                        switch (Choice) {
                            case 1 -> { 
                                System.out.print("ID: ");
                                String id = sc.nextLine();
                                emp.setEmployee_id(id);
                                parts[0] = id;
                            }
                            case 2 -> { 
                                System.out.print("Full Name: ");
                                String name = sc.nextLine();
                                emp.setFull_name(name);
                                parts[1] = name;
                            }
                            case 3 -> {
                                try {
                                    System.out.print("Date of Birth (yyyy-MM-dd): ");
                                    Date dob = sdf.parse(sc.nextLine());
                                    emp.setDate_of_birth(dob);
                                    parts[2] = sdf.format(dob);
                                } catch (ParseException e) {
                                    System.out.println("Invalid date format! Please use yyyy-MM-dd.");
                                }
                            }
                            case 4 -> { 
                                try {
                                    System.out.print("Gender (MALE/FEMALE): ");
                                    Gender gender = Gender.valueOf(sc.nextLine().toUpperCase());
                                    emp.setGender(gender);
                                    parts[3] = gender.toString();
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Invalid gender!");
                                }
                            }
                            case 5 -> { 
                                System.out.print("Email: ");
                                String email = sc.nextLine();
                                emp.setEmail(email);
                                parts[4] = email;
                            }
                            case 6 -> { 
                                System.out.print("Phone: ");
                                String phone = sc.nextLine();
                                emp.setPhone_number(phone);
                                parts[5] = phone;
                            }
                            case 7 -> { 
                                try {
                                    System.out.print("Hire Date (yyyy-MM-dd): ");
                                    Date hireDate = sdf.parse(sc.nextLine());
                                    emp.setHire_date(hireDate);
                                    parts[6] = sdf.format(hireDate);
                                } catch (ParseException e) {
                                    System.out.println("Invalid date format! Please use yyyy-MM-dd.");
                                }
                            }
                            case 8 -> { 
                                System.out.print("Job Title: ");
                                String jobTitle = sc.nextLine();
                                emp.setJob_title(jobTitle);
                                parts[7] = jobTitle;
                            }
                            case 9 -> { 
                                try {
                                    System.out.print("Department ID: ");
                                    int deptId = Integer.parseInt(sc.nextLine());
                                    emp.setDepartment_id(deptId);
                                    parts[9] = String.valueOf(deptId);
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid number!");
                                }
                            }
                            case 10 -> { 
                                try {
                                    System.out.print("Status (ACTIVE/INACTIVE): ");
                                    Status status = Status.valueOf(sc.nextLine().toUpperCase());
                                    emp.setStatus(status);
                                    parts[10] = status.toString();
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Invalid status!");
                                }
                            }
                            case 11 -> { 
                                try {
                                    System.out.print("Last Promotion Date (yyyy-MM-dd): ");
                                    Date promoDate = sdf.parse(sc.nextLine());
                                    emp.setLast_promotion_date(promoDate);
                                    parts[11] = sdf.format(promoDate);
                                } catch (ParseException e) {
                                    System.out.println("Invalid date format! Please use yyyy-MM-dd.");
                                }
                            }
                            case 12 -> { 
                                System.out.print("Password: ");
                                String password = sc.nextLine();
                                emp.setPassword(password);
                                parts[12] = password;
                            }
                            case 13 -> {
                                if (emp instanceof FullTimeEmployee fte) {
                                    System.out.print("Salary: ");
                                    double salary = sc.nextDouble(); sc.nextLine();
                                    fte.setSalary(salary);
                                    parts[13] = String.valueOf(salary); 
                                } else if (emp instanceof PartTimeEmployee pte) {
                                    System.out.print("Hourly Rate: ");
                                    double rate = sc.nextDouble(); sc.nextLine();
                                    pte.setHourly_rate(rate);
                                    parts[13] = String.valueOf(rate); 
                                } else if (emp instanceof ContractEmployee cte) {
                                    System.out.print("No of Months: ");
                                    double months = sc.nextDouble(); sc.nextLine();
                                    cte.setNo_of_months(months);
                                    parts[13] = String.valueOf(months); 
                                }
                            }
                            case 14 -> {
                                if (emp instanceof PartTimeEmployee pte) {
                                    System.out.print("Hours Worked: ");
                                    int hours = sc.nextInt(); sc.nextLine();
                                    pte.setWorking_hrs_per_month(hours);
                                    parts[14] = String.valueOf(hours); 
                                } else if (emp instanceof ContractEmployee cte) {
                                    System.out.print("Overall Payment: ");
                                    double pay = sc.nextDouble(); sc.nextLine();
                                    cte.setOverall_payment(pay);
                                    parts[14] = String.valueOf(pay); 
                                }
                            }

                            default -> System.out.println("Invalid choice!");
                        }

                        line = String.join("\t", parts);
                    }

                    lines.add(line);
                }
            }

            try (FileWriter fw = new FileWriter(fileName, false)) {
                for (String l : lines) {
                    fw.write(l + "\n");
                }
            }

            System.out.println("Employee information updated successfully!");

        } catch (IOException e) {
            System.out.println("Error updating employee file: " + e.getMessage());
        }
    }
    public void updatepersonaldata(Employee emp, int Choice) {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = fileFinder.findFileForEmployee(emp); 

        try {
            List<String> lines = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith(emp.getEmployee_id() + "\t")) {
                        String[] parts = line.split("\t", -1);
                        switch (Choice) {
                            case 1 -> { 
                                System.out.print("Full Name: ");
                                String name = sc.nextLine();
                                emp.setFull_name(name);
                                parts[1] = name;
                            }
                            case 2 -> { 
                                try {
                                    System.out.print("Date of Birth (yyyy-MM-dd): ");
                                    Date dob = sdf.parse(sc.nextLine());
                                    emp.setDate_of_birth(dob);
                                    parts[2] = sdf.format(dob);
                                } catch (ParseException e) {
                                    System.out.println("Invalid date format! Please use yyyy-MM-dd.");
                                }
                            }
                            case 3 -> { 
                                try {
                                    System.out.print("Gender (MALE/FEMALE): ");
                                    Gender gender = Gender.valueOf(sc.nextLine().toUpperCase());
                                    emp.setGender(gender);
                                    parts[3] = gender.toString();
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Invalid gender!");
                                }
                            }
                            case 4 -> { 
                                System.out.print("Email: ");
                                String email = sc.nextLine();
                                emp.setEmail(email);
                                parts[4] = email;
                            }
                            case 5 -> { 
                                System.out.print("Phone: ");
                                String phone = sc.nextLine();
                                emp.setPhone_number(phone);
                                parts[5] = phone;
                            }
                            case 6 -> { 
                                System.out.print("Password: ");
                                String password = sc.nextLine();
                                emp.setPassword(password);
                                parts[12] = password;
                            }
                            default -> System.out.println("Invalid choice!");
                        }

                        line = String.join("\t", parts);
                    }

                    lines.add(line);
                }
            }


            try (FileWriter fw = new FileWriter(fileName, false)) {
                for (String l : lines) fw.write(l + "\n");
            }

        } catch (IOException e) {
            System.out.println("Error updating employee file: " + e.getMessage());
        }
        sc.close();
    }
}