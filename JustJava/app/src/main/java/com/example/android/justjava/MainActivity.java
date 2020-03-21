package com.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;
import java.text.NumberFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        displayMessage(orderSummary(calculatePrice()));
    }

    public void increment(View view) {
        quantity+=1;
        displayQuantity(quantity);
    }
    public void decrement(View view) {
        quantity-=1;
        displayQuantity(quantity);
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice() {
        int price = quantity * 5;
        return price;
    }

    /**
     * Gives a summary of the entire order.
     */
    private String orderSummary(int number){
        return("Name: Kaptain Kunal " + "\nQuantity: " + quantity + "\nTotal: $" + calculatePrice() + "\nThank you!");
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