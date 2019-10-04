package com.example.pizzastore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayout;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
static final String LIST_KEY = "List";
private boolean isChecked = false;
private CheckBox check;
private Button checkout, clearBtn, btnAdd;
private ProgressBar progressBar;
private ImageButton imgBtn;
private FlexboxLayout flexboxLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        check = (CheckBox) findViewById(R.id.checkBox);
        checkout = (Button) findViewById(R.id.checkout);
        clearBtn = (Button) findViewById(R.id.buttonClear);
        btnAdd = (Button) findViewById(R.id.buttonAdd);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final CharSequence[] toppingsList = new CharSequence[]{"Bacon","Cheese","Garlic","Green Pepper","Mushroom","Olive","Onion","Red Pepper"};
        final FlexboxLayout flexboxLayout = (FlexboxLayout) findViewById(R.id.flexboxLayout);
        final List<String> selectedToppings = new ArrayList<>();
        flexboxLayout.setFlexDirection(FlexDirection.ROW);
        progressBar = new ProgressBar(MainActivity.this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(10);



        final ProgressBar finalProgressBar = progressBar;
        builder.setTitle("Choose a Topping")
                .setCancelable(false)
                .setItems(toppingsList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final String selection = (String) toppingsList[i];
                        //selectedToppings.add(selection);
                        int res = display(selection);
                        final int viewCount = flexboxLayout.getChildCount();
                        if(viewCount < 10){
                            selectedToppings.add((String) toppingsList[i]);
                            imgBtn = (ImageButton) new ImageButton(MainActivity.this);
                            imgBtn.setTag(selection);
                            finalProgressBar.setProgress(viewCount+1);
                            imgBtn.setBackgroundResource(res);
                            flexboxLayout.addView(imgBtn);

                            imgBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view1) {
                                    flexboxLayout.removeView(view1);
                                    int viewCountRemove = flexboxLayout.getChildCount();
                                    finalProgressBar.setProgress(viewCountRemove);
                                    selectedToppings.remove(selection);
                                }
                            });
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Toppings limit exceeded!", Toast.LENGTH_SHORT).show();
                        }



                    }
                });


        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flexboxLayout.removeAllViews();
                finalProgressBar.setProgress(0);
                check.setChecked(false);
            }

        });

        final AlertDialog alert = builder.create();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.show();
            }
        });
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(check.isChecked() == true)
                {
                    isChecked = true;
                }
                else if(check.isChecked() == false){
                    isChecked = false;
                }
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);

                Pizza pizza = new Pizza(selectedToppings, isChecked);
                intent.putExtra(LIST_KEY, pizza);

                startActivity(intent);
                finish();
            }
        });

    }


    private int display(String selection) {
        switch(selection){
            case "Bacon":
                return R.drawable.bacon;
            case "Cheese":
                return R.drawable.cheese;
            case "Garlic":
                return R.drawable.garlic;
            case "Green Pepper":
                return R.drawable.green_pepper;
            case "Mushroom":
                return R.drawable.mashroom;
            case "Olive":
                return R.drawable.olive;
            case "Onion":
                return R.drawable.onion;
            case "Red Pepper":
                return R.drawable.red_pepper;

        }

        return 0;
    }

}

