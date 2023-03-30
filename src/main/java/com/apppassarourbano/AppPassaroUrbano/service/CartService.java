package com.apppassarourbano.AppPassaroUrbano.service;

import com.apppassarourbano.AppPassaroUrbano.model.entity.CartItem;
import com.apppassarourbano.AppPassaroUrbano.repository.cart.CartRepository;
import com.apppassarourbano.AppPassaroUrbano.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartService extends AppServiceAbstract<Long, CartItem, CartRepository> {
    @Override
    public void beforeList(Map<String, String> params){
        params.put("user.id", Utils.getCurrentUser().getId().toString());
    }
}
