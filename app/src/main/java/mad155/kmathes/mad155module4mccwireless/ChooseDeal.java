package mad155.kmathes.mad155module4mccwireless;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseDeal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_deal);

        final String plan;

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            plan = extras.getString("planSelected");
        } else {
            plan = "";
        }

        // for debugging.. System.out.println("plan that was selected: " + plan);

        //.. check to make sure project isn't null for some reason
        if(plan != null && !plan.isEmpty()) {

            // for debugging.. System.out.println("Plan is not empty or null : " + plan);

            final CheckBox cBoxIphone = (CheckBox) findViewById(R.id.cbIphone);
            final CheckBox cBoxGalaxy = (CheckBox) findViewById(R.id.cbGalaxy);
            final Spinner spnrIphone = (Spinner) findViewById(R.id.spnIphone);
            final Spinner spnrGalaxy = (Spinner) findViewById(R.id.spnGalaxy);
            final TextView txtPlanDescSel = (TextView) findViewById(R.id.txtPlanSelected);

            Button btnSummary = (Button) findViewById(R.id.btnSummary);

            //.. display the plan that was selected from previous screen
            String planSelectDesc = "";

            if (plan.equalsIgnoreCase("basic")) {
                planSelectDesc = getString(R.string.txtBasic);
                //txtPlanDescSel.setText("Plan Selected: " + plan + "\n" + planSelectDesc);
                txtPlanDescSel.setText("Plan Selected: " + getString(R.string.rbBasicPlan) + "\n" + planSelectDesc);
            } else if (plan.equalsIgnoreCase("middle")) {
                planSelectDesc = getString(R.string.txtMid);
                //txtPlanDescSel.setText("Plan Selected: " + plan + "\n" + planSelectDesc);
                txtPlanDescSel.setText("Plan Selected: " + getString(R.string.rbMidPlan) + "\n" + planSelectDesc);
            } else if (plan.equalsIgnoreCase("premium")){
                planSelectDesc = getString(R.string.txtPremium);
                //txtPlanDescSel.setText("Plan Selected: " + plan + "\n" + planSelectDesc);
                txtPlanDescSel.setText("Plan Selected: " + getString(R.string.rbPremiumPlan) + "\n" + planSelectDesc);
            } else {
                txtPlanDescSel.setText("No Plan Selected");
            }

            //.. if the checkbox or boxes become unchecked, set quantity back to zero
            cBoxIphone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (!cBoxIphone.isChecked()) {
                        spnrIphone.setSelection(0);
                    }
                }
            });
            cBoxGalaxy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (!cBoxGalaxy.isChecked()) {
                        spnrGalaxy.setSelection(0);
                    }
                }
            });

            btnSummary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int qtyIphone = 0;
                    int qtyGalaxy = 0;
                    boolean passIphone = false;
                    boolean passGalaxy = false;
                    boolean passDataOk = true;

                    //.. if a phone is selected for purchase, get the quantity desired
                    if (cBoxIphone.isChecked() && spnrIphone.getSelectedItemPosition() > 0) {
                        qtyIphone = Integer.parseInt(spnrIphone.getSelectedItem().toString());
                        passIphone = true;
                        passDataOk = true;
                    }

                    if (cBoxGalaxy.isChecked() && spnrGalaxy.getSelectedItemPosition() > 0) {
                        qtyGalaxy = Integer.parseInt(spnrGalaxy.getSelectedItem().toString());
                        passGalaxy = true;
                        passDataOk = true;
                    }
                    //.. if no phone is selected, it's still ok but flag to kick off intent must be set
                    if ((!cBoxIphone.isChecked() && spnrIphone.getSelectedItemPosition() == 0) &&
                            ((!cBoxGalaxy.isChecked() && spnrGalaxy.getSelectedItemPosition() == 0))) {
                        passDataOk = true;
                    }

                    //.. if phone is selected but quantity is zero, set Toast
                    if ((cBoxIphone.isChecked() && spnrIphone.getSelectedItemPosition() == 0) ||
                            (cBoxGalaxy.isChecked() && spnrGalaxy.getSelectedItemPosition() == 0)) {
                        passDataOk = false;
                        Toast.makeText(getApplicationContext(), "Please select a quantity", Toast.LENGTH_SHORT)
                                .show();
                    }
                    //.. if quantity is set but phone is not checked, set Toast
                    if ((!cBoxIphone.isChecked() && spnrIphone.getSelectedItemPosition() > 0) ||
                            (!cBoxGalaxy.isChecked() && spnrGalaxy.getSelectedItemPosition() > 0)) {
                        passDataOk = false;
                        Toast.makeText(getApplicationContext(), "Please select phone", Toast.LENGTH_SHORT)
                                .show();
                    }

                    // for debugging.. System.out.println("PLAN SELECTED " + plan);
                    // for debugging.. System.out.println("passIphone: " + passIphone + " qtyIphone: " + qtyIphone);
                    // for debugging.. System.out.println("passGalaxy: " + passGalaxy + " qtyGalaxy: " + qtyGalaxy);

                    if (passDataOk) {
                        Intent intent2 = new Intent(ChooseDeal.this, DisplaySummary.class);
                        intent2.putExtra("planSelected", plan);
                        intent2.putExtra("passIphone", passIphone);
                        intent2.putExtra("qtyIphone", qtyIphone);
                        intent2.putExtra("passGalaxy", passGalaxy);
                        intent2.putExtra("qtyGalaxy", qtyGalaxy);
                        startActivity(intent2);
                    }
                }
            });
        }
    }
}
