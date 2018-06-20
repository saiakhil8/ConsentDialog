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
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class ConsentDialog extends AppCompatDialog implements View.OnClickListener,AdapterView.OnItemSelectedListener{

    private Builder builder;
    private Context context;
    private ImageView mIconImageView;
    private TextView mAppName,mTopLabel,mMainLabel,mExplanataionLabel,mProviderLLearnHowLabel, mListProvidersEplanationLabel,mNonPersonalisedExplain,mNonPersonalisedLearnHow;
    private LinearLayout mMainLayout, mProviderPolicyLayout,mNonPersonalisedLayout;
    private Spinner mProviderSpinner;
    private Button mPersonalisedButton,mNonPersonalisedButton,mPaidButton,mBackButton, mBackNonPersonalisedButton,mAgreeButton;


    ConsentDialog(Context context, Builder builder) {
        super(context,R.style.MyAlertDialogTheme);
        this.context = context;
        this.builder = builder;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        setContentView(R.layout.dialog_layout);

        mIconImageView = findViewById(R.id.app_icon);

        mAppName = findViewById(R.id.app_name);
        mTopLabel = findViewById(R.id.topLabel);
        mMainLabel = findViewById(R.id.mainLabel);
        mExplanataionLabel = findViewById(R.id.explanation_label);
        mMainLayout = findViewById(R.id.mainLayout);
        mProviderPolicyLayout =  findViewById(R.id.providerUrlLayout);
        mNonPersonalisedLayout = findViewById(R.id.non_personalised_layout);
        mProviderLLearnHowLabel=findViewById(R.id.listProvidersLearnHow);
        mNonPersonalisedExplain = findViewById(R.id.nonPersonalisedExplanation);
        mNonPersonalisedLearnHow = findViewById(R.id.NonProvidersLearnHow);
        mAgreeButton = findViewById(R.id.agreeButtonNonPersonalised);
        mPersonalisedButton = findViewById(R.id.personalised_main_button);
        mNonPersonalisedButton = findViewById(R.id.nonpersonalised_main_button);
        mPaidButton = findViewById(R.id.paid_main_button);
        mBackButton = findViewById(R.id.backButtonProviderPolicy);
        mProviderSpinner = findViewById(R.id.spinner_providers);
        mBackNonPersonalisedButton = findViewById(R.id.backButtonNonPersonalised);
        mListProvidersEplanationLabel = findViewById(R.id.personalised_l_m_label);

        Objects.requireNonNull(mProviderLLearnHowLabel).setOnClickListener(this);
        mNonPersonalisedLearnHow.setOnClickListener(this);
        Objects.requireNonNull(mProviderSpinner).setOnItemSelectedListener(this);
        mAgreeButton.setOnClickListener(this);
        Objects.requireNonNull(mPersonalisedButton).setOnClickListener(this);
        Objects.requireNonNull(mNonPersonalisedButton).setOnClickListener(this);
        Objects.requireNonNull(mPaidButton).setOnClickListener(this);
        Objects.requireNonNull(mBackButton).setOnClickListener(this);
        mBackNonPersonalisedButton.setOnClickListener(this);


        initText();


    }

    private void initText(){

        String learnHow = String.format(context.getString(R.string.privacyurl_supporting_label),builder.appName);
        String label = String.format(context.getString(R.string.explanation_label),builder.appName)+" "+learnHow;
        //Checking if there is anyCustom Explanationlabel set by user
        if (!builder.explanationLabel.equals("")){
            learnHow = builder.learnHowLabel;
            label = builder.explanationLabel +" "+learnHow;
        }
        //Setting text
        mExplanataionLabel.setText(label);

        mTopLabel.setText(builder.topLabel);
        mMainLabel.setText(builder.mainLabel);

        //check if there is any LearnHow Label is set by user
        if (builder.listProvidersLearnHowLabel.equals("")) mProviderLLearnHowLabel.setText(String.format(context.getString(R.string.lable_learn),builder.appName));
        else mProviderLLearnHowLabel.setText(builder.listProvidersLearnHowLabel);

        //creating instance of adapter for spinner
        ProviderAdapter providerAdapter = new ProviderAdapter(context,android.R.layout.simple_spinner_dropdown_item,builder.adProviders);
        //Setting adapter for spinner
        mProviderSpinner.setAdapter(providerAdapter);


        //Checking if builder icon is null--If not setting the icon
        if (builder.icon!=null) mIconImageView.setImageDrawable(builder.icon);
        //Setting AppName at the top
        mAppName.setText(builder.appName);

        //Setting text for buttons which are in MainLayout
        mPersonalisedButton.setText(builder.mainPersonalisedButtonText);
        mNonPersonalisedButton.setText(builder.mainNonPersonalisedButtonText);
        mPaidButton.setText(builder.mainPaidButtonText);

        //Checking if there is any Custom ProviderExplanationLabel
        if (builder.mListProviderExplanation.equals("")) mListProvidersEplanationLabel.setText(String.format(context.getString(R.string.listProviders_layout_label),builder.appName));
        else mListProvidersEplanationLabel.setText(builder.mListProviderExplanation);

        //Checking if there is any nonProvderExplanation label
        if (builder.nonPersonalisedExplanation.equals("")) mNonPersonalisedExplain.setText(String.format(context.getString(R.string.non_personalised_explain),builder.appName));
        else mNonPersonalisedExplain.setText(builder.nonPersonalisedExplanation);

        //Checking if there is any CUstom NonPersonalised Page LearnHow
        if (builder.nonPersonalisedLearnHow.equals("")) mNonPersonalisedLearnHow.setText(String.format(context.getString(R.string.lable_learn),builder.appName));
        else mNonPersonalisedLearnHow.setText(builder.nonPersonalisedLearnHow);


        //Setting texts for back and agree Button
        mBackButton.setText(builder.backButtonText);
        mBackNonPersonalisedButton.setText(builder.backButtonText);

        mAgreeButton.setText(builder.agreeButtonText);

        //Setting Clickable Span for Explanationlabel in mainpage
        setClickableString(learnHow,label,mExplanataionLabel);

        //Setting Visibilty for Button based on user choice
        if (builder.isPersonalisedEnabled) mPersonalisedButton.setVisibility(View.VISIBLE);
        if (builder.isNonPersonalisedEnabled) mNonPersonalisedButton.setVisibility(View.VISIBLE);
        if (builder.isPaidEnabled) mPaidButton.setVisibility(View.VISIBLE);
    }


    //This Method Handles Setting Clickable Span for Explaination in mainLayout
    private void setClickableString(String clickableValue, String wholeValue, TextView yourTextView){
        SpannableString spannableString = new SpannableString(wholeValue);
        int startIndex = wholeValue.indexOf(clickableValue);
        int endIndex = startIndex + clickableValue.length();
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }

            @Override
            public void onClick(View widget) {
                //When Clickable Span is CLicked Show ListProvidersLayout
                setLayoutVisibility(mMainLayout,View.GONE,mProviderPolicyLayout,View.VISIBLE);
            }
        }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        yourTextView.setText(spannableString);
        yourTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }




    @Override
    public void onClick(View v) {

        int i = v.getId();
        //Check which is clicked
        if (i == R.id.personalised_main_button) {
            //Call OnPersonalisedAdsSelected
            builder.consentSelectionListener.onPersonalisedAdsSelected();
        }else if (i==R.id.backButtonProviderPolicy){
            //GO Back
            setLayoutVisibility(mMainLayout,View.VISIBLE,mProviderPolicyLayout,View.GONE);
        }else if (i==R.id.listProvidersLearnHow){
            //Clicked LearnHow of app--Open Privacy Url
            openPrivacyUrl(builder.privacyUrl);
        }else if (i==R.id.nonpersonalised_main_button){
            //NonPersonalised MainLayout button clicked--Show NonPersonalisedLayout
            setLayoutVisibility(mMainLayout,View.GONE,mNonPersonalisedLayout,View.VISIBLE);
        }else if(i==R.id.NonProvidersLearnHow){
            //LearnHow is clicked in NonProviders Layout..Open Privacy Url
            openPrivacyUrl(builder.privacyUrl);
        }else if (i==R.id.agreeButtonNonPersonalised){
            //Called when agree is clicked InNon Personalised Layout
            //OnNonPersonalisedAdsSelected is called
            builder.consentSelectionListener.onNonPersonalisedAdsSelected();
        }else if (i==R.id.paid_main_button){
            //Called when PaidButtonClicked and called OnPaidServiceSelected
            builder.consentSelectionListener.onPaidServiceSelected();
        }else if (i==R.id.backButtonNonPersonalised){
            //BackPressed GoBack
            setLayoutVisibility(mMainLayout,View.VISIBLE,mNonPersonalisedLayout,View.GONE);
        }
    }

    //Method for setting Visibilty of Layouts
    private void setLayoutVisibility(LinearLayout oneL,int oneLayout,LinearLayout twoL,int twoLayout){
        oneL.setVisibility(oneLayout);
        twoL.setVisibility(twoLayout);
    }


    //Called When An item of spinner is selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //If posistion 0 dont open Privacy url --We Just include Select to learnMore kind of text for ) index
        if (position==0) return;
        //Open Privacy Url when AdProvider selected
        openPrivacyUrl(builder.adProviders.get(position).getPrivacyUrl());

    }

    //Open Privacy Url when user clicks on LearnHow app uses Data or selects Adprovider
    private void openPrivacyUrl(String privacyUrl){

        //check if user wants clickListener or not..If yes..call that
        if (builder.privacyPolicyClickListener!=null){
            builder.privacyPolicyClickListener.onPrivacyPolicyClicked(privacyUrl);
            return;
        }

        try{
            Uri uri = Uri.parse(privacyUrl);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);

            //checking if there is an App which can handle this intent
            if (intent.resolveActivity(context.getPackageManager()) != null){
                //Set flag
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }catch (Exception e){
            Toast.makeText(context,context.getString(R.string.error_privacy_load),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    //Called when Spinner item is not selected
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public static class Builder{

        private Context context;
        private ConsentSelectionListener consentSelectionListener;
        private ArrayList<AdProviders> adProviders;
        private Drawable icon;
        private String appName;
        private String privacyUrl="";
        private boolean isPersonalisedEnabled=false,isNonPersonalisedEnabled=false,isPaidEnabled=false;
        private String topLabel,mainLabel,explanationLabel="", learnHowLabel ="",listProvidersLearnHowLabel="",nonPersonalisedLearnHow="",nonPersonalisedExplanation="",mListProviderExplanation="";
        private String mainPersonalisedButtonText,mainNonPersonalisedButtonText,mainPaidButtonText,agreeButtonText,backButtonText;
        private PrivacyPolicyClickListener privacyPolicyClickListener;



        public Builder(@NonNull  Context context,@NonNull ArrayList<AdProviders> adProviders){
            this.context = context;
            this.adProviders = adProviders;
            appName = context.getString(R.string.lib_name);
            topLabel = context.getString(R.string.supporting_label);
            mainLabel=context.getString(R.string.main_label);
            backButtonText=context.getString(R.string.back_lib);
            agreeButtonText=context.getString(R.string.agree_button);

            mainNonPersonalisedButtonText=context.getString(R.string.nonpersonalised_button);
            mainPersonalisedButtonText=context.getString(R.string.personalised_button);
            mainPaidButtonText=context.getString(R.string.pay_button);

        }

        public interface PrivacyPolicyClickListener{
            void onPrivacyPolicyClicked(String privacyUrl);
        }

        //For Setting LearnHow in ListProviders Layout
        public Builder listProvidersLearnHowLabel(String listProvidersLearnHowLabel){
            this.listProvidersLearnHowLabel = listProvidersLearnHowLabel;
            return this;
        }

        //For Setting Explanation in ListProviders Layout
        public Builder listProviderExplanation(String mListProviderExplanation){
            this.mListProviderExplanation = mListProviderExplanation;
            return this;
        }

        //For Setting Explanation in nonPersonalised Layout
        public Builder nonPersonalisedExplanation(String nonPersonalisedExplanation){
            this.nonPersonalisedExplanation = nonPersonalisedExplanation;
            return this;
        }

        //For Setting LearnHow in NonPersonalised Label
        public Builder nonPersonalisedLearnHow(String nonPersonalisedLearnHow){
            this.nonPersonalisedLearnHow = nonPersonalisedLearnHow;
            return this;
        }

        //For Setting Explanation and LearnHow in MainLayout
        public Builder mainPageExplanationAndLearnHowLabel(String explanationLabel,String learnHowLabel){
            this.explanationLabel = explanationLabel;
            this.learnHowLabel = learnHowLabel;
            return this;
        }

        //For Setting topLabel --Label below appname and icon
        public Builder topLabelText(String topLabel){
            this.topLabel = topLabel;
            return this;
        }

        //For setting MainLabel which is Bold in style
        public Builder mainLabelText(String mainLabel){
            this.mainLabel = mainLabel;
            return this;
        }

        //For setting text of agreeButton
        public Builder agreeButtonText(String agreeButtonText){
            this.agreeButtonText = agreeButtonText;
            return this;
        }

        //For setting text of backButton
        public Builder backButtonText(String backButtonText){
            this.backButtonText = backButtonText;
            return this;
        }

        //For setting Text of paidButton in mainLayout
        public Builder paidButtonText(String mainPaidButtonText){
            this.mainPaidButtonText = mainPaidButtonText;
            return this;
        }

        //For setting Text of Personalised Button in MainLayout
        public Builder personalisedButtonText(String mainPersonalisedButtonText){
            this.mainPersonalisedButtonText=mainPersonalisedButtonText;
            return this;
        }

        //For setting Text of NonPersonalised Button in mainlayout
        public Builder nonPersonalisedText(String mainNonPersonalisedButtonText){
            this.mainNonPersonalisedButtonText=mainNonPersonalisedButtonText;
            return this;
        }

        //For setting Up selectionListeners
        public Builder withSelectionListeners(ConsentSelectionListener consentSelectionListener){
            this.consentSelectionListener = consentSelectionListener;
            return this;
        }

        //For setting Icon
        public Builder icon(Drawable icon){
            this.icon = icon;
            return this;
        }

        //For setting appName--Important
        public Builder appName(String appName){
            this.appName = appName;
            return this;
        }

        //For Setting App PrivacyUrl
        public Builder privacyUrl(String privacyUrl){
            this.privacyUrl = privacyUrl;
            return this;
        }


        //For Enabling PersonalisedAds Button
        public Builder withPersonalisedAdsOption(){
            isPersonalisedEnabled = true;
            return this;
        }

        //For Enabling NonPersonalisedAds Button
        public Builder withNonPersonalisedAdsOption(){
            isNonPersonalisedEnabled = true;
            return this;
        }

        //For Enabling PaidButton
        public Builder withPaidOption(){
            isPaidEnabled = true;
            return this;
        }

        //for setting listener for privacyPolicyClick
        public Builder withPrivacyPolicyClickListener(PrivacyPolicyClickListener privacyPolicyClickListener){
            this.privacyPolicyClickListener = privacyPolicyClickListener;
            return this;
        }



        public ConsentDialog build(){
            return new ConsentDialog(context,this);
        }


    }
}
