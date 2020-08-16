package com.bharath.springcloud.controllers;

import com.bharath.springcloud.dto.Coupon;
import com.bharath.springcloud.model.Product;
import com.bharath.springcloud.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

	@Autowired
	private ProductRepo repo;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${couponService.url}")
	private String couponServiceURL;

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public Product create(@RequestBody Product product) {
		Coupon coupon = restTemplate.getForObject(couponServiceURL + product.getCouponCode(), Coupon.class);
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return repo.save(product);

	}

	@GetMapping
	public String getCoupon() {
		return "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<title>Page Title</title>\n" + "</head>\n" + "<body>\n"
				+ "\n" + "<h1>This is a success message</h1>\n" + "\n" + "</body>\n" + "</html>";

	}

}
