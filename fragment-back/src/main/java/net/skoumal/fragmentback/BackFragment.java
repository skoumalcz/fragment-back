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

/**
 * Created by gingo on 16.6.2016.
 */
public interface BackFragment {

    int VERY_LOW_BACK_PRIORITY = -2000;

    int LOW_BACK_PRIORITY = -1000;

    int NORMAL_BACK_PRIORITY = 0;

    int HIGH_BACK_PRIORITY = 1000;

    int VERY_HIGH_BACK_PRIORITY = 2000;

    boolean onBackPressed();

    int getBackPriority();
}


