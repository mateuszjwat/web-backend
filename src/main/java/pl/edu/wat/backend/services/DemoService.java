package pl.edu.wat.backend.services;

import pl.edu.wat.backend.dtos.DemoRequest;
import pl.edu.wat.backend.dtos.DemoResponse;

import java.util.List;

public interface DemoService {
    List<DemoResponse> getAllDemos();

    void addDemo(DemoRequest demoRequest);
}
