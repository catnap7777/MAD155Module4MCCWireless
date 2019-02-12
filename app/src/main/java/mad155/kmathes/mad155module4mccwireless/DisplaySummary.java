package mad155.kmathes.mad155module4mccwireless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class DisplaySummary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_summary);

        final String summaryPlan;
        final boolean summaryPassIphone;
        final int summaryQtyIphone;
        final boolean summaryPassGalaxy;
        final int summaryQtyGalaxy;

        TextView phonesSelected = (TextView) findViewById(R.id.txtPhoneAndQtySelected);
        TextView planSelected = (TextView) findViewById(R.id.txtPlanSelected);
        RadioButton radioPayFull = (RadioButton) findViewById(R.id.rbPayFull);
        RadioButton radioPayMonthly = (RadioButton) findViewById(R.id.rbPayMonthly);
        Button btnGetTotals = (Button) findViewById(R.id.btnTotals);
        TextView txtOrderTotals = (TextView) findViewById(R.id.txtOrderTotals);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            summaryPlan = extras.getString("planSelected");
            summaryPassIphone = extras.getBoolean("passIphone");
            summaryQtyIphone = extras.getInt("qtyIphone");
            summaryPassGalaxy = extras.getBoolean("passGalaxy");
            summaryQtyGalaxy = extras.getInt("qtyGalaxy");
        } else {
            summaryPlan = "";
            summaryPassIphone = false;
            summaryQtyIphone = 0;
            summaryPassGalaxy = false;
            summaryQtyGalaxy = 0;
        }

        System.out.println("PLAN SELECTED AND PASSED: " + summaryPlan);
        System.out.println("summaryPassIphone: " + summaryPassIphone + " summaryQtyIphone: " + summaryQtyIphone);
        System.out.println("summaryPassGalaxy: " + summaryPassGalaxy + " summaryQtyGalaxy: " + summaryQtyGalaxy);

        //.. display the plan that was selected; passed in from previous screen

        String displayPlanSelected = "";
        if (summaryPlan.equalsIgnoreCase("basic")) {
            displayPlanSelected = getString(R.string.rbBasicPlan);
        } else if (summaryPlan.equalsIgnoreCase("middle")) {
            displayPlanSelected = getString(R.string.rbMidPlan);
        } else if (summaryPlan.equalsIgnoreCase("premium")) {
            displayPlanSelected = getString(R.string.rbPremiumPlan);
        } else {
            displayPlanSelected = "Error";
        }

        planSelected.setText("Plan Selected: " + displayPlanSelected);

        //.. display phone info if phones were selected for purchase
        if ((summaryPassIphone) && (summaryPassGalaxy)) {
            phonesSelected.setText("Quantity: " + summaryQtyIphone + " - \n" + getString(R.string.cbIPhoneXR) + "\n\n" +
                    "Quantity: " + summaryQtyGalaxy + " - \n" + getString(R.string.cbGalaxy9S));
        } else if ((summaryPassIphone) && (!summaryPassGalaxy)) {
            phonesSelected.setText("Quantity: " + summaryQtyIphone + " - \n" + getString(R.string.cbIPhoneXR));
        } else if ((!summaryPassIphone) && (summaryPassGalaxy)) {
            phonesSelected.setText("Quantity: " + summaryQtyGalaxy + " - \n" + getString(R.string.cbGalaxy9S));
        } else {
            phonesSelected.setText("No phones were selected/ordered");
        }




    }
}
