# UsersWebApp
Web application in Spring Boot.

# Instruction

The application works with a MySQL database, you need to create a database called "userswebapp" in MySQL Workbench.

The web application is in the form of a WAR file.
To run, you need the Apache Tomcat web container.
We move the "userswebapp.war" file to the "webapps" folder.
The package also includes the files "setenv.sh" and "application.properties", which should be moved to the "bin" folder.
In the "application.properties" file, you must complete the username and password for the MySQL database.
We start the server via the "startup.bat" file in the "bin" folder.
The application runs at "localhost:8080/userswebapp".

For Eclipse:
To run the application correctly in Eclipse, add the following arguments to the program configuration: username and password to the database.
In order for the application to know that it has to set the above data in the configuration file, you must also have a VM argument created: -Dmode=development.

# Description

Home page (index) - displays links to the other two pages.

Load data (load-data) - contains a form for importing XML files with a maximum size of 100 MB. After confirming the file, the application checks whether the database already contains any data and deletes it, and then imports new data while displaying a progress bar. Once completed, we see a summary and a button to go to the data page.

Show data (show-data) - the page displays a list of users in a table, pagination and a search form. Sorting is done by clicking on the column headers (changing the sorted column will always start with ascending sorting). Pages contain a maximum of 50 records. If the searched data is not in the database, we receive appropriate information. You can freely browse through the history of visited addresses in the browser, and the application will restore the appropriate display settings.

The package contains an example Users.xml file, which I used to generate in my separate project, which is available here: https://github.com/jacniluk/UsersXmlGenerator.
