package com.ed.myFirstEdProject.service;

import com.ed.myFirstEdProject.entity.JournalEntry;
import com.ed.myFirstEdProject.entity.User;
import com.ed.myFirstEdProject.repository.JournalEntryRepository;
import com.ed.myFirstEdProject.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepo;

    @Autowired
    private UserRepository userRepository;

    public void updateDocument(JournalEntry journalEntry) {
        journalEntryRepo.save(journalEntry);
    }

    @Transactional
    public void saveDocument(JournalEntry journalEntry, String userName){
        journalEntry.setDate(LocalDate.now());
        JournalEntry saved = journalEntryRepo.save(journalEntry);
        User user=userRepository.findByUserName(userName);
        user.getJournal_entries().add(saved);
        userRepository.save(user);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    public boolean deleteEntry(ObjectId id, String userName){
        User user = userRepository.findByUserName(userName);
        user.getJournal_entries().removeIf(x->x.getId().equals(id));
        userRepository.save(user);
        journalEntryRepo.deleteById(id);
        return true;
    }



}
