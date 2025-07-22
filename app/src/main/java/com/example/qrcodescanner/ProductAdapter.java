package com.example.qrcodescanner.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.qrcodescanner.R;
import com.example.qrcodescanner.models.Product;
import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {
    private final Context context;
    private final ArrayList<Product> products;
    private final OnRemoveListener onRemoveListener;

    public interface OnRemoveListener {
        void onRemove(int position);
    }

    public ProductAdapter(Context context, ArrayList<Product> products, OnRemoveListener onRemoveListener) {
        super(context, 0, products);
        this.context = context;
        this.products = products;
        this.onRemoveListener = onRemoveListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        }

        Product product = getItem(position);
        TextView nameTextView = convertView.findViewById(R.id.productName);
        TextView priceTextView = convertView.findViewById(R.id.productPrice);
        Button removeButton = convertView.findViewById(R.id.removeButton);

        nameTextView.setText(product.getName());
        priceTextView.setText(String.format("$%.2f", product.getTotal()));

        removeButton.setOnClickListener(v -> {
            if (onRemoveListener != null) {
                onRemoveListener.onRemove(position);
            }
        });

        return convertView;
    }
}