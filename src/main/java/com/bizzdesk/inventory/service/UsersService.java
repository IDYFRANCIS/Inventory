package com.bizzdesk.inventory.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.bizzdesk.inventory.dto.ActivateUserRequest;
import com.bizzdesk.inventory.dto.PasswordResetDto;
import com.bizzdesk.inventory.dto.ResendUserActivationCodeDto;
import com.bizzdesk.inventory.dto.ResendUserPasswordDto;
import com.bizzdesk.inventory.dto.ServerResponse;
import com.bizzdesk.inventory.dto.SignInRequest;
import com.bizzdesk.inventory.dto.SignUpRequest;
import com.bizzdesk.inventory.dto.UpdateUserRequestDto;
import com.bizzdesk.inventory.model.Users;


@Service
public interface UsersService {
	
	public Collection<Users> findAll();
	
	public Users findById(long usersId);
	
	public Users findByEmail(String emailAddress);
	
	public Users findByPhone(String phoneNumber);
	
	public Users findByEmailOrPhone(String emailAddress, String phoneNumber);

	public ServerResponse create(SignUpRequest request);
	
	public ServerResponse userActivation(ActivateUserRequest request);
	
	public ServerResponse reSendUserActivation(ResendUserActivationCodeDto request);
	
	public ServerResponse reSendUserPassword(ResendUserPasswordDto request);
	
	public ServerResponse passwordReset(PasswordResetDto request);
	
	public ServerResponse updateUser(UpdateUserRequestDto request);
	
	public ServerResponse login(SignInRequest request);
	
	public ServerResponse viewAll();
	
	public ServerResponse delete(long usersId);
	

}
