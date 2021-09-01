package com.chen.data.neo4j.dao.entity;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class TestDemo {

	@Id
	private long id;
	
}
