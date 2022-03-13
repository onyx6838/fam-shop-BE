package com.fam.repository;

import com.fam.entity.DacTrung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDacTrungRepository extends JpaRepository<DacTrung, Integer> {

}
