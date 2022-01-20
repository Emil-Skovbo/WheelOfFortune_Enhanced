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

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/wof")
public class WheelOfFortuneController {

    //Instance of the object
    //WheelOfFortune wof = new WheelOfFortune();

    @Autowired
    WheelOfFortuneRepository repo;

//    //API endpoint for spinning the wheel of fortune. http://localhost:8080/wof/spin
//    @GetMapping("/spin")
//    public int spinWheelOfFortune()
//    {
//        return wof.spin();
//    }


    @GetMapping("/")
    public List<WheelOfFortune> retrieveAllWheelOfFortunes()
    {
        return repo.findAll();
    }

    // This is the only method, which returns hyperlinks, for now
    // If the resource is found, a link to its 'family' is appended to its native load
    @GetMapping("/{id}")
    public EntityModel<WheelOfFortune> retrieveWheel(@PathVariable int id)
    {
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


    @DeleteMapping("/{id}")
    public void deleteWheelOfFortune(@PathVariable int id) {
        repo.deleteById(id);
    }

    // Create a new resource and remember its unique location in the hypermedia
    @PostMapping("/")
    public ResponseEntity<Object> createWheelOfFortune(@RequestBody WheelOfFortune WheelOfFortune) {
        MeeW.intern.WheelOfFortune.entities.WheelOfFortune savedWheelOfFortune = repo.save(WheelOfFortune);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedWheelOfFortune.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateWheelOfFortune(@RequestBody WheelOfFortune WheelOfFortune, @PathVariable int id) {
        Optional<MeeW.intern.WheelOfFortune.entities.WheelOfFortune> wheelOfFortuneOptional = repo.findById(id);
        if (!wheelOfFortuneOptional.isPresent())
            return ResponseEntity.notFound().build();
        WheelOfFortune.setId(id);
        repo.save(WheelOfFortune);
        return ResponseEntity.noContent().build();
    }

}
