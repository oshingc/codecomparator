package pe.com.codecomparator.model.command.facade.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pe.com.codecomparator.domain.ContainerChartResult;
import pe.com.codecomparator.domain.Project;
//import pe.com.codecomparator.domain.Statistic;
import pe.com.codecomparator.model.command.application.service.ServiceComparison;
import pe.com.codecomparator.model.command.facade.ComparisonCommandFacade;
//import pe.com.codecomparator.model.command.persistence.StatisticDTO;

public class ComparisonCommandFacadeImpl implements ComparisonCommandFacade,
		Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ServiceComparison serviceComparison;

	/*
	 * @Override public List<Statistic> comparisionProject(Project
	 * project1,Project project2) {
	 * 
	 * List<StatisticDTO> statistics =
	 * serviceComparison.comparisionProject(project1, project2);
	 * 
	 * 
	 * for(Statistic statistic : statistics){ System.out.println();
	 * System.out.println(statistic.getNombreArchivoProyecto1());
	 * System.out.println(statistic.getNombreArchivoProyecto2());
	 * System.out.println(statistic.getValor()); System.out.println(); } return
	 * statistics; }
	 */

	@Override
	public List<ContainerChartResult> comparisionProject(Project projectOne,
			Project projectTwo) {

		List<ContainerChartResult> containers = serviceComparison.comparisionProject(
				projectOne, projectTwo);

		for (ContainerChartResult container : containers) {
			System.out.println();
			System.out.println(container.getCodeFirstProyect().getName());
			System.out.println(container.getCodeSecondProyect().getName());
			System.out.println(container.getFdtw());
			System.out.println();
		}
		return containers;

	}

}
