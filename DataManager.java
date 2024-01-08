import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DataManager {
    

    public static void convertToCSV(TaskMaster tasks){
        try (PrintWriter pw = new PrintWriter(new FileOutputStream("data.csv"))) {
    
            for( Task t : tasks.getAllTasks() ){
                String type = t.getType();
                String name = t.getName();
                String date = t.getDueDate().toString();
                String weight = Float.toString( t.getWeight() );
                String grade = Float.toString( t.getGrade() );
                String status = t.getStatus();
                String priority = t.getPriority();

                String recordedString = type+","+name+","+date+","+weight+","+grade+","+status+","+priority+"\n";
                pw.print(recordedString);
            }
            
        } catch (IOException e) {
        e.printStackTrace();
        }
    }

    public static TaskMaster convertCSVToTasks(String filepath){
        TaskMaster tasks = new TaskMaster();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String type = values[0];
                String name = values[1];
                Date date = new SimpleDateFormat("dd-mm-yyyy").parse(values[2]);
                float weight = Float.valueOf(values[3]);
                float grade = Float.valueOf(values[4]);
                String status = values[5];
                String priority = values[6];

                Task task = new Task(type, name, date, weight);
                task.setGrade(grade);
                task.setStatus(status);
                task.setPriority(priority);
            }
            reader.close();

        }catch( Exception e ){
            e.printStackTrace();
        }
        return tasks;    
    } 
}
