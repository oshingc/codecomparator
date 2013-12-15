package pe.com.codecomparator.model.command.facade;

import java.util.List;

import pe.com.codecomparator.domain.ContainerChartResult;
import pe.com.codecomparator.domain.Project;

//import pe.com.codecomparator.model.command.persistence.StatisticDTO;

public interface ComparisonCommandFacade {
	public List<ContainerChartResult> comparisionProject(Project project1,
			Project project2);
}
