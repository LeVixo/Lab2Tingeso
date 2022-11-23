package com.tingeso.timestamp.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tingeso.timestamp.entity.Timestamp;
import com.tingeso.timestamp.repository.TimeStampRepository;

@Service
public class TimeStampService {

  @Autowired
  private TimeStampRepository timeStampRepository;

  // Horario de entrada
  static final int TIEMPOENTRADA = 8;
  static final int TIEMPOENTRADAMINUTOS = 0;
  // Descuentos (en minutos)
  static final int DESCUENTO1 = 10;
  static final int DESCUENTO2 = 25;
  static final int DESCUENTO3 = 45;
  // Cuento tiempo es inasistencia
  static final int HORASINASISTENCIA = 1;
  static final int MINUTOSINASISTENCIA = 10;
  // Descuentos (en porentaje)
  static final int PORDESCUENTO1 = 1;
  static final int PORDESCUENTO2 = 3;
  static final int PORDESCUENTO3 = 6;
  // Definiciones
  public int hour = 0;
  public int min = 0;
  public int totalDaysWorked = 0;
  public int arrears = 0;
  public int daytemp = 0;
  public int totalDiscount = 0;

  private final Logger logg = LoggerFactory.getLogger(TimeStampService.class);

  public String save(MultipartFile file) {
    if (!file.isEmpty()) {
      try {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(folder + "DATA.txt");
        Files.write(path, bytes);
        logg.info("Archivo guardado");

      } catch (IOException e) {
        logg.error("No se pudo guardar", e);
      }
    }
    return "Archivo guardado correctamente";
  }

  public void uploadFile(MultipartFile file) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(folder + "DATA.txt"));
      String line = br.readLine();
      String[] dataInicial = line.split(";");
      String[] dateInicial = dataInicial[0].split("/");
      int year = Integer.parseInt(dateInicial[0]);
      int month = Integer.parseInt(dateInicial[1]);
      int day = Integer.parseInt(dateInicial[2]);
      // Abrimos el empleado de referencia, este cuenta los días habiles de cada mes
      TimeStampEntity perfectTimeStamp = timestampRepository.findByRutAndMonthAndYear("admin", month, year);
      perfectTimeStamp.setTotalDaysWorked(perfectTimeStamp.getTotalDaysWorked() + 1);
      // imprimimos
      System.out.println("Fecha inicial: " + year + "/" + month + "/" + day);

      while (null != line) {
        // imprimo linea
        System.out.println(line);
        String[] data = line.split(";");
        String[] date = data[0].split("/");
        daytemp = Integer.parseInt(date[2]);
        if (daytemp != day) {
          // Imprimo el total de dias trabajados
          perfectTimeStamp.setTotalDaysWorked(perfectTimeStamp.getTotalDaysWorked() + 1);
          day = daytemp;
        }
        // buscar si existe el rut en la tabla de empleados
        // si existe, buscar el registro en la tabla de registros
        System.out.println("rut: " + data[2]);
        System.out.println("month: " + month);
        System.out.println("year: " + year);
        TimeStampEntity timestampTemp = timestampRepository.findByRutAndMonthAndYear(data[2], month, year);
        String[] reloj = data[1].split(":");
        hour = Integer.parseInt(reloj[0]);
        // imprimimos la hora
        System.out.println(hour);
        // si la hora es menor a las 8, se considera que es la hora de entrada

        if (hour < TIEMPOENTRADA) {
          // llego a tiempo
          totalDaysWorked = timestampTemp.getTotalDaysWorked() + 1;
          timestampTemp.setTotalDaysWorked(totalDaysWorked);
        } else {
          // llego tarde, evaluamos que tan tarde llego
          min = Integer.parseInt(reloj[1]);
          if ((hour > TIEMPOENTRADA + HORASINASISTENCIA && min > TIEMPOENTRADAMINUTOS + MINUTOSINASISTENCIA)
              || (hour > TIEMPOENTRADA + HORASINASISTENCIA + 1)) {
            // inasisencia
            arrears = timestampTemp.getArrears() + 1;
            timestampTemp.setArrears(arrears);
            // la inasistencia se considera como un día no trabajo, en caso de presentar
            // justificativo, se considera como un día trabajado
          } else {
            totalDaysWorked = timestampTemp.getTotalDaysWorked() + 1;
            timestampTemp.setTotalDaysWorked(totalDaysWorked);
            if ((hour > TIEMPOENTRADA + HORASINASISTENCIA) || (min > DESCUENTO3)) {
              // llego con más de 45 minutos de retraso
              totalDiscount = timestampTemp.getTotalDiscount() + PORDESCUENTO3;
              timestampTemp.setTotalDiscount(totalDiscount);
            } else {
              if (min > DESCUENTO2) {
                // llego con más de 25 minutos de retraso
                totalDiscount = timestampTemp.getTotalDiscount() + PORDESCUENTO2;
                timestampTemp.setTotalDiscount(totalDiscount);
              } else {
                if (min > DESCUENTO1) {
                  // llego con más de 10 minutos de retraso
                  totalDiscount = timestampTemp.getTotalDiscount() + PORDESCUENTO1;
                  timestampTemp.setTotalDiscount(totalDiscount);
                }
              }
            }
          }
        }
        timestampRepository.save(timestampTemp);
        line = br.readLine();
      }
      br.close();
      timestampRepository.save(perfectTimeStamp);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
