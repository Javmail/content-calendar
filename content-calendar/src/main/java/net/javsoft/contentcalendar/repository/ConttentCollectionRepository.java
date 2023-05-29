package net.javsoft.contentcalendar.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import jakarta.annotation.PostConstruct;
import net.javsoft.contentcalendar.model.Content;
import net.javsoft.contentcalendar.model.Status;
import net.javsoft.contentcalendar.model.Type;

@Repository
public class ConttentCollectionRepository {
    private final List<Content> contentList = new ArrayList<>();

    public ConttentCollectionRepository() {
    }

    public List<Content> findAll() {
        return contentList;
    }

    public Optional<Content> findById(Integer id){
        return contentList.stream().filter(x -> x.id().equals(id)).findFirst();
    }
    
    @PostConstruct
    private void init(){
        
        Content c = new Content(1, "My First Blog Post", "My first blog post", 
        Status.IDEA, Type.ARTICLE, LocalDateTime.now(), null, "");
        
        contentList.add(0, c);

    }

    public void save(Content content) {
        this.contentList.add(content);        
    }

    public boolean existById(Integer id) {
        return this.contentList.stream().filter(x -> x.id().equals(id)).findFirst() != null;
    }

}
