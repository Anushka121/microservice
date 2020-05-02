package org.cap.dao;

import org.cap.entities.Cart;

import java.util.List;

public interface ICartDao {

    List<Cart>fetchDetails(int userId);
    Cart save(Cart item);
    boolean delete(int userId);
}
