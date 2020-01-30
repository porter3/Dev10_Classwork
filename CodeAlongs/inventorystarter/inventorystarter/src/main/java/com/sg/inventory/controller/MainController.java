package com.sg.inventory.controller;

import com.sg.inventory.entities.Product;
import com.sg.inventory.entities.Store;
import com.sg.inventory.entities.Supplier;
import com.sg.inventory.repositories.ProductRepository;
import com.sg.inventory.repositories.StoreRepository;
import com.sg.inventory.repositories.SupplierRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author kylerudy
 */
@Controller
public class MainController {
    
    @Autowired
    StoreRepository stores;
    
    @Autowired
    ProductRepository products;
    
    @Autowired
    SupplierRepository suppliers;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("stores", stores.findAll()); // gets all Stores and adds them to model
        return "index"; // renders index page
    }
    
    @PostMapping("/addStore")
    public String addStore(Store store) {
        stores.save(store); // add store to DB. Can be done this way because 'store' is brought in as a whole object from the form
        return "redirect:/"; // render the index page
    }
    
    @GetMapping("/viewInventory")
    public String viewInventory(Integer id, Model model) {
        // create new Store to view the inventory for
        Store store = stores.findById(id).orElse(null);
        List<Product> productList = products.findByStore(store);
        model.addAttribute("store", store);
        model.addAttribute("products", productList);
        
        return "inventory";
    }
    
    @PostMapping("/addProduct")
    public String addProduct(Product product, HttpServletRequest request) {
        // get the store for the product
        int storeId = Integer.parseInt(request.getParameter("storeId"));
        // find the Store object (wrapped in 'Optional'), call orElse(null) to get the object or return null
        Store store = stores.findById(storeId).orElse(null);
        product.setStore(store);
        
        products.save(product);

        return "redirect:/viewInventory?id=" + storeId; // redirect to viewInventory page for that store
    }
    
    @GetMapping("/deleteProduct")
    public String deleteProduct(Integer id, Integer storeId) {
        products.deleteById(id);
        
        return "redirect:/viewInventory?id=" + storeId; // render the viewInventory page for the specific store
    }
    
    @GetMapping("/product")
    public String displayProduct(Integer id, Model model) {
        // get a product to display
        Product product = products.findById(id).orElse(null);
        // get a list of the product's suppliers
        List<Supplier> supplierList = suppliers.findAll();
                
        model.addAttribute("product", product);
        model.addAttribute("suppliers", suppliers);
        
        return "product"; // render product details page
    }
    
    @PostMapping("/addSupplier")
    public String addSupplier(Supplier supplier, HttpServletRequest request) {
        int productId = Integer.parseInt(request.getParameter("productId"));
        // get the product to add a Supplier to
        Product product = products.findById(productId).orElse(null);
        
        // add supplier to the DB and return one that's presumably populated with an ID
        supplier = suppliers.save(supplier);
        
        // add the supplier to the product's list of suppliers
        product.getSuppliers().add(supplier);
        // save the new product to the DB
        products.save(product);
        
        return "redirect:/product?id=" + productId; // redirect to the specific product's page
    }
    
    @PostMapping("/addExistingSupplier")
    public String addExistingSupplier(Integer productId, Integer supplierId) {
        // find the product that needs a supplier added to its list
        Product product = products.findById(productId).orElse(null);
        // find the supplier to add
        Supplier supplier = suppliers.findById(supplierId).orElse(null);
        
        // add the supplier to the product's list
        product.getSuppliers().add(supplier);
        // update the product in the DB
        products.save(product);
        
        return "redirect:/product?id=" + productId;
    }
    
    @GetMapping("/removeSupplier")
    public String removeSupplier(Integer productId, Integer supplierId) {
        // get the product to remove the supplier from
        Product product = products.findById(productId).orElse(null);
        // get the supplier to remove
        Supplier supplier = suppliers.findById(supplierId).orElse(null);
        
        // remove the supplier from the product's list
        product.getSuppliers().remove(supplier);
        // update the product in the DB
        products.save(product);
        
        return "redirect:/product?id=" + productId;
    }
    
    @GetMapping("/supplier")
    public String displaySupplier(Integer id, Model model) {
        
        // get the supplier to display
        Supplier supplier = suppliers.findById(id).orElse(null);
        
        // add supplier's info to the model
        model.addAttribute("supplier", null);
        
        return "supplier";
    }
}
