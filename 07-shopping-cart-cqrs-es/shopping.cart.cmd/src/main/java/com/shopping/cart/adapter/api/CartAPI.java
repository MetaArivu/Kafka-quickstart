package com.shopping.cart.adapter.api;

import static com.shopping.cart.constant.APIConstant.API_VERSION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.command.AddItemCommand;
import com.shopping.cart.command.CheckOutCommand;
import com.shopping.cart.command.ClearCartCommand;
import com.shopping.cart.command.RemoveItemCommand;
import com.shopping.cart.domainlayer.service.CartService;
import com.shopping.cart.event.ClearCartEvent;
import com.shopping.cart.exception.ServiceException;

@RestController
@RequestMapping(API_VERSION)
public class CartAPI {

	@Autowired
	private CartService cartService;

	@PostMapping("/add")
	public ResponseEntity addItem(@RequestBody AddItemCommand addItem) throws ServiceException {
		cartService.addItem(addItem);
		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping("/remove")
	public ResponseEntity addItem(@RequestBody RemoveItemCommand removeItem) throws ServiceException {
		cartService.removeItem(removeItem);
		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping("/checkout")
	public ResponseEntity addItem(@RequestBody CheckOutCommand checkOut) throws ServiceException {
		cartService.checkOut(checkOut);
		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping("/clear")
	public ResponseEntity addItem(@RequestBody ClearCartCommand clearCart) throws ServiceException {
		cartService.clearCart(clearCart);
		return new ResponseEntity(HttpStatus.OK);
	}

}
