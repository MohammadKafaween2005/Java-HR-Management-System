package filemanagers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import employees.LeaveRequest;

public class ManagerFileManager {
public List<LeaveRequest> Leavelist = new ArrayList<>();
private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
public void loadrequeststoarray() {
	 try (BufferedReader br = new BufferedReader(new FileReader("Leaves.txt"))) {
	        String line = br.readLine();
         boolean found = false;

         while ((line = br.readLine()) != null) {
             String[] parts = line.split("\t");

             if (parts.length < 6) continue; 

             String requestId = parts[0];
             String empId = parts[1];
             String leaveType = parts[2];
             Date start = sdf.parse(parts[3]);
             Date end = sdf.parse(parts[4]);
             String status = parts[5];

             if (status.equalsIgnoreCase("Pending")) {
                 found = true;
                 System.out.println("Request ID: " + requestId);
                 System.out.println("Employee ID: " + empId);
                 System.out.println("Leave Type: " + leaveType);
                 System.out.println("Start: " + sdf.format(start));
                 System.out.println("End: " + sdf.format(end));
                 System.out.println("Status: " + status);
                 System.out.println("------------------------------------");
             }
         }

         if (!found) {
             System.out.println("No pending requests found.");
         }

     } catch (Exception e) {
         System.out.println("Error reading leave requests: " + e.getMessage());
     }
 }
public void updateRequestStatus(String empId, String decision, String managerId) {
    try {

        List<String> lines = Files.readAllLines(Paths.get("Leaves.txt"));

        for (int i = 1; i < lines.size(); i++) { 
            String[] parts = lines.get(i).split("\t");

            if (parts.length >= 7 && parts[1].equalsIgnoreCase(empId) && parts[5].equals("Pending")) {
                parts[5] = decision;      
                parts[6] = managerId;      
                lines.set(i, String.join("\t", parts));
                System.out.println("Request for employee " + empId + " has been " + decision + " by manager " + managerId);
                break; 
            }
        }

 
        Files.write(Paths.get("Leaves.txt"), lines);

    } catch (IOException e) {
        System.out.println("Error updating leave request: " + e.getMessage());
    }
}

}

