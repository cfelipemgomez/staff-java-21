package com.crehana.staff.core.model;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeData {
    @NonNull private String id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String role;
}
