package pl.edu.wat.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.backend.dtos.FiszkaCardDTO;
import pl.edu.wat.backend.dtos.FiszkaSetDTO;
import pl.edu.wat.backend.entities.FiszkaCard;
import pl.edu.wat.backend.entities.FiszkaSet;
import pl.edu.wat.backend.entities.StatisticCard;
import pl.edu.wat.backend.entities.UserImpl;
import pl.edu.wat.backend.repositories.FiszkaSetRepository;
import pl.edu.wat.backend.repositories.StatisticCardRep;


@Service
public class FiszkaService {

    @Autowired
    StatisticCardRep statisticCardRep;

    @Autowired
    UserService userService;

    @Autowired
    FiszkaSetRepository fiszkaSetRepository;

    public void deleteStatisticCard(long cardId) {
        for (UserImpl user : userService.findAll()) {
            for (StatisticCard card : user.getStatistics()) {
                if (card.getCardId() == cardId) {
                    user.getStatistics().remove(card);
                    userService.save(user);
                }
            }
        }
    }

    public FiszkaSet makeFiszkaFromDTO(FiszkaSetDTO dto) {
        FiszkaSet fiszkaSet = new FiszkaSet(dto.getTitle(), dto.getDescription());

        for (FiszkaCardDTO cardDTO : dto.getCards()) {
            FiszkaCard card = new FiszkaCard(cardDTO.getFace(), cardDTO.getReverse());
            fiszkaSet.addFiszkaCard(card);
        }

        return fiszkaSet;
    }

    public FiszkaSet getFiszkaSet(long id) {
        UserImpl me = userService.getMe();

        for (FiszkaSet mySet : me.getMyFiszkaSets()) {
            if (mySet.getId() == id)
                return mySet;
        }
        throw new NullPointerException();
    }

    public void deleteSet(long id) {
        UserImpl me = userService.getMe();
        try {
            FiszkaSet set = getFiszkaSet(id);
            me.getMyFiszkaSets().remove(set);
            fiszkaSetRepository.delete(set);
            userService.save(me);
        } catch (Exception e) {
            System.out.println("delete not working i guess");
        }
    }
}
