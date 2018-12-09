# Voice Assistant
<b>News!</b> 
The project has been split into three modules: 
 * backend - the core module of the project
 * plugins - the default set of built-in plugins
 * webapp - an administration/system-panel 
 
## About
The "Voice Consultant" / Assistant is a project which aims to provide a system which works only by voice input provided by the user.
 
The system is able to recognize and analyse the speech of an user (hot word listening) and to react with different kind actions (depending on what a user provided).
 
For example, a user is able to ask for the current time or the current weather simply by saying the keywords "current date" or "current weather".
  
## Commands / Keywords
| Keyword | How the system responds|
|---|---|
| Hello | Simple speech recognition test, "Hello User"   |
| Date | The current date and time |
| Status | The available network interfaces which starts with "192."   |
| Weather | The weather from OpenWeatherMap   |
... more to come

## Usage
This project heavily relies on `gradle/spring/boot/jpa/java/etc` and overall java ecosystem.
<img src="/docs/voice_assistant_screen_1.png" width="1500"> 


 * Clone this repository: 
   * git clone https://github.com/benjaminfoo/Voice-Consultant.git
   
   * run `./gradlew backend:bootRun` for the standalone-assitant
   
   * run `./gradlew webapp:bootRun `for the standalone-assistant and the administration-panel
     * open http://localhost:8080/ for the administration-panel (in development)
	 
   * run `./gradlew backend:jar` for compiling the default plugins
      
   * run `./gradlew tasks` for listing every task in this project
   
   * run `./gradlew clean` for deleting all the files build from compilation/etc.
         
         
 
## WebApp
You can use the web-application to administrate your voice-system. 
It lists all the installed plugins, and some details about system properties. 

Used web-frameworks:
* JQuery 3.3.1
* Popper 1.14.6
* Bootstrap 4.0.0
  
### Screenshots
<img src="/webapp/screens/screen_1.png" width="600"> 
<img src="/webapp/screens/screen_2.png" width="600"> 
<img src="/webapp/screens/screen_3.png" width="600"> 
<img src="/webapp/screens/screen_4.png" width="600"> 

## Plug-Ins
This project contains the plug-in management api and implementations of serviceloaders in order to provide plug-in mechanisms and a set of implementations of the previously mentioned commands. 
It is based on the Java Service-Loader API. 

In order to load your own plug-ins into the backend, just provide a jar with implementations of the `org.owls.voice.plugins.api.PlugInInterface`.

Remember to put a service-descriptor containing your implementations in the resources `<project>\src\main\resources\META-INF\services\org.owls.voice.plugins.api.PlugInInterface` - I recommend to peek into the plugins-project if you're interested in development. 
## Backend
The goal of the core is to provide an expendable system / platform for using a computer without using a keyboard, hands or even a monitor / display.
The system provides speech recognition by CMU Sphinx, text-to-speech-synthesis provided by Mary-TTS. The overall architecture is based on mostly spring-related frameworks (alot).

### Notes about Voice Recognition
As the FAQ of CMU Sphinx states: 
_Speech recognition accuracy is not always great. To test speech recognition you need to run recognition on prerecorded reference database to see what happens and optimize parameters._
More information about voice recognition [at the CMU Sphinx FAQ](https://cmusphinx.github.io/wiki/faq/)

### Used Software / Libraries
<img src="/docs/marytts.png" width="150">    <img src="/docs/poweredbycmusphinx.svg" width="150">    <img src="/docs/springboot.png" width="150">

* CMU Sphinx - https://cmusphinx.github.io/
* Mary TTS - https://github.com/marytts/marytts
* Spring Boot - https://spring.io/
* H2 Database Engine - http://www.h2database.com
* Gradle - https://gradle.org/
* GSon - https://github.com/google/gson
* Jackson - https://github.com/FasterXML/jackson
* LMMS - https://lmms.io/

- benjaminfoo (https://github.com/benjaminfoo/Voice-Consultant/)