package org.sid.buillingservice.service;

import org.sid.buillingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductRestClient {
    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable Long id);
    @GetMapping("/products")
    PagedModel<Product> allProducts();
}
