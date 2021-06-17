package pl.edu.wat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.backend.dtos.FiszkaSetDTO;
import pl.edu.wat.backend.entities.FiszkaCard;
import pl.edu.wat.backend.entities.FiszkaSet;
import pl.edu.wat.backend.entities.UserImpl;
import pl.edu.wat.backend.services.FiszkaService;
import pl.edu.wat.backend.services.UserService;

import javax.validation.Valid;
import java.util.Set;

import static pl.edu.wat.backend.controllers.LoginController.me;

@RestController
@CrossOrigin
@RequestMapping(path = "api/fiszka")
public class FiszkaController {
    @Autowired
    FiszkaService fiszkaService;

    @Autowired
    UserService userService;

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
