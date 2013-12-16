package pe.com.codecomparator.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;

import pe.com.codecomparator.domain.User;
import pe.com.codecomparator.model.command.facade.UserCommandFacade;
import pe.com.codecomparator.model.query.facade.UserQueryFacade;

//@ManagedBean(name = "login")
//@ViewScoped
public class AccountController implements Serializable {

	private static final long serialVersionUID = -25595180939938054L;
	
	@Autowired
	private UserQueryFacade userQueryFacade;
	@Autowired
	private UserCommandFacade userCommandFacade;

	private String username;
	private String password;
	private String email;
	private String showProfile="none";
	

	public AccountController() {
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
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}

	public String getShowProfile() {
		return showProfile;
	}

	public void setShowProfile(String showProfile) {
		this.showProfile = showProfile;
	}

	public void createUser(ActionEvent actionEvent) throws IOException {
		System.out.println("AccountController.init()");
		if (this.username.equals("") && this.password.equals("")) {
			addWarn(actionEvent, "Ingrese todos los campos");
		} else {
			if (this.password.equals("")) {
				addWarn(actionEvent, "Ingrese una contraseña");
			} else {
				if (this.username.equals("")) {
					addWarn(actionEvent, "Ingrese nombre de usuario");

				} else {

					// busqueda en la db
					User actualUser = new User();
					actualUser.setT_username(username);
					actualUser.setT_password(password);
					actualUser.setT_email(email);

					System.out.println(userQueryFacade);

					User obtained = userQueryFacade.userExists(actualUser);
					
					if(obtained == null){
						System.out.println("new user");
						//procedemos a crear usuario
						userCommandFacade.createUser(actualUser);
						System.out.println("create user call");
						setShowProfile("block");
					}else{
						//mensaje ya existe cuenta
						if(obtained.getT_username().equals(actualUser.getT_username())){
							addInfo(actionEvent, "Nombre de usuario ya utilizado");
							System.out.println("user: " + obtained.getT_username());
							//System.out.println("user: " + obtained.getT_password());
							System.out.println("ya existe");
						}
						
					}

					/*FacesContext.getCurrentInstance().getExternalContext()
							.redirect("/codecomparator/views/uploadPF.xhtml");*/

				}
			}

		}
	}

	public void addWarn(ActionEvent actionEvent, String message) {
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Error:",
								message));
	}
	
	public void addInfo(ActionEvent actionEvent, String message) {
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:",
								message));
	}
	
	public void startAccount(ActionEvent actionEvent) throws IOException{
			
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/codecomparator/views/uploadPF.xhtml");
		
	}

}
