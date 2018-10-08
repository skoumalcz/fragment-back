/*
 * Copyright 2015 SKOUMAL, s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.skoumal.fragmentback.example;

import android.os.Bundle;

import net.skoumal.fragmentback.BackFragmentAppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerActivity extends BackFragmentAppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewpager);

        viewPager = findViewById(R.id.view_pager);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return FirstFragment.newInstance();
                    case 1:
                        return SecondFragment.newInstance();
                    default:
                        throw new RuntimeException("Invalid item index.");
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }
}
