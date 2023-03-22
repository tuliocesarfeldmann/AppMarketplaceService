package com.apppassarourbano.AppPassaroUrbano.controller;

import com.apppassarourbano.AppPassaroUrbano.model.entity.AppEntityAbstract;
import com.apppassarourbano.AppPassaroUrbano.service.AppServiceAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class AppRestAbstract <PK extends Serializable, E extends AppEntityAbstract, S extends AppServiceAbstract<PK,E,?>> {
    @Autowired
    protected S service;

    @GetMapping
    public ResponseEntity<?> list(@RequestParam Map<String,String> searchParams) {
        return ResponseEntity.ok(service.list(searchParams));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable("id") PK id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody E entity){
        return ResponseEntity.ok(service.save(entity));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody E entity){
        return ResponseEntity.ok(service.update(entity));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") PK id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
