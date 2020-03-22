package com.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;
import java.text.NumberFormat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);

        EditText name_value = (EditText) findViewById(R.id.name_field);
        String name = name_value.getText().toString();
        Log.v("MainActivity", "Name: " + name);
        Log.v("MainActivity", "Has whipped cream is: " + whippedCreamCheckBox.isChecked());
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name, price,hasWhippedCream, hasChocolate);
        //displayMessage(priceMessage);

        //You can comment the next few intent lines and uncomment the displayMessage line
        //if you just want it to show up on the app instead of using Gmail.
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(intent.EXTRA_SUBJECT, "Just Java order for " + name);
        intent.putExtra(intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void increment(View view) {
        if(quantity<100) {
            quantity += 1;
            displayQuantity(quantity);
        }
        else{
            Toast.makeText(this, "You can't have more than 100 coffees", Toast.LENGTH_SHORT).show();
        }
    }
    public void decrement(View view) {
        if(quantity>1) {
            quantity -= 1;
            displayQuantity(quantity);
        }
        else{
            Toast.makeText(this, "You can't have less than 1 coffee", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;

        if(addWhippedCream){
            basePrice = basePrice + 1;
        }
        if(addChocolate){
            basePrice = basePrice + 2;
        }

        return quantity*basePrice;
    }

    /**
     * Gives a summary of the entire order.
     */
    private String createOrderSummary(String name, int price, boolean whippedCream, boolean chocolate){
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + whippedCream;
        priceMessage += "\nAdd chocolate? " + chocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank You!";
        return priceMessage;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}