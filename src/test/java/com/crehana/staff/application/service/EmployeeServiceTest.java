package com.crehana.staff.application.service;

import com.crehana.staff.application.EmployeePersistencePort;
import com.crehana.staff.application.RolePersistencePort;
import com.crehana.staff.core.exception.DataBaseException;
import com.crehana.staff.core.model.Employee;
import com.crehana.staff.core.model.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerErrorException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeePersistencePort employeePersistence;
    @Mock
    private RolePersistencePort rolePersistence;

    @Mock
    private MultipartFile file;

    @InjectMocks
    private EmployeeService employeeService;


    private MockMultipartFile getMultipartFile(String filePath){
        File file = new File(filePath);
        byte[] fileContent = new byte[(int) file.length()];
        try (FileInputStream inputStream = new FileInputStream(file)) {
            assertTrue(inputStream.read(fileContent) > 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new MockMultipartFile(
                "employees.xlsx",
                "employees.xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                fileContent);
    };

    @Test
    void givenACorrectFile_whenCallProcessFile_thenProcessFileSuccessful() {

        when(employeePersistence.save(any(Employee.class))).thenReturn(Employee.builder().build());
        when(rolePersistence.save(any(Role.class))).thenReturn(Role.builder().build());

        employeeService.processFile(getMultipartFile("src/test/resources/employees.xlsx"));

        verify(employeePersistence, times(2)).save(any(Employee.class));
        verify(rolePersistence, times(2)).save(any(Role.class));
    }

    @Test
    void givenACorrectFile_whenCallProcessFile_thenEmployeePersistenceThrowsDataBaseException() {

        when(employeePersistence.save(any(Employee.class))).thenReturn(Employee.builder().build())
                .thenThrow(DataBaseException.class);

        assertThrows(ServerErrorException.class,
                ()-> employeeService.processFile(getMultipartFile("src/test/resources/employees.xlsx")));

        verify(employeePersistence, times(2)).save(any(Employee.class));
        verify(rolePersistence, times(1)).save(any(Role.class));
    }

    @Test
    void givenAWrongFormatFile_whenCallProcessFile_thenThrowsException() {
        assertThrows(ServerErrorException.class,
                ()-> employeeService.processFile(getMultipartFile("src/test/resources/employees.csv")));
        verifyNoInteractions(employeePersistence);
        verifyNoInteractions(rolePersistence);
    }

    @Test
    void testProcessFileThrowsException() throws IOException {
        when(file.getInputStream()).thenThrow(new IOException("Unable to read file"));

        assertThrows(ServerErrorException.class, () -> employeeService.processFile(file));
    }
}