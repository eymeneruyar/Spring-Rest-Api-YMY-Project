package com.work.ymy.company.controller;

import com.work.ymy.company.payload.request.CompanyRequest;
import com.work.ymy.company.service.CompanyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    @ResponseBody
    @PostMapping("/createOrUpdateCompany")
    ResponseEntity<?> createOrUpdateCompany(@RequestBody @Valid CompanyRequest companyRequest){
        return companyService.createOrUpdateCompany(companyRequest);
    }

}
