package com.nimi.ui.functions;

import com.nimi.ui.pages.Pages;

public class ProfileFunctions {

    public static void editCompanyProfile(String address01, String address02, String city, String country, String email,String mobileNumber){

        Pages.profilePage().enterAddressLine01(address01);
        Pages.profilePage().enterAddressLine02(address02);
        Pages.profilePage().enterCity(city);
        Pages.profilePage().enterCountry(country);
        Pages.profilePage().enterEmail(email);
        Pages.profilePage().clickOnCountryCodeDropdown();
        Pages.profilePage().clickOnSelectLK();
        Pages.profilePage().enterMobileNumber(mobileNumber);
        Pages.profilePage().clickOnPayDay();
        Pages.profilePage().clickOnSelectPayDay();
        Pages.profilePage().clickOnUpdate();
    }
}
