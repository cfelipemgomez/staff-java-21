package com.crehana.staff.application.controller;

import com.crehana.staff.application.service.EmployeeService;
import com.crehana.staff.core.exception.dto.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    void givenARightFile_whenCallUploadEmployees_thenReturnSuccess() {
        doNothing().when(employeeService).processFile(any());
        MultipartFile mockFile = mock(MultipartFile.class);

        ResponseEntity<ErrorMessage> response = employeeController.uploadEmployees(mockFile);

        assertEquals(200, response.getStatusCode().value());
    }

}