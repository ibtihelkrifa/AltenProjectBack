package com.altran.product_trial.domain.service;

import com.altran.product_trial.domain.model.Product;
import com.altran.product_trial.domain.port.in.ProductService;
import com.altran.product_trial.domain.port.out.ProductRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepositoryPort productRepositoryPort;

    public List<Product> getAllProducts() {
        return productRepositoryPort.findAll();
    }

    public Integer createProduct(Product product) {

        if (product == null) {
            throw new IllegalArgumentException("le produit ne doit pas être null");
        }

        if (product.getId() != null) {
            throw new IllegalArgumentException("L'Id du produit doit être null à la création");
        }

        if (product.getName() == null || product.getName().isEmpty() || product.getName().isBlank()) {
            throw new IllegalArgumentException("le nom du produit doit être rempli");
        }
        if (product.getPrice() == null || product.getPrice() < 0) {
            throw new IllegalArgumentException("le prix du produit doit être supérieur ou égal à zero");
        }
        Product createdProduct = productRepositoryPort.save(product);
        return createdProduct.getId();
    }

    public Product getProductById(Integer productId) {

        if (productId == null) {
            throw new IllegalArgumentException("productId ne doit pas être null");
        }
        com.altran.product_trial.domain.model.Product product = productRepositoryPort.findProductById(productId)
                .orElseThrow(() -> new NoSuchElementException("Aucun produit trouvé avec l'id " + productId));
        return product;
    }

    public Product updateProduct(Product product) {

        if (product == null) {
            throw new IllegalArgumentException("le produit à modifier ne doit pas être null");
        }
        if (product.getId() == null) {
            throw new IllegalArgumentException("l'id du produit à modifier ne doit pas être null");
        }

        if (product.getName() == null || product.getName().isEmpty() || product.getName().isBlank()) {
            throw new IllegalArgumentException("l'id du produit à modifier ne doit pas être null");
        }

        productRepositoryPort.findProductById(product.getId())
                .orElseThrow(() -> new NoSuchElementException("aucun produit trouvé avec l'id " + product.getId()));


        com.altran.product_trial.domain.model.Product productUpdated = productRepositoryPort.save(product);

        return productUpdated;
    }

    public void deleteProduct(Integer productId) {
        if (productId == null) {
            throw new IllegalArgumentException("l'Id du produit à supprimer ne peut pas être null");
        }
        productRepositoryPort.findProductById(productId)
                .orElseThrow(() -> new NoSuchElementException("Aucun produit trouvé avec id " + productId));
        productRepositoryPort.deleteProduct(productId);
    }

}
