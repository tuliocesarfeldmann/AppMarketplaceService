package com.apppassarourbano.AppPassaroUrbano.service;

import com.apppassarourbano.AppPassaroUrbano.model.entity.Order;
import com.apppassarourbano.AppPassaroUrbano.repository.order.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends AppServiceAbstract<Long, Order, OrderRepository> {
}
