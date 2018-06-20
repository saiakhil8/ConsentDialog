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

package in.igcorporation.consentdialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
        textView.setText(adProviders.get(position).getName());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return initView(position,convertView,parent);
    }
}
