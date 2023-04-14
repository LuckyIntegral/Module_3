package my.finances.service;

import my.finances.persistence.entity.BaseEntity;

import java.util.Collection;
import java.util.Optional;

public interface BaseEntityService<ENTITY extends BaseEntity> {
    ENTITY findById(Long id);
    Collection<ENTITY> findAll();
}
