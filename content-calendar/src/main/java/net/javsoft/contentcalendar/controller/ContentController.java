package net.javsoft.contentcalendar.controller;

import java.net.ConnectException;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.Response;
import org.apache.coyote.http2.Http2OutputBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.javsoft.contentcalendar.model.Content;
import net.javsoft.contentcalendar.repository.ConttentCollectionRepository;

@RestController
@RequestMapping("/api/content")
public class ContentController {
 
    private final ConttentCollectionRepository repository;

    public ContentController(ConttentCollectionRepository repository) {
        this.repository = repository;
    }

    //make a request and find all ocurrences of content.
    @GetMapping("")
    public List<Content> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id){
        return 
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Content content){
        this.repository.save(content);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id){
        if(!repository.existById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!");
        }

        this.repository.save(content);
    }
        
}
