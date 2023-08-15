
package com.tienda_V1.demo.dao;


import com.tienda_V1.demo.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductoDao extends JpaRepository<Producto, Long> {
    
     public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);
    
 //Ejemplo de método utilizando Consultas con JPQL
    @Query(value="SELECT a FROM Producto a where a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.descripcion ASC")
    public List<Producto> consultaJPQL(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup);
//Ejemplo de método utilizando Consultas con SQL nativo

    @Query(nativeQuery = true,
            value = "SELECT * FROM producto where producto.precio BETWEEN :precioInf AND :precioSup ORDER BY producto.descripcion ASC")
    public List<Producto> consultaNativo(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup); 


}