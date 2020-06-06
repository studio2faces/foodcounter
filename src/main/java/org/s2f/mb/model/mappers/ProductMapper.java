package org.s2f.mb.model.mappers;

import org.json.simple.JSONObject;
import org.s2f.mb.model.DTO.Product;
import com.google.gson.Gson;
import org.s2f.mb.model.entity.ProductEntity;

import javax.servlet.ServletRequest;
import java.util.Map;

public class ProductMapper {

    Gson gson = new Gson();


    public Product mapperJsonToDto(String json) {
        Product product = gson.fromJson(json, Product.class);
        product.setPriceFor1g();
      //  product.setCooked(false);
        return product;
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

    public JSONObject requestParamsToJSON(ServletRequest req) {
        JSONObject jsonObj = new JSONObject();
        Map<String, String[]> params = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            String v[] = entry.getValue();
            Object o = (v.length == 1) ? v[0] : v;
            jsonObj.put(entry.getKey(), o);
        }
        return jsonObj;
    }

}
