package org.s2f.mb.controllers;

import org.s2f.mb.model.dto.Product;
import org.s2f.mb.service.LocalUser;
import org.s2f.mb.service.db.DatabaseHandler;
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
    private final DatabaseHandler databaseHandler;

    @Autowired
    public FridgeController(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    @PostMapping(path = "/addProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product addProduct(@RequestBody Product product) throws IOException {
        // установила isCooked=false прямо в сервлете add, потому что сервлет готовки будет ставить true
        product.setCooked(false);
        log.debug("{} is created.", product);
        databaseHandler.addProduct(product);

        return product;
    }

    @GetMapping(path = "/show", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> showAll() {
        return databaseHandler.getAllProductsByUuid(LocalUser.getLoggedUser().getUuid());
    }
}