package com.altran.product_trial;

import com.altran.product_trial.application.port.out.ProductRepositoryPort;
import com.altran.product_trial.domain.ProductMapper;
import com.altran.product_trial.dto.ProductDTO;
import com.altran.product_trial.domain.Product;
import com.altran.product_trial.application.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    public void shouldReturnProductServiceWhenCalled() {
        assertNotNull(productService);
    }


    @Test
    public void shouldThrowExceptionWhenProductIsNull() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.createProduct(null));
        assertEquals("le produit ne doit pas être null", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenProductNameIsNull() {
        ProductDTO product = new ProductDTO();
        product.setId(1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.createProduct(product));
        assertEquals("le nom du produit doit être rempli", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenProductNameIsEmpty() {
        ProductDTO product = new ProductDTO();
        product.setId(1);
        product.setName("");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.createProduct(product));
        assertEquals("le nom du produit doit être rempli", exception.getMessage());
    }

    @Test
    public void shouldCreateProductWheValidDataIsProvided() {
        ProductDTO product = new ProductDTO();
        product.setId(1);
        product.setName("My product");

        Product savedProduct = new Product();
        savedProduct.setId(1);
        when(productMapper.mapDTOToEntity(any(ProductDTO.class))).thenReturn(savedProduct);
        when(productRepositoryPort.save(any(Product.class))).thenReturn(savedProduct);


        Integer productId = productService.createProduct(product);
        assertNotNull(productId);
    }

    @Test
    public void shouldThrowExceptionWhenProductNameIsBlank() {
        ProductDTO product = new ProductDTO();
        product.setId(1);
        product.setName("    ");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.createProduct(product));
        assertEquals("le nom du produit doit être rempli", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenPriceIsNegative() {
        ProductDTO product = new ProductDTO();
        product.setId(1);
        product.setName("My product");
        product.setPrice((double) -100);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.createProduct(product));
        assertEquals("le prix du produit doit être supérieur ou égal à zero", exception.getMessage());
    }


    @Test
    public void shouldReturnIdOfSavedProductSuccessfully() {
        ProductDTO product = new ProductDTO();
        product.setId(1);
        product.setName("My product");
        product.setPrice(99.99);


        Product savedProduct = new Product();
        savedProduct.setId(1);
        when(productMapper.mapDTOToEntity(any(ProductDTO.class))).thenReturn(savedProduct);
        when(productRepositoryPort.save(any(Product.class))).thenReturn(savedProduct);

        Integer productId = productService.createProduct(product);

        assertEquals(savedProduct.getId(), productId);
    }

    @Test
    public void getAllProductsShouldReturnAnEmptyListWhenNoProductIsCreated() {
        assertEquals(0, productService.getAllProducts().size());
    }

    @Test
    public void getAllProductsShouldReturnListOfProductsCreated() {
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("product1");
        Product product2 = new Product();
        product2.setId(2);
        product2.setName("product2");
        when(productRepositoryPort.findAll()).thenReturn(List.of(product1, product2));

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setId(1);
        productDTO1.setName("product1");

        when(productMapper.mapEntityToDTO(product1)).thenReturn(productDTO1);

        ProductDTO productDTO2 = new ProductDTO();
        productDTO2.setId(2);
        productDTO2.setName("product2");

        when(productMapper.mapEntityToDTO(product2)).thenReturn(productDTO2);

        List<ProductDTO> result = productService.getAllProducts();
        assertEquals(2, result.size());
        assertTrue(result.containsAll(List.of(productDTO1, productDTO2)));
    }
}
