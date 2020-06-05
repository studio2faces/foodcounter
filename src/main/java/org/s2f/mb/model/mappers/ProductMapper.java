package org.s2f.mb.model.mappers;

import org.s2f.mb.model.DTO.Product;
import com.google.gson.Gson;
import org.s2f.mb.model.entity.ProductEntity;

public class ProductMapper {

    Gson gson = new Gson();


    public Product mapperJsonToDto(String json) {
        return gson.fromJson(json, Product.class);
    }

    public ProductEntity mapperDtoToEntity(Product dto) {
        String json = gson.toJson(dto);
        return gson.fromJson(json, ProductEntity.class);
    }

    public Product mapperEntityToDto(ProductEntity entity) {
        String json = gson.toJson(entity);
        return gson.fromJson(json, Product.class);
    }

    public String mapperDtoToJson(Product dto) {
        return gson.toJson(dto);
    }

}
