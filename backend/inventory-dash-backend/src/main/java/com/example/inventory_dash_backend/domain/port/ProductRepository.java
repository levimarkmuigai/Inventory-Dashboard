package com.example.inventory_dash_backend.domain.port;

import com.example.inventory_dash_backend.domain.model.Product;
import com.example.inventory_dash_backend.domain.model.ProductId;

import java.util.Optional;
import java.util.List;

/**
 * Port interface for accessing  and managing products
 * ina a persistence store.
 * Hides details of storage to the domain
 */
public interface ProductRepository {
    /**
     * Retrives a product by its unique identifier
     *
     * @param id the prodcut's id
     * @return an Optional containing the product
     * if found, otherwise empty
     **/
   Optional<Product> findById(ProductId id); 

   /**
    * Retrieves a list of all products
    *
    * @return a list of all products, or an 
    * empty list if non exist
    **/
   List<Product> findAll();
   /**
    * Saves a new product or updates an existing one
    *
    * @param product to persist
    **/
   void save(Product product);

   /**
    * Deletes a product by it unique identifier
    *
    * @param id the product's identity
    **/
   void delete(ProductId id);
}
