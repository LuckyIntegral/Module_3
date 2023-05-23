package my.finances.service;

import my.finances.persistence.entity.BaseEntity;

public interface MutableEntityService<ENTITY extends BaseEntity> extends BaseEntityService<ENTITY> {
    void update(ENTITY entity, Long id);
    void delete(Long id);
}
