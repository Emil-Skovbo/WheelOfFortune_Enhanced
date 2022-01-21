package MeeW.intern.WheelOfFortune.controller;

import MeeW.intern.WheelOfFortune.entities.Winner;
import MeeW.intern.WheelOfFortune.exceptions.FieldNotFoundException;
import MeeW.intern.WheelOfFortune.exceptions.WinnerNotFoundException;
import MeeW.intern.WheelOfFortune.repository.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/winner")
public class WinnerController {

    //implements the interface for Winner that extends JpaRepository
    @Autowired
    WinnerRepository repo;

    //Get method that returns all Winners in the database as a json object
    @GetMapping("/")
    public List<Winner> retrieveAllWinners() {
        if (repo.findAll().isEmpty()) {
            throw new WinnerNotFoundException();
        }
        return repo.findAll();
    }

    //Get method that returns a Winner from the database based on the id as a json object
    @GetMapping("/{id}")
    public EntityModel<Winner> retrieveWinner(@PathVariable int id) {
        Optional<Winner> winner = repo.findById(id);
        if (winner.isEmpty())
            throw new FieldNotFoundException();

        EntityModel<Winner> resource = EntityModel.of(winner.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllWinners());
        resource.add(linkTo.withRel("all-fields"));

        Link selfLink = linkTo(methodOn(this.getClass()).retrieveWinner(id)).withSelfRel();
        resource.add(selfLink);

        return resource;
    }

    //POST method that sends a winner to the database
    @PostMapping("/")
    public ResponseEntity<Object> createWinner(@RequestBody Winner winner) {
        Winner savedWinner = repo.save(winner);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedWinner.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
