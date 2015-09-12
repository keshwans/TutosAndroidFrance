package com.github.florent37.myyoutube.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by florentchampigny on 17/06/15.
 */
public class ItemId implements Parcelable {
    String kind;
    String videoId;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    protected ItemId(Parcel in) {
        kind = in.readString();
        videoId = in.readString();
    }

    public static final Creator<ItemId> CREATOR = new Creator<ItemId>() {
        @Override
        public ItemId createFromParcel(Parcel in) {
            return new ItemId(in);
        }

        @Override
        public ItemId[] newArray(int size) {
            return new ItemId[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kind);
        dest.writeString(videoId);
    }
}
