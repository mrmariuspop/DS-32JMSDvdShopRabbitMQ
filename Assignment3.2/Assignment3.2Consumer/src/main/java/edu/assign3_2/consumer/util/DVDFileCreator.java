package edu.assign3_2.consumer.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DVDFileCreator {

	public DVDFileCreator() {
		
	}
	
	public void createNewDVDFile(String dvd) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
			LocalDateTime now = LocalDateTime.now();
			String filename = "DVD_" + new String(dtf.format(now))+".txt";
			PrintWriter pw = new PrintWriter(filename, "UTF-8");
			pw.write(dvd);
			pw.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
