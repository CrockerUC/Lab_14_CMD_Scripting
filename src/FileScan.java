import javax.swing.*;
import java.io.*;

public class FileScan
{
    public static void main(String[] args)
    {
        File selectedFile;

        if(args.length > 0)
        {
            selectedFile = new File(args[0]);

            if (!selectedFile.exists()) {
                System.out.println("File not found: " + args[0]);
                return;
            }
        }
        else {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("./src"));
            int result = chooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
            } else {
                System.out.println("No file selected");
                return;
            }
        }
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader(selectedFile)))
        {
            String line;

            while((line = reader.readLine()) != null)
            {
                System.out.println(line);
                lineCount++;
                charCount += line.length();
                String[] words = line.trim().split("\\s+"); //split includes all white space
                if(!line.trim().isEmpty())
                {
                    wordCount += words.length;
                }
            }

            System.out.println("\n===== FILE SUMMARY =====");
            System.out.println("File Name: " + selectedFile.getName());
            System.out.println("Lines: " + lineCount);
            System.out.println("Words: " + wordCount);
            System.out.println("Characters: " + charCount);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            System.out.println("Error reading file" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
//