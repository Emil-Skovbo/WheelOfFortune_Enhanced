package MeeW.intern.WheelOfFortune.controller;


import MeeW.intern.WheelOfFortune.entities.WheelOfFortune;
import MeeW.intern.WheelOfFortune.exceptions.WheelNotFoundException;
import MeeW.intern.WheelOfFortune.repository.WheelOfFortuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/wof")
public class WheelOfFortuneController {

    //implements the interface for WheelOfFortune that extends JpaRepository
    @Autowired
    WheelOfFortuneRepository repo;

    //Get method that returns all WheelOfFortunes in the database as a json object
    @GetMapping("/")
    public List<WheelOfFortune> retrieveAllWheelOfFortunes()
    {
        return repo.findAll();
    }


    //Get method that returns a WheelOfFortune from the database based on the id as a json object
    @GetMapping("/{id}")
    public EntityModel<WheelOfFortune> retrieveWheel(@PathVariable int id) {
        Optional<WheelOfFortune> wheel = repo.findById(id);
        if (wheel.isEmpty())
            throw new WheelNotFoundException();

        EntityModel<WheelOfFortune> resource = EntityModel.of(wheel.get()); 						// get the resource
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllWheelOfFortunes()); // get link
        resource.add(linkTo.withRel("all-wheelOfFortunes"));										// append the link

        Link selfLink = linkTo(methodOn(this.getClass()).retrieveWheel(id)).withSelfRel(); //add also link to self
        resource.add(selfLink);
        return resource;
    }

    //Delete method that removes a WheelOfFortune from the database based on its id
    @DeleteMapping("/{id}")
    public void deleteWheelOfFortune(@PathVariable int id) {
        repo.deleteById(id);
    }

    //POST method that sends a WheelOfFortune to the database
    @PostMapping("/")
    public ResponseEntity<Object> createWheelOfFortune(@RequestBody WheelOfFortune WheelOfFortune) {
        WheelOfFortune savedWheelOfFortune = repo.save(WheelOfFortune);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedWheelOfFortune.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    //PUT method that updates a WheelOfFortune in the database based on its id
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateWheelOfFortune(@RequestBody WheelOfFortune WheelOfFortune, @PathVariable int id) {
        Optional<WheelOfFortune> wheelOfFortuneOptional = repo.findById(id);
        if (wheelOfFortuneOptional.isEmpty())
            return ResponseEntity.notFound().build();
        WheelOfFortune.setId(id);
        repo.save(WheelOfFortune);
        return ResponseEntity.noContent().build();
    }

}
