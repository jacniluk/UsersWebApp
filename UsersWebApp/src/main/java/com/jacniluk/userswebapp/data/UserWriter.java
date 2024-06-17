package com.jacniluk.userswebapp.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jacniluk.userswebapp.model.User;
import com.jacniluk.userswebapp.service.UserService;
import com.jacniluk.userswebapp.websocket.ProgressWebSocketController;

@Service
public class UserWriter
{
	@Autowired
    private UserService userService;
	
	@Autowired
    private ProgressWebSocketController progressWebSocketController;
	
	public void writeData(MultipartFile file, long recordsCount)
	{
		if (recordsCount > 0)
		{
			progressWebSocketController.sendSection("uploadToRemove");
			
			userService.deleteAll();
			
			progressWebSocketController.sendSection("removeToProgress");
		}
		else
		{
			progressWebSocketController.sendSection("uploadToProgress");
		}
		
		writeRecords(file);
	}
	
	private void writeRecords(MultipartFile file)
	{
		XmlParser xmlParser = new XmlParser();
		xmlParser.initialize(file);
        List<User> users;
        while ((users = xmlParser.getNextRecordsPart()).isEmpty() == false)
		{
        	userService.saveAll(users);
        	
        	progressWebSocketController.sendProgress(xmlParser.getProgress());
		}
	}
}