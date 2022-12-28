package com.danske.task.services;

import com.danske.task.dto.IbanDto;
import com.danske.task.dto.IbanResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IbanServiceTest {

    @Test
    void verifyIban() {
        IbanService service = new IbanService();
        IbanDto ibanDto = new IbanDto(""); //empty
        IbanResponseDto responseDto =  new IbanResponseDto(false);
        IbanResponseDto response = service.verifyIban(ibanDto);
        Assertions.assertEquals(responseDto, response);

        ibanDto.setIban("GB82"); //incorrect length
        response = service.verifyIban(ibanDto);
        Assertions.assertEquals(responseDto, response);

        ibanDto.setIban("XX82 WEST 1234 5698 7654 32"); //nonexistent country
        response = service.verifyIban(ibanDto);
        Assertions.assertEquals(responseDto, response);

        ibanDto.setIban("GB82 WEST 1234 5698 7654 33"); //incorrect iban
        response = service.verifyIban(ibanDto);
        Assertions.assertEquals(responseDto, response);

        ibanDto.setIban("GB82 WEST 1234 5698 7654 32");
        responseDto.setValid(true);
        response = service.verifyIban(ibanDto);
        Assertions.assertEquals(responseDto, response);
    }
}