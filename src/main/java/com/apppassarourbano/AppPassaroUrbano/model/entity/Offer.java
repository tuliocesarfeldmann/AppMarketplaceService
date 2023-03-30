package com.apppassarourbano.AppPassaroUrbano.model.entity;

import com.apppassarourbano.AppPassaroUrbano.utils.Utils;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "OFFERS")
@Getter @Setter
public class Offer extends AppEntityAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column
    private String title;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "advertiser")
    private User advertiser;
    @Column
    private Double value;
    @Column
    private String details;
    @Column
    private String location;
    @Column
    private Boolean contraste;
    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Image> images;
    
    @PrePersist
    void prePersist(){
        this.advertiser = Utils.getCurrentUser();
    }

    @PreUpdate
    void preUpdate(){
        this.advertiser = Utils.getCurrentUser();
    }
}
