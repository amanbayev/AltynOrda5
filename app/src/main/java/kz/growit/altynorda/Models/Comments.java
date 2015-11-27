package kz.growit.altynorda.Models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Талгат on 27.11.2015.
 */
public class Comments {
    private int Id, ListingId;
    private String Text, AuthorId, PendingDate, ApprovedDate, RejectedDate, LastUpdate, CreatedDate,
            ReplyAuthorId, ParentId;
    private ArrayList<Comments> ChildCommentList = new ArrayList<>();
    private boolean Enabled;

    public Comments(JSONObject resp) {
        try {
            this.Id = resp.getInt("Id");
            this.ListingId = resp.getInt("ListingId");
            this.Text = resp.getString("Text");
            this.AuthorId = resp.getString("AuthorId");
            this.ReplyAuthorId = resp.getString("ReplyAuthorId");
            this.PendingDate = resp.getString("PendingDate");
            this.ApprovedDate = resp.getString("ApprovedDate");
            this.RejectedDate = resp.getString("RejectedDate");
            this.LastUpdate = resp.getString("LastUpdate");
            this.CreatedDate = resp.getString("CreatedDate");
            this.ParentId = resp.getString("ParentId");
            this.Enabled = resp.getBoolean("Enabled");
            String childCommentsStr = resp.getString("ChildCommentList");
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
