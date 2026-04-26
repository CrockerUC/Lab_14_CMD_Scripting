import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        boolean done = false;
        int idCounter = 1;

        while(!done)
        {
            String firstName = SafeInput.getNonZeroLenString(scanner, "Enter First Name");
            String lastName = SafeInput.getNonZeroLenString(scanner, "Enter Last Name");
            String email = SafeInput.getNonZeroLenString(scanner, "Enter Email");
            System.out.println();
            int yob = SafeInput.getRangedInt(scanner, "Enter Year of Birth", 0 , 2026);
            System.out.println();
            String id = String.format("%06d", idCounter);

            String record = firstName + "," + lastName + "," + id + "," + email + "," + yob;
            records.add(record);
            idCounter++;

            done = !SafeInput.getYNConfirm(scanner, "Add another record?");
        }

        String fileName = SafeInput.getNonZeroLenString(scanner, "Enter file name") + ".csv";

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("./src/" + fileName)))
        {
            for(String record : records)
            {
                writer.write(record);
                writer.newLine();
            }
            System.out.println("Records saved to " + fileName);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            System.out.println("Error writing file" + e.getMessage());
            throw new RuntimeException(e);
        }
        scanner.close();
    }
}
