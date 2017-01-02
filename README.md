FragmentBack
============

Tiny library for handling hardware back button click in fragments.


Download
--------

Download the latest JAR:

[ ![Download](https://api.bintray.com/packages/skoumal/maven/fragment-back/images/download.svg) ](https://bintray.com/skoumal/maven/fragment-back/_latestVersion)

or via Gradle:

```groovy
compile 'net.skoumal.fragmentback:fragment-back:0.2.3'
```

Usage
-----

Let your Fragment implement BackFragment interface:

```java
public class MyFragment extends Fragment implements BackFragment {

    public boolean onBackPressed() {

        // -- your code --

        // return true if you want to consume back-pressed event
        return false;
    }

    public int getBackPriority() {
        // use apropriate priority here
        return NORMAL_BACK_PRIORITY;
    }
}
```

Second thing is to delegate back-pressed event in you activity. You have three options:

1. Inherit from BackFragment**AppCompatActivity**

```java
public class MainActivity extends BackFragmentAppCompatActivity {

    // -- your activity code --

}
```

2. Inherit from BackFragment**FragmentActivity**

```java
public class MainActivity extends BackFragmentFragmentActivity {

    // -- your activity code --

}
```

3. Manually redirect back-pressed events

```java
public class MainActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        // first ask your fragments to handle back-pressed event
        if(!BackFragmentHelper.fireOnBackPressedEvent(this)) {
            // lets do the default back action if fragments don't consume it
            super.onBackPressed();
        }
    }
}
```

### Handle backs in both Activity and Fragments

Sometimes it depends on current circumstances if back-pressed event will be handled in Activity or
in one of your Fragments. Lets try to enhance the example from first point to cover most common
use-cases:

```java
public class MainActivity extends BackFragmentAppCompatActivity {

    // -- your activity code --

    @Override
    public void onBackPressed() {

        if(yourConditionHere()) {
            // handle event here before fragments will get noticed
        } else if(!onBackPressedWithResult()) { // ask fragments to handle back
            // if fragments no don't care about back, handle it here
            if(someOtherCondition()) {
                // handle event here aftter fragments miss theirs chance to consume it
            } else {
                // fall back to default back behaviour
                super.onBackPressed();
            }
        } else {
            // back was handled by some fragment
        }
    }
}
```

### Adjust priority
We support five fragment priorities for ordering back-pressed event delivery:

* VERY_LOW_BACK_PRIORITY
* LOW_BACK_PRIORITY
* NORMAL_BACK_PRIORITY
* HIGH_BACK_PRIORITY
* VERY_HIGH_BACK_PRIORITY

This should cover majority of use-cases. If you need more fine control over priorities, feel free to
increase or decrease priority by number constant. Just keep increment/decrement under 100.

```java
public abstract class BaseFragment extends Fragment implements BackFragment {

    public int getBackPriority() {
        // little bit higher than normal priority
        return NORMAL_BACK_PRIORITY + 1;
    }
}
```

License
=======

    Copyright 2015 SKOUMAL, s.r.o.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
