package org.sid.buillingservice.web;

import org.sid.buillingservice.entities.Bill;
import org.sid.buillingservice.repository.BuillRepository;
import org.sid.buillingservice.repository.ProductItemRepository;
import org.sid.buillingservice.service.CustomerRestClient;
import org.sid.buillingservice.service.ProductRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
    @Autowired
    private BuillRepository buillRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerRestClient customerRestClient;
    @Autowired
    private ProductRestClient productRestClient;

    @GetMapping("/fullBill/{id}")
    public Bill bill(Long id){
     Bill bill=buillRepository.findById(id).get();
     bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
     bill.getProductItems().forEach(pi->{
         pi.setProduct(productRestClient.findProductById(pi.getProductId()));
     });
     return bill;
    }

}
