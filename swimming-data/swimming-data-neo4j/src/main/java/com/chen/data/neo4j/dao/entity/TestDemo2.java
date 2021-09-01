package com.chen.data.neo4j.dao.entity;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class TestDemo2 {

	@Id
	private long id;
	
}
