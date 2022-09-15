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

package in.igcorporation.consentdialog;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProviderAdapter extends ArrayAdapter<AdProviders> {

    //adapter for settingUp Spinner

    private Context context;
    private int resource;
    private ArrayList<AdProviders> adProviders;

    ProviderAdapter(@NonNull Context context, int resource, @NonNull ArrayList<AdProviders> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.adProviders = objects;
    }

    @Override
    public int getCount() {
        return adProviders.size();

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return initView(position,convertView,parent);
    }

    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView==null) {
            convertView = LayoutInflater.from(context).inflate(resource,parent,false);
        }
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setText(adProviders.get(position).getName());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return initView(position,convertView,parent);
    }
}
