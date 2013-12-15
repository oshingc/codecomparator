package pe.com.codecomparator.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Package implements Serializable {

	private static final long serialVersionUID = -4843115943266287488L;
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
		return "\nPackage : (name : "
				+ name
				+ ((codes.isEmpty()) ? "" : ", Codes<" + codes.size() + "> : "
						+ codes)
				+ ((packages.isEmpty()) ? "" : ", Packages<" + packages.size()
						+ "> : \n" + packages) + " )";
	}
}
