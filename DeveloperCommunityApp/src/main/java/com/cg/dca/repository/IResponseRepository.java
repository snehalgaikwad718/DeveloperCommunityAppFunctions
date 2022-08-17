package com.cg.dca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.dca.entity.Response;

import org.springframework.stereotype.*;

@Repository
public interface IResponseRepository extends JpaRepository<Response, Long>{

}
