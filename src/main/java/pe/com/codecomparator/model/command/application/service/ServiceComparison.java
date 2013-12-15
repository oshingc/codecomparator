package pe.com.codecomparator.model.command.application.service;

import java.io.BufferedReader;
import java.io.File;
import java.util.List;

import org.primefaces.model.chart.CartesianChartModel;

import pe.com.codecomparator.domain.Code;
import pe.com.codecomparator.domain.ContainerChartResult;
import pe.com.codecomparator.domain.Project;

/**
 * Interface que se encarga de ofrecer servicios para manipular los código
 * fuente.
 * */

public interface ServiceComparison {
	public Double compareFiles(Code code1, Code code2);

	public List<ContainerChartResult> comparisionProject(Project project1,
			Project project2);

	public BufferedReader getCodeFile(File file);

	public CartesianChartModel generateGrafic(Code code);

}
