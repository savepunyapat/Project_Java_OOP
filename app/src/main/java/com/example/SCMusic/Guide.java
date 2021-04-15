package com.example.SCMusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Guide extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guidepage);

        viewPager2 = findViewById(R.id.viewPagerImageSlider);
        back = findViewById(R.id.backGuide);
        back.setPaintFlags(back.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Guide.this,BookingActivity.class);
                startActivity(intent);
            }
        });

        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.guide1));
        sliderItems.add(new SliderItem(R.drawable.guide2));
        sliderItems.add(new SliderItem(R.drawable.guide3));
        sliderItems.add(new SliderItem(R.drawable.guide4));

        viewPager2.setAdapter(new SliderAdapter(sliderItems,viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1 - Math.abs(position);
                page.setScaleY(0.85f +r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
    }
}