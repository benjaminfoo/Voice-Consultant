package de.bwulfert.voice.consultant.persistance;

import de.bwulfert.voice.consultant.model.VoiceCommand;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jt on 3/27/15.
 */
@Repository
public interface ProductService extends CrudRepository<VoiceCommand, Long> {
}