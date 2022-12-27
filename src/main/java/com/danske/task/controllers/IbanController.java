package com.danske.task.controllers;

import com.danske.task.dto.IbanDto;
import com.danske.task.dto.IbanResponseDto;
import com.danske.task.services.IbanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/iban")
public class IbanController {
    private IbanService ibanService;
    public IbanController(IbanService ibanService){this.ibanService = ibanService;}

    @PostMapping()
    public IbanResponseDto verifyIban(@RequestBody IbanDto ibanDto) {
        return ibanService.verifyIban(ibanDto);
    }
}
