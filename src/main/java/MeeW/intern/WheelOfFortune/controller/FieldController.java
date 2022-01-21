package MeeW.intern.WheelOfFortune.controller;

import MeeW.intern.WheelOfFortune.entities.Field;
import MeeW.intern.WheelOfFortune.entities.WheelOfFortune;
import MeeW.intern.WheelOfFortune.exceptions.FieldNotFoundException;
import MeeW.intern.WheelOfFortune.repository.FieldRepository;
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
@RequestMapping("/field")
public class FieldController {

    //implements the interface for Field that extends JpaRepository
    @Autowired
    FieldRepository repo;

    //Get method that returns all Fields in the database as a json object
    @GetMapping("/")
    public List<Field> retrieveAllFields() {
        if (repo.findAll().isEmpty()) {
            throw new RuntimeException();
        }
        return repo.findAll();
    }

    //Get method that returns a field from the database based on the id as a json object
    @GetMapping("/{id}")
    public EntityModel<Field> retrieveField(@PathVariable int id) {
        Optional<Field> field = repo.findById(id);
        if (field.isEmpty())
            throw new FieldNotFoundException();

        EntityModel<Field> resource = EntityModel.of(field.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllFields());
        resource.add(linkTo.withRel("all-fields"));

        Link selfLink = linkTo(methodOn(this.getClass()).retrieveField(id)).withSelfRel();
        resource.add(selfLink);

        return resource;
    }

    //POST method that sends a field to the database
    @PostMapping("/")
    public ResponseEntity<Object> createField(@RequestBody Field field) {
        Field savedField = repo.save(field);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedField.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
