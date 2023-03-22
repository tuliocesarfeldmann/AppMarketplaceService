package com.apppassarourbano.AppPassaroUrbano.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "IMAGES")
@Getter @Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "OFFER_ID")
    @JsonBackReference
    private Offer offer;

    @Column
    private String name;

    @Column
    private String type;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] url;
}
