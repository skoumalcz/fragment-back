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
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.skoumal.fragmentback.BackFragment;

/**
 * Simple fragment with low priority for back button event.
 */
public class ParentFragment extends Fragment implements BackFragment {

    public ParentFragment() {
        // Required empty public constructor
    }

    public static ParentFragment newInstance() {
        ParentFragment fragment = new ParentFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parent, container, false);

        if(savedInstanceState == null) {
            getChildFragmentManager().beginTransaction()
                    .add(R.id.top_fragment, FirstFragment.newInstance())
                    .add(R.id.bottom_fragment, SecondFragment.newInstance())
                    .commit();
        }

        return view;
    }

    @Override
    public boolean onBackPressed() {
        return false; // we do not consume the event here
    }

    @Override
    public int getBackPriority() {
        return LOW_BACK_PRIORITY;
    }
}
