package com.sg.inventory.repositories;


import com.sg.inventory.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jake
 */

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer>{ // Integer is the type of ID

}
