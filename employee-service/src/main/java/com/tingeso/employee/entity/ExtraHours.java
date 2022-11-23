package com.tingeso.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraHours {
    private Integer horas_extra;
    private String rut_employee;
}
