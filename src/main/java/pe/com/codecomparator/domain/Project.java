package pe.com.codecomparator.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Clase que almacenará el contenido del proyecto físico cargado al servidor
 * */
public class Project implements Serializable {

	private static final long serialVersionUID = -9104261263964389144L;
	private String name;
	private Collection<Code> codes = new ArrayList<Code>();
	private Collection<Package> packages = new ArrayList<Package>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Code> getCodes() {
		return codes;
	}

	public void setCodes(Collection<Code> codes) {
		this.codes = codes;
	}

	public Collection<Package> getPackages() {
		return packages;
	}

	public void setPackages(Collection<Package> packages) {
		this.packages = packages;
	}

	@Override
	public String toString() {
		return "Project : [name : " + name
				+ ((codes.isEmpty()) ? "" : "\n\tCodes : " + codes)
				+ ((packages.isEmpty()) ? "" : "\n\tPackages : " + packages)
				+ " ]";
	}
}
