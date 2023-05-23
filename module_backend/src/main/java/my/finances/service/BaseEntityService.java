package my.finances.service;

import my.finances.persistence.entity.BaseEntity;

import java.util.Collection;

public interface BaseEntityService<ENTITY extends BaseEntity> {
    ENTITY findById(Long id);
    Collection<ENTITY> findAll();
}
