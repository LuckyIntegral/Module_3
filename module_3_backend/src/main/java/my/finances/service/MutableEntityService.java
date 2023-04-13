package my.finances.service;

import my.finances.persistence.entity.BaseEntity;

public interface MutableEntityService<ENTITY extends BaseEntity> {
    void update(ENTITY entity);
    void delete(Long id);
}
