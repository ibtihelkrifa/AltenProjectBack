package com.altran.product_trial.application;

import com.altran.product_trial.application.port.in.ProductServiceInterface;
import com.altran.product_trial.application.port.out.ProductRepositoryPort;
import com.altran.product_trial.domain.Product;
import com.altran.product_trial.domain.ProductMapper;
import com.altran.product_trial.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService implements ProductServiceInterface {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductRepositoryPort productRepositoryPort;

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepositoryPort.findAll();
        return products.stream().map(product -> productMapper.mapEntityToDTO(product)).toList();
    }

    public Integer createProduct(ProductDTO product) {

        if (product == null) {
            throw new IllegalArgumentException("le produit ne doit pas être null");
        }

        if (product.getId() != null) {
            throw new IllegalArgumentException("L'Id du produit doit être null à la création");
        }

        if (product.getName() == null || product.getName().isEmpty() || product.getName().isBlank()) {
            throw new IllegalArgumentException("le nom du produit doit être rempli");
        }
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("le prix du produit doit être supérieur ou égal à zero");
        }
        Product createdProduct = productRepositoryPort.save(productMapper.mapDTOToEntity(product));
        return createdProduct.getId();
    }

    public ProductDTO getProductById(Integer productId) {

        if (productId == null) {
            throw new IllegalArgumentException("productId ne doit pas être null");
        }
        Product product = productRepositoryPort.findProductById(productId)
                .orElseThrow(() -> new NoSuchElementException("Aucun produit trouvé avec l'id " + productId));
        return productMapper.mapEntityToDTO(product);
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {

        if (productDTO == null) {
            throw new IllegalArgumentException("le produit à modifier ne doit pas être null");
        }
        if (productDTO.getId() == null) {
            throw new IllegalArgumentException("l'id du produit à modifier ne doit pas être null");
        }

        if (productDTO.getName() == null || productDTO.getName().isEmpty() || productDTO.getName().isBlank()) {
            throw new IllegalArgumentException("l'id du produit à modifier ne doit pas être null");
        }

        productRepositoryPort.findProductById(productDTO.getId())
                .orElseThrow(() -> new NoSuchElementException("aucun produit trouvé avec l'id " + productDTO.getId()));


        Product productUpdated = productRepositoryPort.save(productMapper.mapDTOToEntity(productDTO));

        return productMapper.mapEntityToDTO(productUpdated);
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
