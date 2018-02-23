package com.atelier.it.atelier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class Payment_Form extends AppCompatActivity implements View.OnClickListener{
    private static final int MY_SCAN_REQUEST_CODE = 1;
    private Button iniciar;
    private TextView txttarjeta, txtfecha, txtcvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment__form);
        iniciar = (Button)findViewById(R.id.iniciar);
        iniciar.setOnClickListener(this);

        txttarjeta = (TextView)findViewById(R.id.txttarjeta);
        txtfecha = (TextView)findViewById(R.id.txtfecha);
        txtcvv = (TextView)findViewById(R.id.txtcvv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iniciar:
                Intent scanIntent = new Intent(this, CardIOActivity.class);

                // customize these values to suit your needs.
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false);// default: false

                // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
                startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_SCAN_REQUEST_CODE) {
            String resultDisplayStr;
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

                // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
                resultDisplayStr = "Card Number: " + scanResult.getRedactedCardNumber() + "\n";

                // Do something with the raw number, e.g.:
                // myService.setCardNumber( scanResult.cardNumber );

                if (scanResult.isExpiryValid()) {
                    String resultDisplayStr2 = "Expiration Date: " + scanResult.expiryMonth + "/" + scanResult.expiryYear + "\n";
                    txtfecha.setText(resultDisplayStr2);
                }

                if (scanResult.cvv != null) {
                    // Never log or display a CVV
                    String resultDisplayStr3 = "CVV has " + scanResult.cvv.length() + " digits.\n";
                    txtcvv.setText(resultDisplayStr3);
                }

                if (scanResult.postalCode != null) {
                    resultDisplayStr += "Postal Code: " + scanResult.postalCode + "\n";
                }
            }
            else {
                resultDisplayStr = "Scan was canceled.";
            }
            // do something with resultDisplayStr, maybe display it in a textView
            // resultTextView.setText(resultDisplayStr);
            txttarjeta.setText(resultDisplayStr);


        }
        // else handle other activity results
    }
}
