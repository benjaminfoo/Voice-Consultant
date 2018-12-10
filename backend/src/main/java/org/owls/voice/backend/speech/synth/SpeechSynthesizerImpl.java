package org.owls.voice.backend.speech.synth;

import marytts.LocalMaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.MaryAudioUtils;
import org.owls.voice.plugins.api.SpeechSynthesizer;
import org.springframework.stereotype.Component;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

@Component
public class SpeechSynthesizerImpl implements SpeechSynthesizer {

    // TODO: refactor this class!

    static final String NAME = "txt2wav";
    static final String IN_OPT = "input";
    static final String OUT_OPT = "output";

    public void say(String text) {
        // initializeCommands mary
        LocalMaryInterface mary = null;
        try {
            mary = new LocalMaryInterface();
            mary.setLocale(Locale.GERMAN);
            // mary.getAvailableVoices().forEach(System.out::println);
            // a German voice
            mary.setVoice("bits1-hsmm");
        } catch (MaryConfigurationException e) {
            System.err.println("Could not initialize MaryTTS interface: " + e.getMessage());
        }

        // synthesize
        AudioInputStream audio = null;
        try {
            audio = mary.generateAudio(text);
        } catch (SynthesisException e) {
            System.err.println("Synthesis failed: " + e.getMessage());
            System.exit(1);
        }

        // write to output
        double[] samples = MaryAudioUtils.getSamplesAsDoubleArray(audio);
        try {
            MaryAudioUtils.writeWavFile(samples, "tmp.wav", audio.getFormat());
            System.out.println("Output written to " + "tmp.wav");

            // MaryAudioUtils.playWavFile("tmp.wav", 0, true);


            try {
                // play bootup sound
                File file = new File("tmp.wav");
                Clip audioClip = null;
                audioClip = AudioSystem.getClip();
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                AudioFormat format = audioInputStream.getFormat();
                audioClip.open(audioInputStream);

                boolean playCompleted = false;

                long frames = audioInputStream.getFrameLength();
                double durationInSeconds = (frames + 0.0) / format.getFrameRate();
                durationInSeconds += 1.0;
                System.out.println("Playing sound for " + durationInSeconds + " ...");

                audioClip.start();

                while (!playCompleted) {
                    // wait for the playback completes
                    try {

                        Thread.sleep((long) durationInSeconds * 1000);
                        playCompleted = true;
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                audioClip.close();

            } catch (LineUnavailableException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            System.err.println("Could not write to file: " + "tmp.wav" + "\n" + e.getMessage());
            System.exit(1);
        }

    }

}
