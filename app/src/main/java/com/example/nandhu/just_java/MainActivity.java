/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */
package com.example.nandhu.just_java;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;
import static android.R.attr.name;
import static com.example.nandhu.just_java.R.id.number;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int inc=1;
    /**
     * This method is called when the order button is clicked.
     */

    public void plus(View view){
      if(inc > 0 && inc <=99) {
          inc++;
          display(inc);
      }
      else{
            display(inc);
          Toast.makeText(this, "The Quantity can't be more than 100",
                  Toast.LENGTH_SHORT).show();
        }

    }
    public void minus(View view){
        if(inc > 1 && inc <=100) {
            inc--;
            display(inc);
        }
        else{
            display(inc);
            Toast.makeText(this, "The Quantity can't be less than 1",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void Submit(View view){
        EditText etext = (EditText) findViewById(R.id.etext);
        String ea = etext.getText().toString();
        String message = orderSummary(ea);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:rsnk2013@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order for " + ea);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }



    public String orderSummary(String na){
          String user = "Name: " + na;
          user += "\nQuantity: " + inc;
          user += "\nCoffee Cost Per cup: $5";
          user +=  calculate();
          user += "\nThank You!!!!";
    return user;
}
    public String calculate(){
        CheckBox box = (CheckBox) findViewById(R.id.check);
        Boolean checking = box.isChecked();
        CheckBox box1 = (CheckBox) findViewById(R.id.check1);
        Boolean checking1 = box1.isChecked();
        int price = 5;
        String mes = "";
        if(checking == true){
            price += 2;
            mes = "\nWhipped topping cost Per Cup: $2";
        }
        if(checking1 == true){
            price += 3;
            mes += "\nChacolate topping cost Per Cup: $3";
        }

        return  mes + "\nTotal: $"+(inc * price);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.number);
        quantityTextView.setText("" + number);
    }




}