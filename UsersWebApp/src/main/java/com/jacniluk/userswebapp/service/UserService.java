package com.jacniluk.userswebapp.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jacniluk.userswebapp.model.User;
import com.jacniluk.userswebapp.repository.UserRepository;
import com.jacniluk.userswebapp.specifications.UserSpecifications;

@Service
public class UserService
{
	private final UserRepository userRepository;

    public UserService(UserRepository _userRepository)
    {
        userRepository = _userRepository;
    }
    
    public void saveAll(List<User> users)
    {
    	userRepository.saveAll(users);
    }
    
    public void deleteAll()
    {
    	userRepository.deleteAll();
    }
    
    public List<User> findAll(String sortColumn, String sortType, int page, int pageSize)
    {
    	Specification<User> specification = Specification.where(UserSpecifications.sort(sortColumn, sortType));
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        
        return userRepository.findAll(specification, pageable).getContent();
    }
    
    public List<User> findAll(String searchColumn, String searchValue, String sortColumn, String sortType, int page, int pageSize)
    {
    	Specification<User> specification = Specification.where(UserSpecifications.search(searchColumn, searchValue)).and(UserSpecifications.sort(sortColumn, sortType));
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        
        return userRepository.findAll(specification, pageable).getContent();
    }

    public long getCount()
    {
        return userRepository.count();
    }
    
    public long getCount(String searchColumn, String searchValue)
    {
    	Specification<User> specification = Specification.where(UserSpecifications.search(searchColumn, searchValue));
        
        return userRepository.count(specification);
    }
}
