package com.jacniluk.userswebapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String name;
	private String surname;
	private String login;
	
	public User()
	{
		
	}
	
	public User(String _name, String _surname, String _login)
	{
		name = _name;
		surname = _surname;
		login = _login;
	}
	
	public String getName()
	{
		return name;
	}

	public String getSurname()
	{
		return surname;
	}
	
	public void setSurname(String _surname)
	{
		surname = _surname;
	}
	
	public String getLogin()
	{
		return login;
	}
}
