package com.example.pizzastore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class SecondActivity extends AppCompatActivity {
private TextView toppings, delivery, mytotal;
private Button btnFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        toppings = (TextView) findViewById(R.id.selectedtopp);
        delivery = (TextView) findViewById(R.id.tv_cost);
        mytotal = (TextView) findViewById(R.id.mytotal);
        btnFinish = (Button) findViewById(R.id.finish);

        Pizza getToppings = (Pizza) getIntent().getExtras().getSerializable(MainActivity.LIST_KEY);
        String thetoppings = getToppings.toppings.toString().replace("[","").replace("]","");
        toppings.setText(thetoppings);
        int count = getToppings.toppings.size();
        double price = 1.50*count;
        TextView topprice = (TextView) findViewById(R.id.topp_price);
        topprice.setText(Double.toString(price)+"$");
        double delCost = 0;
        if(getToppings.check == true){
            delivery.setText("4.0$");
            delCost = 4;
        }
        else{
            delivery.setText("0.0$");
            delCost = 0;
        }
        double total = price + delCost + 6.5;
        mytotal.setText(Double.toString(total)+"$");


        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this,MainActivity.class);

                startActivity(intent);
                finish();
            }
        });
    }
}
