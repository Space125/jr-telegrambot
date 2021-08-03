package com.github.space125.jrtb.repository;

import com.github.space125.jrtb.repository.entity.GroupSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link Repository} for {@link GroupSub} entity.
 *
 * @author Ivan Kurilov on 02.08.2021
 */
@Repository
public interface GroupSubRepository extends JpaRepository<GroupSub, Integer> {
}
