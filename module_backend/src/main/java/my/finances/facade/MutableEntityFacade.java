package my.finances.facade;

import my.finances.persistence.entity.BaseEntity;

public interface MutableEntityFacade<ENTITY extends BaseEntity> {
    void update(ENTITY entity, long id);
    void delete(long id);
}
