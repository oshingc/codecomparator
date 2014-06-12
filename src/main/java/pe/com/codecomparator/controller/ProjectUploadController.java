package pe.com.codecomparator.controller;

import java.io.Serializable;
import java.util.ArrayList;
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

	private Project firstProject = new Project();
	private Project secondProject = new Project();
	private List<ContainerChartResult> containers = new ArrayList<>();

	// Utilizado para visualizar o no las etiquetas de carga para el segundo
	// proyecto
	private boolean firstFileUpload = false;
	private boolean secondFileUpload = false;

	@Autowired
	private ProjectCommandFacade projectCommandFacade;

	@Autowired
	private ComparisonCommandFacade comparisonCommandFacade;

	public void uploadProjectA(FileUploadEvent event) {
		firstProject = new Project();
		try {
			projectCommandFacade.copyProject(firstProject, event.getFile()
					.getFileName(), event.getFile().getInputstream(),
					getPath(URLPROYECTO1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void uploadProjectB(FileUploadEvent event) {
		secondProject = new Project();
		try {
			projectCommandFacade.copyProject(secondProject, event.getFile()
					.getFileName(), event.getFile().getInputstream(),
					getPath(URLPROYECTO2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que se encarga de comparar ambos proyectos almacenados en el
	 * servidor
	 */
	public void compare() {
		resetAll();

		containers = new ArrayList<>();
		containers = comparisonCommandFacade.comparisionProject(
				this.firstProject, this.secondProject);
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/codecomparator/views/reports.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Restaura a false los booleanos que renderizan etiquetas de las vistas
	 */
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

	public Project getFirstProject() {
		return firstProject;
	}

	public void setFirstProject(Project firstProject) {
		this.firstProject = firstProject;
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

}