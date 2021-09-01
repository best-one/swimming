package com.chen.data.neo4j.dao.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.support.CypherdslConditionExecutor;
import org.springframework.stereotype.Repository;

import com.chen.data.neo4j.dao.entity.TestDemo;

@Repository
public interface TestRepository extends Neo4jRepository<TestDemo, Long>,CypherdslConditionExecutor<TestDemo>{

}
