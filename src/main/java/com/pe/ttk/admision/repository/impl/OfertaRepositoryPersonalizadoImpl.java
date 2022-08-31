package com.pe.ttk.admision.repository.impl;

import com.pe.ttk.admision.dto.entity.admision.OfertaEntity;
import com.pe.ttk.admision.repository.OfertaRepositoryPersonalizado;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OfertaRepositoryPersonalizadoImpl implements OfertaRepositoryPersonalizado {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OfertaEntity> listarOfertas(Pageable pageable, String titulo, List estado, String fechaPublicacion, List creador, Integer estadoActivo) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<OfertaEntity> query = cb.createQuery(OfertaEntity.class);
        Root<OfertaEntity> t = query.from(OfertaEntity.class);
        List<Predicate> condiciones = new ArrayList<>();

        if(!titulo.isBlank()){
            condiciones.add(cb.like(t.get("titulo"), "%"+titulo+"%"));
        }
        if(!fechaPublicacion.isBlank()){
            condiciones.add(cb.like(cb.function("date_format", String.class, t.get("fechaPublicacion"),
                    cb.literal("%d/%m/%Y")), "%"+fechaPublicacion+"%"));
        }
        if(!estado.isEmpty()){
            condiciones.add(cb.and(t.get("estadoOferta").get("id").in(estado)));
        }

        query.select(t)
                .where(cb.equal(t.get("estado"), estadoActivo),
                        cb.and(condiciones.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<OfertaEntity> listarOfertasLanding(Pageable pageable, Long estado, Integer estadoActivo) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<OfertaEntity> query = cb.createQuery(OfertaEntity.class);
        Root<OfertaEntity> t = query.from(OfertaEntity.class);

        query.select(t)
                .where(cb.equal(t.get("estadoOferta").get("id"), estado),
                        cb.equal(t.get("estado"), estadoActivo));

        return entityManager.createQuery(query).getResultList();
    }
}
