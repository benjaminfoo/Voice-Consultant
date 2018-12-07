package de.bwulfert.voice.consultant.model;

import de.bwulfert.voice.consultant.commands.Command;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@ToString
@NoArgsConstructor
public class VoiceCommand {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    private String clazz;

    private String[] alias;


    public VoiceCommand(String k, String input, Class<? extends Command> aClass, int i) {
        this.name = input;
        this.clazz = aClass.getName();
        this.alias = new String[]{clazz};
    }
}