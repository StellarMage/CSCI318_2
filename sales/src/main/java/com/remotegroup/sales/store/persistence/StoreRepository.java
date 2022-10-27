package com.remotegroup.sales.store.persistence;

import com.remotegroup.sales.store.domain.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long>{

}
