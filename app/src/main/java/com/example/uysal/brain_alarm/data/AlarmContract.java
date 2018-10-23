package com.example.uysal.brain_alarm.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
public final class AlarmContract{

    private AlarmContract(){}

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.example.uysal.brain_alarm.data";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.pets/pets/ is a valid path for
     * looking at pet data. content://com.example.android.pets/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_ALARMS = "alarms";

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class AlarmEntry implements BaseColumns{

        /**
         * The MIME type of the {@link #} for a list of ALARMS.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ALARMS;
        /**
         * The MIME type of the {@link #} for a single ALARM.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ALARMS;

        /** The content URI to access the alarm data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_ALARMS);

        /** Name of database table for alarms */
        public final static String TABLE_NAME = "alarms";

        /**
         * Unique ID number for the alarms (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the alarm.
         *
         * Type: TEXT
         */
        public final static String COLUMN_ALARM ="alarms";

        public final static String COLUMN_STATUS = "status";
    }
}