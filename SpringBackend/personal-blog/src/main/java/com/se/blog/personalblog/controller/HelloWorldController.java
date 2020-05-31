package com.se.blog.personalblog.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Slf4j
@RestController("/test")
public class HelloWorldController {
    @GetMapping("/")
    public String hello() {
        return "Hello World, from Spring Boot 2!";
    }

    @GetMapping("/test")
    public ResponseEntity tmpMethod(){

        return ResponseEntity.ok().body("Test string");
    }
    @PostMapping("/test")
    public String getLocationInTime(
            @RequestBody User user) {

        //Convert instant to LocalDateTime, no timezone, add a zero offset / UTC+0
        LocalDateTime ldt = LocalDateTime.ofInstant(user.getIntstant(), ZoneOffset.UTC);

        log.info("local date param {}",ldt);
        return "response";
    }


}
@Data
class User {
    private  int id;

    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(dataType = "java.sql.Date")
    private Date birthDate;

    private Instant intstant;


    public Date dateOfJoining;
}