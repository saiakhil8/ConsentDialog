/*
 *
 *     Copyright (C) 2018  VENKATA SAI AKHIL KUMAR VEMULA (IGCORPORATION.IN)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package in.igcorporation.consentform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
                .withNonPersonalisedAdsOption()
                .appName("AppName")
                .build();

        consentDialog.show();
    }
}
