package com.bridgelabz.fundoo.repositoryimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("rawtypes")
@Repository
public class RedisUserRepository {

	@Autowired
    private HashOperations hashOperations;
	
	@Autowired
	private RedisTemplate redisTemplate;

    public RedisUserRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

}