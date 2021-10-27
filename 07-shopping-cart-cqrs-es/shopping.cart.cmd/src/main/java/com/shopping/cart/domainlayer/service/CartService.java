package com.shopping.cart.domainlayer.service;

import com.shopping.cart.command.AddItemCommand;
import com.shopping.cart.command.CheckOutCommand;
import com.shopping.cart.command.ClearCartCommand;
import com.shopping.cart.command.RemoveItemCommand;
import com.shopping.cart.exception.ServiceException;

public interface CartService {

	public boolean addItem(AddItemCommand addItem) throws ServiceException;

	public boolean removeItem(RemoveItemCommand addItem) throws ServiceException;

	public boolean checkOut(CheckOutCommand checkOut) throws ServiceException;

	public boolean clearCart(ClearCartCommand clearCart) throws ServiceException;

}
