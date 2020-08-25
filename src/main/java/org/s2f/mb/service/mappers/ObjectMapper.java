package org.s2f.mb.service.mappers;

import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.s2f.mb.model.dto.Product;
import com.google.gson.Gson;
import org.s2f.mb.model.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Component
public class ObjectMapper {
   /* private static final Logger log = LoggerFactory.getLogger(ObjectMapper.class);
    private Gson gson = new Gson();

    public Product jsonToProduct(String json) {
        return gson.fromJson(json, Product.class);
    }

    public Product requestToProduct(ServletRequest request) {
        Product product = null;
        JSONObject jsonObject = requestParamsToJSON(request);
        log.info("Get JSON object: {}", jsonObject.toJSONString());
        product = jsonToProduct(jsonObject.toJSONString());
        return product;
    }

    public User requestToUser(HttpServletRequest request) {
        User user = null;
        JSONObject jsonObject = requestParamsToJSON(request);
        user = jsonToUser(jsonObject.toJSONString());
        return user;
    }

    public String productToJson(Product dto) {
        gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(dto);
    }

    public User jsonToUser(String json) {
        return gson.fromJson(json, User.class);
    }

    public String userUuidToJson(User user) {
        gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(user.getUuid());
    }

    public JSONObject requestParamsToJSON(ServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Map<String, String[]> params = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            String[] values = entry.getValue();
            Object o = (values.length == 1) ? values[0] : values;
            jsonObject.put(entry.getKey(), o);
        }
        return jsonObject;
    }

    public String getJsonDataFromList(List<Product> products) {
        return gson.toJson(products);
    }*/
}