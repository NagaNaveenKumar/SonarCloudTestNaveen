package com.ed.myFirstEdProject.controller;

import com.ed.myFirstEdProject.entity.JournalEntry;
import com.ed.myFirstEdProject.entity.User;
import com.ed.myFirstEdProject.service.JournalEntryService;
import com.ed.myFirstEdProject.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> getAllJournals(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user = userService.findByUsername(userName);
        List<JournalEntry> all = user.getJournal_entries();
        if(all!=null&&!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> postJournalByuser(@RequestBody JournalEntry newJounralEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        try {
            journalEntryService.saveDocument(newJounralEntry,userName);
            return new ResponseEntity<>(newJounralEntry, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getJournal(@PathVariable ObjectId id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user = userService.findByUsername(userName);
        List<JournalEntry> collect = user.getJournal_entries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> byId = journalEntryService.findById(id);
            if(byId.isPresent()){
                return new ResponseEntity<>(byId.get(),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateJournal(@PathVariable ObjectId id,@RequestBody JournalEntry journalEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user = userService.findByUsername(userName);
        List<JournalEntry> collect = user.getJournal_entries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> oldEntry=journalEntryService.findById(id);
            if(oldEntry.isPresent()){
                oldEntry.get().setTitle(journalEntry.getTitle()!=null&&!journalEntry.getTitle().equals("")?journalEntry.getTitle(): oldEntry.get().getTitle());
                oldEntry.get().setContent(journalEntry.getContent()!=null&&!journalEntry.getContent().equals("")?journalEntry.getContent():oldEntry.get().getContent());
                journalEntryService.updateDocument(oldEntry.get());
                return new ResponseEntity<>(oldEntry.get(),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJournal(@PathVariable ObjectId id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user = userService.findByUsername(userName);
        List<JournalEntry> collect = user.getJournal_entries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
                journalEntryService.deleteEntry(id,userName);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("journal not found with id: "+id);
    }
}
