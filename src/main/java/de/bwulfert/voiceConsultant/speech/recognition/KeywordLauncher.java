package de.bwulfert.voiceConsultant.speech.recognition;//Imports

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import de.bwulfert.voiceConsultant.Txt2Wav;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import marytts.exceptions.MaryConfigurationException;
import org.springframework.core.io.ClassPathResource;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class KeywordLauncher {

    public static void main(String[] args) {
        try {
            new KeywordLauncher().listenForKeyword();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File bootupSoundFile;
    private Clip bootupSoundClip;

    private Configuration configuration;

    public KeywordLauncher() {
        configuration = new Configuration();

        try {
            // Set path to the acoustic model.
            configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");

            // Set path to the dictionary.
            configuration.setDictionaryPath(new ClassPathResource("cmusphix_assistant_data/cmusphix_assistant_data.dic").getFile().getAbsolutePath());

            // Set path to the language model.
            configuration.setLanguageModelPath(new ClassPathResource("cmusphix_assistant_data/cmusphix_assistant_data.lm").getFile().getAbsolutePath());

            // configure bootup sound
            bootupSoundFile = new ClassPathResource("boot.wav").getFile();
            bootupSoundClip  = AudioSystem.getClip();
            bootupSoundClip.open(AudioSystem.getAudioInputStream(bootupSoundFile));
            bootupSoundClip.start();
        } catch (Exception e){
            e.printStackTrace();
        }
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
            String work = null;
            Process p;

            System.out.println(command);

            if(command != null){

                    if(command.equalsIgnoreCase("hallo")){

                        URL url = new URL("file:///C:/dev_workspaces/assistant/src/main/resources/turn_on.wav");
                        Clip clip = null;
                        try {
                            clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(url));
                            clip.start();
                        } catch (LineUnavailableException e) {
                            e.printStackTrace();
                        } catch (UnsupportedAudioFileException e) {
                            e.printStackTrace();
                        }

                        try {
                            new Txt2Wav().say("Guten Tag Benutzer, wie geht es dir?");
                        } catch (MaryConfigurationException e) {
                            e.printStackTrace();
                        } catch (UnsupportedAudioFileException e) {
                            e.printStackTrace();
                        } catch (LineUnavailableException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }



                     if(command.equalsIgnoreCase("byebye")){

                        URL url = new URL("file:///C:/dev_workspaces/assistant/src/main/resources/turn_off.wav");
                        Clip clip = null;
                        try {
                            clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(url));
                            clip.start();
                        } catch (LineUnavailableException e) {
                            e.printStackTrace();
                        } catch (UnsupportedAudioFileException e) {
                            e.printStackTrace();
                        }


                        try {
                            new Txt2Wav().say("Okay, ich mache ein kleines Päuschen");
                        } catch (MaryConfigurationException e) {
                            e.printStackTrace();
                        } catch (UnsupportedAudioFileException e) {
                            e.printStackTrace();
                        } catch (LineUnavailableException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }




                     if(command.equalsIgnoreCase("datum")){

                        URL url = new URL("file:///C:/dev_workspaces/assistant/src/main/resources/turn_on.wav");
                        Clip clip = null;
                        try {
                            clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(url));
                            clip.start();
                        } catch (LineUnavailableException e) {
                            e.printStackTrace();
                        } catch (UnsupportedAudioFileException e) {
                            e.printStackTrace();
                        }

                        SimpleDateFormat sdf = new SimpleDateFormat("HH 'uhr' mm 'am' dd.MM.yyyy");

                        try {
                            new Txt2Wav().say("Die aktuelle Uhrzeit ist " + sdf.format(new Date()));
                        } catch (MaryConfigurationException e) {
                            e.printStackTrace();
                        } catch (UnsupportedAudioFileException e) {
                            e.printStackTrace();
                        } catch (LineUnavailableException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }




                if(command.equalsIgnoreCase("wetter")){

                    String brunswickCurl = "http://api.openweathermap.org/data/2.5/weather?q=Braunschweig&APPID=725aef3186e225614b6b693530c5a75e&units=metric&lang=de";

                    URL oracle = new URL(brunswickCurl);
                    URLConnection yc = oracle.openConnection();
                    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String inputLine;
                    while ((inputLine = in.readLine()) != null){
                        sb.append(inputLine);
                    }
                    in.close();



                    System.out.println(sb.toString());

                    JsonObject jobj = new Gson().fromJson(sb.toString(), JsonObject.class);
                    JsonObject main =  jobj.getAsJsonObject("main");
                    JsonObject weather = jobj.getAsJsonArray("weather").get(0).getAsJsonObject();
                    System.out.println("Temperatur: " + main.get("temp") + " Grad Celsius");
                    System.out.println("Status: " + weather.get("description") );
                    System.out.println("Luftfeuchtigkeit: " + main.get("humidity") + "%");

                    try {
                        new Txt2Wav().say(
                                "Das aktuelle Wetter für Braunschweig ist: "
                                        + weather.get("description") +
                                        " bei einer Temperatur von " + main.get("temp") + " Grad Celsius" +
                                        " und einer Luftfeuchtigkeit von " + main.get("humidity") + "%"
                        );
                    } catch (MaryConfigurationException e) {
                        e.printStackTrace();
                    } catch (UnsupportedAudioFileException e) {
                        e.printStackTrace();
                    } catch (LineUnavailableException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }


                if(command.equalsIgnoreCase("status")){

                    Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
                    String lanAdress = "n/a";
                    for (; n.hasMoreElements();)
                    {
                        NetworkInterface e = n.nextElement();

                        Enumeration<InetAddress> a = e.getInetAddresses();
                        for (; a.hasMoreElements();)
                        {
                            InetAddress addr = a.nextElement();
                            if(addr.getHostAddress().startsWith("192."))
                                lanAdress = addr.getHostAddress();
                        }
                    }

                    try {
                        new Txt2Wav().say(
                                "Meine IP-Adresse ist: " + lanAdress
                        );
                    } catch (MaryConfigurationException e) {
                        e.printStackTrace();
                    } catch (UnsupportedAudioFileException e) {
                        e.printStackTrace();
                    } catch (LineUnavailableException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                }

            }

           /*
                if(command.equalsIgnoreCase("test")) {
                    work = "test";
                } else if (command.equalsIgnoreCase("close file manager")) {
                    work = "pkill nautilus";
                } else if (command.equalsIgnoreCase("open browser")) {
                    work = "google-chrome";
                } else if (command.equalsIgnoreCase("close browser")) {
                    work = "pkill google-chrome";
                }
                //In case command recognized is none of the above hence work might be null
                if(work != null) {
                    //Execute the command
                    p = Runtime.getRuntime().exec(work);
                }
            */
    }
}