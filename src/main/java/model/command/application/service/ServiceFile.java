package model.command.application.service;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.Part;


/**
 * Interface que se encarga de ofrecer servicios para manipular
 * los código fuente.
 * */

public interface ServiceFile {
	 public void unzip(String source ,String destination,String password);
	 public void listf(String directoryName, ArrayList<File> files, String endWith);
	 public void deleteContentFolder(String path);
	 public boolean deleteDirectory(File path);
	 public  String getFilename(Part part);
}
