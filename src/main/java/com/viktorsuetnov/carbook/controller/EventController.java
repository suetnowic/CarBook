package com.viktorsuetnov.carbook.controller;


import com.viktorsuetnov.carbook.model.Car;
import com.viktorsuetnov.carbook.model.Event;
import com.viktorsuetnov.carbook.model.User;
import com.viktorsuetnov.carbook.service.CarService;
import com.viktorsuetnov.carbook.service.EventService;
import com.viktorsuetnov.carbook.service.ExcelService;
import com.viktorsuetnov.carbook.util.ExcelHelper;
import com.viktorsuetnov.carbook.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

import static com.viktorsuetnov.carbook.util.Utilities.getParseDouble;
import static com.viktorsuetnov.carbook.util.Utilities.isEquals;

@Controller
@RequestMapping("/user-cars")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private CarService carService;

    @Autowired
    ExcelService excelService;

    @GetMapping
    public String greeting() {
        return "index";
    }

    @GetMapping("/{car}/events")
    public String getEvents(
            @AuthenticationPrincipal User currentUser,
            @PathVariable(value = "car") Car car,
            @PageableDefault(sort = {"dateEvent"}, direction = Sort.Direction.ASC) Pageable pageable,
            Model model
    ) {
        if (isEquals(currentUser, car)) {
            Page<Event> pages = eventService.getAllEventsWithPagingAndCarIdAndCurrentUser(car.getId(), currentUser, pageable);
            model.addAttribute("page", pages);
            model.addAttribute("url", "/user-cars/" + car.getId() + "/events");
            model.addAttribute("id", car.getId());
            return "eventsList";
        }

        return "redirect:/user-cars/cars";
    }

    @PostMapping("/{car}/events")
    public ResponseEntity<ResponseMessage> uploadFile(
            @AuthenticationPrincipal User currentUser,
            @PathVariable("car") Car car,
            @RequestParam("file") MultipartFile file
    ){

        String message = "";

        if (ExcelHelper.hasExcelFormat(file)){
            try {
                excelService.save(car, file);
                message = "uploaded the file successfully " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e){
                message = "can't upload the file " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an Excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/{car}/events/download")
    public ResponseEntity<Resource> getFile(
            @AuthenticationPrincipal User currentUser,
            @PathVariable("car") Car car
    ) {
            String filename = "events " + car.getCarBrand() + car.getCarModel() + car.getVrp() + ".xlsx";
            InputStreamResource file = new InputStreamResource(excelService.load(car));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                    .body(file);
        }


    // TODO архив автомобилей
    // TODO загрузка данных из файла xlsx

    @GetMapping("/{car}")
    public String eventEditForm(
            @AuthenticationPrincipal User currentUser,
            @PathVariable("car") Car car,
            @RequestParam(value = "event", required = false) Event event,
            Model model
    ) {
        if (isEquals(currentUser, car)) {
            Event eventFromDb = null;
            if (event != null) {
                eventFromDb = eventService.getEventById(event.getId());
            }
            model.addAttribute("event", eventFromDb);
            model.addAttribute("date", eventFromDb != null ? eventFromDb.getDateEvent().toString() : "");

            return "eventEdit";
        }

        return "redirect:/user-cars/cars";
    }

    @PostMapping("/{car}")
    public String createEvent(
            @AuthenticationPrincipal User currentUser,
            @RequestParam(value = "event", required = false) Event getEvent,
            @PathVariable("car") Long car,
            @RequestParam("date") Date date,
            @RequestParam("typeOfWork") String typeOfWork,
            @RequestParam("consumables") String consumables,
            @RequestParam("numberOfLitres") String numberOfLitres,
            @RequestParam("price") String price,
            @RequestParam("odometerReading") String odometerReading,
            @RequestParam("note") String note
    ) {
        if (!isEquals(currentUser, getCarById(car))) {
            return "redirect:/user-cars/cars";
        }

        Event event = null;
        if (getEvent != null) {
            event = eventService.getEventById(getEvent.getId());
        } else {
            event = new Event();
        }
        Event update = createAndUpdate(car, date, typeOfWork, consumables, numberOfLitres, price, odometerReading, note, event);
        eventService.saveEvent(update);
        return "redirect:/user-cars/" + car + "/events";
    }

    @GetMapping("/{car}/delete")
    public String deleteEvent(
            @AuthenticationPrincipal User currentUser,
            @PathVariable("car") Long car,
            @RequestParam("event") Long event
    ) {
        if (isEquals(currentUser, getCarById(car))) {
            eventService.deleteEventById(event);
        }
        return "redirect:/user-cars/" + car + "/events";
    }

    private Event createAndUpdate(Long carId, Date date, String typeOfWork, String consumables, String numberOfLitres,
                                  String price, String odometerReading, String note, Event event) {

        event.setDateEvent(date);
        event.setTypeOfWork(typeOfWork);
        event.setConsumables(consumables);
        event.setNumberOfLitres(getParseDouble(numberOfLitres));
        event.setPrice(getParseDouble(price));
        event.setOdometerReading(getParseDouble(odometerReading));
        event.setNote(note);
        event.setCar(getCarById(carId));

        return event;
    }

    private Car getCarById(Long car) {
        return carService.getCarById(car);
    }

}
