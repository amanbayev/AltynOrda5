package kz.growit.altynorda.Models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Талгат on 27.11.2015.
 */
public class Comments {
    private int Id, ListingId;
    private String Text, AuthorId, PendingDate, ApprovedDate, RejectedDate, LastUpdate, CreatedDate,
            ReplyAuthorId, ParentId;
    private Profile profile;
    private ArrayList<Comments> ChildCommentList = new ArrayList<>();
    private boolean Enabled;

    public Comments(JSONObject resp) {
        try {
            this.Id = resp.optInt("Id", 0);
            this.ListingId = resp.optInt("ListingId", 0);
            this.Text = resp.optString("Text", "");
            this.AuthorId = resp.optString("AuthorId", "");
            this.ReplyAuthorId = resp.optString("ReplyAuthorId", "");
            this.PendingDate = resp.optString("PendingDate", "");
            this.ApprovedDate = resp.optString("ApprovedDate", "");
            this.RejectedDate = resp.optString("RejectedDate", "");
            this.LastUpdate = resp.optString("LastUpdate", "");
            String dateStr = resp.optString("CreatedDate", "");
            dateStr = dateStr.substring(6, dateStr.length() - 2);
            this.CreatedDate = dateStr;
            this.ParentId = resp.optString("ParentId", "");
            JSONObject profileJS = resp.optJSONObject("Profile");
            this.profile = new Profile(profileJS);
            this.Enabled = resp.optBoolean("Enabled", false);
            String childCommentsStr = resp.optString("ChildCommentList", "");
            if (!childCommentsStr.equals("null")) {
                JSONArray childComments = new JSONArray(childCommentsStr);
                for (int i = 0; i < childComments.length(); i++) {
                    JSONObject commentchild = childComments.getJSONObject(i);
                    this.ChildCommentList.add(new Comments(commentchild));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Date getDate() {
        return new Date(Long.valueOf(this.CreatedDate));
    }

    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        return sdf.format(this.getDate());
    }

    public String getImageUrl() {
        return "http://altynorda.kz" + this.profile.getImageFileName();
    }

    public String getCommentatorName() {
        return this.profile.getFirstname() + " " + this.profile.getLastname();
    }

    public int getId() {
        return Id;
    }

    public int getListingId() {
        return ListingId;
    }

    public String getText() {
        return Text;
    }

    public String getAuthorId() {
        return AuthorId;
    }

    public String getPendingDate() {
        return PendingDate;
    }

    public String getApprovedDate() {
        return ApprovedDate;
    }

    public String getRejectedDate() {
        return RejectedDate;
    }

    public String getLastUpdate() {
        return LastUpdate;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public String getReplyAuthorId() {
        return ReplyAuthorId;
    }

    public String getParentId() {
        return ParentId;
    }

    public ArrayList<Comments> getChildCommentList() {
        return ChildCommentList;
    }

    public boolean isEnabled() {
        return Enabled;
    }
}
