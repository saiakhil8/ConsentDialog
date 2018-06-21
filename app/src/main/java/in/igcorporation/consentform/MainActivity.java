/*
 *
 *     Copyright (C) 2018  VENKATA SAI AKHIL KUMAR VEMULA (IGCORPORATION.IN)
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package in.igcorporation.consentform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import in.igcorporation.consentdialog.AdProviders;
import in.igcorporation.consentdialog.ConsentDialog;
import in.igcorporation.consentdialog.ConsentSelectionListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<AdProviders> adProviders = new ArrayList<>();

        for (int i=0;i<10;i++){
            if (i==0) adProviders.add(new AdProviders("","Select to LearnMore",""));
            if (i<5&&i>0) adProviders.add(new AdProviders("","Name","http://saiakhil.com"));
            else adProviders.add(new AdProviders("","Namesssssssssssssss","http://saiakhil.com"));
        }

        ConsentDialog consentDialog = new ConsentDialog.Builder(this,adProviders)
                .withSelectionListeners(new ConsentSelectionListener() {
                    @Override
                    public void onPersonalisedAdsSelected() {

                    }

                    @Override
                    public void onNonPersonalisedAdsSelected() {

                    }

                    @Override
                    public void onPaidServiceSelected() {

                    }
                })
                .icon(getResources().getDrawable(R.mipmap.ic_launcher))
                .privacyUrl("https://saiakhil.com")
                .withPaidOption()
                .withPersonalisedAdsOption()
                .withPrivacyPolicyClickListener(new ConsentDialog.Builder.PrivacyPolicyClickListener() {
                    @Override
                    public void onPrivacyPolicyClicked(String privacyUrl) {

                    }
                })
                .withNonPersonalisedAdsOption()
                .appName("AppName")
                .build();

        consentDialog.show();
    }
}
