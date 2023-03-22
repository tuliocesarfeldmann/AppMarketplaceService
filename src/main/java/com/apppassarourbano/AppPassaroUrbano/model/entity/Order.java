package com.apppassarourbano.AppPassaroUrbano.model.entity;

import com.apppassarourbano.AppPassaroUrbano.utils.Utils;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter @Setter
public class Order extends AppEntityAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String address;

    @Column
    private String number;

    @Column
    private String complement;

    @Column(name = "form_payment")
    private String formOfPayment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "value_total")
    private Double valueTotal;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderDetails> orderDetails;

    @PrePersist
    void prePersist(){
        this.user = Utils.getCurrentUser();
    }
}
