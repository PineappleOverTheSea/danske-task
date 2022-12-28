//package com.danske.task.controllers;
//
//import com.danske.task.dto.IbanDto;
//import com.danske.task.services.IbanService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(IbanController.class)
//class IbanControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//    @Autowired
//    private IbanService ibanService;
//
//    IbanControllerTest(IbanService ibanService) {
//        this.ibanService = ibanService;
//    }
//
//    @Test
//    void verifyIban() throws Exception {
//        RequestBuilder request = MockMvcRequestBuilders.post("/api/iban", new IbanDto());
//        MvcResult result = mvc.perform(request).andReturn();
//        assertEquals(false, result.getResponse().getHeaderValue("valid"));
//    }
//}