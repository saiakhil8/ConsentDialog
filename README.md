# ConsentDialog   [![](https://jitpack.io/v/saiakhil90/ConsentDialog.svg)](https://jitpack.io/#saiakhil90/ConsentDialog)

A Fully Customisable, Simple and Easy to use Library for Custom Consent Form.Looks alike Admob Consent Form and Does everything we want.This Library is for those who have more than 12 adproviders in admob..However can be used for universal adProviders.

| MainLayout-Shown Immediately after Calling show        |Shown When users click on LearnHow in mainLayout|Shown When Users Clicks on NonPersonalisedButton|
|-------------|-------------|-------------|
 <img src="preview/preview-1.png" width="250">     | <img src="preview/preview-2.png" width="250"> | <img src="preview/preview-3.png" width="250"> 
 


 # Features
 
 * Fully Customisable (Colours Customisation will be added in future)
 * Google AdMob ConsentForm look Alike.
 * Simple Fast and Easy to use
 * Behaviour Similar to Admob Consent Form
 
 
 # How To Use
 
 AdProviders Class Contains 3 childs.Create an arrayList of adProviders
 
 ```
    ArrayList<AdProviders> adProviders = new ArrayList();
    
    adProviders.add(new AdProviders(id,name,privacyUrl))
 
    //Here id,name and privacyUrl are strings
 ```
 
 NextCreate dialog as it is
 
 
 ```
    //Pass the adProviders arraylist in the constructor
    
    ConsentDialog consentDialog = new ConsentDialog.Builder(this,adProviders)
                .withSelectionListeners(new ConsentSelectionListener() {
                    @Override
                    public void onPersonalisedAdsSelected() {
                      //Called when user clicks on Choose Personalised Button
                    }

                    @Override
                    public void onNonPersonalisedAdsSelected() {
                      //WHen clicks agree on NonPersonalisedPage
                    }

                    @Override
                    public void onPaidServiceSelected() {
                        //WHen user clicks paidButton
                    }
                })
                .appName("AppName")
                .icon(getResources().getDrawable(R.mipmap.ic_launcher))
                .privacyUrl("https://saiakhil.com")
                
           //Choose the Buttons u want
           
                .withPaidOption()
                .withPersonalisedAdsOption()
                .withNonPersonalisedAdsOption()
                
           //Set if u want privacyPolicyClickListener
                .withPrivacyPolicyClickListener(new ConsentDialog.Builder.PrivacyPolicyClickListener() {
                    @Override
                    public void onPrivacyPolicyClicked(String privacyUrl) {
                                         
                    }
                })   
                
           //Build     
           
                .build();

        //Show Consent Dialog
        consentDialog.show();
 
 
 
 ```
 
 
 
 Texts can be customised Completely
 
 ```
 
    
    ConsentDialog consentDialog = new ConsentDialog.Builder(this,adProviders)
                .withSelectionListeners(new ConsentSelectionListener() {
                    @Override
                    public void onPersonalisedAdsSelected() {
                      //Called when user clicks on Choose Personalised Button
                    }

                    @Override
                    public void onNonPersonalisedAdsSelected() {
                      //WHen clicks agree on NonPersonalisedPage
                    }

                    @Override
                    public void onPaidServiceSelected() {
                        //WHen user clicks paidButton
                    }
                })
                .appName("AppName")
                .icon(getResources().getDrawable(R.mipmap.ic_launcher))
                .privacyUrl("https://saiakhil.com")
                
           //Choose the Buttons u want
           
                .withPaidOption()
                .withPersonalisedAdsOption()
                .withNonPersonalisedAdsOption()
                
           //Customise Texts
                .agreeButtonText("Agree")
                .backButtonText("Back")
                .mainLabelText("We collect etc etc")
                .mainPageExplanationAndLearnHowLabel("Ex","LearnHow")
                
           //Build     
           
                .build();

        //Show Consent Dialog
        consentDialog.show();
 
 ```
 
 
 # Note
 
 * Set `.appName()` and `.icon()` compulsory.
 
 * Set Required Button..If u dont set Dialog wont show any ..Set Buttons in this way `.withPersonalisedAdsOption()`
 
 # Installation
 
 Add Jitpack to ur Project build.gradle
 
 ```
    repositories {
        maven { url 'https://jitpack.io' }
    }
 
 ```
 
 
 Add ConsentDialog to ur app Build.gradle
 
 ```
    implementation 'com.github.saiakhil90:ConsentDialog:v(LatestRelease)'
 
 ```
 
 # Credits
 
 This project was initiated by SaiAkhil V(IGCORPORATION.IN). You can contribute to this project by submitting issues or/and by forking this repo and sending a pull request.
 
 Follow us on:

[![Twitter](https://cdn.macrumors.com/article-new/2016/05/twitterlogo.jpg)](https://twitter.com/saiakhil13)

Hire Me On:

[![Upwork](https://www.3divs.com/upwork_icon.png)](https://www.upwork.com/o/profiles/users/_~013788d80534af363d/)

Author: [SaiAkhil](https://github.com/saiakhil90)


# License

```
    Copyright (C) 2018  VENKATA SAI AKHIL KUMAR VEMULA (IGCORPORATION.IN)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

```
     
