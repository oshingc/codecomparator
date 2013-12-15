package pe.com.codecomparator.model.command.facade.impl;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;

import pe.com.codecomparator.domain.CodeComparatorConstants;
import pe.com.codecomparator.domain.Project;

import pe.com.codecomparator.model.command.application.service.ServiceFile;
import pe.com.codecomparator.model.command.application.service.ServiceProject;
import pe.com.codecomparator.model.command.facade.ProjectCommandFacade;

public class ProjectCommandFacadeImpl implements ProjectCommandFacade {

	@Autowired
	private ServiceFile serviceFile;

	@Autowired
	private ServiceProject serviceProject;

	@Override
	public void copyProject(Project project, String fileName,
			InputStream inputStream, String directoryName) {
		serviceFile.deleteContentFolder(directoryName
				+ CodeComparatorConstants.FORWARDSLASH);

		serviceFile.writeContentFolder(fileName, inputStream, directoryName);

		serviceFile.unzip(directoryName + CodeComparatorConstants.FORWARDSLASH
				+ fileName, directoryName, "");

		String[] words = fileName.split(CodeComparatorConstants.POINT);
		String fileNameWithoutExtension = words[0];

		int ideProject = serviceProject.checkIdeToBuildProject(directoryName
				+ CodeComparatorConstants.FORWARDSLASH
				+ fileNameWithoutExtension);

		String projectSourcePath = serviceFile.getProjectSourcePath(ideProject,
				directoryName + CodeComparatorConstants.FORWARDSLASH
						+ fileNameWithoutExtension);
		projectSourcePath = directoryName
				+ CodeComparatorConstants.FORWARDSLASH
				+ fileNameWithoutExtension
				+ CodeComparatorConstants.FORWARDSLASH + projectSourcePath;

		project.setName(fileNameWithoutExtension);
		serviceProject.populateProject(project, projectSourcePath);

		// System.out.println(project.getPackages());

		// TODO eliminar este recorrido, sólo útil para ver los archivos java en
		// el directorio del proyecto
		// ArrayList<File> filesProyecto1 = new ArrayList<File>();
		// serviceFile.listf(directoryName, filesProyecto1,
		// CodeComparatorConstants.JAVA_FILE_EXTENSION);
		//
		// System.out.println("Archivos java del proyecto:");
		// for (File file : filesProyecto1) {
		// System.out.println(file.getName());
		// System.out.println(file.getAbsolutePath());
		// System.out.println();
		//
		// }
		//
		// System.out.println("New file created!");
	}

}
