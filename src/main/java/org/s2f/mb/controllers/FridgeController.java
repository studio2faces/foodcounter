package org.s2f.mb.controllers;


import org.s2f.mb.model.entity.Product;
import org.s2f.mb.service.LocalUser;

import org.s2f.mb.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/fridge")
public class FridgeController {
    private static final Logger log = LoggerFactory.getLogger(FridgeController.class);

    @Autowired
    public ProductService productService;

    @PostMapping(path = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product addProduct(@RequestBody Product product) throws IOException {
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

