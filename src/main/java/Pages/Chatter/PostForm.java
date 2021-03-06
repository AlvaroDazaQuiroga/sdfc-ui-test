package Pages.Chatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Framework.CommonActions;
import Pages.Base.AbstractSectionFrame;


public class PostForm extends AbstractSectionFrame{

	
	@FindBy(id = "cke_26_contents")
	@CacheLookup
	private WebElement createTxtArea;	
	
	@FindBy(id = "publishersharebutton")
    @CacheLookup
    private WebElement shareBtn;
	
	
	@FindBy(name = "quickActionSubmitButton")
    @CacheLookup
    private WebElement saveBtn;
	
	@FindBy(id = "quickActionFeedBodyInput")
	@CacheLookup
    private WebElement editTxtArea;

	@FindBy(css = "iframe[class='cke_wysiwyg_frame cke_reset']")
	@CacheLookup
	private WebElement textAreaFrame;
	@FindBy(tagName = "body")
	@CacheLookup
	private WebElement textAreaField;
	
	private String postText;

	public void PostForm(){
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(textAreaFrame));
	}
	
	public PostForm setPostTxt(String postText) {
		this.postText = postText;
		Actions action = new Actions(driver);
		action.moveToElement(textAreaField).sendKeys(postText).build().perform();
		returnRoot();
		return this;
	}
	
	public PostForm setCommentTxt(String commentText, String postText) {
		this.postText = commentText;
		WebElement commentTxt = driver.findElement(By.xpath("//span[contains(.,'"+postText+"')]/following::textarea[contains(@class,'foobar cxnewcommenttext groupAtMentionsEnabled')]"));
		CommonActions.setValue(commentTxt, commentText);		
		return this;
	}
	
	public PostForm editPostTxt(String postTxt) {	
		this.postText = postTxt;
		CommonActions.setValue(editTxtArea, postTxt);
		return this;
		
	}
	public PostContainer clickShareBtn() {
		CommonActions.click(shareBtn);
		return new PostContainer().setPostTxt(postText);
	}
	
	public PostContainer clickSaveBtn() {
		CommonActions.click(saveBtn);
		return new PostContainer().setPostTxt(postText);
	}
	
	public PostContainer clickCommentBtn() {
		WebElement commentBtn = driver.findElement(By.xpath("//span[contains(.,'"+this.postText+"')]/following::input[@value = 'Comment']"));
		CommonActions.click(commentBtn);
		return new PostContainer().setPostTxt(this.postText);
	}

}
