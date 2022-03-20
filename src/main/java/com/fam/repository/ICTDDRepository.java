package com.fam.repository;

import com.fam.entity.CTDD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICTDDRepository extends JpaRepository<CTDD, Integer> {

}
