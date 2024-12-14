package org.sid.buillingservice;

import org.sid.buillingservice.entities.Bill;
import org.sid.buillingservice.entities.ProductItem;
import org.sid.buillingservice.model.Customer;
import org.sid.buillingservice.model.Product;
import org.sid.buillingservice.repository.BuillRepository;
import org.sid.buillingservice.repository.ProductItemRepository;
import org.sid.buillingservice.service.CustomerRestClient;
import org.sid.buillingservice.service.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BuillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuillingServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(BuillRepository buillRepository,
							ProductItemRepository productItemRepository,
							CustomerRestClient customerRestClient,
							ProductRestClient productRestClient){
		return args -> {
			Collection<Product> products=productRestClient.allProducts().getContent();
			Long customerId=1L;
			Customer customer=customerRestClient.findCustomerById(customerId);
			if(customer==null) throw new RuntimeException("customer not found");
			Bill bill=new Bill();
			bill.setBillDate(new Date());
			bill.setCustomerId(customerId);
			Bill saveBill =buillRepository.save(bill);
			products.forEach(product -> {
				ProductItem productItem=new ProductItem();
				productItem.setBill(saveBill);
				productItem.setProductId(product.getId());
				productItem.setQuantity(1+new Random().nextInt(10));
				productItem.setPrice(product.getPrice());
				productItem.setDiscount(Math.random());
				productItemRepository.save(productItem);
			});
		};
	}

}
