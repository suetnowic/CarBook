package com.viktorsuetnov.carbook.service;

import com.viktorsuetnov.carbook.model.Event;
import com.viktorsuetnov.carbook.model.User;
import com.viktorsuetnov.carbook.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

//    public Iterable<Event> getAllEventsWithPagingAndCarIdAndCurrentUser(Long id, User currentUser){
//        return eventRepository.getEventsByCarIdAndCarOwner(id, currentUser);
//    }

    public Page<Event> getAllEventsWithPagingAndCarIdAndCurrentUser(Long id, User currentUser, Pageable pageable){
        return eventRepository.getEventsByCarIdAndCarOwner(id, currentUser, pageable);
    }

    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

    public Event getEventById(Long id){
        return eventRepository.getEventById(id);
    }

    public void deleteEventById(Long event) {
        eventRepository.deleteById(event);
    }
}
