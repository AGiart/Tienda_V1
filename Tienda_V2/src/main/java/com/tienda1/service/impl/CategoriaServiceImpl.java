/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda1.service.impl;

import com.tienda1.dao.CategoriaDao;
import com.tienda1.domain.Categoria;
import com.tienda1.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author isalr
 */
@Service
public class CategoriaServiceImpl implements CategoriaService{
 
    //La anotacion autowired crea un unico objeto mietras se ejecuta el app
    
    @Autowired
    private CategoriaDao categoriaDao;
    
    @Override
    public List<Categoria> getCategorias(boolean activos) {
        var lista=categoriaDao.findAll();
        if(activos){//se deben eliminar todos los que no esten activos
            lista.removeIf(e -> !e.isActivo());
            
        }
        
        return lista;
    }
    
}
