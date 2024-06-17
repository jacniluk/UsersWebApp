package com.jacniluk.userswebapp.data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.jacniluk.userswebapp.model.User;

public class HashManager
{
	public void addMD5ToSurnames(List<User> users)
	{
		for (int i = 0; i < users.size(); i++)
		{
			users.get(i).setSurname(users.get(i).getSurname() + "_" + getMD5(users.get(i).getName()));
		}
	}
	
	private String getMD5(String text)
	{
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] messageDigestBytes = messageDigest.digest(text.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < messageDigestBytes.length; i++)
            {
            	stringBuilder.append(String.format("%02x", messageDigestBytes[i] & 0xff));
            }
            
            return stringBuilder.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            
            return "";
        }
	}
}
