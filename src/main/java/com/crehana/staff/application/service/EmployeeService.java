package com.crehana.staff.application.service;

import com.crehana.staff.application.EmployeePersistencePort;
import com.crehana.staff.application.RolePersistencePort;
import com.crehana.staff.core.mapper.EmployeeMapper;
import com.crehana.staff.core.mapper.RoleMapper;
import com.crehana.staff.core.model.Employee;
import com.crehana.staff.core.model.EmployeeData;
import com.crehana.staff.core.model.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerErrorException;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.apache.logging.log4j.util.Strings.EMPTY;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeePersistencePort employeePersistence;
    private final RolePersistencePort rolePersistence;


    public void processFile(MultipartFile file) {
        try {
            LocalDateTime initTime = LocalDateTime.now();
            List<EmployeeData> employees = processExcelFile(file);

            employees.forEach(employeeData -> {
                Employee employee = EmployeeMapper.INSTANCE.employeeDataToEmployee(employeeData);
                employeePersistence.save(employee);
                Role role = RoleMapper.INSTANCE.dtoToModel(employeeData);
                role.setEmployee(employee);
                rolePersistence.save(role);
            });

            log.info("%s processed in %s".formatted(file.getOriginalFilename(), getProcessingTime(initTime)));

        } catch (RuntimeException | IOException | InterruptedException | ExecutionException e) {
            throw new ServerErrorException(e.getMessage(), e.getCause());
        }
    }

    private static List<EmployeeData> processExcelFile(MultipartFile file) throws IOException, InterruptedException, ExecutionException {
        List<EmployeeData> employees = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream());
            ExecutorService executorService = newFixedThreadPool(20, Thread.ofVirtual().factory())) {
            Sheet sheet = workbook.getSheetAt(0);
            List<Future<EmployeeData>> futures = new ArrayList<>();
            log.info("Processing %d records ...".formatted(sheet.getLastRowNum()));
            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Skip header row
                    continue;
                }
                futures.add(executorService.submit(new RowProcessor(row)));
            }

            for (Future<EmployeeData> future : futures) {
                employees.add(future.get());
            }

        }
        return employees;
    }


    static class RowProcessor implements Callable<EmployeeData> {
        private final Row row;

        public RowProcessor(Row row) {
            this.row = row;
        }

        @Override
        public EmployeeData call() {
            DataFormatter formatter = new DataFormatter();
            EmployeeData employee = new EmployeeData();
            employee.setId(formatter.formatCellValue(row.getCell(0)));
            employee.setName(row.getCell(1) == null ? EMPTY : row.getCell(1).getStringCellValue());
            employee.setLastName(row.getCell(2) == null ? EMPTY : row.getCell(2).getStringCellValue());
            employee.setEmail(row.getCell(3) == null ? EMPTY : row.getCell(3).getStringCellValue());
            employee.setPhone(row.getCell(4) == null ? EMPTY : formatter.formatCellValue(row.getCell(4)));
            employee.setRole(row.getCell(5).getStringCellValue());
            return employee;
        }
    }

    private String getProcessingTime(LocalDateTime startDateTime){
        LocalDateTime endDateTime = LocalDateTime.now();
        Duration duration = Duration.between(startDateTime, endDateTime);
        return "%d:%d:%d".formatted(duration.toMinutesPart(), duration.toSeconds(), duration.toMillis());
    }
}
