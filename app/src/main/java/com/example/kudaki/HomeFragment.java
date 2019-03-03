package com.example.kudaki;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.carouselEvent) CarouselView carouselEvent;

    private int events[] = {
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container,false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        carouselEvent.setPageCount(events.length);
        carouselEvent.setImageListener((position, imageView) ->
                imageView.setImageResource(events[position]));
    }

    @Override
    public void onResume() {
        super.onResume();
        carouselEvent.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                // to page event
                // ...
            }
        });
    }
}
