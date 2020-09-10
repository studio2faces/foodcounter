package org.s2f.mb.controllers;

import lombok.extern.slf4j.Slf4j;
import org.s2f.mb.model.entity.Product;
import org.s2f.mb.service.LocalUser;

import org.s2f.mb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/fridge")
public class FridgeController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product addProduct(@RequestBody Product product) {
        // установила isCooked=false прямо в сервлете add, потому что сервлет готовки будет ставить true
        product.setCooked(false);
        product.setPriceByOneGramm(product.countPriceByOneGramm());
        product.setUuid(LocalUser.getLoggedUser().getUuid());
        log.debug("{} is created.", product);
        return productService.save(product);
    }

    @GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> showAll() {
        return productService.getAllByUuid(LocalUser.getLoggedUser().getUuid());
    }
}

