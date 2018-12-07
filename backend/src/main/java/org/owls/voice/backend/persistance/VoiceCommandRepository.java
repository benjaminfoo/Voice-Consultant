package org.owls.voice.backend.persistance;

import org.owls.voice.backend.model.VoiceCommand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jt on 3/27/15.
 */
@Repository
public interface VoiceCommandRepository extends CrudRepository<VoiceCommand, Long> {

    public List<VoiceCommand> findAll();

}