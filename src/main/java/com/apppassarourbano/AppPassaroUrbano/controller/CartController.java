package com.apppassarourbano.AppPassaroUrbano.controller;

import com.apppassarourbano.AppPassaroUrbano.model.entity.CartItem;
import com.apppassarourbano.AppPassaroUrbano.service.CartService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart")
public class CartController extends AppRestAbstract<Long, CartItem, CartService> {

}
