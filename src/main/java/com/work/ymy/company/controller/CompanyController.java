package com.work.ymy.company.controller;

import com.work.ymy.company.exception.CompanyNotFoundException;
import com.work.ymy.company.payload.request.CompanyRequest;
import com.work.ymy.company.service.CompanyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    @ResponseBody
    @PostMapping("/createOrUpdateCompany")
    public ResponseEntity<?> createOrUpdateCompany(@RequestBody @Valid CompanyRequest companyRequest){
        return companyService.createOrUpdateCompany(companyRequest);
    }

    @ResponseBody
    @GetMapping("/getListCompany")
    public ResponseEntity<?> getListCompany(){
        return companyService.getListCompany();
    }

    @ResponseBody
    @PutMapping("/deleteCompany/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable String id) throws CompanyNotFoundException {
        return companyService.deleteCompany(id);
    }

    @ResponseBody
    @GetMapping("/getListCity")
    public ResponseEntity<?> getListCity(){
        return companyService.getListCity();
    }

    @ResponseBody
    @GetMapping("/getListTownBySelectedCity/{id}")
    public ResponseEntity<?> getListTownBySelectedCity(@PathVariable String id){
        return companyService.getListTownBySelectedCity(id);
    }

    @ResponseBody
    @GetMapping("/getDetailCityByCityKey/{id}")
    public ResponseEntity<?> getDetailCityByCityKey(@PathVariable String id){
        return companyService.getDetailCityByCityKey(id);
    }

    @ResponseBody
    @GetMapping("/getDetailCompany/{id}")
    public ResponseEntity<?> getDetailCompany(@PathVariable String id) throws CompanyNotFoundException {
        return companyService.getDetailCompany(id);
    }

}
