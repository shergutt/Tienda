/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda_V1.demo.controller;

import com.tienda_V1.demo.domain.Categoria;
import com.tienda_V1.demo.domain.Producto;
import com.tienda_V1.demo.sevice.CategoriaService;
import org.springframework.ui.Model;
import com.tienda_V1.demo.sevice.ProductoService;
import com.tienda_V1.demo.sevice.FirebaseStorageService;
import com.tienda_V1.demo.sevice.impl.FirebaseStorageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    
        @Autowired
    private FirebaseStorageService firebaseStorageService;
        
            @Autowired
    private CategoriaService categoriaService;
        
        

    @GetMapping("/listado")
    public String inicio(Model model) {
        var productos = productoService.getProductos(false);
        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("categorias", categorias); 
        return "/producto/listado";
    }

    @GetMapping("/nuevo")
    public String productoNuevo(Producto producto) {
        return "/producto/modifica";
    }



@PostMapping("/guardar")
public String categoriaGuardar(Categoria categoria,
        @RequestParam("imagenFile") MultipartFile imagenFile) {
    if (!imagenFile.isEmpty()) {
        categoria.setRutaImagen(
                firebaseStorageService.cargaImagen(
                        imagenFile,
                        "producto",
                        categoria.getIdCategoria()));
    }
    categoriaService.save(categoria);
    return "redirect:/categoria/listado";
}




    @GetMapping("/eliminar/{idProducto}")
    public String productoEliminar(Producto producto) {
        productoService.delete(producto);
        return "redirect:/producto/listado";
    }

@GetMapping("/modificar/{idProducto}")
public String productoModificar(Producto producto, Model model) {
    producto = productoService.getProducto(producto);
    var categorias = categoriaService.getCategorias(true); // Obtener todas las categorías activas
    model.addAttribute("producto", producto);
    model.addAttribute("categorias", categorias); // Agregar las categorías al modelo
    return "/producto/modifica";
}
}

