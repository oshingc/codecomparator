package pe.com.codecomparator.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import pe.com.codecomparator.model.command.facade.ProjectCommandFacade;

import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;

import pe.com.codecomparator.domain.Project;

public class ProjectUploadController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7371096881101908449L;
	private static final String URLPROYECTO1 = "/projects/one/";
	private static final String URLPROYECTO2 = "/projects/two/";

	private Project firtsProject = new Project();
	private Project secondProject = new Project();
	private String display = "none";

	@Autowired
	private ProjectCommandFacade projectCommandFacade;

	public void uploadProjectA(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Success! ", event.getFile()
				.getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		try {
			projectCommandFacade.copyProject(firtsProject, event.getFile()
					.getFileName(), event.getFile().getInputstream(),
					getPath(URLPROYECTO1));
			setDisplay("block");
			System.out.println("result : "+display);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void uploadProjectB(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Success! ", event.getFile()
				.getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		try {
			projectCommandFacade.copyProject(secondProject, event.getFile()
					.getFileName(), event.getFile().getInputstream(),
					getPath(URLPROYECTO2));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getPath(String path) {

		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context
				.getExternalContext().getContext();

		return servletContext.getRealPath(path);

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
	
	public void setDisplay(String display){
		this.display = display;
	}
	
	public String getDisplay(){
		return display;
	}
	

}