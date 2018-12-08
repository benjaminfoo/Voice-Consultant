# Voice Assistant
<b>News!</b> 
The project has been split into two modules: 
 * backend - the core module of the project
 * webapp - an administration/system-panel 
 
## About
The "Voice Consultant" / Assistant is a proof of concept project which aims to provide a system which works only by voice input provided by the user.
 
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
 * Clone this repository: 
   * git clone https://github.com/benjaminfoo/Voice-Consultant.git
   
   * run `org.owls.voice.backend.AssistantApplication.java` for the standalone-assitant
   
   * run `org.owls.voice.webapp.WebApplication.java `for the standalone-assistant and the administration-panel
     * open http://localhost:8080/ for the administration-panel (in development)
 
## WebApp
You can use the web-application to administrate your voice-system. 
It lists all the installed plugins, and some details about system properties. 

Used web-frameworks:
* JQuery 3.3.1
* Popper 1.14.6
* Bootstrap 4.0.0
  
### Screenshots
<img src="/webapp/screens/screen_1.png" width="325"> 
<img src="/webapp/screens/screen_2.png" width="325"> 
<img src="/webapp/screens/screen_3.png" width="325"> 
<img src="/webapp/screens/screen_4.png" width="325"> 

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

