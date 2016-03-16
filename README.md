# Read theses very carefully before you start

### How it works:

* Your must have internet connection.
* Your must have a github account.
* First of all fork this repo.
* When you are done, push the changes to your forked repo and send us your repo url.
* We will automatically clone your repo, compile it and run against our own input set.
* This template is managed using gradle build system.
* To use gradle you do not need to install gradle by yourself. build system will automatically download it.
* There are two different files inside project root directory (`gradle.bat and gradlew`).
* Use `gradle.bat` if you are in Windows system.
* Use `gradlew` if you are in Unix system.
* Compile project using `./gradlew compile` command in unix system or `gradle.bat` command in windows.
* Run the application and test using `./gradlew run` in unix system or `gradle.bat` in windows system.
* All of you application logic should be placed inside `Solution` class.
* `Solution` class is located inside `./src/main/java/com/tigerit/exam/` directory
* If you have any questions, feel free feel free to contact us (faisal.ahmed@tigeritbd.com, zobayer@tigeritbd.com).

### System Requirements:

* Internet Connection
* Github Account
* JDK7+

### Caution:

* Do not change anything inside `build.gradle` file. That may cause failure building your application.
* Do not use any external `jar` file as your solution dependencies (you may add source to your project). 
* Use standard input/output for your application testing (we will redirect that to file input/output when we test).