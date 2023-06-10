package com.ogutcenali.controller;

import com.ogutcenali.dto.response.OrdersResponse;
import com.ogutcenali.dto.response.UserProfileResponse;
import com.ogutcenali.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {


    private final UserService userService;


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getUserProfile(id));
    }

    @GetMapping("/{id}/orders")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<OrdersResponse>> getOrdersForUser(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getOrdersForUser(id));
    }
}
