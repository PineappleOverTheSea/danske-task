package com.danske.task.services;

import com.danske.task.dto.IbanDto;
import com.danske.task.dto.IbanResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class IbanService {
    public IbanResponseDto verifyIban(IbanDto ibanDto){
        String iban = ibanDto.getIban().replace(" ", "").toUpperCase();
        System.out.println((int) 'A');
//        List<Character> ibanChars = Arrays.asList(iban.toCharArray()) ;
        return IbanResponseDto.builder().valid(false).build();
    }
}
