package com.sg.inventory.repositories;

import com.sg.inventory.entities.Product;
import com.sg.inventory.entities.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jake
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{ // Integer is the type of ID
    List<Product> findByStore(Store store);
}
