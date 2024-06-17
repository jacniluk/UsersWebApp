package com.jacniluk.userswebapp.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jacniluk.userswebapp.model.User;

public class XmlParser
{
	private final int partSize = 1000;
	
	private NodeList usersNodeList;
	private int currentIndex;
	
	public void initialize(MultipartFile file)
	{
		try
		{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setIgnoringComments(true);
            documentBuilderFactory.setIgnoringElementContentWhitespace(true);
            documentBuilderFactory.setNamespaceAware(true);
            documentBuilderFactory.setValidating(false);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file.getInputStream());
            usersNodeList = document.getElementsByTagName("user");
        }
		catch (Exception e)
		{
            e.printStackTrace();
        }
	}
	
	public List<User> getNextRecordsPart()
	{
		List<User> users = new ArrayList<>();
		
		for (int i = 0; i < partSize; i++)
		{
			if (currentIndex >= usersNodeList.getLength())
			{
				return users;
			}
			
			Node userNode = usersNodeList.item(currentIndex);
	        NodeList userPropertiesNode = userNode.getChildNodes();
	        String name = "";
	        String surname = "";
	        String login = "";
	        for (int j = 0; j < userPropertiesNode.getLength(); j++)
	        {
	            Node userPropertyNode = userPropertiesNode.item(j);
	            if (userPropertyNode.getNodeType() == Node.ELEMENT_NODE)
	            {
	            	switch (userPropertyNode.getNodeName())
	            	{
	                	case "name":
	                		name = userPropertyNode.getTextContent();
	                		break;
	                	case "surname":
	                		surname = userPropertyNode.getTextContent();
	                		break;
	                	case "login":
	                		login = userPropertyNode.getTextContent();
	                		break;
	            	}
	            }
	        }
	        User user = new User(name, surname, login);
	        users.add(user);
	        
	        currentIndex++;
		}
        
        return users;
	}
	
	public float getProgress()
	{
		return (float) (currentIndex + 1) / usersNodeList.getLength() * 100;
	}
}