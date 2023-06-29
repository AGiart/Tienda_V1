
package com.tienda1.service;

import com.tienda1.domain.Producto;
import java.util.List;

/**
 *
 * @author isalr
 */
public interface ProductoService {
    //se declara un metodo para ibtener un ArrayList de objetos producto
    //los objetos vienen de la tabla producto
    //son todos los registros o solo los activos 
    public List<Producto> getProductos(boolean activos);
    // Se obtiene un Producto, a partir del id de un producto
    public Producto getProducto(Producto producto);
    
    // Se inserta un nuevo producto si el id del producto esta vacío
    // Se actualiza un producto si el id del producto NO esta vacío
    public void save(Producto producto);
    
    // Se elimina el producto que tiene el id pasado por parámetro
    public void delete(Producto producto);
}
