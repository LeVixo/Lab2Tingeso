package com.tingeso.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Justificative {
  private int day;
  private int month;
  private String rut_employee;
}
