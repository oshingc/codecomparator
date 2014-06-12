package pe.com.codecomparator.model.command.application.service;

import pe.com.codecomparator.domain.Project;

public interface ServiceProject {

	/**
	 * Método que nos permite reconocer el tipo de IDE utilizado para el
	 * proyecto
	 * */
	public int checkIdeToBuildProject(String directoryName);

	/**
	 * Método que se encarga de poblar un objeto {@link pe.com.codecomparator.domain.Project}
	 * */
	public void populateProject(Project project, String directoryName);

}
