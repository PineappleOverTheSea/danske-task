package com.danske.task.services;

import com.danske.task.dto.IbanDto;
import com.danske.task.dto.IbanResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IbanServiceTest {
    @Test
    void verifyIbanEmpty(){
        IbanService service = new IbanService();
        IbanDto ibanDto = new IbanDto(""); //empty
        IbanResponseDto responseDto = new IbanResponseDto(false);

        IbanResponseDto response = service.verifyIban(ibanDto);

        Assertions.assertEquals(responseDto, response);
    }

    @Test
    void verifyIbanLength(){
        IbanService service = new IbanService();
        IbanDto ibanDto = new IbanDto("GB82"); //incorrect length
        IbanResponseDto responseDto = new IbanResponseDto(false);

        IbanResponseDto response = service.verifyIban(ibanDto);

        Assertions.assertEquals(responseDto, response);
    }

    @Test
    void verifyIbanCountry(){
        IbanService service = new IbanService();
        IbanDto ibanDto = new IbanDto("XX82 WEST 1234 5698 7654 32"); //nonexistent country
        IbanResponseDto responseDto = new IbanResponseDto(false);

        IbanResponseDto response = service.verifyIban(ibanDto);

        Assertions.assertEquals(responseDto, response);
    }

    @Test
    void verifyIbanWrong(){
        IbanService service = new IbanService();
        IbanDto ibanDto = new IbanDto("GB82 WEST 1234 5698 7654 33"); //incorrect iban
        IbanResponseDto responseDto = new IbanResponseDto(false);

        IbanResponseDto response = service.verifyIban(ibanDto);

        Assertions.assertEquals(responseDto, response);
    }

    @Test
    void verifyIban(){
        IbanService service = new IbanService();
        IbanDto ibanDto = new IbanDto("GB82 WEST 1234 5698 7654 32"); //incorrect iban
        IbanResponseDto responseDto = new IbanResponseDto(true);

        IbanResponseDto response = service.verifyIban(ibanDto);

        Assertions.assertEquals(responseDto, response);
    }
}