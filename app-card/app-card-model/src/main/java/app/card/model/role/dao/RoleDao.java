package app.card.model.role.dao;

import app.card.model.role.RoleVO;

public interface RoleDao {

	public RoleVO getRoleById(Long id);
	
	public RoleVO getRoleByUserId(Long id);
	
	public Long saveRole(RoleVO roleVO);
}
