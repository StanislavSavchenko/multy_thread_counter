package com.test.task.controller;

import com.test.task.counter.CounterManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Counter API"})
@RestController
public class CounterController {
    private final CounterManager counterManager;

    @Autowired
    public CounterController(CounterManager counterManager) {
        this.counterManager = counterManager;
    }

    @ApiResponses({
            @ApiResponse(code = 201, message = "Count of counter thread was increased")
    })
    @PostMapping(value = "/counter/thread/increment")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> configureCounter(
            @ApiParam(value = "Increase count of consumer thread", required = true) @RequestParam Integer consumer,
            @ApiParam(value = "Increase count of producer thread", required = true) @RequestParam Integer producer
    ) {
        counterManager.increaseThreadCount(consumer, producer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "New value of counter was set")
    })
    @PostMapping(value = "/counter")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> setCounterValue(
            @ApiParam(value = "Set counter value", required = true) @RequestParam Integer counter
    ) {
        counterManager.setCounterValue(counter);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
