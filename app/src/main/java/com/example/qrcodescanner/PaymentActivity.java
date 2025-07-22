package com.example.qrcodescanner;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {

    private double totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        totalAmount = getIntent().getDoubleExtra("TOTAL_AMOUNT", 0.0);

        TextView totalTextView = findViewById(R.id.totalTextView);
        totalTextView.setText(String.format("Total Amount: %.2f INR", totalAmount));

        Button btnPay = findViewById(R.id.btnPay);
        btnPay.setOnClickListener(v -> startPayment());
    }

    private void startPayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_OhIwCkQHrhuGfd");

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Your Company Name");
            options.put("description", "Payment for Order #123456");
            options.put("currency", "INR");
            options.put("amount", totalAmount * 100); // Convert to paise

            JSONObject prefill = new JSONObject();
            prefill.put("email", "user@example.com");
            prefill.put("contact", "9876543210");

            options.put("prefill", prefill);

            checkout.open(this, options);
        } catch (Exception e) {
            Toast.makeText(this, "Error in payment: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPaymentError(int code, String response) {
        Toast.makeText(this, "Payment failed: " + response, Toast.LENGTH_LONG).show();
    }
}
