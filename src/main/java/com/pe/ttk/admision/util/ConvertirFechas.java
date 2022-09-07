package com.pe.ttk.admision.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

public class ConvertirFechas {

    public static final ConvertirFechas convertirFechas = new ConvertirFechas();

    public static ConvertirFechas getInstance(){
        return convertirFechas;
    }

    private static final Logger logger = LoggerFactory.getLogger(ConvertirFechas.class);

    private Date fechaSqlDate;
    private String fecha;
    private java.util.Date fechaD;

    public Date stringToDateSql(){

        try {
            DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
             fecha = dtf3.format(LocalDateTime.now());
            fechaSqlDate = Date.valueOf(fecha);
        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return fechaSqlDate;
    }

    public Integer obtenerAnioMesDia(Integer condicion){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Lima"));
        if(condicion == Calendar.YEAR){
            return calendar.get(Calendar.YEAR);
        }
        if(condicion == Calendar.MONTH){
            return calendar.get(Calendar.MONTH)+1;
        }
        if(condicion == Calendar.DAY_OF_MONTH){
            return calendar.get(Calendar.DAY_OF_MONTH);
        }
        return 0;
    }

    public Integer obtenerAnioMesDia(Integer condicion, java.util.Date fecha){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Lima"));
        calendar.setTime(fecha);
        if(condicion == Calendar.YEAR){
            return calendar.get(Calendar.YEAR);
        }
        if(condicion == Calendar.MONTH){
            return calendar.get(Calendar.MONTH)+1;
        }
        if(condicion == Calendar.DAY_OF_MONTH){
            return calendar.get(Calendar.DAY_OF_MONTH);
        }
        return 0;
    }

    public java.util.Date obtenerFechaActualSinHora() {
        try {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Lima"));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setTimeZone(TimeZone.getTimeZone("America/Lima"));
            java.util.Date date = new java.util.Date();
            fechaD = sdf.parse(sdf.format(date));

        }catch (ParseException e){
            logger.error(e.getMessage());
        }
        return fechaD;

    }

    public java.util.Date obtenerFecha(java.util.Date fecha){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Lima"));
        calendar.setTime(fecha);
        fechaD = calendar.getTime();
        return fechaD;
    }
}
