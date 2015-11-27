package kz.growit.altynorda.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Талгат on 27.11.2015.
 */
public class Listings {
    private int Id, AgencyId, AgentId, CityId, ExchangeId, PropertyTypeId,
            Floor, TotalFloors, RoomCount, RentType, BedCount,
            WallMaterialId, ConditionId, ListingStatusId;
    private String Title, Address, Price, Description, Username, UserId,
            Latitude, Longitude, TotalArea, KitchenArea, ConstructionYear, CeilingHeight,
            MaxNumberPeopleForReservation;
    private boolean IsZalog, IsDorm, IsMicrowave, IsSmokingAllowed, IsPool, IsToaster, IsCoffeePot,
            IsCableTV, IsParquet, IsRoofTerrace, IsTerrace, IsBalcony, IsGarage, IsPharmacy,
            IsKindergarten, IsBarbers, IsSchool, IsShoppingMall, IsRestaurant, IsBusStop, IsPark,
            IsAtm, IsParking, IsTrc, IsInternet, IsWifi, IsTv, IsPhone, IsWashingMachine, IsIron,
            IsHairDryer, IsAc, IsTeaPot, IsDishes, IsSheets, IsTowel, IsRefrigerator, IsPrepaid,
            IsReservable, IsShower, IsBath, IsPetAllowed, IsDocumentProvided, IsTransfer,
            IsHygieneItems, isFeatured, isPriceDropped, Enabled, Pending, Approved, Rejected;
    private String PendingDate, ApprovedDate, RejectedDate, LastUpdate, CreatedDate;
    private ArrayList<AllPictures> allPictures = new ArrayList<>();
    private ArrayList<Comments> allComments = new ArrayList<>();

    public Listings(JSONObject resp) {
        try {
            if (!resp.get("allPictures").equals("null")) {
                JSONArray pics = resp.getJSONArray("allPictures");
                for (int i = 0; i < pics.length(); i++) {
                    AllPictures item = new AllPictures(pics.getJSONObject(i));
                    this.allPictures.add(item);
                }
            }


            this.UserId = resp.optString("UserId");
            this.Id = resp.optInt("Id", 0);
            this.CityId = resp.optInt("CityId", 0);
            this.ExchangeId = resp.optInt("ExchangeId", 0);
            this.PropertyTypeId = resp.optInt("PropertyTypeId", 0);
            this.Floor = resp.optInt("Floor", 0);
            this.TotalFloors = resp.optInt("TotalFloors", 0);
            this.RoomCount = resp.optInt("RoomCount", 0);
            this.RentType = resp.optInt("RentType", 0);
            this.BedCount = resp.optInt("BedCount", 0);
            this.WallMaterialId = resp.optInt("WallMaterialId");
            this.ConditionId = resp.optInt("ConditionId", 0);
            this.ListingStatusId = resp.optInt("ListingStatusId", 0);
            this.AgencyId = resp.optInt("AgencyId", 0);
            this.AgentId = resp.optInt("AgentId", 0);
            this.Title = resp.optString("Title","");
            this.Address = resp.optString("Address","");
            this.Price = resp.optString("Price","");
            this.Description = resp.optString("Description","");
            this.Username = resp.optString("Username","");
            this.Latitude = resp.optString("Latitude","");
            this.Longitude = resp.optString("Longitude","");
            this.TotalArea = resp.optString("TotalArea","");
            this.KitchenArea = resp.optString("KitchenArea","");
            this.ConstructionYear = resp.optString("ConstructionYear","");
            this.CeilingHeight = resp.optString("CeilingHeight","");
            this.MaxNumberPeopleForReservation = resp.optString("MaxNumberPeopleForReservation","");


            JSONArray comments = resp.getJSONArray("allComments");

            if (comments.length() != 0) {
                for (int i = 0; i < comments.length(); i++) {
                    Comments item = new Comments(comments.getJSONObject(i));
                    allComments.add(item);
                }
            }
            this.PendingDate = resp.optString("PendingDate", "");
            this.ApprovedDate = resp.optString("ApprovedDate","");
            this.RejectedDate = resp.optString("RejectedDate","");
            this.LastUpdate = resp.optString("LastUpdate","");
            this.CreatedDate = resp.optString("CreatedDate","");
            this.IsZalog = resp.optBoolean("IsZalog", false);
            this.IsDorm = resp.optBoolean("IsDorm", false);
            this.IsMicrowave = resp.optBoolean("IsMicrowave", false);
            this.IsSmokingAllowed = resp.optBoolean("IsSmokingAllowed", false);
            this.IsPool = resp.optBoolean("IsPool", false);
            this.IsToaster = resp.optBoolean("IsToaster", false);
            this.IsCoffeePot = resp.optBoolean("IsCoffeePot", false);
            this.IsCableTV = resp.optBoolean("IsCableTV", false);
            this.IsParquet = resp.optBoolean("IsParquet", false);
            this.IsRoofTerrace = resp.optBoolean("IsRoofTerrace", false);
            this.IsTerrace = resp.optBoolean("IsTerrace", false);
            this.IsBalcony = resp.optBoolean("IsBalcony", false);
            this.IsGarage = resp.optBoolean("IsGarage", false);
            this.IsPharmacy = resp.optBoolean("IsPharmacy", false);
            this.IsKindergarten = resp.optBoolean("IsKindergarten", false);
            this.IsBarbers = resp.optBoolean("IsBarbers", false);
            this.IsSchool = resp.optBoolean("IsSchool", false);
            this.IsShoppingMall = resp.optBoolean("IsShoppingMall", false);
            this.IsRestaurant = resp.optBoolean("IsRestaurant", false);
            this.IsBusStop = resp.optBoolean("IsBusStop", false);
            this.IsPark = resp.optBoolean("IsPark", false);
            this.IsAtm = resp.optBoolean("IsAtm", false);
            this.IsParking = resp.optBoolean("IsParking", false);
            this.IsTrc = resp.optBoolean("IsTrc", false);
            this.IsInternet = resp.optBoolean("IsInternet", false);
            this.IsWifi = resp.optBoolean("IsWifi", false);
            this.IsTv = resp.optBoolean("IsTv", false);
            this.IsPhone = resp.optBoolean("IsPhone", false);
            this.IsWashingMachine = resp.optBoolean("IsWashingMachine", false);
            this.IsIron = resp.optBoolean("IsIron", false);
            this.IsHairDryer = resp.optBoolean("IsHairDryer", false);
            this.IsAc = resp.optBoolean("IsAc", false);
            this.IsTeaPot = resp.optBoolean("IsTeaPot", false);
            this.IsDishes = resp.optBoolean("IsDishes", false);
            this.IsSheets = resp.optBoolean("IsSheets", false);
            this.IsTowel = resp.optBoolean("IsTowel", false);
            this.IsRefrigerator = resp.optBoolean("IsRefrigerator", false);
            this.IsPrepaid = resp.optBoolean("IsPrepaid", false);
            this.IsZalog = resp.optBoolean("IsZalog", false);
            this.IsReservable = resp.optBoolean("IsReservable", false);
            this.IsShower = resp.optBoolean("IsShower", false);
            this.IsBath = resp.optBoolean("IsBath", false);
            this.IsPetAllowed = resp.optBoolean("IsPetAllowed", false);
            this.IsDocumentProvided = resp.optBoolean("IsDocumentProvided", false);
            this.IsTransfer = resp.optBoolean("IsTransfer", false);
            this.IsHygieneItems = resp.optBoolean("IsHygieneItems", false);
            this.isFeatured = resp.optBoolean("isFeatured", false);
            this.isPriceDropped = resp.optBoolean("isPriceDropped", false);
            this.Enabled = resp.optBoolean("Enabled", false);
            this.Pending = resp.optBoolean("Pending", false);
            this.Approved = resp.optBoolean("Approved", false);
            this.Rejected = resp.optBoolean("Rejected", false);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return Id;
    }

    public int getAgencyId() {
        return AgencyId;
    }

    public int getAgentId() {
        return AgentId;
    }

    public int getCityId() {
        return CityId;
    }

    public int getExchangeId() {
        return ExchangeId;
    }

    public int getPropertyTypeId() {
        return PropertyTypeId;
    }

    public int getFloor() {
        return Floor;
    }

    public int getTotalFloors() {
        return TotalFloors;
    }

    public int getRoomCount() {
        return RoomCount;
    }

    public int getRentType() {
        return RentType;
    }

    public int getBedCount() {
        return BedCount;
    }

    public int getWallMaterialId() {
        return WallMaterialId;
    }

    public int getConditionId() {
        return ConditionId;
    }

    public int getListingStatusId() {
        return ListingStatusId;
    }

    public String getTitle() {
        return Title;
    }

    public String getAddress() {
        return Address;
    }

    public String getPrice() {
        return Price;
    }

    public String getDescription() {
        return Description;
    }

    public String getUsername() {
        return Username;
    }

    public String getUserId() {
        return UserId;
    }

    public String getLatitude() {
        return Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public String getTotalArea() {
        return TotalArea;
    }

    public String getKitchenArea() {
        return KitchenArea;
    }

    public String getConstructionYear() {
        return ConstructionYear;
    }

    public String getCeilingHeight() {
        return CeilingHeight;
    }

    public String getMaxNumberPeopleForReservation() {
        return MaxNumberPeopleForReservation;
    }

    public boolean isZalog() {
        return IsZalog;
    }

    public boolean isDorm() {
        return IsDorm;
    }

    public boolean isMicrowave() {
        return IsMicrowave;
    }

    public boolean isSmokingAllowed() {
        return IsSmokingAllowed;
    }

    public boolean isPool() {
        return IsPool;
    }

    public boolean isToaster() {
        return IsToaster;
    }

    public boolean isCoffeePot() {
        return IsCoffeePot;
    }

    public boolean isCableTV() {
        return IsCableTV;
    }

    public boolean isParquet() {
        return IsParquet;
    }

    public boolean isRoofTerrace() {
        return IsRoofTerrace;
    }

    public boolean isTerrace() {
        return IsTerrace;
    }

    public boolean isBalcony() {
        return IsBalcony;
    }

    public boolean isGarage() {
        return IsGarage;
    }

    public boolean isPharmacy() {
        return IsPharmacy;
    }

    public boolean isKindergarten() {
        return IsKindergarten;
    }

    public boolean isBarbers() {
        return IsBarbers;
    }

    public boolean isSchool() {
        return IsSchool;
    }

    public boolean isShoppingMall() {
        return IsShoppingMall;
    }

    public boolean isRestaurant() {
        return IsRestaurant;
    }

    public boolean isBusStop() {
        return IsBusStop;
    }

    public boolean isPark() {
        return IsPark;
    }

    public boolean isAtm() {
        return IsAtm;
    }

    public boolean isParking() {
        return IsParking;
    }

    public boolean isTrc() {
        return IsTrc;
    }

    public boolean isInternet() {
        return IsInternet;
    }

    public boolean isWifi() {
        return IsWifi;
    }

    public boolean isTv() {
        return IsTv;
    }

    public boolean isPhone() {
        return IsPhone;
    }

    public boolean isWashingMachine() {
        return IsWashingMachine;
    }

    public boolean isIron() {
        return IsIron;
    }

    public boolean isHairDryer() {
        return IsHairDryer;
    }

    public boolean isAc() {
        return IsAc;
    }

    public boolean isTeaPot() {
        return IsTeaPot;
    }

    public boolean isDishes() {
        return IsDishes;
    }

    public boolean isSheets() {
        return IsSheets;
    }

    public boolean isTowel() {
        return IsTowel;
    }

    public boolean isRefrigerator() {
        return IsRefrigerator;
    }

    public boolean isPrepaid() {
        return IsPrepaid;
    }

    public boolean isReservable() {
        return IsReservable;
    }

    public boolean isShower() {
        return IsShower;
    }

    public boolean isBath() {
        return IsBath;
    }

    public boolean isPetAllowed() {
        return IsPetAllowed;
    }

    public boolean isDocumentProvided() {
        return IsDocumentProvided;
    }

    public boolean isTransfer() {
        return IsTransfer;
    }

    public boolean isHygieneItems() {
        return IsHygieneItems;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public boolean isPriceDropped() {
        return isPriceDropped;
    }

    public boolean isEnabled() {
        return Enabled;
    }

    public boolean isPending() {
        return Pending;
    }

    public boolean isApproved() {
        return Approved;
    }

    public boolean isRejected() {
        return Rejected;
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

    public ArrayList<AllPictures> getAllPictures() {
        return allPictures;
    }

    public ArrayList<Comments> getAllComments() {
        return allComments;
    }
}
