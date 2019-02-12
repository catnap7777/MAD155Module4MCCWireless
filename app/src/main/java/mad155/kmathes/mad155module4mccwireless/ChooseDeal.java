package mad155.kmathes.mad155module4mccwireless;

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


        String plan = "";
        String planDesc = "";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            plan = extras.getString("planSelected");
            planDesc = extras.getString("planSelectedDesc");
        }

        System.out.println("plan that was selected: " + plan);
        System.out.println("plan desc that was selected: " + planDesc);

        // Check to make sure project isn't null for some reason
        if(plan != null && !plan.isEmpty()) {

            System.out.println("Plan is not empty or null : " + plan);
            System.out.println("PlanDesc is not empty or null : " + planDesc);

            final CheckBox cBoxIphone = (CheckBox) findViewById(R.id.cbIphone);
            final CheckBox cBoxGalaxy = (CheckBox) findViewById(R.id.cbGalaxy);
            final Spinner spnrIphone = (Spinner) findViewById(R.id.spnIphone);
            final Spinner spnrGalaxy = (Spinner) findViewById(R.id.spnGalaxy);
            final TextView txtPlanDescSel = (TextView) findViewById(R.id.txtPlanSelected);

            Button btnSummary = (Button) findViewById(R.id.btnSummary);

            txtPlanDescSel.setText("Plan Selected: " + plan + "\n" + planDesc);

            btnSummary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String qtyIphone = "";
                    String qtyGalaxy = "";
                    String descIphone = "";
                    String descGalaxy = "";
                    boolean passIphone = false;
                    boolean passGalaxy = false;

                    if (cBoxIphone.isChecked() && spnrIphone.getSelectedItemPosition() > 0) {
                        qtyIphone = spnrIphone.getSelectedItem().toString();
                        descIphone = getString(R.string.cbIPhoneXR);
                        passIphone = true;
                    }

                    if (cBoxGalaxy.isChecked() && spnrGalaxy.getSelectedItemPosition() > 0) {
                        qtyGalaxy = spnrGalaxy.getSelectedItem().toString();
                        descGalaxy = getString(R.string.cbGalaxy9S);
                        passGalaxy = true;

                    }

                    if ((cBoxIphone.isChecked() && spnrIphone.getSelectedItemPosition() == 0) ||
                            (cBoxGalaxy.isChecked() && spnrGalaxy.getSelectedItemPosition() == 0)) {

                        Toast.makeText (getApplicationContext(), "Please select a quantity", Toast.LENGTH_SHORT)
                                .show();
                    }

                    if ((!cBoxIphone.isChecked() && spnrIphone.getSelectedItemPosition() > 0) ||
                            (!cBoxGalaxy.isChecked() && spnrGalaxy.getSelectedItemPosition() > 0)) {

                        Toast.makeText (getApplicationContext(), "Please select phone", Toast.LENGTH_SHORT)
                                .show();
                    }

                    // attempt to reset qty if box is unchecked

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


                    System.out.println("passIphone: " + passIphone + " qtyIphone: " + qtyIphone);
                    System.out.println("descIphone: " + descIphone);
                    System.out.println("passGalaxy: " + passGalaxy + " qtyGalaxy: " + qtyGalaxy);
                    System.out.println("descGalaxy: " + descGalaxy);



                }
            });

            //tv.setText(getString(R.string.Converasation) + "HELLo");
            //if (cBoxIphone.isChecked() )

        }
    }
}
