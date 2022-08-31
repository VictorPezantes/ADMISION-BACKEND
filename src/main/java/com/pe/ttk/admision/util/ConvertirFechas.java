package com.pe.ttk.admision.util;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class ConvertirFechas {

    public static final ConvertirFechas convertirFechas = new ConvertirFechas();

    public static ConvertirFechas getInstance(){
        return convertirFechas;
    }

    private Date fechaSqlDate;
    private String fecha;
    private java.util.Date fechaD;

    public Date stringToDateSql(){

        try {
            DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
             fecha = dtf3.format(LocalDateTime.now());
            fechaSqlDate = Date.valueOf(fecha);
        }catch (Exception e){

        }

        return fechaSqlDate;
    }

    public java.util.Date obtenerFechaActual(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        fechaD = new java.util.Date();
        return fechaD;

    }

    public java.util.Date obtenerFecha(java.util.Date fecha){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        fechaD = fecha;
        return fechaD;
    }
}
