package com.apppassarourbano.AppPassaroUrbano.controller;

import com.apppassarourbano.AppPassaroUrbano.model.entity.Order;
import com.apppassarourbano.AppPassaroUrbano.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController extends AppRestAbstract<Long, Order, OrderService> {

}
