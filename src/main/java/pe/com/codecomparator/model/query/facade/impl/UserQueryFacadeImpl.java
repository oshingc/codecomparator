package pe.com.codecomparator.model.query.facade.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import pe.com.codecomparator.domain.User;
import pe.com.codecomparator.model.query.facade.UserQueryFacade;
import pe.com.codecomparator.model.query.infrastructure.UserQueryInfrastructureService;

public class UserQueryFacadeImpl implements UserQueryFacade, Serializable {

	private static final long serialVersionUID = -5406191334386793613L;
	@Autowired
	private UserQueryInfrastructureService userQueryInfrastructureService;

	@Override
	public User validateUser(User user) {
		return userQueryInfrastructureService.validateUser(user);
	}

}
