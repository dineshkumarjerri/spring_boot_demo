package com.org.nace.manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.nace.manager.domain.NaceData;


@Repository
public interface NaceDataRepository extends JpaRepository<NaceData, Long>{
	
	List<NaceData> findByOrderId(Integer order);
   
}
