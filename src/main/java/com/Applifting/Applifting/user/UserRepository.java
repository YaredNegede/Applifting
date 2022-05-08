package com.Applifting.Applifting.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserRepository  extends PagingAndSortingRepository<User,Long> {
}
