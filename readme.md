# Voice Consultant 
The "Voice Consultant" is a proof of concept project which aims to provide a system which works only by voice input provided by the user.

The system is able to recognize and analyse the speech of an user (hot word listening) and to react with different kind actions (depending on what a user provided).

For example, a user is able to ask for the current time or the current weather simply by saying the keywords "current date" or "current weather".

## Vision
The Voice Consultant's primary goal is to provide an expendable system / platform for using a computer without using a keyboard, hands or even a monitor / display.
Currently, the system provide speech recognition (provided by CMU Sphinx), text-to-speech-synthesis, health- and other webservices in order to monitor the system with different kinds of clients.

The goal of this project is NOT to create another 'smart' or 'home assistant' which allows someone to order soap on a online-marketplace.

## Technical Notes
* TODO

## Notes on technical dept. / TODO
* This is the initial commit of the project. I'd like to keep working on this project after my exams in january
* Because of that and little to no time were spend by implementing this prototype, this project is currently in a bad state:
  * It's currently NOT possible to build it without refactoring it 
  * Hardcoded paths 
  * No abstraction
  * No architecture
  * Only tested on windows systems and a single raspberry pi
  * ... In- & Output is in german language

## Keywords 
* TODO

## Used Services
* OpenWeatherMap
* TODO

## Used Libraries
* CMU Sphinx - https://cmusphinx.github.io/
* Mary TTS - https://github.com/marytts/marytts
* Spring Boot - https://spring.io/
* Gradle - https://gradle.org/
* GSon - https://github.com/google/gson
