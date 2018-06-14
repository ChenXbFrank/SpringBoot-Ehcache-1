package com.roncoo.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roncoo.example.bean.Info;

public interface InfoDao extends JpaRepository<Info, Integer>{

}
