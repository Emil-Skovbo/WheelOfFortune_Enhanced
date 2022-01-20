package MeeW.intern.WheelOfFortune.controller;

import MeeW.intern.WheelOfFortune.entities.Field;
import MeeW.intern.WheelOfFortune.exceptions.FieldNotFoundException;
import MeeW.intern.WheelOfFortune.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/field")
public class FieldController {

    @Autowired
    FieldRepository repo;

    @RequestMapping("/")
    public List<Field> retrieveAllFields() {
        if (repo.findAll().isEmpty()) {
            throw new RuntimeException();
        }
        return repo.findAll();
    }

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
}
