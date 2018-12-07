package de.bwulfert.voice.consultant.persistance;

import de.bwulfert.voice.consultant.model.VoiceCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface VoiceCommandRepository extends JpaRepository<VoiceCommand, Long>{
}

