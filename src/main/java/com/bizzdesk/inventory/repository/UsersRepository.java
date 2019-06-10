package com.bizzdesk.inventory.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bizzdesk.inventory.model.Users;


@Repository
public interface UsersRepository extends CrudRepository<Users, Long>{
	
	public Users findById(long id);

	public Users findByEmailAddress(String emailAddress);
	
	public Users findByPhoneNumber(String phoneNumber);
	
	public Users findByPhoneNumberOrEmailAddress(String emailAddress, String phoneNumber);
	
	public Users findByActivationCode(String activationCode);
	
	public Users findByPasswordResetCode(String passwordResetCode);
	

}
