package pe.com.codecomparator.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

//import org.springframework.beans.factory.annotatison.Autowired;

import pe.com.codecomparator.model.command.application.service.ServiceFile;
import pe.com.codecomparator.model.command.application.service.impl.ServiceFileImpl;
//import model.command.application.service.impl.ServiceFileImpl;

//@ManagedBean(name = "fileController")
//@SessionScoped
public class FileController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String URLPROYECTO1 = "/webapp/projects/one/";
	private static final String URLPROYECTO2 = "/webapp/projects/two/";

	private Part file1;
	private Part file2;

	private ServiceFile f = new ServiceFileImpl();

	public Part getFile1() {
		return file1;
	}

	public void setFile1(Part file1) {
		this.file1 = file1;
	}

	public Part getFile2() {
		return file2;
	}

	public void setFile2(Part file2) {
		this.file2 = file2;
	}

	public String upload() throws IOException {

		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context
				.getExternalContext().getContext();

		String folderProyecto1 = servletContext.getRealPath(URLPROYECTO1);
		String folderProyecto2 = servletContext.getRealPath(URLPROYECTO2);

		f.deleteContentFolder(folderProyecto1);
		f.deleteContentFolder(folderProyecto2);

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

		ArrayList<File> filesProyecto2 = new ArrayList<File>();
		f.listf(folderProyecto2, filesProyecto2, ".java");

		return "success";
	}

	public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
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
