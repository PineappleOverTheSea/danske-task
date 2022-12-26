package com.danske.task.services;

import com.danske.task.dto.IbanDto;
import com.danske.task.dto.IbanResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        String ibanMoved = iban.substring(4).concat(iban.substring(0, 4));
        List<Character> ibanCharList = ibanMoved.chars().mapToObj(e -> (char) e).toList();
        StringBuilder modStringBuilder = new StringBuilder();

        ibanCharList.forEach(ibanChar -> {
            if(Character.isLetter(ibanChar)){
                modStringBuilder.append((int) ibanChar - 55);
            }
            else modStringBuilder.append(Character.getNumericValue(ibanChar));
        });

        String modString = modStringBuilder.toString();
        int modResult = calculateMod(modString);
        return IbanResponseDto.builder().valid(modResult == 1).build();
    }

    private int calculateMod(String modString){
        int startIndex = 2;
        int endIndex = 9;
        int length = modString.length();
        StringBuilder mod = new StringBuilder().append(modString, 0, 2);
        while (length > 0){
            mod = Integer.toString(parseInt(mod.append(modString.substring(startIndex, endIndex))) % 97);
            length -= endIndex;
            int step = Math.min(length, 7);
            startIndex += step;
            endIndex += step;
        }

        return parseInt(mod);
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
