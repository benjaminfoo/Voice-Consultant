package org.owls.voice.backend.speech.recognition;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import org.owls.voice.backend.persistance.VoiceCommandRepository;
import org.owls.voice.backend.speech.synth.SpeechSynthesizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;

@Component
public class KeywordLauncher {

    @Autowired
    SpeechSynthesizer speechSynthesizer;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    VoiceCommandRepository voiceRepo;

    private File bootupSoundFile;
    private Clip bootupSoundClip;
    private Configuration configuration;

    private static final Logger log = LoggerFactory.getLogger(KeywordLauncher.class);

    public KeywordLauncher() {
/*
        try {
            bootupSoundFile = new ClassPathResource("boot.wav").getFile();
            bootupSoundClip = AudioSystem.getClip();
            bootupSoundClip.open(AudioSystem.getAudioInputStream(bootupSoundFile));
            bootupSoundClip.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
*/
        log.info(getClass().getSimpleName() + " has been started!");

    }

    public void initializeCommands() {

        configuration = new Configuration();

        /*
        try {
            // Set path to the acoustic model.
            configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");

            // Set path to the dictionary.
            configuration.setDictionaryPath(new ClassPathResource("cmusphix_assistant_data/cmusphix_assistant_data.dic").getFile().getAbsolutePath());

            // Set path to the language model.
            configuration.setLanguageModelPath(new ClassPathResource("cmusphix_assistant_data/cmusphix_assistant_data.lm").getFile().getAbsolutePath());

            // configure bootup sound
            bootupSoundFile = new ClassPathResource("boot.wav").getFile();
            bootupSoundClip = AudioSystem.getClip();
            bootupSoundClip.open(AudioSystem.getAudioInputStream(bootupSoundFile));
            bootupSoundClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }

    public void listenForKeyword() throws IOException {

        //Recognizer object, Pass the Configuration object
        LiveSpeechRecognizer recognize = new LiveSpeechRecognizer(configuration);

        //Start Recognition Process (The bool parameter clears the previous cache if true)
        recognize.startRecognition(true);

        //Creating SpeechResult object
        SpeechResult result;

        //Check if recognizer recognized the speech
        System.out.println("Waiting for recognizable words ...");
        while ((result = recognize.getResult()) != null) {

            //Get the recognized speech
            String command = result.getHypothesis();
            command = command.toLowerCase();
            String work = null;
            Process p;

            System.out.println(command);

            /*
            if (command != null && commandMap.contains(command)) {
                // commandMap.get(command).execute();
                // TODO: re-implemente execute functionality
                // commandMap.get(commandMap.indexOf(command)).execute();
            }
            */
        }
    }

}
