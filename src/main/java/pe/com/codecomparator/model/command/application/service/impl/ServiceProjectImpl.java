package pe.com.codecomparator.model.command.application.service.impl;

import java.io.File;

import pe.com.codecomparator.domain.Code;
import pe.com.codecomparator.domain.CodeComparatorConstants;
import pe.com.codecomparator.domain.Project;
import pe.com.codecomparator.domain.Package;
import pe.com.codecomparator.domain.util.FileUtil;
import pe.com.codecomparator.model.command.application.service.ServiceProject;
import pe.com.codecomparator.model.command.core.converter.CodeConverter;

public class ServiceProjectImpl implements ServiceProject {

	@Override
	public int checkIdeToBuildProject(String directoryName) {
		int ideProject = CodeComparatorConstants.WITHOUT_ID;
		File directoryProject = new File(directoryName);
		// ECLIPSE PROJECT
		for (File file : directoryProject.listFiles()) {
			if (file.isHidden()) {
				if (file.getName().equals(
						CodeComparatorConstants.NAME_XML_CLASSPATH_ECLIPSE))
					return CodeComparatorConstants.ECLIPSE_PROJECT;
			}
		}

		// NETBEANS PROJECT
		directoryProject = new File(directoryName);
		for (File file : directoryProject.listFiles()) {
			if (file.getName().equals(
					CodeComparatorConstants.NAME_BUILD_XML_FILE_NETBEANS)
					|| file.getName()
							.equals(CodeComparatorConstants.NAME_NBPROJECT_DIRECTORY_NETBEANS))
				return CodeComparatorConstants.NETBEANS_PROJECT;
		}

		return ideProject;
	}

	@Override
	public void populateProject(Project project, String directoryName) {
		travelDirectory(project, directoryName);
	}

	private void travelDirectory(Project project, String directory) {
		File projectSourceDirectory = new File(directory);
		for (File file : projectSourceDirectory.listFiles()) {
			if (file.isDirectory()) {
				Package _package = new Package();
				_package.setName(file.getName());
				project.getPackages().add(_package);
				travelDirectory(_package, file.getAbsolutePath());
			} else if (file.isFile() && isJavaCode(file)) {
				Code _code = new Code();
				_code.setName(file.getName());
				_code.setFile(file);
				_code.setQ(convertCode(_code));
				project.getCodes().add(_code);
			}
		}

	}

	private void travelDirectory(Package _package, String directory) {
		File projectSourceDirectory = new File(directory);
		for (File file : projectSourceDirectory.listFiles()) {
			if (file.isDirectory()) {
				Package __package = new Package();
				__package.setName(file.getName());
				_package.getPackages().add(__package);
				travelDirectory(__package, file.getAbsolutePath());
			} else if (file.isFile() && isJavaCode(file)) {
				Code _code = new Code();
				_code.setName(file.getName());
				_code.setFile(file);
				_code.setQ(convertCode(_code));
				_package.getCodes().add(_code);
			}
		}

	}

	private boolean isJavaCode(File file) {
		if (file.getName()
				.endsWith(CodeComparatorConstants.JAVA_FILE_EXTENSION)) {
			return true;
		}
		return false;
	}

	private Double[] convertCode(Code code) {
		CodeConverter converter = new CodeConverter();
		try {
			converter.setFile(FileUtil.obtainBufferedReaderFromFile(code
					.getFile()));
			converter.TransformCodeOperatorLevel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return converter.Q;
	}

}
