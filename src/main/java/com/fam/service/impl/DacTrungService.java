package com.fam.service.impl;

import com.fam.entity.DacTrung;
import com.fam.repository.IDacTrungRepository;
import com.fam.service.IDacTrungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DacTrungService implements IDacTrungService {
    @Autowired
    private IDacTrungRepository dacTrungRepository;

    @Override
    public Map<String, List<DacTrung>> getAllDacTrungs() {
        List<DacTrung> test = dacTrungRepository.findAll();
        return test.stream().collect(Collectors.groupingBy(DacTrung::getLoaiDacTrung));
    }
}
