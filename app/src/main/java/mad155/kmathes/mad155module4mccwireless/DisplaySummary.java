package mad155.kmathes.mad155module4mccwireless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

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

        final double totalAmtPlan;


        TextView phonesSelected = (TextView) findViewById(R.id.txtPhoneAndQtySelected);
        TextView planSelected = (TextView) findViewById(R.id.txtPlanSelected);
        final RadioButton radioPayFull = (RadioButton) findViewById(R.id.rbPayFull);
        final RadioButton radioPayMonthly = (RadioButton) findViewById(R.id.rbPayMonthly);
        final RadioGroup radioGrp1 = (RadioGroup) findViewById(R.id.radioGroup2);
        Button btnGetTotals = (Button) findViewById(R.id.btnTotals);
        final TextView txtOrderTotals = (TextView) findViewById(R.id.txtOrderTotals);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            summaryPlan = extras.getString("planSelected");
            summaryPassIphone = extras.getBoolean("passIphone");
            summaryQtyIphone = extras.getInt("qtyIphone");
            summaryPassGalaxy = extras.getBoolean("passGalaxy");
            summaryQtyGalaxy = extras.getInt("qtyGalaxy");
        } else {
            summaryPlan = " ";
            summaryPassIphone = false;
            summaryQtyIphone = 0;
            summaryPassGalaxy = false;
            summaryQtyGalaxy = 0;
        }

        // for debugging.. System.out.println("PLAN SELECTED AND PASSED: " + summaryPlan);
        // for debugging.. System.out.println("summaryPassIphone: " + summaryPassIphone + " summaryQtyIphone: " + summaryQtyIphone);
        // for debugging.. System.out.println("summaryPassGalaxy: " + summaryPassGalaxy + " summaryQtyGalaxy: " + summaryQtyGalaxy);

        //.. display the plan that was selected; passed in from previous screen

        String displayPlanSelected = "";
        if (summaryPlan.equalsIgnoreCase("basic")) {
            displayPlanSelected = getString(R.string.rbBasicPlan);
            totalAmtPlan = Double.parseDouble(getString(R.string.amtBasicPlan));
        } else if (summaryPlan.equalsIgnoreCase("middle")) {
            displayPlanSelected = getString(R.string.rbMidPlan);
            totalAmtPlan = Double.parseDouble(getString(R.string.amtMidPlan));
        } else if (summaryPlan.equalsIgnoreCase("premium")) {
            displayPlanSelected = getString(R.string.rbPremiumPlan);
            totalAmtPlan = Double.parseDouble(getString(R.string.amtPremiumPlan));
        } else {
            displayPlanSelected = "Error";
            totalAmtPlan = 0;
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

        //.. if no phones are being purchased, set phone payment type radio buttons as unclickable
        if ((!summaryPassIphone) && (!summaryPassGalaxy)) {
            for (int i = 0; i < radioGrp1.getChildCount(); i++) {
                radioGrp1.getChildAt(i).setEnabled(false);
            }
        }

        //.. if phones are being purchased and radio button payment option changes, zero out summary
        //    and make user hit orders total button again to get refreshed data
        radioPayFull.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                txtOrderTotals.setText(" ");
            }
        });

        radioPayMonthly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                txtOrderTotals.setText(" ");
            }
        });

        btnGetTotals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double totalAmtOrder = 0;
                int totalPhonesPurch = 0;
                String giftCardEarned = "";
                double percentOffTotalEarned = 0;
                //.. multiplier for 10% off
                double percentOffAmt = .10;
                String txtPercentOffEarned = "No";
                String phonePaymentType = "";

                // for debugging.. System.out.println("summaryPassIphone:" + summaryPassIphone);
                // for debugging.. System.out.println("summaryPassGalaxy:" + summaryPassGalaxy);
                // for debugging.. System.out.println("radioPayFull:" + radioPayFull.isChecked());
                // for debugging.. System.out.println("radioPayMonthly:" + radioPayMonthly.isChecked());

                //.. check to see if payment option for purchasing phones is checked
                if (((summaryPassIphone) || (summaryPassGalaxy)) && ((!radioPayFull.isChecked()) && (!radioPayMonthly.isChecked()))) {
                    Toast.makeText(getApplicationContext(), "Please select payment type", Toast.LENGTH_SHORT)
                            .show();
                } else {

                    //.. if only buying iPhone
                    if ((summaryPassIphone) && (!summaryPassGalaxy)) {
                        if (radioPayFull.isChecked()) {
                            totalAmtOrder = totalAmtPlan + (summaryQtyIphone * Double.parseDouble(getString(R.string.iPhonePrcFull)));
                            phonePaymentType = "Paid in full";
                        } else if (radioPayMonthly.isChecked()) {
                            totalAmtOrder = totalAmtPlan + (summaryQtyIphone * Double.parseDouble(getString(R.string.iPhonePrcMonthly)));
                            phonePaymentType = "Paid monthly";
                        }
                    }
                    //.. if only buying Galaxy
                    if ((summaryPassGalaxy) && (!summaryPassIphone)) {
                        if (radioPayFull.isChecked()) {
                            totalAmtOrder = totalAmtPlan + (summaryQtyGalaxy * Double.parseDouble(getString(R.string.galaxyPrcFull)));
                            phonePaymentType = "Paid in full";
                        } else if (radioPayMonthly.isChecked()) {
                            totalAmtOrder = totalAmtPlan + (summaryQtyGalaxy * Double.parseDouble(getString(R.string.galaxyPrcMonthly)));
                            phonePaymentType = "Paid monthly";
                        }
                    }
                    //.. if buying both iPhone and Galaxy
                    if ((summaryPassGalaxy) && (summaryPassIphone)) {
                        if (radioPayFull.isChecked()) {
                            totalAmtOrder = totalAmtPlan + (summaryQtyIphone * Double.parseDouble(getString(R.string.iPhonePrcFull))) +
                                    (summaryQtyGalaxy * Double.parseDouble(getString(R.string.galaxyPrcFull)));
                            phonePaymentType = "Paid in full";
                        } else if (radioPayMonthly.isChecked()) {
                            totalAmtOrder = totalAmtPlan + (summaryQtyIphone * Double.parseDouble(getString(R.string.iPhonePrcMonthly))) +
                                    (summaryQtyGalaxy * Double.parseDouble(getString(R.string.galaxyPrcMonthly)));
                            phonePaymentType = "Paid monthly";
                        }
                    }
                    //.. if no phone selected
                    if ((!summaryPassIphone) && (!summaryPassGalaxy)) {
                        totalAmtOrder = totalAmtPlan;
                        phonePaymentType = "No phones purchased";
                        giftCardEarned = "No gift cards earned";
                    }

                    //.. did user earn gift card? how many phones purchased?
                    if ((summaryPassIphone) || (summaryPassGalaxy)) {
                        totalPhonesPurch = summaryQtyIphone + summaryQtyGalaxy;
                        if (totalPhonesPurch == 1) {
                            giftCardEarned = getString(R.string.deal50GC);
                            txtPercentOffEarned = "No";
                        } else if (totalPhonesPurch == 2) {
                            giftCardEarned = getString(R.string.deal100GC);
                            txtPercentOffEarned = "No";
                        //.. if 3 or more phones purchased and they will be paid in full
                        } else if ((totalPhonesPurch >= 3) && (radioPayFull.isChecked())) {
                            giftCardEarned = getString(R.string.deal100GC);
                            txtPercentOffEarned = "Yes";
                            //.. multiplier to get total with 10% off
                            percentOffTotalEarned = totalAmtOrder * percentOffAmt;
                            totalAmtOrder = totalAmtOrder - percentOffTotalEarned;
                        //.. if 3 or more phones purchased but they will be paid monthly
                        } else if (totalPhonesPurch >= 3) {
                            giftCardEarned = getString(R.string.deal100GC);
                        }
                    }

                    DecimalFormat amtFormatted = new DecimalFormat("$########.00");

                    txtOrderTotals.setText("Estimated order totals with first months payment included: " +
                            "\nPlan Amount: " + amtFormatted.format(totalAmtPlan) +
                            "\nTotal Number Phones Purchased: " + totalPhonesPurch +
                            "\n\t\t\t\tiPhones Purchased: " + summaryQtyIphone +
                            "\n\t\t\t\tGalaxys Purchased: " + summaryQtyGalaxy +
                            "\nPayment type for phones: " + phonePaymentType +
                            "\n10% off order earned: " + txtPercentOffEarned +
                            "\nEstimated 1st Months Payment: " + amtFormatted.format(totalAmtOrder) +
                            "\nGift Card Earned: " + giftCardEarned);
                }
            }
        });

    }
}
