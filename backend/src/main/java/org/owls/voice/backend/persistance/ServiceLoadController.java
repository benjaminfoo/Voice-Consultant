package org.owls.voice.backend.persistance;

import org.owls.voice.backend.plugins.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ServiceLoadController {

    @Autowired
    ApplicationContext applicationContext;

    public ServiceLoadController (){
        commandMap = new LinkedList<>();
        commandMap.add(new DateCommand("datum", applicationContext));
        commandMap.add(new HelloCommand("hello", applicationContext));
        commandMap.add(new ListDirectoryCommand("files", applicationContext));
        commandMap.add(new StatusCommand("status", applicationContext));
        commandMap.add(new WeatherCommand("weather", applicationContext));
    }

    // TODO: Refactor this into a plugin-service (service-loader based)
    private List<Command> commandMap;

    public void loadPlugins(ApplicationContext applicationContext){
        // TODO: Refactor this into a plugin-service (service-loader based)

    }

    public void testMeNot(){
        /*
        commandMap.forEach((k) -> {
            VoiceCommand entity = new VoiceCommand();
            entity.setName(k.getClass().getSimpleName());
            entity.setAlias(new String[]{k.getClass().getSimpleName()});
            entity.setDescription("This is the \"" + k.getName() + "\"-PlugIn (v" + k.getVersion()+ "), provided by " + k.getProvider() );
            entity.setClazz(k.getClass().getSimpleName());
            entity.setLoaded("true");
            entity.setProvider(k.getProvider());
            voiceRepo.save(entity);
        });
        */
    }

    public List<Command> getPlugins(){
        return commandMap;
    }

}
