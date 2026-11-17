package filemanagers;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import employees.LeaveRequest;

public class LeavesFileManager {
	public void OpenLeavesandwrite(LeaveRequest Leave) {
		 String line = writeinfile(Leave);

		 try (FileWriter writer = new FileWriter("Leaves.txt", true))
				 {
		        writer.write(line + "\n");  
		        System.out.println("Request inserted");
		    } catch (IOException e) {
		        System.out.println(e.getMessage());
		    }
	}
public String writeinfile(LeaveRequest leave) {
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	    StringBuilder sb = new StringBuilder();
	    sb.append(leave.getEmployee_id().getEmployee_id()).append("-").append(sdf.format(leave.getStart_date())).append("\t");
	    sb.append(leave.getEmployee_id().getEmployee_id()).append("\t");	   
	    sb.append(leave.getLeave_type()).append("\t");
	    sb.append(sdf.format(leave.getStart_date())).append("\t");
	    sb.append(sdf.format(leave.getEnd_date())).append("\t");
	    sb.append(leave.getApproval_status()).append("\t");
	    return sb.toString();
}
}
