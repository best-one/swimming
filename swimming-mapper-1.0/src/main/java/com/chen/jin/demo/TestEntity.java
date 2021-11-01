package com.chen.jin.demo;

import io.mybatis.provider.Entity;

@Entity.Table("")
public class TestEntity {
    @Entity.Column(id=true)
    private Long id;
}
