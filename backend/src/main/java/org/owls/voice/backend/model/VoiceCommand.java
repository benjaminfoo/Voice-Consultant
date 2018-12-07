package org.owls.voice.backend.model;

import org.owls.voice.backend.commands.Command;
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

    private String loaded;

    private String description;

    public VoiceCommand(String k, String input, String aClass, String someWhat) {
        this.name = input;
        this.clazz = aClass;
        this.alias = new String[]{clazz};
    }

    public String toPrettyString(){
        return this.clazz;
    }
}