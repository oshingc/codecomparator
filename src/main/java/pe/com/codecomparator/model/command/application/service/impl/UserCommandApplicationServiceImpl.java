package pe.com.codecomparator.model.command.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import pe.com.codecomparator.domain.User;
import pe.com.codecomparator.model.command.application.service.UserCommandApplicationService;
import pe.com.codecomparator.model.command.infrastructure.UserCommandInfrastructureService;

public class UserCommandApplicationServiceImpl implements UserCommandApplicationService{
	
	@Autowired
	private UserCommandInfrastructureService userCommandInfrastructureService; 

	@Override
	public void createUser(User user) {
		userCommandInfrastructureService.createUser(user);
		
	}
}
	
