package com.apppassarourbano.AppPassaroUrbano.service;

import com.apppassarourbano.AppPassaroUrbano.model.entity.AppEntityAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AppServiceAbstract<PK extends Serializable, E extends AppEntityAbstract, DAO extends JpaRepository<E,PK> & JpaSpecificationExecutor<E>> {
    @Autowired
    protected DAO dao;

    public List<?> list(Map<String,String> params){
        beforeList(params);

        Specification<E> specification = Specification.where(null);

        for(Map.Entry<String,String> param: params.entrySet()){
            if(param.getKey().contains("_like")){
                specification = specification.and((root, query, cb) -> cb.like(root.get(param.getKey().split("_")[0]), "%" + Normalizer.normalize(param.getValue(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "") + "%"));
            } else if(param.getKey().contains(".")){
                specification = specification.and((root, query, cb) -> cb.equal(getPathFromMetamodel(root, param.getKey()), param.getValue()));
            } else {
                specification = specification.and((root, query, cb) -> cb.equal(root.get(param.getKey()), Normalizer.normalize(param.getValue(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")));
            }
        }

        return dao.findAll(specification);
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

    private Expression<E> getPathFromMetamodel(Root<E> root, String key) {
        String[] keyParts = key.split("\\.");
        javax.persistence.criteria.Path<E> path = root.get(keyParts[0]);
        for (int i = 1; i < keyParts.length; i++) {
            path = path.get(keyParts[i]);
        }
        return path;
    }
}
