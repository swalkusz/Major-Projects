# Cloud Google - WebDriver framework

### Technologies

>`Java`, `Selenium WebDriver`, `JUnit`, `Maven`
<br>  

### Brief description

---

This WebDriver allows you to estimate the costs of renting a virtual CPU via the https://cloud.google.com/ platform.  
The test involves trying to re-enter the *Cloud Google* page 5 times and fill out the form.  
The program launches the website https://cloud.google.com/ in one go, then searches for a phrase, selects one result, accepts the agreements, fills out the form and finally sends it and displays the report.

### How it works?

---

#### Let's take a look at what it looks like from the programmer's point of view.  

<br>  

![img_1.png](readme-images/img_1.png)
![img.png](readme-images/img.png)  
Img 1, 2 - Project structure  

<br>

As you might see, first you have to create the Maven project and add appropriate dependencies. Next, you need to upload desired drivers into your project. In this situation, the drivers was located in `src/test/resources/drivers/`.  
The resource directory also contains a number of files that include: environment properties, specific test suites to run, and log format.  

<br>  

"*browser*" and "*environment*" system properties needed to be set to be able to run the test.
![img_2.png](readme-images/img_2.png)  
Img 3 - *.bat* file, with necessary settings  

<br>

>`Note:`  The *`start.bat`* file allows you to conveniently start the program.  
If the test fails, a screenshot is taken, and saved in *target/screenshots/* directory.  

<br>

#### Running 

---

Below are several images of the webdriver working.  

![alt text](readme-images/image.png)  
Img 4 - Search results  

![alt text](readme-images/image-1.png)  
Img 5 - First option to choose  

![alt text](readme-images/image-2.png)  
Img 6 - Completed form  

![alt text](readme-images/image-3.png)  
Img 7 - Report  

