package my.finances.persistence.repository;

import my.finances.persistence.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseEntityRepository<ENTITY extends BaseEntity> extends JpaRepository<ENTITY, Long> {
}
