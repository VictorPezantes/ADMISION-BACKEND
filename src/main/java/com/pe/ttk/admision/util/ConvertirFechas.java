package com.pe.ttk.admision.util;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConvertirFechas {

    private Date fechaSqlDate;
    private String fecha;

    public Date stringToDateSql(){

        try {
            DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
             fecha = dtf3.format(LocalDateTime.now());
            fechaSqlDate = Date.valueOf(fecha);
        }catch (Exception e){

        }

        return fechaSqlDate;
    }
}
