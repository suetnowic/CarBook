package com.viktorsuetnov.carbook.service;

import com.viktorsuetnov.carbook.model.Car;
import com.viktorsuetnov.carbook.model.Event;
import com.viktorsuetnov.carbook.repository.EventRepository;
import com.viktorsuetnov.carbook.util.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    EventRepository eventRepository;

    public void save(Car car, MultipartFile file){
        try {
            List<Event> events = ExcelHelper.excelToEvent(car, file.getInputStream());
            eventRepository.saveAll(events);
        } catch (IOException e){
            throw new RuntimeException("fail to save excel data " + e.getMessage());
        }
    }

    public ByteArrayInputStream load(Car car){
        List<Event> events = eventRepository.getEventsByCarId(car.getId());
        ByteArrayInputStream inputStream = ExcelHelper.eventsToExcel(events);
        return inputStream;
    }
}
