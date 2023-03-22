package com.apppassarourbano.AppPassaroUrbano.repository.order;

import com.apppassarourbano.AppPassaroUrbano.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderCustomRepository {

}
