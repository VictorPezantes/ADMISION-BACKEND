package com.pe.ttk.admision.exceptions;

public class TTKDataException extends Exception {

  private static final long serialVersionUID = 5436136707693773092L;
  private String msjError = "";

  public TTKDataException(String message) {
    super(message);
  }

  public TTKDataException(Exception e) {
    super(e);
    msjError = e.getMessage();
  }

  public TTKDataException(Exception e, String msg) {
    super(e);

    msjError = msg;
  }

  public String getMensajeError() {
    return msjError;
  }

  public void setMensajeError(String msg) {
    msjError = msg;
  }
}
