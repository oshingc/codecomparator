package pe.com.codecomparator.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;

import pe.com.codecomparator.domain.User;
//import pe.com.codecomparator.model.command.facade.UserCommandFacade;
import pe.com.codecomparator.model.query.facade.UserQueryFacade;

//@ManagedBean(name = "login")
//@ViewScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = -25595180939938054L;

	// @Autowired
	// private UserCommandFacade userCommandFacade;
	@Autowired
	private UserQueryFacade userQueryFacade;

	private String username;
	private String password;

	public LoginController() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void init(ActionEvent actionEvent) throws IOException {
		System.out.println("LoginController.init()");
		if (this.username.equals("") && this.password.equals("")) {
			addWarn(FacesMessage.SEVERITY_WARN, "Complete todos los campos");
		} else {
			if (this.password.equals("")) {
				addWarn(FacesMessage.SEVERITY_WARN, "Ingrese una contraseña");
			} else {
				if (this.username.equals("")) {
					addWarn(FacesMessage.SEVERITY_WARN,
							"Ingrese nombre de usuario");

				} else {
					// Cotejar el usuario con los registrados en la base de
					// datos
					User actualUser = new User();
					actualUser.setT_username(username);
					actualUser.setT_password(password);

					User obtained = userQueryFacade.validateUser(actualUser);

					if (obtained == null)
						addWarn(FacesMessage.SEVERITY_ERROR,
								"Usuario no registrado");
					else {
						System.out.println(obtained);
						System.out.println("user: " + obtained.getT_username());
						System.out.println("user: " + obtained.getT_password());

						FacesContext.getCurrentInstance().getExternalContext()
								.redirect("/codecomparator/views/upload.xhtml");
					}
				}
			}

		}
	}

	public void addWarn(Severity severity, String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(severity, "Error:", message));
	}

}
