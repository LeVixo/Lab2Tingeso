package com.tingeso.timestamp.repository;
import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tingeso.timestamp.entity.Timestamp;

@Repository
public interface TimeStampRepository extends JpaRepository<Timestamp, Integer>{
    // find by rutEmployee
    public RegistrationEntity findByRutEmployee(String rutEmployee);
    
    // find by rut, month and year
    @Query("SELECT r FROM RegistrationEntity r WHERE r.rutEmployee = :rutEmployee AND r.month = :month AND r.year = :year")
    RegistrationEntity findByRutAndMonthAndYear(@Param("rutEmployee") String rutEmployee, @Param("month") int month, @Param("year") int year);

    // lista de registros por mes y año
    @Query("SELECT r FROM RegistrationEntity r WHERE r.month = :month AND r.year = :year")
    List<RegistrationEntity> findByMonthAndYear(@Param("month") int month, @Param("year") int year);

    // findAllByMonthAndYear
    public List<RegistrationEntity> findAllByMonthAndYear(int month, int year);
    
}
