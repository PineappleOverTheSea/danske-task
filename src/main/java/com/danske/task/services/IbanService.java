package com.danske.task.services;

import com.danske.task.dto.IbanDto;
import com.danske.task.dto.IbanResponseDto;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;

@Service
public class IbanService {
    public IbanResponseDto verifyIban(IbanDto ibanDto) {
        JSONObject countries;

        //tries to read countries from a json file
        try{
            countries = (JSONObject) new JSONParser().parse(
                    new FileReader(
                            Objects.requireNonNull(
                                    IbanService.class.getClassLoader().getResource("static/countries.json")
                            ).getFile()
                    ));
        }
        catch (IOException | ParseException e){
            throw new RuntimeException(e);
        }

        String iban = ibanDto.getIban().replace(" ", "").toUpperCase();
        String country = iban.substring(0, 2);
        long length = (long) countries.get(country);

        if(length != iban.length())
            return IbanResponseDto.builder().valid(false).build();

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
}
