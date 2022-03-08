package com.fam.service;

import com.fam.entity.DacTrung;

import java.util.List;
import java.util.Map;

public interface IDacTrungService {
    Map<String, List<DacTrung>> getAllDacTrungs();
}
