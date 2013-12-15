package pe.com.codecomparator.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.codecomparator.domain.ContainerChartResult;

public class ReportController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7121477687076224246L;

	private List<ContainerChartResult> containers = new ArrayList<>();

	public List<ContainerChartResult> getContainers() {
		return containers;
	}

	public void setContainers(List<ContainerChartResult> containers) {
		this.containers = containers;
	}

}
