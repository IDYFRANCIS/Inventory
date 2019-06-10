package com.bizzdesk.inventory.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.bizzdesk.inventory.dto.ServerResponse;
import com.bizzdesk.inventory.model.Role;


@Service
public interface RoleService {
	
	 public Role findById(long id);

	    public Role findByName(String name);

	    public Collection<Role> getRoles();
	    
	    public ServerResponse findAllRole();

}
