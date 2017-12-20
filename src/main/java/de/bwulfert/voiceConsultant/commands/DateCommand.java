package de.bwulfert.voiceConsultant.commands;

import de.bwulfert.voiceConsultant.speech.synth.SpeechSynthesizer;
import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCommand extends Command {

    public DateCommand(String input, ApplicationContext applicationContext) {
        super(input, applicationContext);
    }

    @Override
    public void execute() {
        SpeechSynthesizer speechSynthesizer = (SpeechSynthesizer) getApplicationContext().getBean("speechSynthesizer");
        SimpleDateFormat sdf = new SimpleDateFormat("HH 'uhr' mm 'am' dd.MM.yyyy");
        speechSynthesizer.say("Die aktuelle Uhrzeit ist " + sdf.format(new Date()));
    }

}
