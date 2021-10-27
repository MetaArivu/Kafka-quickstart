package com.shoppping.cart.adapter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppping.cart.adapter.entity.ShoppingCart;
import com.shoppping.cart.domainlayer.service.ShoppingCartService;

@RestController
@RequestMapping("/api/v1")
public class ShoppingCartAPI {

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ShoppingCart> findByCustomerId(@PathVariable("id") String id){
		return new ResponseEntity<ShoppingCart>(shoppingCartService.findByCustomerId(id), HttpStatus.OK);
	}
}
