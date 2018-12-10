package org.owls.voice.plugins.impl;


import org.owls.voice.plugins.api.Command;

public class StatusCommand implements Command {


    @Override
    public String getName() {
        return "Status";
    }

    @Override
    public void start() {

    }

    @Override
    public void execute() {
        /*
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
        */
    }

    @Override
    public void finish() {

    }
}
