package MeeW.intern.WheelOfFortune.controller;

import MeeW.intern.WheelOfFortune.entities.Winner;
import MeeW.intern.WheelOfFortune.exceptions.WinnerNotFoundException;
import MeeW.intern.WheelOfFortune.repository.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/winner")
@ComponentScan("com.example")
public class WinnerController {

    @Autowired
    WinnerRepository repo;

    @GetMapping("/")
    public List<Winner> retrieveAllWinners() {
        if (repo.findAll().isEmpty()) {
            throw new WinnerNotFoundException();
        }
        return repo.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Object> createWinner(@RequestBody Winner winner) {
        Winner savedWinner = repo.save(winner);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedWinner.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
