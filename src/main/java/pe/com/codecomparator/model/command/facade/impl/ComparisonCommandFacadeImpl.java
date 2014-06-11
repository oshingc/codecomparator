package pe.com.codecomparator.model.command.facade.impl;

import java.io.Serializable;
import java.util.ArrayList;
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

	@Override
	public List<ContainerChartResult> comparisionProject(Project projectOne,
			Project projectTwo) {

		List<ContainerChartResult> containers = new ArrayList<>();
		containers = serviceComparison.comparisionProject(projectOne,
				projectTwo);

		return containers;

	}

}
