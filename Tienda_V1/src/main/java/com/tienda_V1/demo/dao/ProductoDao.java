
package com.tienda_V1.demo.dao;


import com.tienda_V1.demo.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoDao extends JpaRepository<Producto, Long> {
    
     
    
}
