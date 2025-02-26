package ee.ksaveri.veebipood.controller;


import ee.ksaveri.veebipood.entity.Product;
import ee.ksaveri.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    // localhost:5074/products
    @GetMapping("products")
    public List<Product> getProducts() {
        return productRepository.findAll(); // SELECT * FROM  extends JpaRepository <Repository>
    }

    @PostMapping("products") // POSTMAN rakendus
    public List<Product> addProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new RuntimeException("ERROR_CANNOT_ADD_WITH_ID");
        }
        if (product.getPrice() <== 0){
            
        }
        productRepository.save(product); // INSERT INTO products
        return productRepository.findAll();
    }

    //DELETE localhost:5074/products/1
    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    @PutMapping("products")
    public List<Product> editProduct(@RequestBody Product product) {
        if (product.getId() == null) {
            throw new RuntimeException("ERROR_CANNOT_EDIT_WITHOUT_ID");
        }
        productRepository.save(product);
        return productRepository.findAll();
    }

    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @PatchMapping("products")
    public List<Product> editProductValue(@RequestParam Long id, String field, String value) {
        if (id == null) {
            throw new RuntimeException("ERROR_CANNOT_EDIT_WITHOUT_ID");
        }
        Product product = productRepository.findById(id).orElseThrow();
        switch (field) {
                case "name" -> product.setName(value);
                case "price" -> product.setPrice(Double.parseDouble(value));
                case "image" -> product.setImage(value);
                case "active" -> product.setActive(Boolean.parseBoolean(value));
            }
            if (field.equals("name")){
                product.setName(value);
            } else if (field.equals("price")){
                product.setPrice(Double.parseDouble(value));
            } else if (field.equals("image")){
                product.setImage(value);
            } else if (field.equals("active")){
                product.setActive(Boolean.parseBoolean(value));
            }
            productRepository.save(product);
            return productRepository.findAll();
        }

}

// 1xx --> informatiivsed - meie ei kasuta
// 2xx --> edukad 200 201(created)
// 3xx --> Suunamine - meie ei kasuta
// 4xx --> paringu tegija veaga (front-end viga). client error
//      400 --> uldine viga
//      401, 403 --> autentimisega seotud vead
//      402 --> maksetega seotud vead
//      404 --> api endpoint on vale
//      405 --> Method not allowed
//      415 --> sisu tuup on vale
// 5xx --> back-end viga. 500