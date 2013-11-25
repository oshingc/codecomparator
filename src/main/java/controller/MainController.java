package controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "mc")
@ViewScoped
public class MainController implements Serializable {

	private static final long serialVersionUID = -25595180939938054L;
	private String jsfName;
	private String primeName;

	public String getJsfName() {
		return jsfName;
	}

	public void setJsfName(String jsfName) {
		this.jsfName = jsfName;
	}

	public String getPrimeName() {
		return primeName;
	}

	public void setPrimeName(String primeName) {
		this.primeName = primeName;
	}

}
