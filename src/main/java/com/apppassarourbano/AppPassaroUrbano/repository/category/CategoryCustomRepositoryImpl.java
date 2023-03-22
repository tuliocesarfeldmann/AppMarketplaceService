package com.apppassarourbano.AppPassaroUrbano.repository.category;

import com.apppassarourbano.AppPassaroUrbano.model.entity.Category;
import com.apppassarourbano.AppPassaroUrbano.model.entity.Offer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.Normalizer;
import java.util.List;
import java.util.Map;

public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Offer> findAllByParams(Map<String, String> params) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM category c ");
        sql.append("WHERE 1 = 1 ");

        for(Map.Entry<String,String> param: params.entrySet()){
            if(param.getKey().contains("_like")){
                sql.append("AND c.").append(param.getKey().split("_")[0]).append(" LIKE '%").append(Normalizer.normalize(param.getValue(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")).append("%' ");
            } else {
                sql.append("AND c.").append(param.getKey()).append(" = '").append(Normalizer.normalize(param.getValue(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")).append("' ");
            }
        }

        Query query = entityManager.createNativeQuery(sql.toString(), Category.class);

        return query.getResultList();
    }
}
