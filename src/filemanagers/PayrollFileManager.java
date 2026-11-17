package filemanagers;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import employees.Payroll;

public class PayrollFileManager {
    private String fileName = "payroll.txt";

    private String writeInFile(Payroll p) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        String today = sdf.format(new Date());

        StringBuilder sb = new StringBuilder();
        sb.append(p.getEmployee_id().getEmployee_id() + "-" + p.getMonth() + "-25").append("\t");
        sb.append(p.getEmployee_id().getEmployee_id()).append("\t");
        sb.append(p.getMonth()).append("-25\t");
        sb.append(String.format("%.2f", p.getMonthly_payment())).append("\t");
        sb.append(String.format("%.2f", p.getBonuses())).append("\t");
        sb.append(String.format("%.2f", p.getDeductions())).append("\t");
        sb.append(String.format("%.2f", p.getNet_salary())).append("\t");
        sb.append(String.format("%.2f", p.getTax_withheld())).append("\t");

        sb.append(today); 

        return sb.toString();
    }

    public void Fileopenerandwriter(Payroll p ) {
        String line = writeInFile(p);

        if (line == null || line.isEmpty()) {
            return;
        }

        try (FileWriter writer = new FileWriter(fileName, true)) { 
            writer.write(line + "\n");  
            System.out.println("Payroll record inserted ");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
