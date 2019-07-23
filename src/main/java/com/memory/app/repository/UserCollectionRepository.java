package com.memory.app.repository;

import com.memory.entity.UserCollection;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author INS6+
 * @date 2019/7/23 19:40
 */

public interface UserCollectionRepository extends JpaRepository<UserCollection,String> {

    UserCollection getUserCollectionByCollectionUserIdAndAttentionUserId(String collectionUserId,String attentionUserId);

}
