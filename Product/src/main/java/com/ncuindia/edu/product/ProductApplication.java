package com.ncuindia.edu.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {

		// ProductDto dto = new ProductDto("lenovo", 100, 5);
		// ModelMapper mapper = new ModelMapper();
		// Product p = mapper.map(dto, Product.class);
		// int pid = 122;
		// p.setPid(pid);

		// Product newp = new Product(122, "lenovo", 100, 5);
		// ProductDto newdto = mapper.map(newp, ProductDto.class);

		// // send this dto to user;

		// // send to db
		SpringApplication.run(ProductApplication.class, args);
	}

}
