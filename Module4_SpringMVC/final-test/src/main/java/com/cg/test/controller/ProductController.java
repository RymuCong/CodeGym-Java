package com.cg.test.controller;

import com.cg.test.entity.Product;
import com.cg.test.repository.CategoryRepository;
import com.cg.test.repository.ProductRepository;
import com.cg.test.service.ProductService;
import com.cg.test.util.PriceFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    private final CategoryRepository categoryRepo;
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductService productService, CategoryRepository categoryRepo, ProductRepository productRepository) {
        this.productService = productService;
        this.categoryRepo = categoryRepo;
        this.productRepository = productRepository;
    }

    @GetMapping("")
    public String getAll(Model model, Pageable pageable) {

        Page<Product> products = productService.findAll(pageable);

        List <Product> productList = products.toList();

        String page = "product_list";

        model.addAttribute("formatter", PriceFormatter.class);
        model.addAttribute("categories",  categoryRepo.findAll());
        model.addAttribute("totalPage", products.getTotalPages());
        model.addAttribute("currentPage", products.getNumber());
        model.addAttribute("products", productList);
        model.addAttribute("page", page);
        return "home_index";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Product> tempProduct = Optional.ofNullable(productService.findById(id));
        if (tempProduct.isEmpty()) {
            System.out.println("Not found the id :" + id);
        } else
            productService.deleteById(id);
        return "redirect:/product";
    }

    @PostMapping("/deleteSelected")
    public String deleteSelected(@RequestBody List<Integer> productIds) {
        for (Integer id : productIds) {
            productService.deleteById(id);
        }
        return "redirect:/product";
    }

    @GetMapping("addForm")
    public String showFormForAdd (Model model)
    {
        Product theProduct = new Product();

        model.addAttribute("page","product-add");

        model.addAttribute("product", theProduct);

        model.addAttribute("categories",  categoryRepo.findAll());

        return "home_index";
    }

    @PostMapping("save")
    public String save(@ModelAttribute Product product)  {

        // Save the product
        productRepository.save(product);

        return "redirect:/product";
    }

    @GetMapping("edit/{id}")
    public String edit (@PathVariable Integer id, Model model)
    {
        Optional <Product> editProduct = Optional.ofNullable(productService.findById(id));

        if (editProduct.isEmpty())
            System.out.println("Not found product id: " + id);

        Product product = editProduct.get();

        model.addAttribute("page", "product-edit");

        model.addAttribute("product", product);

        model.addAttribute("categories",  categoryRepo.findAll());

        return "home_index";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(name = "productName", required = false) String productName,
                                 @RequestParam(name = "category", required = false) String category,
                                 @RequestParam(name = "price", required = false) String price,
                                 Model model, Pageable pageable) {

        String page = "product_list";

        long priceValue = -1;
        if (price != null && !price.isEmpty()) {
            try {
                priceValue = Long.parseLong(price);
            } catch (NumberFormatException e) {
                priceValue = Long.MAX_VALUE;
            }
        }

        int categoryId = -1;
        if (category != null && !category.isEmpty()) {
            try {
                categoryId = Integer.parseInt(category);
            } catch (NumberFormatException e) {
                categoryId = Integer.MAX_VALUE;
            }
        }

        Page<Product> searchResults = productService.searchProducts(productName, categoryId, priceValue, pageable);

        List <Product> productList = searchResults.toList();

        model.addAttribute("formatter", PriceFormatter.class);
        model.addAttribute("productName", productName);
        model.addAttribute("category", category);
        model.addAttribute("price", priceValue);
        model.addAttribute("categories",  categoryRepo.findAll());
        model.addAttribute("products", productList);
        model.addAttribute("totalPage", searchResults.getTotalPages());
        model.addAttribute("currentPage", searchResults.getNumber());
        model.addAttribute("page", page);
        return "home_index";
    }
}
