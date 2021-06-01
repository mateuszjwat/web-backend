package pl.edu.wat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.backend.dtos.DemoRequest;
import pl.edu.wat.backend.dtos.DemoResponse;
import pl.edu.wat.backend.services.DemoService;

import java.util.List;

@RestController
public class DemoController {

    private final DemoService demoService;

    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/")
    String hello() {
        return "This tutorial is the best. All hail the great Kristijan.";
    }

    @GetMapping("/api/demo")
    public ResponseEntity<List<DemoResponse>> getDemos() {
        List<DemoResponse> demos = demoService.getAllDemos();

        return new ResponseEntity<>(demos, HttpStatus.OK);
    }

    @PostMapping("/api/demo")
    public ResponseEntity addDemo(@RequestBody DemoRequest demoRequest) {
        demoService.addDemo(demoRequest);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
