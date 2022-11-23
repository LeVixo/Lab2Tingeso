package com.tingeso.timestamp.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "timestamp")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    private String rutEmployee; //foreign key
    private int month;
    private int year;
    private int arrears; // Son justificables
    private int totalDaysWorked;
    private int totalDiscount; // No justificable
    private int totalSalary;
}
