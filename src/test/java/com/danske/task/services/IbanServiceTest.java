package com.danske.task.services;

import com.danske.task.dto.IbanDto;
import com.danske.task.dto.IbanResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IbanServiceTest {

    IbanService service = new IbanService();
    IbanDto ibanDto = new IbanDto();
    IbanResponseDto responseDto =  new IbanResponseDto(false);
    IbanResponseDto response;

    @Test
    void verifyIbanEmpty(){
        ibanDto.setIban(""); //empty
        response = service.verifyIban(ibanDto);
        Assertions.assertEquals(responseDto, response);
    }

    @Test
    void verifyIbanLength(){
        ibanDto.setIban("GB82"); //incorrect length
        response = service.verifyIban(ibanDto);
        Assertions.assertEquals(responseDto, response);
    }

    @Test
    void verifyIbanCountry(){
        ibanDto.setIban("XX82 WEST 1234 5698 7654 32"); //nonexistent country
        response = service.verifyIban(ibanDto);
        Assertions.assertEquals(responseDto, response);
    }

    @Test
    void verifyIbanWrong(){
        ibanDto.setIban("GB82 WEST 1234 5698 7654 33"); //incorrect iban
        response = service.verifyIban(ibanDto);
        Assertions.assertEquals(responseDto, response);
    }

    @Test
    void verifyIban(){
        ibanDto.setIban("GB82 WEST 1234 5698 7654 32");
        responseDto.setValid(true);
        response = service.verifyIban(ibanDto);
        Assertions.assertEquals(responseDto, response);
    }
}