package org.fundacionjala.sfdc.pages.products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import org.fundacionjala.sfdc.framework.common.CommonActions;
import org.fundacionjala.sfdc.pages.base.AbstractBasePage;

/**
 * This class handle the product details.
 */
public class ProductDetail extends AbstractBasePage {

    private static final String CHECKED = "Checked";

    private static final String TITLE = "title";

    @FindBy(name = "del")
    @CacheLookup
    private WebElement deleteButton;

    // edit button
    @FindBy(name = "edit")
    @CacheLookup
    private WebElement editButton;

    // product name
    @FindBy(id = "Name_ileinner")
    @CacheLookup
    private WebElement productNameLabel;

    // product code
    @FindBy(id = "ProductCode_ileinner")
    @CacheLookup
    private WebElement productCodeLabel;

    // product description
    @FindBy(id = "Description_ileinner")
    @CacheLookup
    private WebElement productDescriptionLabel;

    // active flag
    @FindBy(id = "IsActive_chkbox")
    @CacheLookup
    private WebElement activeFlagImg;

    // product family
    @FindBy(id = "Family_ileinner")
    @CacheLookup
    private WebElement productFamilyLabel;

    /**
     * This method doing click on "Delete" button.
     */
    public void clickDeleteButton() {
        CommonActions.clickElement(deleteButton);
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
    }

    /**
     * This method doing click on "Edit" button.
     *
     * @return ProductForm page.
     */
    public ProductForm clickEditProduct() {
        CommonActions.clickElement(editButton);
        return new ProductForm();
    }

    /**
     * This method verify that product is displayed.
     *
     * @param product String with product.
     * @return Return true if product is displayed.
     */
    public boolean isProductDisplayed(final String product) {
        WebElement productContainer;
        try {
            productContainer = driver.findElement(By.linkText(product));
        } catch (WebDriverException e) {
            loggerManager.addWarnLog(ProductDetail.class.getName(), e.getMessage(), e);
            return false;
        }
        return isElementPresent(productContainer);
    }

    /**
     * This method verify that element is present.
     *
     * @param webElement WebElement with element.
     * @return Return true if element is present.
     */
    private boolean isElementPresent(WebElement webElement) {
        try {
            webElement.getText();
            return true;
        } catch (WebDriverException e) {
            loggerManager.addWarnLog(ProductDetail.class.getName(), e.getMessage(), e);
            return false;
        }
    }

    /**
     * This method verify that flag is active.
     *
     * @return Return true if is active.
     */
    public boolean isActiveFlag() {
        boolean result = false;
        String attributeState = activeFlagImg.getAttribute(TITLE);
        if (attributeState.equals(CHECKED)) {
            result = true;
        }
        return result;
    }

    /**
     * This method obtains the product family.
     *
     * @return String with product family.
     */
    public String getProductFamily() {
        return CommonActions.getText(productFamilyLabel);
    }

    /**
     * This method obtains the product name.
     *
     * @return String with product name.
     */
    public String getProductName() {
        return CommonActions.getText(productNameLabel);
    }

    /**
     * This method obtains the product code.
     *
     * @return String with product code.
     */
    public String getProductCode() {
        return CommonActions.getText(productCodeLabel);
    }

    /**
     * This method obtains the product code.
     *
     * @return String with product code.
     */
    public String getDescription() {
        return CommonActions.getText(productDescriptionLabel);
    }

}