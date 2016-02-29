package com.myproject.controller.springrest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.dao.*;
import com.myproject.model.*;

@RestController
public class GetProductDetailsService {

	private GetProductDetailsDAO getProductDetailsDAO;

	public GetProductDetailsService(GetProductDetailsDAO getProductDetailsDAO) {
		this.getProductDetailsDAO = getProductDetailsDAO;
	}

	@RequestMapping("/getProduct")
	public @ResponseBody ProductDetails getProductMethod1(
			@RequestParam(value = "itemcode", defaultValue = "1") String itemcode) {
		return getProductDetailsDAO.getProductDetails(itemcode);
	}

	@RequestMapping("/getProduct/{itemcode}")
	public @ResponseBody ProductDetails getProductMethod2(
			@PathVariable(value = "itemcode") String itemcode) {
		return getProductDetailsDAO.getProductDetails(itemcode);
	}

}