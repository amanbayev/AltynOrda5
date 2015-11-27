package kz.growit.altynorda.Models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Талгат on 27.11.2015.
 */
public class AllPictures {

    private int Id, ListingId, PictureType, PictureSize, ParentPictureId;
    private String Dins, LastUpdate, CreatedDate, ImageUrl;

    public AllPictures(JSONObject jsonObject) {
        try {
            this.Id = jsonObject.getInt("Id");
            this.ListingId = jsonObject.getInt("ListingID");
            this.PictureSize = jsonObject.getInt("PictureSize");
            this.PictureType = jsonObject.getInt("PictureType");
            this.ParentPictureId = jsonObject.getInt("ParentPictureId");
            this.ImageUrl = jsonObject.getString("ImageUrl");
            this.Dins = jsonObject.getString("Dins");
            this.LastUpdate = jsonObject.getString("LastUpdate");
            this.CreatedDate = jsonObject.getString("CreatedDate");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return Id;
    }

    public int getListingId() {
        return ListingId;
    }

    public int getPictureType() {
        return PictureType;
    }

    public int getPictureSize() {
        return PictureSize;
    }

    public int getParentPictureId() {
        return ParentPictureId;
    }

    public String getDins() {
        return Dins;
    }

    public String getLastUpdate() {
        return LastUpdate;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public String getImageUrl() {
        return ImageUrl;
    }
}
