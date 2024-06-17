package com.jacniluk.userswebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jacniluk.userswebapp.data.UserReader;
import com.jacniluk.userswebapp.data.UserWriter;

@Controller
public class LoadDataController
{
	private final UserReader userReader;
	private final UserWriter userWriter;
	
	public LoadDataController(UserReader _userReader, UserWriter _userWriter)
	{
		userReader = _userReader;
        userWriter = _userWriter;
    }
	
	@GetMapping("/load-data")
    public String loadData(Model model)
    {
        return "load-data";
    }
    
    @PostMapping("/import-file")
    public String importFile(@RequestParam("file") MultipartFile file, Model model)
    {
    	userWriter.writeData(file, userReader.getRecordsCount());
    	
        return "load-data";
    }
}