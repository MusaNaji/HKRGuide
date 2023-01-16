package com.example.hkrguide.InfoActivity.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hkrguide.InfoActivity.util.MenuEntry;
import com.example.hkrguide.R;

import java.util.Objects;

public class EntryFragment extends Fragment implements MenuEntriesRecyclerViewAdapter.ItemClickListener, PhoneNumbersRecyclerViewAdapter.ItemClickListener, LinksRecyclerViewAdapter.ItemClickListener {

    MenuEntriesRecyclerViewAdapter subEntriesAdapter;
    PhoneNumbersRecyclerViewAdapter phoneNrsAdapter;
    LinksRecyclerViewAdapter linksAdapter;

    MenuEntry entry;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_entry, container, false);

        // Receive Menu Entry from Bundle
        Bundle bundle = getArguments();
        entry = (MenuEntry) Objects.requireNonNull(bundle).get("entry");

        ((TextView) view.findViewById(R.id.text_entry_title)).setText(entry.TITLE);
        ((TextView) view.findViewById(R.id.text_entry_body)).setText(entry.BODY);





        /* Sub Entries Recycler */

        // Initialize Recycler View
        RecyclerView subEntriesRecyclerView = view.findViewById(R.id.recycler_sub_entries);

        // Set Recycler View Layout Manager
        LinearLayoutManager subEntriesManager = new LinearLayoutManager(this.getContext());
        subEntriesRecyclerView.setLayoutManager(subEntriesManager);

        // Create Recycler View Adapter
        // Data is passed here
        subEntriesAdapter = new MenuEntriesRecyclerViewAdapter(this.getContext(), entry.NEXT_ENTRIES);

        // Set Row Click Listener
        subEntriesAdapter.setClickListener(this);

        // Add Divider to Recycler View
        subEntriesRecyclerView.addItemDecoration(new DividerItemDecoration(subEntriesRecyclerView.getContext(), subEntriesManager.getOrientation()));

        // Connect Recycler View and its Adapter
        subEntriesRecyclerView.setAdapter(subEntriesAdapter);





        /* Phone Numbers Recycler */

        // Initialize Recycler View
        RecyclerView phoneNrsRecycler = view.findViewById(R.id.recycler_phoneNrs);

        // Set Recycler View Layout Manager
        LinearLayoutManager phoneNrsManager = new LinearLayoutManager(this.getContext());
        phoneNrsRecycler.setLayoutManager(phoneNrsManager);

        // Create Recycler View Adapter
        // Data is passed here
        phoneNrsAdapter = new PhoneNumbersRecyclerViewAdapter(this.getContext(), entry.PHONE_NUMBERS);

        // Set Row Click Listener
        phoneNrsAdapter.setClickListener(this);

        // Add Divider to Recycler View
        phoneNrsRecycler.addItemDecoration(new DividerItemDecoration(phoneNrsRecycler.getContext(), phoneNrsManager.getOrientation()));

        // Connect Recycler View and its Adapter
        phoneNrsRecycler.setAdapter(phoneNrsAdapter);





        /* Links Recycler */

        // Initialize Recycler View
        RecyclerView linksRecycler = view.findViewById(R.id.recycler_links);

        // Set Recycler View Layout Manager
        LinearLayoutManager linksManager = new LinearLayoutManager(this.getContext());
        linksRecycler.setLayoutManager(linksManager);

        // Create Recycler View Adapter
        // Data is passed here
        linksAdapter = new LinksRecyclerViewAdapter(this.getContext(), entry.LINKS);

        // Set Row Click Listener
        linksAdapter.setClickListener(this);

        // Add Divider to Recycler View
        linksRecycler.addItemDecoration(new DividerItemDecoration(linksRecycler.getContext(), linksManager.getOrientation()));

        // Connect Recycler View and its Adapter
        linksRecycler.setAdapter(linksAdapter);


        return view;
    }

    // When a entry is clicked
    // Switch to Entry Fragment
    // Push current fragment to back stack
    @Override
    public void onEntryClick(View view, int position) {
        // Bundle Menu Entry Information
        Bundle bundle = new Bundle();
        bundle.putSerializable("entry", entry.NEXT_ENTRIES.get(position));

        // Pass bundle to new Entry Fragment instance
        EntryFragment fragment = new EntryFragment();
        fragment.setArguments(bundle);

        // Switch to the new First Fragment instance
        new Handler().postDelayed(() -> {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.infoFragment, fragment).addToBackStack(null).commit();
        }, 250);
    }

    // When a phone number is clicked
    // Dial using Intent
    @Override
    public void onPhoneNumberClick(View view, int position) {
        String uri = "tel:" + entry.PHONE_NUMBERS.get(position).NUMBER.trim();

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    // When a link is clicked
    // Open browser using Intent
    @Override
    public void onLinkClick(View view, int position) {
        String uri = entry.LINKS.get(position).URL.trim();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }
}
