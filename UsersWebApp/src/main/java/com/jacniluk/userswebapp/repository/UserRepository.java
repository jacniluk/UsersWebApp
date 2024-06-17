package com.jacniluk.userswebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jacniluk.userswebapp.model.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>
{
	
}
