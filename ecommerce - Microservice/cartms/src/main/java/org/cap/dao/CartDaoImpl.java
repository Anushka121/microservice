package org.cap.dao;

import org.cap.entities.Cart;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CartDaoImpl implements ICartDao {
    private Map<Integer, Cart> store = new HashMap<>();
    
  
	

	@Override
	
	public Cart save(Cart item) {
		int id=generateId();
		item.setId(id);
		store.put(id,item);
		return item;
	}

	
	@Override
	public List<Cart> fetchDetails(int userId) {
		Collection<Cart>cart = store.values();
		List<Cart>cartList= new ArrayList<Cart>();
		for(Cart cart1:cart) {
			if(cart1.getUserId()==userId) {
				cartList.add(cart1);
			}
		}
		return cartList;
		

}


	@Override
	public boolean delete(int userId) {
		Collection<Cart>cart = store.values();
		List<Cart>cartList= new ArrayList<Cart>();
		for(Cart cart1:cart) {
			if(cart1.getUserId()==userId) {
				cartList.remove(cart1);
			}
		}
		return true;
		
	}
	 
	private int generatedId;
	 int generateId(){
	      
			generatedId++;
			
	        return generatedId;
	    }

}