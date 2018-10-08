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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.skoumal.fragmentback.BackFragment;

import androidx.fragment.app.Fragment;

/**
 * Simple fragment with low priority for back button event.
 */
public class FirstFragment extends Fragment implements BackFragment {

    TextView myTextView;

    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        myTextView = view.findViewById(R.id.my_text_view);
        ((TextView) view.findViewById(R.id.header_text_view)).setText(R.string.first_fragment);
        return view;
    }

    @Override
    public boolean onBackPressed() {

        // here should be your own logic
        if (TextUtils.isEmpty(myTextView.getText())) {
            myTextView.setText(getString(R.string.back_handled));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getBackPriority() {
        return LOW_BACK_PRIORITY;
    }
}
