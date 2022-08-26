package com.pe.ttk.admision.util.input.data;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import com.pe.ttk.admision.exceptions.TTKDataException;
import com.pe.ttk.admision.util.SearchCriteria;
import lombok.Data;

@Data
public class PostulanteFindInputData {

  public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
  private static final String DISTRITO = "distrito";
  private static final String PROVINCIA = "provincia";
  private static final String DEPARTAMENTO = "departamento";
  private static final String DNI = "dni";
  private static final String ESTADO_POSTULACION = "estadoPostulacion";
  private static final String PROFESION = "profesion";
  private static final String RESPONSABLE_ASIGNADO = "responsableAsignado";
  private static final String PROCEDENCIA = "procedencia";
  private static final String APELLIDO_PATERNO = "apellidoPaterno";
  //private static final String FECHA_POSTULACION = "fechaPostulacion";

  private String distrito;
  private String dni;
  private String provincia;
  private String departamento;
  private String estadoPostulacion;
  private String profesion;
  private String responsableAsignado;
  private String procedencia;
  private String apellidoPaterno;
  //private String fechaPostulacion;


  /**
   * Fill the data with the query string params
   */
  public void fillData(List<SearchCriteria> params) throws TTKDataException {
    fillDefaultValues();

    DateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    format.setLenient(false);

    if (params.isEmpty()) {
      throw new TTKDataException("Error: Debe ingresar como mínimo un parámetro de búsqueda.");
    }

    for (SearchCriteria criteria : params) {
      String key = criteria.getKey();
      String value = (String) criteria.getValue();
      List<String> listValue = criteria.getListValue();

      switch (key) {
        case DISTRITO:
          if (value != null) {
            setDistrito(value);
          }
          break;
        case PROVINCIA:
          if (value != null) {
            setProvincia(value);
          }
          break;
        case DEPARTAMENTO:
          if (value != null) {
            setDepartamento(value);
          }
          break;
        case DNI:
          if (value != null) {
            setEstadoPostulacion(value);
          }
          break;


      }
    }
  }

  /**
   * Default values
   */
  private void fillDefaultValues() {
    // Put here all the default values using the set methods below
    setDistrito("-1");
    setProvincia("-1");
    setDepartamento("-1");
    setEstadoPostulacion("-1");
  }


}
