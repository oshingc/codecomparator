package pe.com.codecomparator.controller;

import java.io.Serializable;
import java.util.List;

//import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import pe.com.codecomparator.model.command.facade.ComparisonCommandFacade;
import pe.com.codecomparator.model.command.facade.ProjectCommandFacade;

import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;

import pe.com.codecomparator.domain.ContainerChartResult;
import pe.com.codecomparator.domain.Project;

public class ProjectUploadController implements Serializable {

	private static final long serialVersionUID = -7371096881101908449L;
	private static final String URLPROYECTO1 = "/projects/one/";
	private static final String URLPROYECTO2 = "/projects/two/";

	private Project firtsProject = new Project();
	private Project secondProject = new Project();
	private List<ContainerChartResult> containers;

	// Utilizado para visualizar o no las etiquetas de carga para el segundo
	// proyecto
	private boolean firstFileUpload = false;
	private boolean secondFileUpload = false;
	private String display = "none";

	@Autowired
	private ProjectCommandFacade projectCommandFacade;

	@Autowired
	private ComparisonCommandFacade comparisonCommandFacade;

	public void uploadProjectA(FileUploadEvent event) {
		// FacesMessage msg = new FacesMessage("Success! ", event.getFile()
		// .getFileName() + " is uploaded.");
		// FacesContext.getCurrentInstance().addMessage(null, msg);
		try {
			projectCommandFacade.copyProject(firtsProject, event.getFile()
					.getFileName(), event.getFile().getInputstream(),
					getPath(URLPROYECTO1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void uploadProjectB(FileUploadEvent event) {
		// FacesMessage msg = new FacesMessage("Success! ", event.getFile()
		// .getFileName() + " is uploaded.");
		// FacesContext.getCurrentInstance().addMessage(null, msg);
		try {
			projectCommandFacade.copyProject(secondProject, event.getFile()
					.getFileName(), event.getFile().getInputstream(),
					getPath(URLPROYECTO2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void compare() {

		resetAll();

		containers = comparisonCommandFacade.comparisionProject(
				this.firtsProject, this.secondProject);
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/codecomparator/views/reports.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void resetAll() {
		firstFileUpload = false;
		secondFileUpload = false;
	}

	public String getPath(String path) {
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context
				.getExternalContext().getContext();

		return servletContext.getRealPath(path);
	}

	public void resetOne() {
		firstFileUpload = true;
	}

	public void resetTwo() {
		secondFileUpload = true;
	}

	public Project getFirtsProject() {
		return firtsProject;
	}

	public void setFirtsProject(Project firtsProject) {
		this.firtsProject = firtsProject;
	}

	public Project getSecondProject() {
		return secondProject;
	}

	public void setSecondProject(Project secondProject) {
		this.secondProject = secondProject;
	}

	public List<ContainerChartResult> getContainers() {
		return containers;
	}

	public void setContainers(List<ContainerChartResult> containers) {
		this.containers = containers;
	}

	public boolean getFirstFileUpload() {
		return firstFileUpload;
	}

	public boolean getSecondFileUpload() {
		return secondFileUpload;
	}

	public String getDisplay() {
		return display;
	}

}