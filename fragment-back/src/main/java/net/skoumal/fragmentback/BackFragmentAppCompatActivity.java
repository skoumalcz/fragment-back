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

import androidx.appcompat.app.AppCompatActivity;

public class BackFragmentAppCompatActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        if (!onBackPressedWithResult()) {
            super.onBackPressed();
        }
    }

    /**
     * Enhanced version of {@link #onBackPressed()} with indication if back press was consumed.
     * It does not call default implementation of {@link #onBackPressed()}, you have to call it
     * your self in cases you want standard back behaviour.
     *
     * @return true if back press was handled by some fragment
     */
    public boolean onBackPressedWithResult() {
        return BackFragmentHelper.fireOnBackPressedEvent(this);
    }
}
