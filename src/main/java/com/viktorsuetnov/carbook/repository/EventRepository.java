package com.viktorsuetnov.carbook.repository;

import com.viktorsuetnov.carbook.model.Event;
import com.viktorsuetnov.carbook.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long>
        {

//    List<Event> getEventsByCarIdAndCarOwner(Long id, User currentUser);

    List<Event> getEventsByCarId(Long id);

    Page<Event> getEventsByCarIdAndCarOwner(Long id, User currentUser, Pageable pageable);

    Event getEventById(Long id);

}
