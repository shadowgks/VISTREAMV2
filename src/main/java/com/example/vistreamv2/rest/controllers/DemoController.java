package com.example.vistreamv2.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1.0.0/public/demo")
public class DemoController {
    @GetMapping()
    public ResponseEntity<?> helloWorld(){
        String test = "Hello World";
        return new ResponseEntity<>(test, HttpStatus.OK);
    }
}
