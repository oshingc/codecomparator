package pe.com.codecomparator.model.query.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;

import pe.com.codecomparator.domain.User;
import pe.com.codecomparator.model.query.facade.UserQueryFacade;
import pe.com.codecomparator.model.query.infrastructure.UserQueryInfrastructureService;

public class UserQueryFacadeImpl implements UserQueryFacade{

	@Autowired
	private UserQueryInfrastructureService userQueryInfrastructureService;
	
	@Override
	public User validateUser(User user) {
		return userQueryInfrastructureService.validateUser(user);
	}
	
	@Override
	public User userExists(User user){
		return userQueryInfrastructureService.userExists(user);
	}

	@Override
	public User emailExists(User user) {
		return userQueryInfrastructureService.emailExists(user);
	}

}
