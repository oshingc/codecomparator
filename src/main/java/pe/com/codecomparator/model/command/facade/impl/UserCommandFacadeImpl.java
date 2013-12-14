package pe.com.codecomparator.model.command.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;

import pe.com.codecomparator.domain.User;
import pe.com.codecomparator.model.command.application.service.UserCommandApplicationService;
import pe.com.codecomparator.model.command.facade.UserCommandFacade;

public class UserCommandFacadeImpl implements UserCommandFacade {

	@Autowired
	private UserCommandApplicationService userCommandApplicationService;

	@Override
	public void createUser(User user) {
		userCommandApplicationService.createUser(user);
		
	}

}
