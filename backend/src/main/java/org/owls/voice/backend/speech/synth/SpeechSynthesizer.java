package org.owls.voice.backend.speech.synth;

import marytts.LocalMaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.MaryAudioUtils;
import org.springframework.stereotype.Component;

import javax.sound.sampled.AudioInputStream;
import java.io.IOException;

@Component
public class SpeechSynthesizer {

    static final String NAME = "txt2wav";
    static final String IN_OPT = "input";
    static final String OUT_OPT = "output";

    public SpeechSynthesizer() {

    }

    public void say(String text) {
        // init mary
        LocalMaryInterface mary = null;
        try {
            mary = new LocalMaryInterface();
            // mary.setLocale(Locale.GERMAN);
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
        } catch (IOException e) {
            System.err.println("Could not write to file: " + "tmp.wav" + "\n" + e.getMessage());
            System.exit(1);
        }

        MaryAudioUtils.playWavFile("tmp.wav", 0, true);
    }
}