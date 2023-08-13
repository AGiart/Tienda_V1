package com.tienda.service.impl;

import com.tienda.service.ReporteService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    DataSource dataSource;
    
    @Override
    public ResponseEntity<Resource>
            generaReporte(
                    String reporte,
                    Map<String, Object> parametros,
                    String tipo
            ) throws IOException {
                try{
                    //Se define el tipo de salida del reporte
                    String estilo;
                    if(tipo.equals("vPdf")){
                        estilo="inline; ";
                    }else{
                        estilo="attachment; ";
                    }
                    //se establece la ruta donde etsan los archivos de reporte
                    String reportePath="reportes";
                    
                    //se define donde se generaran en memoria
                    ByteArrayOutputStream salida = new ByteArrayOutputStream();
                    
                    //se define el objeto para leer el reporte
                    ClassPathResource fuente = new ClassPathResource(
                            reportePath
                                    +File.separator
                                    +reporte
                                    +".jasper"
                    );
                    //se define un objeto para poder leer el archivo
                    InputStream elReporte = fuente.getInputStream();
                    
                    //se geenera elreporte segun su descripcion .jasper
                    var reporteJasper =
                            JasperFillManager.fillReport(elReporte, parametros, dataSource.getConnection());
                    //se inicia el proceso para responderle al usuario
                    
                    //se define el tipo de salida de la respuesta
                    MediaType mediaType=null;
                    
                    //se establece el String para hacer el archivo de salida
                    String archivoSalida="";
                    
                    //se usa un arreglo byte para extraer la info generada
                    byte[] data;
                    
                    //se considera el tipo de salida
                    switch (tipo){
                        case "Pdf", "vPdf" ->{
                            JasperExportManager
                                    .exportReportToPdfStream(reporteJasper, salida);
                            
                            mediaType = MediaType.APPLICATION_PDF;
                            archivoSalida = reporte+".pdf";
                        }
                    }
                    
                    //se recuperan los bytes del reporte generado
                    data= salida.toByteArray();
                    
                    //se definen los encabezados de la pagina a responder o descargar
                    HttpHeaders headers = new HttpHeaders();
                    headers.set("Content-Disposition", estilo+"filename=\""+archivoSalida+"\"");
                    
                    //se retorna la respuesta del usuario
                    return ResponseEntity
                            .ok()
                            .headers(headers)
                            .contentType(mediaType)
                            .body(
                                    new InputStreamResource(
                                            new ByteArrayInputStream
                                                    (data)));
                  
                
                            
                    
                }catch(SQLException | JRException e){
                    e.printStackTrace();
                }
                return null;
            }
}
