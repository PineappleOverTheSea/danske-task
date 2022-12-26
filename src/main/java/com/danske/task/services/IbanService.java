package com.danske.task.services;

import com.danske.task.dto.IbanDto;
import com.danske.task.dto.IbanResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class IbanService {
    private final List<String> countries33 = List.of("RU");
    private final List<String> countries32 = List.of("LC");
    private final List<String> countries31 = List.of("MT", "SC");
    private final List<String> countries30 = List.of("JO", "KW");
    private final List<String> countries29 = List.of("BR", "EG", "PS", "QA", "UA");
    private final List<String> countries28 = List.of("AL", "AZ", "BY", "CY", "DO", "SV", "GT", "HU", "LB", "PL");
    private final List<String> countries27 = List.of("FR", "GR", "IT", "MR", "MC", "SM");
    private final List<String> countries26 = List.of("IS", "TR");
    private final List<String> countries25 = List.of("LY", "PT", "ST");
    private final List<String> countries24 = List.of("AD", "CZ", "MD", "PK", "RO", "SA", "SK", "ES", "SE", "TN", "VG");
    private final List<String> countries23 = List.of("TL", "GI", "IQ", "IL", "AE");
    private final List<String> countries22 = List.of("BH", "BG", "CR", "GE", "DE", "IE", "ME", "IE", "ME", "RS", "GB", "VA");
    private final List<String> countries21 = List.of("HR", "LV", "LI", "CH");
    private final List<String> countries20 = List.of("AT", "BA", "EE", "KZ", "XK", "LT", "LU");
    private final List<String> countries19 = List.of("MK", "SI");
    private final List<String> countries18 = List.of("DE", "FO", "FI", "GL", "NL", "SD");
    private final List<String> countries16 = List.of("BE");
    private final List<String> countries15 = List.of("NO");
    public IbanResponseDto verifyIban(IbanDto ibanDto){
        String iban = ibanDto.getIban().replace(" ", "").toUpperCase();
        String country = iban.substring(0, 2);

        if(!validateLength(country, iban.length())) {
            return IbanResponseDto.builder().valid(false).build();
        }

        //moves first 4 characters of iban to the back and turns it into a character list for ease of manipulation
        String ibanMoved = iban.substring(4).concat(iban.substring(0, 4));
        List<Character> ibanCharList = ibanMoved.chars().mapToObj(e -> (char) e).toList();
        StringBuilder numberStringBuilder = new StringBuilder();

        //converts letters to appropriate numeric values
        ibanCharList.forEach(ibanChar -> {
            if(Character.isLetter(ibanChar)){
                numberStringBuilder.append((int) ibanChar - 55);
            }
            else numberStringBuilder.append(Character.getNumericValue(ibanChar));
        });

        int remainder = calculateRemainder(numberStringBuilder.toString());
        return IbanResponseDto.builder().valid(remainder == 1).build();
    }

    //Splits the number into smaller chunks and calculates the remainder
    private int calculateRemainder(String numberString){
        int endIndex = 9;
        int step = 7;
        int length = numberString.length();
        StringBuilder remainder = new StringBuilder().append(numberString, 0, 2);
        while (endIndex < length){
            int newRemainder = parseInt(remainder.append(numberString, endIndex - step, endIndex).toString()) % 97;
            remainder.delete(0, 9).append(newRemainder);
            step = Math.min(length - endIndex, step);
            endIndex += step;
        }

        return parseInt(remainder.append(numberString, endIndex - step, endIndex).toString()) % 97;
    }

    private boolean validateLength(String country, int ibanLength){
        switch (ibanLength){
            case (33):{
                return countries33.contains(country);
            }
            case (32):{
                return countries32.contains(country);
            }
            case (31):{
                return countries31.contains(country);
            }
            case (30):{
                return countries30.contains(country);
            }
            case (29):{
                return countries29.contains(country);
            }
            case (28):{
                return countries28.contains(country);
            }
            case (27):{
                return countries27.contains(country);
            }
            case (26):{
                return countries26.contains(country);
            }
            case (25):{
                return countries25.contains(country);
            }
            case (24):{
                return countries24.contains(country);
            }
            case (23):{
                return countries23.contains(country);
            }
            case (22):{
                return countries22.contains(country);
            }
            case (21):{
                return countries21.contains(country);
            }
            case (20):{
                return countries20.contains(country);
            }
            case (19):{
                return countries19.contains(country);
            }
            case (18):{
                return countries18.contains(country);
            }
            case (16):{
                return countries16.contains(country);
            }
            case (15):{
                return countries15.contains(country);
            }
            default: return false;
        }
    }
}
