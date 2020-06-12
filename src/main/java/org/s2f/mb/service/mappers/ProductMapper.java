package org.s2f.mb.service.mappers;

import org.json.simple.JSONObject;
import org.s2f.mb.model.dto.Product;
import com.google.gson.Gson;
import org.s2f.mb.model.dto.User;

import javax.servlet.ServletRequest;
import java.util.Map;

public class ProductMapper {

    private Gson gson = new Gson();

    public Product mapperJsonToProduct(String json) {
        Product product = gson.fromJson(json, Product.class);
        return product;
    }

    public String mapperProductToJson(Product dto) {
        return gson.toJson(dto);
    }

    public User mapperJsonToUser(String json) {
        User user = gson.fromJson(json, User.class);
        return user;
    }

    public String mapperUsersUUIDToJson(User user) {
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
}
