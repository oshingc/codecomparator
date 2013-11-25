package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import model.command.application.service.ServiceFile;
import model.command.application.service.impl.ServiceFileImpl;

@ManagedBean(name = "fileController")
public class FileController {

	private static final String URLPROYECTO1 = "/opt/apache-tomcat-7.0.42/webapps/codecomparator/projects/uno";
	private static final String URLPROYECTO2 = "/opt/apache-tomcat-7.0.42/webapps/codecomparator/projects/dos";
	private Part file1;
	private Part file2;
	private ServiceFile f = new ServiceFileImpl();

	public Part getFile1() {
		System.out.println("FileController.getFile1()");
		return file1;
	}

	public void setFile1(Part file1) {
		System.out.println("FileController.setFile1()");
		this.file1 = file1;
	}

	public Part getFile2() {
		System.out.println("FileController.getFile2()");
		return file2;
	}

	public void setFile2(Part file2) {
		System.out.println("FileController.setFile2()");
		this.file2 = file2;
	}

	public String upload() throws IOException {
		System.out.println("FileController.upload()");

		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context
				.getExternalContext().getContext();

		File uno = new File(URLPROYECTO1);
		uno.mkdir();
		File dos = new File(URLPROYECTO2);
		dos.mkdir();

		String folderProyecto1 = servletContext.getRealPath(URLPROYECTO1);
		String folderProyecto2 = servletContext.getRealPath(URLPROYECTO2);

		f.deleteContentFolder(folderProyecto1);
		f.deleteContentFolder(folderProyecto2);

		System.out.println(folderProyecto1);
		System.out.println(folderProyecto2);

		String archivoProyecto1 = servletContext.getRealPath(URLPROYECTO1
				+ f.getFilename(file1));
		String archivoProyecto2 = servletContext.getRealPath(URLPROYECTO2
				+ f.getFilename(file2));

		file1.write(archivoProyecto1);
		file2.write(archivoProyecto2);

		f.unzip(archivoProyecto1, folderProyecto1, "");
		f.unzip(archivoProyecto2, folderProyecto2, "");

		ArrayList<File> filesProyecto1 = new ArrayList<File>();
		f.listf(folderProyecto1, filesProyecto1, ".java");

		System.out.println("Archivos java del proyecto 1");
		for (File file : filesProyecto1) {
			System.out.println(file.getName());
			System.out.println(file.getAbsolutePath());
			System.out.println();

		}

		ArrayList<File> filesProyecto2 = new ArrayList<File>();
		f.listf(folderProyecto2, filesProyecto2, ".java");

		System.out.println("Archivos java del proyecto 2");
		for (File file : filesProyecto2) {
			System.out.println(file.getName());
			System.out.println(file.getAbsolutePath());
			System.out.println();

		}

		return "success";
	}

	public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
		System.out.println("FileController.validateFile()");
		ArrayList<FacesMessage> msgs = new ArrayList<FacesMessage>();
		Part file = (Part) value;
		String filename = f.getFilename(file);
		String extension = filename.substring(filename.lastIndexOf(".") + 1,
				filename.length());
		if (extension.compareTo("zip") != 0) {
			msgs.add(new FacesMessage("Solo archivos .zip"));
		}

		if (!msgs.isEmpty()) {

			throw new ValidatorException(msgs);
		}
	}

}
