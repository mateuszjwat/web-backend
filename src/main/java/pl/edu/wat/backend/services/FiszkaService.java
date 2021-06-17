package pl.edu.wat.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.backend.dtos.FiszkaCardDTO;
import pl.edu.wat.backend.dtos.FiszkaSetDTO;
import pl.edu.wat.backend.entities.FiszkaCard;
import pl.edu.wat.backend.entities.FiszkaSet;
import pl.edu.wat.backend.repositories.FiszkaSetRepository;


@Service
public class FiszkaService {

    @Autowired
    FiszkaSetRepository fiszkaSetRepository;

    public FiszkaSet makeFiszkaFromDTO(FiszkaSetDTO dto) {
        FiszkaSet fiszkaSet = new FiszkaSet(dto.getTitle(), dto.getDescription());

        for (FiszkaCardDTO cardDTO : dto.getCards()) {
            FiszkaCard card = new FiszkaCard(cardDTO.getFace(), cardDTO.getReverse());
            fiszkaSet.addFiszkaCard(card);
        }

        return fiszkaSet;
    }
}
