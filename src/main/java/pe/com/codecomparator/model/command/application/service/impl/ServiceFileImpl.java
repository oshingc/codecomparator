package pe.com.codecomparator.model.command.application.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.Part;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import pe.com.codecomparator.domain.CodeComparatorConstants;
import pe.com.codecomparator.domain.util.StringUtils;

import pe.com.codecomparator.model.command.application.service.ServiceFile;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class ServiceFileImpl implements ServiceFile {

	public void unzip(String source, String destination, String password) {

		try {
			ZipFile zipFile = new ZipFile(source);
			if (zipFile.isEncrypted()) {
				zipFile.setPassword(password);
			}
			zipFile.extractAll(destination);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}

	public void listf(String directoryName, ArrayList<File> files,
			String endWith) {
		File directory = new File(directoryName);

		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile() && file.getName().endsWith(endWith)) {
				files.add(file);
			} else if (file.isDirectory()) {
				listf(file.getAbsolutePath(), files, endWith);
			}
		}
	}

	@Override
	public void writeContentFolder(String fileName, InputStream inputStream,
			String directoryName) {
		try {
			OutputStream out = new FileOutputStream(new File(directoryName
					+ CodeComparatorConstants.FORWARDSLASH + fileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			inputStream.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteContentFolder(String path) {

		File directory = new File(path);

		File[] files = directory.listFiles();

		if (files != null) {
			for (File file : files) {

				if (file.isDirectory()) {
					deleteDirectory(file);
				}
			}
		}
	}

	public boolean deleteDirectory(File path) {
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (path.delete());
	}

	public String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1)
						.substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
			}
		}
		return null;
	}

	@Override
	public String getProjectSourcePath(int ideProject,
			String directoryProjectName) {
		File directoryProject = new File(directoryProjectName);
		String sourcePathProject = "";

		if (ideProject == CodeComparatorConstants.ECLIPSE_PROJECT) {
			File xml = null;
			for (File file : directoryProject.listFiles()) {
				if (file.isHidden()
						&& file.getName()
								.equals(CodeComparatorConstants.NAME_XML_CLASSPATH_ECLIPSE)) {
					xml = file;
					break;
				}
			}
			sourcePathProject = getSourcePathInEclipse(xml);
		}

		if (ideProject == CodeComparatorConstants.NETBEANS_PROJECT) {
			File nbproject = null;
			for (File file : directoryProject.listFiles()) {
				if (file.getName()
						.equals(CodeComparatorConstants.NAME_NBPROJECT_DIRECTORY_NETBEANS)) {
					nbproject = file;
					break;
				}
			}
			sourcePathProject = getSourcePathInNetbeans(directoryProjectName
					+ CodeComparatorConstants.FORWARDSLASH
					+ nbproject.getName()
					+ CodeComparatorConstants.FORWARDSLASH
					+ CodeComparatorConstants.PROPERTY_FILE_SRC_NETBEANS);
		}

		return sourcePathProject;
	}

	/**
	 * Método que retorna la ruta del código fuente dentro del proyecto
	 * 
	 * @param nbproject
	 *            String que contiene la ruta del directorio del proyecto
	 * */
	private String getSourcePathInNetbeans(String nbproject) {
		String sourcePath = null;
		try {
			Properties project = new Properties();
			project.load(new FileInputStream(nbproject));
			sourcePath = project
					.getProperty(CodeComparatorConstants.PROPERTY_SRC_NETBEANS);
			if (sourcePath.contains("\\")) {
				sourcePath = StringUtils
						.replaceBackslashByForwardslash(sourcePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sourcePath;
	}

	/**
	 * Método que retorna la ruta del código fuente dentro del proyecto
	 * 
	 * @param xml
	 *            objeto File que contiene el directorio del proyecto
	 * */
	private String getSourcePathInEclipse(File xml) {
		String sourcePath = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xml);
			doc.getDocumentElement().normalize();

			NodeList classpathentry = doc
					.getElementsByTagName(CodeComparatorConstants.TAG_NAME_CLASSPATH_ECLIPSE);
			NamedNodeMap namedNodeMap = classpathentry.item(0).getAttributes();

			for (int i = 0; i < namedNodeMap.getLength(); i++) {
				if (namedNodeMap
						.item(i)
						.getNodeName()
						.equals(CodeComparatorConstants.NODE_NAME_ATTRIBUTE_SRC_ECLIPSE)) {
					sourcePath = namedNodeMap.item(i).getNodeValue();
					break;
				}
			}

			return sourcePath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sourcePath;
	}

}
