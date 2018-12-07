package de.bwulfert.voice.consultant.commands;

import de.bwulfert.voice.consultant.speech.synth.SpeechSynthesizer;
import org.springframework.context.ApplicationContext;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class StatusCommand extends Command {

    public StatusCommand(String input, ApplicationContext applicationContext) {
        super(input, applicationContext);
    }

    @Override
    public void execute() {
        SpeechSynthesizer speechSynthesizer = (SpeechSynthesizer) getApplicationContext().getBean("speechSynthesizer");

        try {
            Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
            String lanAdress = "n/a";
            for (; n.hasMoreElements(); ) {
                NetworkInterface e = n.nextElement();

                Enumeration<InetAddress> a = e.getInetAddresses();
                for (; a.hasMoreElements(); ) {
                    InetAddress addr = a.nextElement();
                    if (addr.getHostAddress().startsWith("192."))
                        lanAdress = addr.getHostAddress();
                }
            }

            speechSynthesizer.say(
                    "Meine IP-Adresse ist: " + lanAdress
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
