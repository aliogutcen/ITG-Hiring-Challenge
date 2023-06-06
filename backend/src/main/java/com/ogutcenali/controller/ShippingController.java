package com.ogutcenali.controller;

import com.ogutcenali.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shippings")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ShippingController {

    private final ShippingService shippingService;


}
