package com.memory.cms.repository;

import com.memory.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName CmsGoodsRepository
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/6/25 19:42
 */
public interface CmsGoodsRepository extends JpaRepository<Goods,String> {

    Goods findByIdAndGoodsIsShelf(String id, Integer isShelf);
}
