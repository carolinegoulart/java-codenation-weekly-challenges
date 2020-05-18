package br.com.codenation.service;

import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

import static java.util.stream.Collectors.groupingBy;

public class OrderServiceImpl implements OrderService {

	final static double DISCOUNT = 0.2;

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
		List<Product> products = productIds.stream()
				.filter(this::checkIfIdExists)
				.map(this::getProduct)
				.collect(Collectors.toList());
		return products.stream().collect(groupingBy(Product::getIsSale));
	}

	public Double getProductPrice(Long productId) {
		Product product = getProduct(productId);
		if (product.getIsSale()) {
			return product.getValue() * (1 - DISCOUNT);
		}
		return product.getValue();
	}

	public Product getProduct(Long id) {
		Optional<Product> product = productRepository.findById(id);
		return product.orElse(null);
	}

	public boolean checkIfIdExists(Long id) {
		return productRepository.findById(id).isPresent();
	}

}
