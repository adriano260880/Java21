package br.com.aal.java21.virtualtheads.controller;

import br.com.aal.java21.virtualtheads.service.ProductService;
import br.com.aal.java21.virtualtheads.service.ProductView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ProductView getProduct(@PathVariable String id) {
        return service.getProduct(id);
    }
}
