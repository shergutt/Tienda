
package com.tienda_V1.demo.sevice;

import com.tienda_V1.demo.domain.Categoria;
import java.util.List;


public interface CategoriaService {
    
    public List<Categoria> getCategorias(boolean activos);
    
}
