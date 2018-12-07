package de.bwulfert.voice.consultant;

import de.bwulfert.voice.consultant.model.VoiceCommand;
import de.bwulfert.voice.consultant.persistance.ProductService;
import de.bwulfert.voice.consultant.persistance.VoiceCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        System.out.println("Serving index ... / ... " );

        System.out.println("model is null ? " + (model == null ? "true" : "false"));

        Iterable<VoiceCommand> attributeValue = productService.findAll();
        System.out.println("Counting models ..." + productService.count());
        attributeValue.forEach(vc -> {
                System.out.println("MODEL: "+ vc.toString());
            });
            model.addAttribute("voiceCommands", attributeValue);

        return "index";
    }
}