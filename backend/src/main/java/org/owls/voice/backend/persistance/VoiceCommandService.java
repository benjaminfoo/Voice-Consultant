package org.owls.voice.backend.persistance;

import org.owls.voice.backend.model.VoiceCommand;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Deprecated
public class VoiceCommandService implements IVoiceCommandService {


    /*
    @Autowired
    private VoiceCommandRepository repository;
*/
    public long count() {
        // return repository.count();
        return -1;
    }

    @Override
    public List<VoiceCommand> findAll() {

        // List<VoiceCommand> countries = (List<VoiceCommand>) repository.findAll();
        return Collections.emptyList();
    }
}