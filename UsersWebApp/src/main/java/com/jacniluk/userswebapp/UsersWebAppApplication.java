package com.jacniluk.userswebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsersWebAppApplication
{
	public static void main(String[] args)
	{
		String mode = System.getProperty("mode");
		if (mode != null && mode.equals("development"))
		{
			System.setProperty("spring.datasource.username", args[1]);
		    System.setProperty("spring.datasource.password", args[2]);
		}

	    SpringApplication.run(UsersWebAppApplication.class, args);
	}
}
