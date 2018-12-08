package backend.plugins;

import backend.speech.synth.SpeechSynthesizer;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.context.ApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherCommand extends Command {

    public WeatherCommand(String input, ApplicationContext applicationContext) {
        super(input, applicationContext);
    }

    @Override
    public String getName() {
        return "Weather";
    }

    @Override
    public void start(ApplicationContext context) {

    }

    @Override
    public void execute() {
        SpeechSynthesizer speechSynthesizer = (SpeechSynthesizer) getApplicationContext().getBean("speechSynthesizer");

        try {
            String brunswickCurl = "http://api.openweathermap.org/data/2.5/weather?q=Braunschweig&APPID=725aef3186e225614b6b693530c5a75e&units=metric&lang=de";

            URL oracle = new URL(brunswickCurl);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            in.close();


            System.out.println(sb.toString());

            JsonObject jobj = new Gson().fromJson(sb.toString(), JsonObject.class);
            JsonObject main = jobj.getAsJsonObject("main");
            JsonObject weather = jobj.getAsJsonArray("weather").get(0).getAsJsonObject();
            System.out.println("Temperatur: " + main.get("temp") + " Grad Celsius");
            System.out.println("Status: " + weather.get("description"));
            System.out.println("Luftfeuchtigkeit: " + main.get("humidity") + "%");

            speechSynthesizer.say(
                    "Das aktuelle Wetter für Braunschweig ist: "
                            + weather.get("description") +
                            " bei einer Temperatur von " + main.get("temp") + " Grad Celsius" +
                            " und einer Luftfeuchtigkeit von " + main.get("humidity") + "%"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void finish() {

    }

}
