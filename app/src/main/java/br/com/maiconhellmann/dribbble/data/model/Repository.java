package br.com.maiconhellmann.dribbble.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repository implements Parcelable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("owner")
    @Expose
    private Owner owner;

    @SerializedName("description")
    @Expose
    private String description;

    public Repository() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(fullName);
        dest.writeString(description);
    }

    public static final Parcelable.Creator<Repository> CREATOR = new Parcelable.Creator<Repository>() {
        public Repository createFromParcel(Parcel pc) {
            return new Repository(pc);
        }
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };

    public Repository(Parcel pc){
        id = pc.readInt();
        name = pc.readString();
        fullName = pc.readString();
        description= pc.readString();
    }
}