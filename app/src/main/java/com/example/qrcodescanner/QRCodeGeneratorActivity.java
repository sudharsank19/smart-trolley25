package com.example.qrcodescanner;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter; // Correct import for QRCodeWriter
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRCodeGeneratorActivity extends AppCompatActivity {

    private EditText editTextProductName;
    private EditText editTextProductPrice;
    private EditText editTextProductQuantity; // Changed to Quantity
    private ImageView imageViewQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_generator);

        editTextProductName = findViewById(R.id.editTextProductName);
        editTextProductPrice = findViewById(R.id.editTextProductPrice);
        editTextProductQuantity = findViewById(R.id.editTextProductQuantity); // Changed to Quantity
        imageViewQRCode = findViewById(R.id.imageViewQRCode);
    }

    public void onGenerateQRCodeClick(View view) {
        String productName = editTextProductName.getText().toString().trim();
        String productPrice = editTextProductPrice.getText().toString().trim();
        String productQuantity = editTextProductQuantity.getText().toString().trim(); // Changed to Quantity

        // Validate input fields
        if (productName.isEmpty() || productPrice.isEmpty() || productQuantity.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create the QR code data string in the specified format
        String qrCodeData = "Name: " + productName + ", Price: " + productPrice + ", Quantity: " + productQuantity;

        try {
            // Generate the QR code
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageViewQRCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error generating QR Code", Toast.LENGTH_SHORT).show();
        }
    }
}