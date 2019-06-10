package com.bizzdesk.inventory.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.bizzdesk.inventory.dto.ServerResponse;
import com.bizzdesk.inventory.model.Privilege;
import com.bizzdesk.inventory.model.Role;


@Service
public interface PrivilegeService {
	
	 	public Privilege findById(long id);

	    public Privilege findByName(String name);

	    public ServerResponse update(Privilege privilege);

	    public ServerResponse create(Privilege privilege);

	    public ServerResponse delete(Privilege role);

	    public ServerResponse getPrivileges();
	    
	    public ServerResponse getPrivilegesByRole(String name);

	    public Collection<Privilege> findAllByRoles(Role role);

}
