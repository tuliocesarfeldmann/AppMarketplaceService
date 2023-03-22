package com.apppassarourbano.AppPassaroUrbano.repository.offer;

import com.apppassarourbano.AppPassaroUrbano.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>, OfferCustomRepository {

}
