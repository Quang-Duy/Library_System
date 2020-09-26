package Library;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Test {
	
	public static void saveData() 
	{
		try {
			String data = "Hello!!";
			File file = new File("Members_Output.txt");
			
			if(!file.exists())
				file.createNewFile();
			
			FileWriter fileWriter = new FileWriter(file.getName(), false);
			BufferedWriter output = new BufferedWriter(fileWriter);
			
			output.write(data);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		saveData();
	}
	
	
}
