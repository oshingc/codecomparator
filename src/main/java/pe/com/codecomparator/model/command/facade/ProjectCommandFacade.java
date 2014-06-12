package pe.com.codecomparator.model.command.facade;

import java.io.InputStream;

import pe.com.codecomparator.domain.Project;

public interface ProjectCommandFacade {

	/**
	 * Método que copia el contenido del .zip al servidor utilizando métodos de
	 * la clase {@link pe.com.codecomparator.model.command.application.service.ServiceFile}
	 * */
	public void copyProject(Project project, String fileName,
			InputStream inputStream, String directoryName);

}
