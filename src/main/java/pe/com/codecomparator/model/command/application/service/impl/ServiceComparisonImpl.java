package pe.com.codecomparator.model.command.application.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import pe.com.codecomparator.domain.Code;
import pe.com.codecomparator.domain.ContainerChartResult;
import pe.com.codecomparator.domain.Package;
import pe.com.codecomparator.domain.Project;
import pe.com.codecomparator.model.command.application.service.ServiceComparison;
import pe.com.codecomparator.model.command.core.fdtw.FDTW;

public class ServiceComparisonImpl implements ServiceComparison {

	private Code code2 = new Code();
	private Double min = new Double(Double.MAX_VALUE);
	private FDTW fdtw = new FDTW();

	public Double compareFiles(Code code1, Code code2) {

		/*
		 * BufferedReader code1 = getCodeFile(file1); BufferedReader code2 =
		 * getCodeFile(file2); codeconverter = new CodeConverter();
		 * codeconverter.setFile(code1);
		 * codeconverter.TransformCodeOperatorLevel(); Double[] d1 =
		 * codeconverter.Q; codeconverter = new CodeConverter();
		 * codeconverter.setFile(code2);
		 * codeconverter.TransformCodeOperatorLevel(); Double[] d2 =
		 * codeconverter.Q;
		 */
		fdtw = new FDTW();
		fdtw.SetSequences(code1.getQ(), code2.getQ());
		Double a = fdtw.GetDistance();

		return a;
	}

	public BufferedReader getCodeFile(File file) {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

		} catch (Exception e) {
			return null;
		}
		return br;
	}

	public List<ContainerChartResult> comparisionProject(Project project1,
			Project project2) {
		List<ContainerChartResult> containers = new ArrayList<ContainerChartResult>();

		travelComparisionProject(project1, project2, containers);
		return containers;
	}

	private void travelComparisionProject(Project project1, Project project2,
			List<ContainerChartResult> containers) {
		ArrayList<Code> codes = (ArrayList<Code>) project1.getCodes();
		ArrayList<Package> packages = (ArrayList<Package>) project1
				.getPackages();

		for (Code code1 : codes) {
			this.code2 = new Code();
			this.min = new Double(Double.MAX_VALUE);
			travelComparisionMinProject(project2, code1);

			ContainerChartResult container = new ContainerChartResult();

			container.setCodeFirstProyect(code1);
			container.setCodeSecondProyect(this.code2);
			container.setFdtw(String.valueOf(this.min));
			container.setNameFirstProyect(project1.getName());
			container.setNameSecondProyect(project2.getName());
			container.setChartOne(this.generateGrafic(code1));
			container.setChartTwo(this.generateGrafic(this.code2));
			containers.add(container);
		}

		for (Package _package : packages) {
			travelComparisionProject(_package, project1, project2, containers);
		}

	}

	private void travelComparisionProject(Package package1, Project project1,
			Project project2, List<ContainerChartResult> containers) {
		ArrayList<Code> codes = (ArrayList<Code>) package1.getCodes();
		ArrayList<Package> packages = (ArrayList<Package>) package1
				.getPackages();

		for (Code code1 : codes) {
			this.code2 = new Code();
			this.min = new Double(Double.MAX_VALUE);
			travelComparisionMinProject(project2, code1);
			ContainerChartResult container = new ContainerChartResult();
			container.setCodeFirstProyect(code1);
			container.setCodeSecondProyect(this.code2);
			container.setFdtw(String.valueOf(this.min));
			container.setNameFirstProyect(project1.getName());
			container.setNameSecondProyect(project2.getName());

			container.setChartOne(this.generateGrafic(code1));
			container.setChartTwo(this.generateGrafic(this.code2));

			containers.add(container);

		}

		for (Package _package : packages) {
			travelComparisionProject(_package, project1, project2, containers);
		}

	}

	private void travelComparisionMinProject(Project project2, Code code1) {

		ArrayList<Code> codes = (ArrayList<Code>) project2.getCodes();
		ArrayList<Package> packages = (ArrayList<Package>) project2
				.getPackages();

		for (Code code : codes) {
			Double rpt = compareFiles(code1, code);
			if (rpt < this.min) {
				this.code2 = code;
				this.min = new Double(rpt);
			}
		}

		for (Package _package : packages) {
			travelComparisionMinProject(_package, code1);
		}

	}

	private void travelComparisionMinProject(Package package2, Code code1) {

		ArrayList<Code> codes = (ArrayList<Code>) package2.getCodes();
		ArrayList<Package> packages = (ArrayList<Package>) package2
				.getPackages();

		for (Code code : codes) {
			Double rpt = compareFiles(code1, code);
			if (rpt < this.min) {
				this.code2 = code;
				this.min = new Double(rpt);
			}
		}

		for (Package _package : packages) {
			travelComparisionMinProject(_package, code1);
		}

	}

	public CartesianChartModel generateGrafic(Code code) {
		CartesianChartModel chart = new CartesianChartModel();
		ChartSeries value = new ChartSeries();
		// value.setLabel(code.getName());
		Double[] Q = code.getQ();

		for (int i = 0; i < Q.length; i++)
			value.set(i + 1, Q[i]);
		chart.addSeries(value);

		return chart;
	}

	public Code getCode2() {
		return code2;
	}

	public void setCode2(Code code2) {
		this.code2 = code2;
	}

	public Double getMin() {
		return min;
	}

	public void setMinr(Double min) {
		this.min = min;
	}

}
