package org.owls.voice.backend.model;

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
@Deprecated
public class VoiceCommand {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    private String clazz;

    private String[] alias;

    private String loaded;

    private String description;

    private String provider;

    public String toPrettyString(){
        return this.clazz;
    }

    // TODO - Add command from plugins to this model

}