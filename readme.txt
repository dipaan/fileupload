1. This is a java web application that uses maven for build (configurable in pom.xml) and can be built into a WAR file by running the command "mvn package" at the root directory.

2. The WAR file may be deployed to any Java web app container, e.g. tomcat, jetty, etc. Once successfully deployed, the welcome page may be accessed at http://localhost:8080/.

3. I have used Google App Engine for deployment and testing and its also available at http://fileupload.dipaan.com/. The GAE configuration is in WEB-INF/appengine-web.xml.

4. The app uses slf4j/log4j for logging. The logging configuration is in src/main/resources/log4j.properties.

5. "The rows that are displayed should be unique based on the date and description" - I have assumed date here means month, day and year and does not include the time.

6. I have made the assumption that when two rows exists with the same unique key (date and description), the amounts/values are rolled into one single UI row.

7. A sample test input file is present in the root directory as "test-input". To test using this file, please choose this file by clicking "Choose a File" on the landing page at at http://localhost:8080/ and hit "Upload File". The results appear in a table on the next page titled "Uploaded Items".

