package com.danske.task.controllers;

import com.danske.task.dto.IbanDto;
import com.danske.task.dto.IbanResponseDto;
import com.danske.task.services.IbanService;
import org.springframework.web.bind.annotation.*;

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
