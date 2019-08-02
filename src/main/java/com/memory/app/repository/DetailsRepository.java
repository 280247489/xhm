package com.memory.app.repository;

import com.memory.entity.Details;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName DetailsRepository
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/29 11:25
 */
public interface DetailsRepository extends JpaRepository<Details,String> {
}
