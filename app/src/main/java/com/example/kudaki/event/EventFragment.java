package com.example.kudaki.event;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kudaki.R;
import com.example.kudaki.model.event.Event;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {
    @BindView(R.id.rvEvents)
    RecyclerView rvEvents;

    @BindView(R.id.eventToolbar)
    Toolbar toolbar;

    private List<Event> eventList;
    private EventAdapter eventAdapter;

    public EventFragment() {
        // Required empty public constructor
    }

    // dummy event loader
    private void loadEvents() {
        eventList.clear();
        eventList.add(new Event("Jambore 2011",
                "https://images.unsplash.com/photo-1553362200-d2f027c173e0?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        eventList.add(new Event("Jambore 2012",
                "https://images.unsplash.com/photo-1553362200-d2f027c173e0?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        eventList.add(new Event("Jambore 2013",
                "https://images.unsplash.com/photo-1553362200-d2f027c173e0?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        eventList.add(new Event("Jambore 2014",
                "https://images.unsplash.com/photo-1553362200-d2f027c173e0?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        eventList.add(new Event("Jambore 2015",
                "https://images.unsplash.com/photo-1553362200-d2f027c173e0?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        eventList.add(new Event("Jambore 2016",
                "https://images.unsplash.com/photo-1553362200-d2f027c173e0?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        eventList.add(new Event("Jambore 2017",
                "https://images.unsplash.com/photo-1553362200-d2f027c173e0?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        eventAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.event_menu, menu);
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.optSearchEvent).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        ButterKnife.bind(this, view);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        eventList = new ArrayList<>();
        eventAdapter = new EventAdapter(getActivity(), eventList);
        rvEvents.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvEvents.setAdapter(eventAdapter);
        loadEvents();

        setHasOptionsMenu(true);
        return view;
    }

}
