package io;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {
	public static void to_file(String string, String source){ //TODO: source als file
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(source,false));
			bw.write(string);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static String read_file(String source) throws FileNotFoundException,SecurityException, IOException{ 
			return new String(Files.readAllBytes(Paths.get(source)));
	}
}
