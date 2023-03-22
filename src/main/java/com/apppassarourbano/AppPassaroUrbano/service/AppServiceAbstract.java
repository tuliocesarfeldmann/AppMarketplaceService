package com.apppassarourbano.AppPassaroUrbano.service;

import com.apppassarourbano.AppPassaroUrbano.model.entity.AppEntityAbstract;
import com.apppassarourbano.AppPassaroUrbano.repository.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AppServiceAbstract<PK extends Serializable, E extends AppEntityAbstract, DAO extends JpaRepository<E,PK> & CustomRepository> {
    @Autowired
    protected DAO dao;

    public List<?> list(Map<String,String> params){
        beforeList(params);
        return dao.findAllByParams(params);
    }

    public Optional<?> findById(PK id){
        return dao.findById(id);
    }

    public E save(E entity) {
        return dao.save(entity);
    }

    public E update(E entity){
        return dao.save(entity);
    }

    public void deleteById(PK id){
        dao.deleteById(id);
    }

    protected void beforeList(Map<String, String> params){}
}
