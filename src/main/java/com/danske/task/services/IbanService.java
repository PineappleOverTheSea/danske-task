package com.danske.task.services;

import com.danske.task.dto.IbanDto;
import com.danske.task.dto.IbanResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IbanService {
    private final List<String> countries28 = List.of("AL", "AZ");
    public IbanResponseDto verifyIban(IbanDto ibanDto){
        String iban = ibanDto.getIban().replace(" ", "").toUpperCase();
        String country = iban.substring(0, 1);

        if(!validateLength(country, iban.length())) {
            return IbanResponseDto.builder().valid(false).build();
        }

        System.out.println((int) 'A');
        List<Character> ibanList = iban.chars().mapToObj(e -> (char) e).toList();
        return IbanResponseDto.builder().valid(true).build();
    }

    private boolean validateLength(String country, int ibanLength){
        switch (ibanLength){
            case (28):{
                return countries28.contains(country);
            }
            case (24):{

            }
            case (20):{

            }
            default: return false;
        }
    }
}
