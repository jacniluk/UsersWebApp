package com.jacniluk.userswebapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jacniluk.userswebapp.data.UserReader;
import com.jacniluk.userswebapp.data.HashManager;
import com.jacniluk.userswebapp.model.User;

@Controller
public class ShowDataController
{
	private final UserReader userReader;
	
	private final int pageSize = 50;
	
	public ShowDataController(UserReader _userReader)
	{
        userReader = _userReader;
    }
	
	@GetMapping("/show-data")
    public String showData(
    		@RequestParam(name = "search-column", defaultValue = "name") String searchColumn,
    		@RequestParam(name = "search-value", defaultValue = "") String searchValue,
    		@RequestParam(name = "sort-column", defaultValue = "name") String sortColumn,
    		@RequestParam(name = "sort-type", defaultValue = "asc") String sortType,
    		@RequestParam(name = "page", defaultValue = "1") int page,
    		Model model)
    {
		List<User> users = userReader.findAll(searchColumn, searchValue, sortColumn, sortType, page, pageSize);
        HashManager hashManager = new HashManager();
        hashManager.addMD5ToSurnames(users);
        
        model.addAttribute("users", users);
        model.addAttribute("pagesAmount", (int) Math.ceil((float) userReader.getRecordsCount(searchColumn, searchValue) / pageSize));
        
        model.addAttribute("searchColumn", searchColumn);
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("sortColumn", sortColumn);
        model.addAttribute("sortType", sortType);
        model.addAttribute("page", page);

        return "show-data";
    }
	
    @GetMapping("/search-data")
    public String searchData(
    		@RequestParam("search-column") String searchColumn,
    		@RequestParam("search-value") String searchValue,
    		@RequestParam("sort-column") String sortColumn,
    		@RequestParam("sort-type") String sortType,
    		RedirectAttributes redirectAttributes)
    {
    	if (searchValue.isEmpty() == false)
    	{
    		redirectAttributes.addAttribute("search-column", searchColumn);
        	redirectAttributes.addAttribute("search-value", searchValue);
    	}
    	if (sortColumn.equals("name") == false || sortType.equals("asc") == false)
    	{
    		redirectAttributes.addAttribute("sort-column", sortColumn);
            redirectAttributes.addAttribute("sort-type", sortType);
    	}

        return "redirect:/show-data";
    }
    
    @GetMapping("/sort-data")
    public String sortData(
    		@RequestParam("search-column") String searchColumn,
    		@RequestParam("search-value") String searchValue,
    		@RequestParam("sort-column") String sortColumn,
    		@RequestParam("sort-type") String sortType,
    		@RequestParam("page") int page,
    		RedirectAttributes redirectAttributes)
    {
    	if (searchValue.isEmpty() == false)
    	{
    		redirectAttributes.addAttribute("search-column", searchColumn);
        	redirectAttributes.addAttribute("search-value", searchValue);
    	}
    	if (sortColumn.equals("name") == false || sortType.equals("asc") == false)
    	{
    		redirectAttributes.addAttribute("sort-column", sortColumn);
            redirectAttributes.addAttribute("sort-type", sortType);
    	}
        if (page != 1)
    	{
    		redirectAttributes.addAttribute("page", page);
    	}
        
        return "redirect:/show-data";
    }
    
    @GetMapping("/change-page")
	public String changePage(
			@RequestParam("search-column") String searchColumn,
    		@RequestParam("search-value") String searchValue,
			@RequestParam("sort-column") String sortColumn,
    		@RequestParam("sort-type") String sortType,
			@RequestParam("page") int page,
			RedirectAttributes redirectAttributes)
	{
		if (searchValue.isEmpty() == false)
		{
			redirectAttributes.addAttribute("search-column", searchColumn);
		    redirectAttributes.addAttribute("search-value", searchValue);
		}
		if (sortColumn.equals("name") == false || sortType.equals("asc") == false)
		{
			redirectAttributes.addAttribute("sort-column", sortColumn);
	        redirectAttributes.addAttribute("sort-type", sortType);
		}
		if (page != 1)
		{
			redirectAttributes.addAttribute("page", page);
		}
	    
	    return "redirect:/show-data";
	}
}