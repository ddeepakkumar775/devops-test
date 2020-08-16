package com.bharath.springcloud.controllers;

import com.bharath.springcloud.model.Coupon;
import com.bharath.springcloud.repos.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/couponapi")
public class CouponRestController {

	@Autowired
	CouponRepo repo;

	@RequestMapping(value = "/coupons", method = RequestMethod.POST)
	public Coupon create(@RequestBody Coupon coupon) {

		return repo.save(coupon);

	}

	@RequestMapping(value = "/coupons/{code}", method = RequestMethod.GET)
	public Coupon getCoupon(@PathVariable("code") String code) {
		return repo.findByCode(code);

	}

	@GetMapping
	public String getCoupon() {
		return "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<title>Page Title</title>\n" + "</head>\n" + "<body>\n"
				+ "\n" + "<h1>This is a success message</h1>\n" + "\n" + "</body>\n" + "</html>";

	}
}
