package com.altran.product_trial;

import com.altran.product_trial.application.ProductService;
import com.altran.product_trial.application.port.out.ProductRepositoryPort;
import com.altran.product_trial.domain.Product;
import com.altran.product_trial.domain.ProductMapper;
import com.altran.product_trial.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public void should_return_product_service_when_called() {
        assertNotNull(productService);
    }


    @Test
    public void should_throw_exception_when_product_is_null() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.createProduct(null));
        assertEquals("le produit ne doit pas être null", exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_product_name_is_null() {
        ProductDTO product = new ProductDTO();
        product.setId(1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.createProduct(product));
        assertEquals("le nom du produit doit être rempli", exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_product_name_is_empty() {
        ProductDTO product = new ProductDTO();
        product.setId(1);
        product.setName("");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.createProduct(product));
        assertEquals("le nom du produit doit être rempli", exception.getMessage());
    }

    @Test
    public void should_create_product_when_valid_data_is_provided() {
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
    public void should_throw_exception_when_product_name_is_blank() {
        ProductDTO product = new ProductDTO();
        product.setId(1);
        product.setName("    ");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.createProduct(product));
        assertEquals("le nom du produit doit être rempli", exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_price_is_negative() {
        ProductDTO product = new ProductDTO();
        product.setId(1);
        product.setName("My product");
        product.setPrice((double) -100);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.createProduct(product));
        assertEquals("le prix du produit doit être supérieur ou égal à zero", exception.getMessage());
    }

    @Test
    public void shoul_throw_exception_when_product_id_is_not_null() {
        ProductDTO product = new ProductDTO();
        product.setId(1);
        product.setName("My product");
        product.setPrice((double) -100);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.createProduct(product));
        assertEquals("L'Id du produit doit être null à la création", exception.getMessage());

    }

    @Test
    public void should_return_Id_of_saved_product_successfully() {
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
    public void get_all_products_should_return_an_empty_list_when_no_product_is_created() {
        assertEquals(0, productService.getAllProducts().size());
    }

    @Test
    public void get_all_products_should_return_list_of_products_created() {
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

    @Test
    public void get_product_by_id_should_throw_exception_when_product_id_is_null() {
        assertThrows(IllegalArgumentException.class,() -> productService.getProductById(null));
    }

    @Test
    public void get_product_by_id_should_throw_exception_when_no_product_found() {
        assertThrows(NoSuchElementException.class,() -> productService.getProductById(0));
    }

    @Test
    public void get_product_by_id_should_return_product_when_no_product_found() {
        Product productSaved = new Product();
        productSaved.setId(0);
        when(productRepositoryPort.findProductById(any(Integer.class))).thenReturn(Optional.of(productSaved));

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(0);
        when(productMapper.mapEntityToDTO(any(Product.class))).thenReturn(productDTO);


        assertEquals(productDTO, productService.getProductById(0));
    }

    @Test
    public void update_product_should_throw_exception_when_product_to_update_is_null() {
        assertThrows(IllegalArgumentException.class, () -> productService.updateProduct(null));
    }


    @Test
    public void update_product_should_throw_exception_when_product_id_to_update_is_null() {
        assertThrows(IllegalArgumentException.class, () -> productService.updateProduct(new ProductDTO()));
    }

    @Test
    public void update_product_should_throw_exception_when_product_id_to_update_is_not_found() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        when(productRepositoryPort.findProductById(any(Integer.class))).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> productService.updateProduct(productDTO));
    }

    @Test
    public void update_product_should_throw_exception_when_product_name_is_null() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        assertThrows(IllegalArgumentException.class, () -> productService.updateProduct(productDTO));
    }

    @Test
    public void update_product_should_throw_exception_when_product_name_is_empty() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setName("");
        assertThrows(IllegalArgumentException.class, () -> productService.updateProduct(productDTO));
    }

    @Test
    public void update_product_should_throw_exception_when_product_name_is_blank() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setName("    ");
        assertThrows(IllegalArgumentException.class, () -> productService.updateProduct(productDTO));
    }


    @Test
    public void update_product_should_return_product_updated_succefully() {
        ProductDTO productDtoToUpdate = new ProductDTO();
        productDtoToUpdate.setId(1);
        productDtoToUpdate.setName("My product Updated");

        Product oldProduct = new Product();
        oldProduct.setId(1);
        oldProduct.setName("My product");


        Product productToUpdate = new Product();
        productToUpdate.setId(1);
        productToUpdate.setName("My product Updated");

        Product updatedEntity = new Product();
        updatedEntity.setId(1);
        updatedEntity.setName("My product updated");

        ProductDTO updatedEntityDto = new ProductDTO();
        updatedEntityDto.setId(1);
        updatedEntityDto.setName("My product updated");

        when(productRepositoryPort.findProductById(productDtoToUpdate.getId())).thenReturn(Optional.of(oldProduct));
        when(productMapper.mapDTOToEntity(productDtoToUpdate)).thenReturn(productToUpdate);
        when(productRepositoryPort.save(productToUpdate)).thenReturn(updatedEntity);
        when(productMapper.mapEntityToDTO(updatedEntity)).thenReturn(updatedEntityDto);

        ProductDTO updatedProduct = productService.updateProduct(productDtoToUpdate);

        assertNotNull(updatedProduct);
        assertEquals(updatedEntityDto.getId(), updatedProduct.getId());
        assertEquals(updatedEntityDto.getName(), updatedProduct.getName());
    }

    @Test
    public void delete_product_should_throw_illegal_argument_exception_when_product_id_to_delete_is_null() {
        assertThrows(IllegalArgumentException.class, () -> productService.deleteProduct(null));
    }

    @Test
    public void delete_product_should_throw_no_such_element_exception_when_product_to_delete_not_found() {
        when(productRepositoryPort.findProductById(any(Integer.class))).thenThrow(NoSuchElementException.class);
        assertThrows(NoSuchElementException.class, () -> productService.deleteProduct(2));
    }

    @Test
    public void delete_product_should_run_successfully_when_product_found() {
        Product productToDelete = new Product();
        productToDelete.setId(1);
        when(productRepositoryPort.findProductById(any(Integer.class))).thenReturn(Optional.of(productToDelete));

        assertDoesNotThrow(() -> productService.deleteProduct(productToDelete.getId()));
    }

}
