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

        final String SummaryPlan;
        final String SummaryPlanDesc;
        final boolean SummaryPassIphone;
        final String SummaryDescIphone;
        //final String SummaryQtyIphone;
        final int SummaryQtyIphone;
        final boolean SummaryPassGalaxy;
        final String SummaryDescGalaxy;
        //final String SummaryQtyGalaxy;
        final int SummaryQtyGalaxy;

        TextView phonesSelected = (TextView) findViewById(R.id.txtPhoneAndQtySelected);
        TextView planSelected = (TextView) findViewById(R.id.txtPlanSelected);
        RadioButton radioPayFull = (RadioButton) findViewById(R.id.rbPayFull);
        RadioButton radioPayMonthly = (RadioButton) findViewById(R.id.rbPayMonthly);
        Button btnGetTotals = (Button) findViewById(R.id.btnTotals);
        TextView txtOrderTotals = (TextView) findViewById(R.id.txtOrderTotals);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            SummaryPlan = extras.getString("planSelected");
            SummaryPlanDesc = extras.getString("planSelectedDesc");
            SummaryPassIphone = extras.getBoolean("passIphone");
            SummaryDescIphone = extras.getString("descIphone");
            //SummaryQtyIphone = extras.getString("qtyIphone");
            SummaryQtyIphone = extras.getInt("qtyIphone");
            SummaryPassGalaxy = extras.getBoolean("passGalaxy");
            SummaryDescGalaxy = extras.getString("descGalaxy");
            //SummaryQtyGalaxy = extras.getString("qtyGalaxy");
            SummaryQtyGalaxy = extras.getInt("qtyGalaxy");
        } else {
            SummaryPlan = "";
            SummaryPlanDesc = "";
            SummaryPassIphone = false;
            SummaryDescIphone = "";
            SummaryQtyIphone = 0;
            SummaryPassGalaxy = false;
            SummaryDescGalaxy = "";
            SummaryQtyGalaxy = 0;
        }

        System.out.println("SummaryPassIphone: " + SummaryPassIphone + " SummaryQtyIphone: " + SummaryQtyIphone);
        System.out.println("SummaryDescIphone: " + SummaryDescIphone);
        System.out.println("SummaryPassGalaxy: " + SummaryPassGalaxy + " SummaryQtyGalaxy: " + SummaryQtyGalaxy);
        System.out.println("SummaryDescGalaxy: " + SummaryDescGalaxy);

        planSelected.setText("Plan Selected: " + SummaryPlan + "\n\t" + SummaryPlanDesc);

        if ((SummaryPassIphone) && (SummaryPassGalaxy)) {
            phonesSelected.setText("Quantity: " + SummaryQtyIphone + " - \n\t\t" + SummaryDescIphone + "\n" +
                    "Quantity: " + SummaryQtyGalaxy + " - \n\t\t" + SummaryDescGalaxy);
        } else if ((SummaryPassIphone) && (!SummaryPassGalaxy)) {
            phonesSelected.setText("Quantity: " + SummaryQtyIphone + " - \n\t\t" + SummaryDescIphone);
        } else if ((!SummaryPassIphone) && (SummaryPassGalaxy)) {
            phonesSelected.setText("Quantity: " + SummaryQtyGalaxy + " - \n\t\t" + SummaryDescGalaxy);
        } else {
            phonesSelected.setText("No phones were selected/ordered");
        }
    }
}
