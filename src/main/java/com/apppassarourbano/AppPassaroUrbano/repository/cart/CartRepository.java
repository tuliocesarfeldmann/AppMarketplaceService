package com.apppassarourbano.AppPassaroUrbano.repository.cart;

import com.apppassarourbano.AppPassaroUrbano.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartItem,Long>, CartCustomRepository {

}
