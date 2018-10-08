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
package net.skoumal.fragmentback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * Created by gingo on 16.6.2016.
 */
public class BackFragmentHelper {

    private BackFragmentHelper() {
        // empty private constructor to avoid instantiation
    }

    /**
     * Fire {@link BackFragment#onBackPressed()} event on all {@link BackFragment} fragments in
     * given {@link FragmentActivity}.
     *
     * @param gActivity activity to be scanned for {@link BackFragment} instances
     * @return true if back was handled by some fragment
     */
    public static boolean fireOnBackPressedEvent(FragmentActivity gActivity) {
        List<Fragment> fragmentList = getAllActivityFragments(gActivity);
        return fireOnBackPressedEvent(fragmentList);
    }

    private static List<Fragment> getAllActivityFragments(FragmentActivity gActivity) {
        List<Fragment> fragmentList = gActivity.getSupportFragmentManager().getFragments();

        if (fragmentList != null && fragmentList.size() > 0) {
            List<Fragment> result = new ArrayList<>(fragmentList.size());

            for (Fragment f : fragmentList) {
                if (f != null) {
                    result.add(f);

                    List<Fragment> nestedFragmentList = f.getChildFragmentManager().getFragments();
                    if (nestedFragmentList != null && nestedFragmentList.size() > 0) {
                        result.addAll(nestedFragmentList);
                    }
                }
            }

            return result;
        } else {
            return new ArrayList<>(0);
        }
    }

    private static boolean fireOnBackPressedEvent(List<?> gFragmentList) {

        // find all fragments with back support
        List<BackFragment> backFragmentList = new ArrayList<>(gFragmentList.size());
        for (Object f : gFragmentList) {
            if (f instanceof BackFragment) {
                backFragmentList.add((BackFragment) f);
            }
        }

        // sort fragments by back priority
        Collections.sort(backFragmentList, new Comparator<BackFragment>() {
            @Override
            public int compare(BackFragment lhs, BackFragment rhs) {
                return rhs.getBackPriority() - lhs.getBackPriority();
            }
        });

        // send them onBackPressed event
        boolean handled = false;
        for (BackFragment f : backFragmentList) {
            handled = f.onBackPressed();

            if (handled) {
                break;
            }
        }

        return handled;
    }
}
