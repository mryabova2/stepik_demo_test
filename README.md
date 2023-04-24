<h2>Demo Project</h2>
<p>A small project to demonstrate my skills:<br>
<i>Prepared after studies in QAguru</i></p>
<p><a href="https://stepik.org/"><img src="icons/stepik.png" width="900"></a></p>
<h4><img src="icons/tools.png" height="15" align="center"/> Tools and instruments</h4>
<p> 
<a href="https://www.jetbrains.com/idea/"><img src="icons/Intelij_IDEA.svg" height="40" alt="IDEA" title="IntelliJ IDEA"/></a>
<a href="https://www.java.com/"><img src="icons/Java.svg" height="50" alt="Java" title="Java"/></a>
<a href="https://gradle.org/"><img src="icons/gradle-color.svg" height="40" alt="Gradle" title="Gradle"/></a>
<a href="https://junit.org/junit5/"><img src="icons/JUnit5.svg" height="40" alt="JUnit 5" title="JUnit 5"/></a>
<a href="https://selenide.org/"><img src="icons/Selenide.svg" height="40" alt="Selenide" title="Selenide"></a>
<a href="https://aerokube.com/selenoid/"><img src="icons/Selenoid.svg" height="40" alt="Selenoid" title="Selenoid"/></a>
<a href="https://rest-assured.io/"><img src="icons/RestAssured.png" height="40" alt="RestAssured" title="RestAssured"/></a>
<a href="https://www.jenkins.io/"><img src="icons/Jenkins.svg" height="50" alt="Jenkins" title="Jenkins"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="icons/Allure_Report.svg" height="40" alt="Allure Report" title="Allure Report"/></a>
<a href="https://qameta.io/"><img src="icons/Allure_TO.svg" height="40" alt="Allure TestOps" title="Allure TestOps"/></a>
<a href="https://github.com/qa-guru/allure-notifications"><img src="icons/telegram-color.svg" height="40" alt="Telegram Notifications" title="Telegram Notifications"/></a><br>
</p>

<h4>UI Tests</h4>
<h5>Tests</h5>
<p><img src="icons/check.png" height="10"/> Search by key word<br>
<img src="icons/check.png" height="10"/> Search with filters<br>
<img src="icons/check.png" height="10"/> Change profile info<br>
<img src="icons/check.png" height="10"/> Add course in wishlist</p>

<h5>Key features</h5>
<p><img src="icons/star.png" height="12"/> Local or remote launch<br>
<img src="icons/star.png" height="12"/> Page Object approach<br>
<img src="icons/star.png" height="12"/> Lambda steps<br>
<img src="icons/star.png" height="12"/> Data randomizer</p>


<h4>API Tests</h4>
<h4><img src="icons/tools.png" height="15" align="center"/> Tools and instruments</h4>
<p>

<h5>Tests</h5>
<p><img src="icons/check.png" height="10"/> Valid and invalid authorization<br>
<img src="icons/check.png" height="10"/> Enrolment on course <br>


<h5>Key features</h5>
<p><img src="icons/star.png" height="12"/> Specifications for requests and responses<br>
<img src="icons/star.png" height="12"/> Customized allure report<br>
<img src="icons/star.png" height="12"/> Handling CSRF-cookies<br>


<h4>How it works</h4>
<p><img src="icons/Schemedark.png" height="600"/> </p>

<h3>How to launch</h3>
<h4>Jenkins
<a href="https://www.jenkins.io/"><img src="icons/Jenkins.svg" height="50" alt="Jenkins" title="Jenkins" align="center"/></a></h3>
Configured jenkins job is<a href="https://jenkins.autotests.cloud/job/012-mv_ry-demo_test/"> here</a>
<p>1. Choose "Parametrized build"<br>
<br><img src="icons/Jenkins build.jpg"/><br><br>
2. Select parameters (browser and window size)<br>
<br><img src="icons/Parameters choiсe.jpg"/><br>
</p>

<h4>Local launch</h4>
For local launch through terminal, please, insert command:
```
gradle clean test
```
<p><img src="icons/exclamation.png" height="15"> For local launch authorization data is needed in auth.properties. Otherwise, all tests requiring authorization will be failed <img src="icons/exclamation.png" height="15"></p>

<h3>Using Allure TestOps <a href="https://qameta.io/"><img src="icons/Allure_TO.svg" height="40" alt="Allure TestOps" title="Allure TestOps" align="center"/></a></h3>
Allure TestOps Project is <a href="https://allure.autotests.cloud/project/2162/test-cases/"> here</a>
<p>In "Test Cases" select tests and click "Run"<br>
<br><img src="icons/TestOps run.jpg"/><br>
<br>TestOps starts Jenkins build. It is possible to observe build in progress. Test cases are renewed automatically if any changes happened
</p>

<h4><img src="icons/bar-chart.png" height="20" align="center"/>  Enjoy reports</h4>
<p> 
Overall Allure Report <br>
<br><img src="icons/Allure Report common.jpg"><br>
<h4>For UI Tests</h4>
<p>
Outlining failing steps<br>
<br><img src="icons/ReportWithFailure.jpg"><br>
<br>Showing tests in progress<br>
<br><img src="icons/Testvideo.gif">
</p>
<h4>For API Tests</h4>
<p>
Representing requests and responses with body and headers<br>
<br><img src="icons/ReportAPI.jpg">
</p>
<h4>Telegram notifications <a href="https://github.com/qa-guru/allure-notifications"><img src="icons/telegram-color.svg" height="20" alt="Telegram Notifications" title="Telegram Notifications" align="center"/></a><br></h4>
<p>After building in Jenkins, Telegram notification with results is sent by Telegram bot<br>
<br><img src="icons/TelegramReport.jpg"></p>



