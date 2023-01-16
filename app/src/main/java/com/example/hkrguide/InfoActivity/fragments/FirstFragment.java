package com.example.hkrguide.InfoActivity.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hkrguide.InfoActivity.util.MenuEntry;
import com.example.hkrguide.R;

import java.util.List;

public class FirstFragment extends Fragment implements MenuEntriesRecyclerViewAdapter.ItemClickListener {

    // Recycler View Adapter
    MenuEntriesRecyclerViewAdapter adapter;

    // Menu Entries
    List<MenuEntry> entries = null;

    @SuppressWarnings("unchecked")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        // Receive Menu Entries from Bundle
        entries = (List<MenuEntry>) requireArguments().getSerializable("entries");
        if (entries == null) {
            return view;
        }

        // Initialize Recycler View
        RecyclerView recyclerView = view.findViewById(R.id.recycler_entries);

        // Set Recycler View Layout Manager
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(manager);

        // Create Recycler View Adapter
        // Data is passed here
        adapter = new MenuEntriesRecyclerViewAdapter(this.getContext(), entries);

        // Set Row Click Listener
        adapter.setClickListener(this);

        // Add Divider to Recycler View
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), manager.getOrientation()));

        // Connect Recycler View and its Adapter
        recyclerView.setAdapter(adapter);

        return view;
    }

    // When a row is clicked
    // Switch to Entry Fragment
    // Push current fragment to back stack
    @Override
    public void onEntryClick(View view, int position) {
        // Bundle Menu Entry Information
        Bundle bundle = new Bundle();
        bundle.putSerializable("entry", entries.get(position));

        // Pass bundle to new Entry Fragment instance
        EntryFragment fragment = new EntryFragment();
        fragment.setArguments(bundle);

        // Switch to the new First Fragment instance
        new Handler().postDelayed(() -> {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.infoFragment, fragment).addToBackStack(null).commit();
        }, 250);
    }
}