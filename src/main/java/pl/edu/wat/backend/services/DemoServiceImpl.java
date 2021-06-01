package pl.edu.wat.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.backend.dtos.DemoRequest;
import pl.edu.wat.backend.dtos.DemoResponse;
import pl.edu.wat.backend.entities.DemoEntity;
import pl.edu.wat.backend.repositories.DemoRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DemoServiceImpl implements DemoService {

    private final DemoRepository demoRepository;

    @Autowired
    public DemoServiceImpl(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    @Override
    public List<DemoResponse> getAllDemos() {
        return StreamSupport.stream(demoRepository.findAll().spliterator(), false)
                .map(entity -> new DemoResponse(entity.getName(), entity.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public void addDemo(DemoRequest demoRequest) {
        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setName(demoRequest.getName());
        demoEntity.setValue(demoRequest.getValue());

        demoRepository.save(demoEntity);
    }
}
