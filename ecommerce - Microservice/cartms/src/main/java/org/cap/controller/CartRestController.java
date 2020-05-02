package org.cap.controller;

import org.cap.dto.AddCartDto;
import org.cap.dto.CartDetailsDto;
import org.cap.dto.ProductDto;
import org.cap.entities.Cart;
import org.cap.service.ICartService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/carts") 
public class CartRestController {
	
    private static final Logger Log = LoggerFactory.getLogger(CartRestController.class);

    @Value("${productservice.baseurl}")
    private String baseProductServiceUrl;
    
    public String getBaseProductServiceUrl(){
        return baseProductServiceUrl;
    }

    public void setBaseProductServiceUrl(String url){
        this.baseProductServiceUrl=url;
    }

    @Autowired
    private ICartService cartService;

    @Autowired
    private RestTemplate restTemplate;

   public CartDetailsDto cartDetailsDto(Cart item, ProductDto productDto) {
	   CartDetailsDto cartDetailsDto = new CartDetailsDto();
	   cartDetailsDto.setProductPrice(productDto.getProductPrice());
	   cartDetailsDto.setProductId(item.getProductId());
	   cartDetailsDto.setProductName(productDto.getProductName());
	   return cartDetailsDto; 
   }
   
   public ProductDto fetchProductById(String id) {
	   
	   String url = baseProductServiceUrl+"/get/"+id;
	   ProductDto dto = restTemplate.getForObject(url, ProductDto.class);
	   return dto;
   }
   
   @PostMapping("/add")
   public ResponseEntity<Cart>addCartItem(@RequestBody AddCartDto dto){
	   Cart cart = new Cart();
	   cart.setUserId(dto.getUserId());
	   cart.setProductId(dto.getProductId());
	   cart = cartService.add(cart);
	   ResponseEntity<Cart>response = new ResponseEntity<>(cart, HttpStatus.OK);
	   return response;
   }
   
   @GetMapping("/cartdetails/{userid}")
   public ResponseEntity<List<CartDetailsDto>>cartDetails(@PathVariable("userid")int userId){
	   List<Cart>list = cartService.fetchCartDetails(userId);
	   List<CartDetailsDto>dtoList = new ArrayList<>();
	   for(Cart cart : list) {
		   ProductDto productDto = fetchProductById(cart.getProductId());
		   CartDetailsDto cartDetailsDto = cartDetailsDto(cart, productDto);
		   dtoList.add(cartDetailsDto);
	   }
	   ResponseEntity<List<CartDetailsDto>>response = new ResponseEntity<>(dtoList, HttpStatus.OK);
	   return response;
   }
   
   @DeleteMapping("/delete/{userid}")
   public ResponseEntity<Boolean>deleteCartDetails(@PathVariable("userid")int userId){
	   boolean result=cartService.delete(userId);
	   
   ResponseEntity<Boolean> response=new ResponseEntity<>(result,HttpStatus.OK);
	   
   return response;
   }
   
   

}
