package pe.com.codecomparator.model.query.infrastructure;

import pe.com.codecomparator.domain.User;

public interface UserQueryInfrastructureService {
	
	public User validateUser(User user);
}
