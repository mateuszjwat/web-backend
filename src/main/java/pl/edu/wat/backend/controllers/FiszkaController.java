package pl.edu.wat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.backend.dtos.FiszkaSetDTO;
import pl.edu.wat.backend.dtos.SignUpForm;
import pl.edu.wat.backend.entities.FiszkaCard;
import pl.edu.wat.backend.entities.FiszkaSet;
import pl.edu.wat.backend.entities.PublicFiszkaSet;
import pl.edu.wat.backend.entities.UserImpl;
import pl.edu.wat.backend.repositories.PublicFiszkaSetRepository;
import pl.edu.wat.backend.services.FiszkaService;
import pl.edu.wat.backend.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

import static pl.edu.wat.backend.controllers.LoginController.me;

@RestController
@CrossOrigin
@RequestMapping(path = "api/fiszka")
public class FiszkaController {
    @Autowired
    FiszkaService fiszkaService;

    @Autowired
    PublicFiszkaSetRepository publicFiszkaSetRepository;

    @Autowired
    UserService userService;

    @GetMapping("/test")
    public ResponseEntity<?> test() {

        return ResponseEntity.ok("test went successfully!");
    }

//    @GetMapping("/getFiszka")
//    public ResponseEntity<?> getFiszkaSet(@RequestParam long id) {
//        try {
//            return ResponseEntity.ok(fiszkaService.getFiszkaSet(id));
//        } catch (NullPointerException e){
//            return ResponseEntity.status(404).body("fiszki nie znaleziono");
//        }
//    }

    @PostMapping("/makePublic")
    public ResponseEntity<?> makePublicFiszkaSet(@RequestParam long id) {
        FiszkaSet set = fiszkaService.getFiszkaSet(id);
        List<PublicFiszkaSet> all = publicFiszkaSetRepository.findAll();
        for(PublicFiszkaSet pub: all){
            if(pub.getTemplate_id() == id)
                return ResponseEntity.ok("fiszka already made public");
        }

        PublicFiszkaSet publicFiszkaSet = new PublicFiszkaSet(set, me().getUsername());
        publicFiszkaSetRepository.save(publicFiszkaSet);
        return ResponseEntity.ok("fiszka made public");
    }

    @GetMapping("/public")
    public ResponseEntity<?> getPublic() {
        return ResponseEntity.ok(publicFiszkaSetRepository.findAll());
    }

    @DeleteMapping("/deleteSet")
    public ResponseEntity<?> deleteFiszkaSet(@RequestParam long id) {
        System.out.println(id);
        fiszkaService.deleteSet(id);
        return ResponseEntity.ok("fiszka deleted");
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiszkaSet(@RequestBody FiszkaSetDTO fiszkaDTO) {
        FiszkaSet set = fiszkaService.makeFiszkaFromDTO(fiszkaDTO);
        UserImpl me = userService.getMe();
        me.addFiszkaSet(set);
        userService.save(me);
        return ResponseEntity.ok("fiszka saved!");
    }

    @GetMapping("/myFiszka")
    public ResponseEntity<?> getFiszkas() {
        return ResponseEntity.ok(userService.getMe().getMyFiszkaSets());
    }
}
