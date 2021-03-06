package com.rcyyd.bookdinner.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rcyyd.bookdinner.domain.Cart;
import com.rcyyd.bookdinner.domain.Order_Dish;
import com.rcyyd.bookdinner.persistence.CartDao;
import com.rcyyd.bookdinner.service.CartService;

@Service
@Transactional
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartDao cartDao;
	
	@Override
	public void addToCart(Cart cart) {
		cartDao.save(cart);
	}

	@Override
	public boolean deleteFromCart(Cart cart) {
		return cartDao.deleteByDoubleId(cart.getDishid(), cart.getUserid(), "dishid", "userid");
	}

	@Override
	public boolean changeCart(Cart cart) {
		cartDao.update(cart);
		return true;
	}

	@Override
	public List<Cart> getAllDishesByUserId(Integer userid) {
		return cartDao.findByKey(userid, "userid");
	}

	@Override
	public Cart getCartByKeys(int userid, int dishid) {
		return cartDao.findCartByKeys(userid, dishid);
	}
}
