
package com.tienda1.service;

import com.tienda1.domain.Categoria;
import java.util.List;

/**
 *
 * @author isalr
 */
public interface CategoriaService {
    //se declara un metodo para ibtener un ArrayList de objetos categoria
    //los objetos vienen de la tabla categoria
    //son todos los registros o solo los activos 
    public List<Categoria> getCategorias(boolean activos);
}
