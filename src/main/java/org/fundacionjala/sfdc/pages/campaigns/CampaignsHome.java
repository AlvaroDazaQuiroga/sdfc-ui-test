package org.fundacionjala.sfdc.pages.campaigns;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import org.fundacionjala.sfdc.framework.common.CommonActions;
import org.fundacionjala.sfdc.pages.base.AbstractBasePage;


public class CampaignsHome extends AbstractBasePage {

    //region Locators

    @FindBy(xpath = "//*[@name='new' and @type='button']")
    @CacheLookup
    WebElement NewButtton;
    //endregion

    public NewCampaignForm clickNewButton() {
        CommonActions.clickElement(NewButtton);
        return new NewCampaignForm();
    }

    public CampaignProfile goCampaingProfile(String url) {
        driver.navigate().to(url);
        return new CampaignProfile();
    }
}