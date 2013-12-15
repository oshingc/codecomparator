package pe.com.codecomparator.domain;

import org.primefaces.model.chart.CartesianChartModel;

public class ContainerChartResult {

	private CartesianChartModel chartOne;
	private CartesianChartModel chartTwo;
	private String fdtw;
	private Code codeFirstProyect;
	private Code codeSecondProyect;
	private String nameFirstProyect;
	private String nameSecondProyect;
	
	public CartesianChartModel getChartOne() {
		return chartOne;
	}

	public void setChartOne(CartesianChartModel chartOne) {
		this.chartOne = chartOne;
	}

	public CartesianChartModel getChartTwo() {
		return chartTwo;
	}

	public void setChartTwo(CartesianChartModel chartTwo) {
		this.chartTwo = chartTwo;
	}

	public String getFdtw() {
		return fdtw;
	}

	public void setFdtw(String fdtw) {
		this.fdtw = fdtw;
	}
	

	public Code getCodeFirstProyect() {
		return codeFirstProyect;
	}

	public void setCodeFirstProyect(Code codeFirstProyect) {
		
		this.codeFirstProyect = codeFirstProyect;
	}

	public Code getCodeSecondProyect() {
		return codeSecondProyect;
	}

	public void setCodeSecondProyect(Code codeSecondProyect) {
		
		this.codeSecondProyect = codeSecondProyect;
	}

	public String getNameFirstProyect() {
		return nameFirstProyect;
	}

	public void setNameFirstProyect(String nameFirstProyect) {
		this.nameFirstProyect = nameFirstProyect;
	}

	public String getNameSecondProyect() {
		return nameSecondProyect;
	}

	public void setNameSecondProyect(String nameSecondProyect) {
		this.nameSecondProyect = nameSecondProyect;
	}
	
	


}
