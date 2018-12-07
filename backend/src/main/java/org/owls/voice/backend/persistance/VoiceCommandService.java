package org.owls.voice.backend.persistance;

import org.owls.voice.backend.model.VoiceCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoiceCommandService implements IVoiceCommandService {

    @Autowired
    private VoiceCommandRepository repository;

    public long count() {
        return repository.count();
    }

    @Override
    public List<VoiceCommand> findAll() {

        List<VoiceCommand> countries = (List<VoiceCommand>) repository.findAll();
        return countries;
    }
}