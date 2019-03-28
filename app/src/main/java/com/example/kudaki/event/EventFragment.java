package com.example.kudaki.event;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kudaki.R;
import com.example.kudaki.model.event.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {

    @BindView(R.id.rvEvents) RecyclerView rvEvents;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        ButterKnife.bind(this, view);

        eventList = new ArrayList<>();
        eventAdapter = new EventAdapter(getActivity(), eventList);
        rvEvents.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvEvents.setAdapter(eventAdapter);
        loadEvents();

        return view;
    }

}
