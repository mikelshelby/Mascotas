package com.lm2a.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lm2a.model.Mascota;

public interface MascotaRepository extends PagingAndSortingRepository <Mascota, Long> {

}
