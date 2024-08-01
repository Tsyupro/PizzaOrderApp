package com.example.pizzaorderappfree;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeliveryActivity extends AppCompatActivity {

    private TextView orderSummary;
    private EditText address, phoneNumber;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        orderSummary = findViewById(R.id.orderSummary);
        address = findViewById(R.id.address);
        phoneNumber = findViewById(R.id.phoneNumber);
        confirmButton = findViewById(R.id.confirmButton);

        // Перевірити, чи існує Intent і чи передаються дані
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("ORDER_DETAILS")) {
            String orderDetails = intent.getStringExtra("ORDER_DETAILS");
            orderSummary.setText(orderDetails);
        } else {
            Toast.makeText(this, "Помилка отримання даних замовлення", Toast.LENGTH_SHORT).show();
        }

        confirmButton.setOnClickListener(v -> {
            String addressText = address.getText().toString();
            String phoneText = phoneNumber.getText().toString();

            if (addressText.isEmpty() || phoneText.isEmpty()) {
                Toast.makeText(DeliveryActivity.this, "Будь ласка, заповніть всі поля", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DeliveryActivity.this, "Замовлення підтверджено", Toast.LENGTH_SHORT).show();
                // Тут можна додати логіку для обробки підтвердження замовлення
            }
        });
    }
}
