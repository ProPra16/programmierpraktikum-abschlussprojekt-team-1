package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
}
