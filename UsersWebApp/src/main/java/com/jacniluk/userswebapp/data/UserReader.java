package com.jacniluk.userswebapp.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacniluk.userswebapp.model.User;
import com.jacniluk.userswebapp.service.UserService;

@Service
public class UserReader
{
	@Autowired
    private UserService userService;
	
	public List<User> findAll(String searchColumn, String searchValue, String sortColumn, String sortType, int page, int pageSize)
	{
        if (searchValue.isEmpty())
		{
			return userService.findAll(sortColumn, sortType , page, pageSize);
		}
        else
        {
        	return userService.findAll(searchColumn, searchValue, sortColumn, sortType , page, pageSize);
        }
	}
	
	public long getRecordsCount()
	{
		return userService.getCount();
	}
	
	public long getRecordsCount(String searchColumn, String searchValue)
	{
		if (searchValue.isEmpty())
		{
			return getRecordsCount();
		}
		else
		{
			return userService.getCount(searchColumn, searchValue);
		}
	}
}