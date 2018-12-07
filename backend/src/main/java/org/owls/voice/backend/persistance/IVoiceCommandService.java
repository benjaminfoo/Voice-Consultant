package org.owls.voice.backend.persistance;

import org.owls.voice.backend.model.VoiceCommand;

import java.util.List;

public interface IVoiceCommandService {

    public List<VoiceCommand> findAll();
}