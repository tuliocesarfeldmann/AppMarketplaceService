package com.apppassarourbano.AppPassaroUrbano.model.entity;

import com.apppassarourbano.AppPassaroUrbano.utils.Utils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CART_ITEM")
@Getter @Setter
public class CartItem extends AppEntityAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @Column
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    void prePersist(){
        this.user = Utils.getCurrentUser();
    }
}
