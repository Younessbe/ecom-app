package org.sid.buillingservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.buillingservice.model.Customer;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Bill {
    @jakarta.persistence.Id
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billDate;
    private Long customerId;
    @OneToMany(mappedBy = "bill")
    private List<ProductItem> productItems;
    @Transient
    private Customer customer;

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
