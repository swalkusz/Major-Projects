# Pastebin WebDriver

### Technologies

>`Java`, `Selenium WebDriver`, `JUnit`, `Maven`
<br>

### Brief description

---

This WebDriver allows you to submit *New Paste* on the https://pastebin.com/ platform.  
The test involves trying to re-enter the pastebin page 3 times and fill out the form.  
The program launches the website https://pastebin.com/ in one go, then accepts the agreements, fills out the form and finally sends the submit.

### How it works?

---

#### Let's take a look at what it look like from the programmer's point of view.

![InteliJIDEA prevew](README%20images/previewInteliJIdea.png)
Img 1 - Code preview from IDE InteliJIDEA
<br><br>
As you might see, first you have to create the Maven project and add appropriate dependencies. Next, you need to upload desired drivers into your project. In this situation, the drivers was located in `src/main/resources/drivers/`.  

<br>
After launching the website, the program accepts the agreements and fills out the form:
<br>

![Agreements](README%20images/previewAgreement.png)  
Img 2 - Pastebin Agreements
<br><br>


![filled form](README%20images/previewPastebin.png)  
Img 3 - The result of entering data by the program, before sending the form.
<br><br>


![Posted paste](README%20images/Posted%20paste.png)
Img 4 - Posted paste