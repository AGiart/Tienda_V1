package com.tienda.service;

import java.io.IOException;
import java.util.Map;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface ReporteService {

    public ResponseEntity<Resource>
            generaReporte(
                    String reporte,  //El nombre del archivo llamado .jasper
                    Map<String, Object> parametros,//Los parametros del reporte si este los tiene
                    String tipo//El tipo del reporte (La extension)
            ) throws IOException;
}
