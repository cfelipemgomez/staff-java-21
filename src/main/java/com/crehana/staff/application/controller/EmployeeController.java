package com.crehana.staff.application.controller;

import com.crehana.staff.application.service.EmployeeService;
import com.crehana.staff.core.exception.dto.ErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/upload")
    public ResponseEntity<ErrorMessage> uploadEmployees(@RequestPart("file") MultipartFile file) {
        log.info("Start to process file %s ".formatted(file.getOriginalFilename()));
        employeeService.processFile(file);
        return ResponseEntity.ok(new ErrorMessage(HttpStatus.OK, "File uploaded successfully"));
    }
}
