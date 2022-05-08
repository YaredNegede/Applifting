package com.Applifting.Applifting.monitor;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface MonitorRepository extends PagingAndSortingRepository<Monitor,Long>{

}