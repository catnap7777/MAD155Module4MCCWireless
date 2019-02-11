package mad155.kmathes.mad155module4mccwireless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ChooseDeal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_deal);

        String plan = "";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            plan = extras.getString("planSelected");
        }

        System.out.println("plan that was selected: " + plan);

        // Check to make sure project isn't null for some reason
        if(plan != null && !plan.isEmpty()) {

            System.out.println("Project is not empty or null : " + plan);
            // For Bicycle...
        }
    }
}
