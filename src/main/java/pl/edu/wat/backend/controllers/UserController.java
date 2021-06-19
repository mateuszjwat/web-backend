package pl.edu.wat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.backend.dtos.StatisticDTO;
import pl.edu.wat.backend.entities.FiszkaSet;
import pl.edu.wat.backend.entities.StatisticCard;
import pl.edu.wat.backend.entities.UserImpl;
import pl.edu.wat.backend.repositories.FiszkaSetRepository;
import pl.edu.wat.backend.repositories.StatisticCardRep;
import pl.edu.wat.backend.services.UserService;

import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping(path = "api/user")
public class UserController {

    @Autowired
    FiszkaSetRepository fiszkaSetRepository;

    @Autowired
    UserService userService;

    @Autowired
    StatisticCardRep statisticCardRep;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        return ResponseEntity.ok(userService.getMe());
    }

    @PostMapping("/postStatistic")
    public ResponseEntity<?> postStatistics(@RequestBody StatisticDTO dto) {
        Optional<StatisticCard> card = statisticCardRep.findStatisticByParentId(dto.getSetId());
        StatisticCard sCard;
        if(card.isPresent()){
            sCard = card.get();
        } else{
            sCard = new StatisticCard();
            sCard.setParentId(dto.getSetId());
            sCard.setSetName(dto.getSetName());
        }
        sCard.setGoodAns(dto.getGoodAns());
        sCard.setWrongAns(dto.getWrongAns());

        userService.getMe().addStatistic(sCard);
        userService.save(userService.getMe());

        return ResponseEntity.ok("ok");
    }

    @PostMapping("/updatePrivate")
    public ResponseEntity<?> updatePrivate(@RequestBody StatisticDTO dto) {
        UserImpl me = userService.getMe();
        FiszkaSet set = me.getFiszkaSetFromId(dto.getSetId());
        set.setLastGood(dto.getGoodAns());
        set.setLastWrong(dto.getWrongAns());
        fiszkaSetRepository.save(set);

        return ResponseEntity.ok("ok");
    }
}