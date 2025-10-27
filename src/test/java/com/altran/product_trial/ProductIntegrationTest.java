package com.altran.product_trial;

import com.altran.product_trial.domain.model.User;
import com.altran.product_trial.domain.port.in.UserService;
import com.altran.product_trial.infrastructure.config.JwtService;
import com.altran.product_trial.infrastructure.dto.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    @Test
    public void testCreateProduct() throws Exception {
        User user = userService.createUser(User.builder()
                .email("admin@email.com")
                .password("password")
                .username("user")
                .firstname("user").build());
        var userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("USER")
                .build();

        String token = jwtService.generateToken(userDetails);
        ProductDTO productDTO = ProductDTO.builder().name("My product").price(20.0).build();
        mockMvc.perform(post("/products")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isCreated());
    }
}
