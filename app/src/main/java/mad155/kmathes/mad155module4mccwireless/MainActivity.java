package mad155.kmathes.mad155module4mccwireless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btnPlan = (Button) findViewById(R.id.btnChoosePlan);

        btnPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedPlan = "";
                String selectedPlanDesc = "";

                final RadioButton rbBasic = (RadioButton) findViewById(R.id.rbBasicPlan);
                final RadioButton rbMid = (RadioButton) findViewById(R.id.rbMidPlan);
                final RadioButton rbPremium = (RadioButton) findViewById(R.id.rbPremiumPlan);

                if (rbBasic.isChecked()) {
                    selectedPlan = "Basic";
                    selectedPlanDesc = getString(R.string.txtBasic);
                } else if (rbMid.isChecked()) {
                    selectedPlan = "Middle";
                    selectedPlanDesc = getString(R.string.txtMid);
                } else if (rbPremium.isChecked()) {
                    selectedPlan = "Premium";
                    selectedPlanDesc = getString(R.string.txtPremium);
                } else {
                    selectedPlan = "Wrong";
                }

                Intent intent1 = new Intent(MainActivity.this, ChooseDeal.class);
                intent1.putExtra("planSelected", selectedPlan);
                intent1.putExtra("planSelectedDesc", selectedPlanDesc);
                startActivity(intent1);
            }
        });

    }
}
