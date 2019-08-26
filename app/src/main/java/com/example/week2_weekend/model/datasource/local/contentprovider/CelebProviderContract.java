package com.example.week2_weekend.model.datasource.local.contentprovider;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class CelebProviderContract {
    public static final String CONTENT_AUTHORITY = "com.example.week2_weekend.model.datasource.local.contentprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_CELEB = "celebrity";

    public static final class CelebEntry implements BaseColumns{
        public static final Uri CELEB_CONTENT_URI = CONTENT_URI.buildUpon().appendPath("celebrity").build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir" + CELEB_CONTENT_URI + "/celebrity";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item" + CELEB_CONTENT_URI + "/celebrity";

        public static Uri buildPhoneUri(long id) {
            return ContentUris.withAppendedId(CELEB_CONTENT_URI, id);
        }
    }
}
