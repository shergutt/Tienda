
package com.tienda_V1.demo.sevice.impl;

import com.tienda_V1.demo.dao.CategoriaDao;
import com.tienda_V1.demo.domain.Categoria;
import com.tienda_V1.demo.sevice.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceimpl implements CategoriaService {

    @Autowired
    private CategoriaDao categoriaDao;
    
    @Override
    public List<Categoria> getCategorias(boolean activos) {
       
        var lista = categoriaDao.findAll();
        
        if(activos == true){
            
            lista.removeIf(e -> !e.isActivo());
            
        }
        
        return lista;
        
    }
    
}
