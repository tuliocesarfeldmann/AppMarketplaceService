package com.apppassarourbano.AppPassaroUrbano.controller;

import com.apppassarourbano.AppPassaroUrbano.model.entity.Offer;
import com.apppassarourbano.AppPassaroUrbano.service.OfferService;
import com.apppassarourbano.AppPassaroUrbano.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("offer")
public class OfferController extends AppRestAbstract<Long, Offer, OfferService> {
    @Autowired
    OfferService offerService;

    @GetMapping("/my-offers")
    public ResponseEntity<?> myOffers(){
        Map<String,String> map = new HashMap<>();
        map.put("advertiser", String.valueOf(Utils.getCurrentUser().getId()));

        return ResponseEntity.ok(offerService.list(map));
    }
}
