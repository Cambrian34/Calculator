package com.chambers.calculator;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {

    private final AdView mAdView = findViewById(R.id.adView);
    private EditText display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);

        display.setOnClickListener(v -> {
            try {


                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }
            }catch (Exception e) {
                e.printStackTrace();
            }

        });
        MobileAds.initialize(this, initializationStatus -> {
        });
        AdView adView = new AdView(this);

        adView.setAdSize(AdSize.BANNER);

        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    private void updateText(String strToAdd){
        String oldstr = display.getText().toString();
        int curpos = display.getSelectionStart();
        String leftstr = oldstr.substring(0, curpos);
        String rightstr = oldstr.substring(curpos);
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText("");
        }
        else{
            display.setText(String.format("%s%s%s", leftstr, strToAdd, rightstr));
            display.setSelection(curpos +1);}



    }

    public void zerobtn(View view){
        updateText("0");

    }
    public void onebtn(View view){
        updateText("1");

    }
    public void twobtn(View view){
        updateText("2");

    }
    public void threebtn(View view){
        updateText("3");

    }
    public void fourbtn(View view){

        updateText("4");
    }
    public void fivebtn(View view){

        updateText("5");
    }
    public void sixbtn(View view){
        updateText("6");

    }
    public void sevenbtn(View view){

        updateText("7");
    }
    public void eightbtn(View view){

        updateText("8");
    }
    public void ninebtn(View view){

        updateText("9");
    }
    public void minusbtn(View view){

        updateText("-");
    }
    public void plusbtn(View view){

        updateText("+");
    }
    public void pointbtn(View view){

        updateText(".");
    }
    public void plusminusbtn(View view){


    }
    public void equalbtn(View view){
        String userex = display.getText().toString();

        userex = userex.replaceAll("÷", "/");
        userex = userex.replaceAll("×", "*");

        Expression expression = new Expression(userex);

        String result = String.valueOf(expression.calculate());

        display.setText(result);
        display.setSelection(result.length());

    }
    public void multibtn(View view){
        updateText("*");

    }
    public void clearbtn(View view){
        updateText("");

    }
    public void parbtn(View view){
        int curpos = display.getSelectionStart();
        int openpar = 0;
        int closepar = 0;
        int textlen = display.getText().length();

        for (int i = 0; i < curpos; i++){
            if (display.getText().toString().charAt(i) == '('){
                openpar +=1;
            }
            if (display.getText().toString().charAt(i) == ')'){
                closepar +=1;
            }
        }
        if (openpar == closepar || display.getText().toString().charAt(textlen - 1) == '('){
            updateText("(");
        }
        else if (closepar < openpar && display.getText().toString().charAt(textlen - 1) != '('){
            updateText(")");

        }

        display.setSelection(curpos + 1);

    }
    public void expobtn(View view){
        updateText("^");


    }
    public void divbtn(View view){
        updateText("/");

    }
    public void backbtn(View view){
        int curpos = display.getSelectionStart();
        int textlen = display.getText().length();

        if (curpos != 0 && textlen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(curpos -1 , curpos, "");
            display.setText(selection);
            display.setSelection(curpos-1);
        }

    }
}