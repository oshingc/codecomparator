package pe.com.codecomparator.model.command.application.service;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.http.Part;

/**
 * Interface que se encarga de ofrecer servicios para manipular los código
 * fuente.
 * */

public interface ServiceFile {

	public void unzip(String source, String destination, String password);

	public void listf(String directoryName, ArrayList<File> files,
			String endWith);

	public void writeContentFolder(String fileName, InputStream inputStream,
			String directoryName);

	public void deleteContentFolder(String path);

	public boolean deleteDirectory(File path);

	public String getFilename(Part part);

	/**
	 * Método que retorna la ruta del código fuente dentro de las carpetas del
	 * proyecto
	 * 
	 * @param ideProject
	 *            int que identifica el tipo de IDE
	 * @param directoryProjectName
	 *            String que contiene la ruta del proyecto
	 * */
	public String getProjectSourcePath(int ideProject,
			String directoryProjectName);

}
