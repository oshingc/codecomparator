package pe.com.codecomparator.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
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

	private boolean login = false;

	public LoginController() {
	}

	public void login(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		if (username != null && username.equals("admin") && password != null
				&& password.equals("admin")) {
			login = true;
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@",
					username);
		} else {
			login = false;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
					"Credenciales no válidas");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("login", login);
		if (login)
			context.addCallbackParam("view",
					"/codecomparator/views/upload.xhtml");
	}

	public void logout() throws IOException {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.invalidate();
		login = false;

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/codecomparator/");
	}

	public void init(ActionEvent actionEvent) throws IOException {
		RequestContext context = RequestContext.getCurrentInstance();

		if (this.username.equals("") && this.password.equals("")) {
			login = false;
			addWarn(FacesMessage.SEVERITY_WARN, "Complete todos los campos");
		} else {
			if (this.password.equals("")) {
				login = false;
				addWarn(FacesMessage.SEVERITY_WARN, "Ingrese una contraseña");
			} else {
				if (this.username.equals("")) {
					login = false;
					addWarn(FacesMessage.SEVERITY_WARN,
							"Ingrese nombre de usuario");
				} else {
					// Cotejar el usuario con los registrados en la base de
					// datos
					User actualUser = new User();
					actualUser.setT_username(username);
					actualUser.setT_password(password);

					User obtained = userQueryFacade.validateUser(actualUser);
					if (obtained == null) {
						login = false;
						addWarn(FacesMessage.SEVERITY_ERROR,
								"Usuario no registrado");
					} else {
						login = true;
						addWarn(FacesMessage.SEVERITY_INFO,
								obtained.getT_username());
						context.addCallbackParam("login", login);
						context.addCallbackParam("view",
								"/codecomparator/views/upload.xhtml");
					}
				}
			}
		}
	}

	public void addWarn(Severity severity, String message) {
		String summary = (severity.equals(FacesMessage.SEVERITY_INFO)) ? "Bienvenid@:"
				: (severity.equals(FacesMessage.SEVERITY_WARN)) ? "Advertencia:"
						: "Error:";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(severity, summary, message));
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

	public boolean getLogin() {
		return login;
	}

}
