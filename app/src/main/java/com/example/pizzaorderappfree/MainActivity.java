package com.example.pizzaorderappfree;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private RadioGroup pizzaGroup;
    private CheckBox extraCheese, extraOlives, extraPepper;
    private Button orderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pizzaGroup = findViewById(R.id.pizzaGroup);
        extraCheese = findViewById(R.id.extraCheese);
        extraOlives = findViewById(R.id.extraOlives);
        extraPepper = findViewById(R.id.extraPepper);
        orderButton = findViewById(R.id.orderButton);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedPizzaId = pizzaGroup.getCheckedRadioButtonId();
                RadioButton selectedPizza = findViewById(selectedPizzaId);

                if (selectedPizza == null) {
                    Toast.makeText(MainActivity.this, "Будь ласка, виберіть піцу", Toast.LENGTH_SHORT).show();
                    return;
                }

                String orderMessage = "Ви замовили " + selectedPizza.getText().toString() + " з додатками: ";

                if (extraCheese.isChecked()) {
                    orderMessage += "Додатковий сир, ";
                }
                if (extraOlives.isChecked()) {
                    orderMessage += "Оливки, ";
                }
                if (extraPepper.isChecked()) {
                    orderMessage += "Перець, ";
                }

                if (orderMessage.endsWith(", ")) {
                    orderMessage = orderMessage.substring(0, orderMessage.length() - 2);
                }

                Intent intent = new Intent(MainActivity.this, DeliveryActivity.class);
                intent.putExtra("ORDER_DETAILS", orderMessage);
                startActivity(intent);
            }
        });
    }
}
