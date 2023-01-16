package com.example.hkrguide.InfoActivity.util;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

// Parses XML From Server

public class ContentXmlManager {

    private InputStream inputStream;         // File Input Stream
    private ZonedDateTime lastModifiedTime = null;  // Last Modified Time
    private List<MenuEntry> menuEntries;     // Menu Entries

    // Constructor
    public ContentXmlManager(InputStream inputXmlStream) throws IOException, XmlPullParserException {
        this.inputStream = inputXmlStream;
        this.menuEntries = new ArrayList<>();
        this.parse();
        this.inputStream.close();
    }

    // Get Last Modified Time
    public ZonedDateTime getLastModifiedTime() throws IllegalStateException {
        if (lastModifiedTime != null) {
            return lastModifiedTime;
        } else {
            throw new IllegalStateException();
        }
    }

    // Get Menu Entries
    public List<MenuEntry> getMenuEntries() throws IllegalStateException {
        if (menuEntries != null) {
            return menuEntries;
        } else {
            throw new IllegalStateException();
        }
    }

    // Start Parsing XML
    private void parse() throws IOException, XmlPullParserException {
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(inputStream, null);
        parser.nextTag();

        doParse(parser);
    }

    // Actual XML Parsing Implementation
    private void doParse(XmlPullParser parser) throws IOException, XmlPullParserException {
        while (parser.next() != XmlPullParser.END_TAG) {
            // Skip if not start tag
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            // Search for interested tags
            switch (parser.getName()) {
                case "lastModified":
                    // Read Last Modified Time
                    DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
                    lastModifiedTime = ZonedDateTime.parse(readText(parser), formatter);
                    Log.i("Test XML Parser", "Last Modified: " + lastModifiedTime.toString());
                    break;

                case "menuEntry":
                    // Read Menu Entries
                    menuEntries.add(readMenuEntry(parser));
                    break;

                default:
                    // Skip Other Tags
                    skip(parser);
            }
        }
    }

    // Read a Menu Entry
    // Recurse if nesting menu entries exist
    private MenuEntry readMenuEntry(XmlPullParser parser) throws IOException, XmlPullParserException {
        String entryName = parser.getAttributeValue(null, "entry");
        String title = "";
        String body = "";

        List<MenuEntry> subEntries = new ArrayList<>();
        List<MenuEntry.PhoneNumber> phoneNrs = new ArrayList<>();
        List<MenuEntry.Link> links = new ArrayList<>();

        while (parser.next() != XmlPullParser.END_TAG) {
            // Skip if not start tag
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            // Search for interested tags
            switch (parser.getName()) {
                case "title":
                    // Title Text
                    title = readText(parser);
                    break;
                case "body":
                    // Body Text
                    body = readText(parser);
                    break;
                case "menuEntry":
                    // If nested menu entries are found
                    // Recurse
                    subEntries.add(readMenuEntry(parser));
                    break;
                case "phoneNumber":
                    // Phone Number
                    String phoneNrDesc = parser.getAttributeValue(null, "description");
                    if (phoneNrDesc == null) {
                        phoneNrDesc = "";
                    }
                    phoneNrs.add(new MenuEntry.PhoneNumber(phoneNrDesc, readText(parser)));
                    break;
                case "link":
                    // Link
                    String linkDesc = parser.getAttributeValue(null, "description");
                    if (linkDesc == null) {
                        linkDesc = "";
                    }
                    links.add(new MenuEntry.Link(linkDesc, readText(parser)));
                    break;
                default:
                    // Skip Other Tags
                    skip(parser);
            }
        }

        return new MenuEntry(entryName, title, body, subEntries, phoneNrs, links);
    }

    // Read Text
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            result = result.replace("\\n", System.getProperty("line.separator"));
            parser.nextTag();
        }
        return result;
    }

    // Skip Tag
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }

        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.START_TAG:
                    depth += 1;
                    break;
                case XmlPullParser.END_TAG:
                    depth -= 1;
                    break;
            }
        }
    }
}
