package com.example.hkrguide.InfoActivity.util;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

// Data Structure for Menu Entries

public class MenuEntry implements Serializable {

    public static class PhoneNumber implements Serializable {
        public final String DESCRIPTION, NUMBER;

        public PhoneNumber(String description, String number) {
            this.DESCRIPTION = description;
            this.NUMBER = number;
        }
    }

    public static class Link implements Serializable {
        public final String DESCRIPTION, URL;

        public Link(String description, String url) {
            this.DESCRIPTION = description;
            this.URL = url;
        }
    }

    public final String ENTRY_NAME, TITLE, BODY;

    public final List<MenuEntry> NEXT_ENTRIES;
    public final List<PhoneNumber> PHONE_NUMBERS;
    public final List<Link> LINKS;

    public MenuEntry(String entryName) {
        this(entryName, null, null);
    }

    public MenuEntry(String entryName, String title, String body) {
        this(entryName, title, body, null, null, null);
    }

    public MenuEntry(String entryName, List<MenuEntry> nextEntries) {
        this(entryName, null, null, nextEntries, null, null);
    }

    public MenuEntry(String entryName, String title, String body, @Nullable List<PhoneNumber> phoneNrs, @Nullable List<Link> links) {
        this(entryName, title, body, null, phoneNrs, links);
    }

    public MenuEntry(String entryName, String title, String body, List<MenuEntry> nextEntries, List<PhoneNumber> phoneNrs, List<Link> links) {
        this.ENTRY_NAME = entryName;
        this.TITLE = title;
        this.BODY = body;
        this.NEXT_ENTRIES = nextEntries;
        this.PHONE_NUMBERS = phoneNrs;
        this.LINKS = links;
    }
}
