package com.apppassarourbano.AppPassaroUrbano.repository.cart;

import com.apppassarourbano.AppPassaroUrbano.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CartRepository extends JpaRepository<CartItem,Long>, JpaSpecificationExecutor<CartItem> {

}
