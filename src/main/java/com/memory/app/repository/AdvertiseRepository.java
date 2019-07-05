package com.memory.app.repository;

import com.memory.entity.Advertise;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName AdvertiseRepository
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/6/29 19:10
 */
public interface AdvertiseRepository extends JpaRepository<Advertise,String> {
    Advertise findByAdvertiseIsOnline(Integer online);
}
