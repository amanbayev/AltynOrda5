package kz.growit.altynorda.Models;

import org.json.JSONObject;

/**
 * Created by Талгат on 28.11.2015.
 */
public class Profile {
    private String UserId, Username, PhoneConfirmationCode, LastUpdate, CreatedDate, ImageFileName, EmailConfirmationCode, Firstname, Middlename, Lastname, Email, ShortUrl, Phone, Mobile;
    private int AccountType;
    private boolean IsEmailConfirmed, IsPhoneConfirmed, AllRequiredFieldsFilled, Enabled;

    public Profile(JSONObject profileJSON) {
        this.UserId = profileJSON.optString("UserId", "");
        this.Username = profileJSON.optString("Username", "");
        this.PhoneConfirmationCode = profileJSON.optString("PhoneConfirmationCode", "");
        this.LastUpdate = profileJSON.optString("LastUpdate", "");
        this.CreatedDate = profileJSON.optString("CreatedDate", "");
        this.ImageFileName = profileJSON.optString("ImageFileName", "");
        this.EmailConfirmationCode = profileJSON.optString("EmailConfirmationCode", "");
        this.Firstname = profileJSON.optString("Firstname", "");
        if (this.Firstname.equals("null")) this.Firstname = "";
        this.Middlename = profileJSON.optString("Middlename", "");
        if (this.Middlename.equals("null")) this.Middlename = "";
        this.Lastname = profileJSON.optString("Lastname", "");
        if (this.Lastname.equals("null")) this.Lastname = "";
        this.ShortUrl = profileJSON.optString("ShortUrl", "");
        this.Email = profileJSON.optString("Email", "");
        this.Phone = profileJSON.optString("Phone", "");
        this.Mobile = profileJSON.optString("Mobile", "");

        this.AccountType = profileJSON.optInt("AccountType", 1);
        this.IsEmailConfirmed = profileJSON.optBoolean("IsEmailConfirmed", false);
        this.IsPhoneConfirmed = profileJSON.optBoolean("IsPhoneConfirmed", false);
        this.AllRequiredFieldsFilled = profileJSON.optBoolean("AllRequiredFieldsFilled", false);
        this.Enabled = profileJSON.optBoolean("Enabled", false);
    }

    public String getUserId() {
        return UserId;
    }

    public String getUsername() {
        return Username;
    }

    public String getPhoneConfirmationCode() {
        return PhoneConfirmationCode;
    }

    public String getLastUpdate() {
        return LastUpdate;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public String getImageFileName() {
        return ImageFileName;
    }

    public String getEmailConfirmationCode() {
        return EmailConfirmationCode;
    }

    public String getFirstname() {
        return Firstname;
    }

    public String getMiddlename() {
        return Middlename;
    }

    public String getLastname() {
        return Lastname;
    }

    public String getEmail() {
        return Email;
    }

    public String getShortUrl() {
        return ShortUrl;
    }

    public String getPhone() {
        return Phone;
    }

    public String getMobile() {
        return Mobile;
    }

    public int getAccountType() {
        return AccountType;
    }

    public boolean isEmailConfirmed() {
        return IsEmailConfirmed;
    }

    public boolean isPhoneConfirmed() {
        return IsPhoneConfirmed;
    }

    public boolean isAllRequiredFieldsFilled() {
        return AllRequiredFieldsFilled;
    }

    public boolean isEnabled() {
        return Enabled;
    }
}
