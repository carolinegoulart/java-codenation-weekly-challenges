package br.com.codenation.service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	final double DISCOUNT = 0.2;

	private ProductRepository productRepository = new ProductRepositoryImpl();

	@Override
	public Double calculateOrderValue(List<OrderItem> items) {
		return items.stream()
			.map(orderItem -> (getProductPrice(orderItem.getProductId()) * orderItem.getQuantity()))
			.mapToDouble(Double::doubleValue)
			.sum();
	}

	@Override
	public Set<Product> findProductsById(List<Long> ids) {
		return ids.stream()
			.filter(this::checkIfIdExists)
			.map(this::getProduct)
			.collect(Collectors.toSet());
	}

	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
		return orders.stream()
			.map(this::calculateOrderValue)
			.mapToDouble(Double::doubleValue)
			.sum();
	}

	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {

		Map<Boolean, List<Product>> map = new HashMap<Boolean, List<Product>>();
		List<Product> productsOnSale = filterBy(product -> product.getIsSale(), productIds);
		List<Product> productsNotOnSale = filterBy(product -> !product.getIsSale(), productIds);

		map.put(true, productsOnSale);
		map.put(false, productsNotOnSale);

		return map;
	}

	public List<Product> filterBy(Predicate<Product> predicate, List<Long> productIds) {
		return findProductsByIdList(productIds).stream()
			.filter(predicate)
			.collect(Collectors.toList());
	}

	public Double getProductPrice(Long productId) {
		Product product = getProduct(productId);
		if (product.getIsSale()) {
			return product.getValue() * (1 - DISCOUNT);
		}
		return product.getValue();
	}

	public List<Product> findProductsByIdList(List<Long> ids) {
		return ids.stream()
			.filter(this::checkIfIdExists)
			.map(this::getProduct)
			.collect(Collectors.toList());
	}

	public Product getProduct(Long id) {
		if (productRepository.findById(id).isPresent()) {
			return productRepository.findById(id).get();
		}
		return null;
	}

	public boolean checkIfIdExists(Long id) {
		return productRepository.findById(id).isPresent();
	}

}