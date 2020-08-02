package org.s2f.mb.service.mappers;

import org.json.simple.JSONArray;
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
    private static final Logger log = LoggerFactory.getLogger(ObjectMapper.class);
    private Gson gson = new Gson();

    public Product jsonToProduct(String json) {
        Product product = gson.fromJson(json, Product.class);
        return product;
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
        return gson.toJson(dto);
    }

    public User jsonToUser(String json) {
        User user = gson.fromJson(json, User.class);
        return user;
    }

    public String userUuidToJson(User user) {
        return gson.toJson(user.getUuid());
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

    public JSONArray getJsonArrayFromList(List<Product> products) {
        JSONArray jsonArray = new JSONArray();
        for (Product p : products) {
            jsonArray.add(productToJson(p));
        }

        return jsonArray;
    }
}