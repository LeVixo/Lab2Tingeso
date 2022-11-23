package com.tingeso.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Employee {
    @Id
    private String rut;
    
    private String name;
    private String secondname;
    private String category;
    private int monthIncorporation;
    private int yearIncorporation;
}
