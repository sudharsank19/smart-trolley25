package com.example.qrcodescanner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.qrcodescanner.adapters.ProductAdapter;
import com.example.qrcodescanner.models.Product;
import com.google.gson.Gson;
import com.journeyapps.barcodescanner.CaptureActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductListActivity extends AppCompatActivity {
    private ListView productListView;
    private ArrayList<Product> products;
    private ProductAdapter adapter;
    private TextView totalTextView; // Declare the TextView for total
    private static final String PREFS_NAME = "ProductPrefs";
    private static final String PRODUCTS_KEY = "products";
    private static final int SCAN_REQUEST_CODE = 100; // Define the scan request code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        productListView = findViewById(R.id.productListView);
        totalTextView = findViewById(R.id.totalTextView); // Initialize the TextView
        products = new ArrayList<>();

        // Load products from SharedPreferences
        loadProducts();

        // Retrieve product details from the intent
        String productName = getIntent().getStringExtra("PRODUCT_NAME");
        double productPrice = getIntent().getDoubleExtra("PRODUCT_PRICE", -1.0);
        int productQuantity = getIntent().getIntExtra("PRODUCT_QUANTITY", -1);

        // If a product was scanned, add it to the list
        if (productName != null && !productName.isEmpty() && productPrice >= 0 && productQuantity >= 0) {
            Product newProduct = new Product(productName, productPrice, productQuantity);
            products.add(newProduct);
            saveProducts(); // Save updated product list
        }

        // Set up the adapter
        adapter = new ProductAdapter(this, products, this::removeProduct);
        productListView.setAdapter(adapter);

        // Set up the checkout button
        Button checkoutButton = findViewById(R.id.checkoutButton);
        checkoutButton.setOnClickListener(v -> {
            // Start the PaymentActivity
            Intent paymentIntent = new Intent(this, PaymentActivity.class);
            paymentIntent.putExtra("TOTAL_AMOUNT", getTotalAmount());
            startActivity(paymentIntent);
        });
// Set up the ImageButton to go to MainActivity
        ImageButton homeButton = findViewById(R.id.imageButton7);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProductListActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Optional: Call finish() if you want to close this activity
        });
        // Update the total when the activity is created
        updateTotal();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update the total when the activity is resumed
        updateTotal();
    }

    public void launchScanner(View view) {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, SCAN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SCAN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("SCAN_RESULT");
                Product scannedProduct = fetchProductData(result);
                if (scannedProduct != null) {
                    Intent intent = new Intent(this, ProductListActivity.class);
                    intent.putExtra("PRODUCT_NAME", scannedProduct.getName());
                    intent.putExtra("PRODUCT_PRICE", scannedProduct.getPrice());
                    intent.putExtra("PRODUCT_QUANTITY", scannedProduct.getQuantity());
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Invalid product data scanned. Please ensure the format is correct.", Toast.LENGTH_SHORT).show();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Scan canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Product fetchProductData(String scannedData) {
        if (scannedData == null || scannedData.isEmpty()) {
            return null; // Return null if scanned data is invalid
        }

        String[] parts = scannedData.split(", ");
        String productName = "";
        double productPrice = -1.0; // Use -1 as an invalid price
        int productQuantity = -1; // Use -1 as an invalid quantity

        for (String part : parts) {
            if (part.startsWith("Name: ")) {
                productName = part.substring(6);
            } else if (part.startsWith("Price: ")) {
                try {
                    productPrice = Double.parseDouble(part.substring(7));
                } catch (NumberFormatException e) {
                    productPrice = -1.0; // Set to invalid if parsing fails
                }
            } else if (part.startsWith("Quantity: ")) {
                try {
                    productQuantity = Integer.parseInt(part.substring(10));
                } catch (NumberFormatException e) {
                    productQuantity = -1; // Set to invalid if parsing fails
                }
            }
        }

        // Validate the extracted data
        if (productName.isEmpty() || productPrice < 0 || productQuantity < 0) {
            return null; // Return null if any data is invalid
        }

        return new Product(productName, productPrice, productQuantity);
    }

    public void removeProduct(int position) {
        if (position >= 0 && position < products.size()) {
            products.remove(position); // Remove the product from the list
            saveProducts(); // Save the updated list
            updateTotal(); // Update the total after removal
            adapter.notifyDataSetChanged(); // Notify the adapter of the data change
        }
    }

    private void updateTotal() {
        double total = 0.0;
        for (Product product : products) {
            total += product.getTotal(); // Calculate total
        }
        totalTextView.setText(String.format("Total: $%.2f", total)); // Update the TextView
    }

    private double getTotalAmount() {
        double total = 0.0;
        for (Product product : products) {
            total += product.getTotal(); // Calculate total
        }
        return total;
    }

    private void saveProducts() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(products);
        editor.putString(PRODUCTS_KEY, json);
        editor.apply();
    }

    private void loadProducts() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(PRODUCTS_KEY, null);
        Product[] productArray = gson.fromJson(json, Product[].class);
        if (productArray != null) {
            products.addAll(Arrays.asList(productArray));
        }
    }
}