package com.sg.inventory.repositories;

import com.sg.inventory.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jake
 */

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer>{ // Integer is the type of ID

}
