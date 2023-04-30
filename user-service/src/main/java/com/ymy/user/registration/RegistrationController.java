package com.ymy.user.registration;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/registerUser")
    public String register(@Valid @RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }


}
