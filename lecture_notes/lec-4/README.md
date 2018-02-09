# Fragments
A Fragment represents a behavior or a portion of user interface in an Activity. You can think of a fragment as a modular section of an activity, which has its own lifecycle, receives its own input events, and which you can add or remove while the activity is running (sort of like a "sub activity" that you can reuse in different activities).


### Types Of Fragments

1. `Static fragments` are placed within an activity layout and never change.

  ```XML
  <?xml version="1.0" encoding="utf-8"?>
  <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
      android:layout_width="match_parent"
      android:layout_height="match_parent"›

      <fragment
          android:layout_width="wrap_content"
          android:layout_height="match_parent"
          android:id="@+id/fragment" />1

  </LinearLayout>
```
2. `Dynamic fragments` are almost reconfigurable puzzle pieces. They have their own lifecycle, anatomy, classes and even subtypes, with the addition of their layouts (similar to Activity in many respects). Using a dynamic fragment simply requires the usage of a container to place that fragment in. This is normally a framed layout within a standard activity.

  - `Framework fragments` have been around since fragment’s inception, five years ago. They are rarely updated and do not work with AppCompatActivity, which is the current activities version.

  - `Support fragments` come from the version four support library and do work with AppCompatActivities. They are constantly updated alongside the support library, usually three or four times per year. As Android’s constantly updated, there are new features added to fragments, e.g. child fragments.

### Lifecycle
Fragment's lifecycle is directly affected by the host activity's lifecycle. For example, when the activity is paused, so are all fragments in it, and when the activity is destroyed, so are all fragments. So it is important to understand activity's lifecycle first.

  * #### Activity lifecycle
  ![](https://developer.android.com/images/activity_lifecycle.png)

  * #### Fragment lifecycle
  ![](https://developer.android.com/images/fragment_lifecycle.png)

    - `onAttach()` is called by the managing activity when a fragment is first attach.

    ```java
      @Override
      public void onAttach(Context context) {
          super.onAttach(context);
      }
    ```

    - `onCreateView()` onCreate() is called, followed by onCreateView(). onCreateView() is where you set up your user interface

    ```java
      @Nullable
      @Override
      public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
      }  
    ```
    - `onActivtyCreated()` Beyond the onCreateView(), onActivityCreated() is where the heavy lifting occurs. That is called in conjunction with an activity’s onCreate method, so anything that you may want to do within the latter, you would do here:

    ```java
      @Override
      public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
      }
    ```
    - `onDestroyView()`In the process of the fragments being broken down, onDestroyView() is called. That is where you clean up anything that goes on with your views to ensure that system resources themselves are saved. You want to make sure to unregister things like button clicks, text listeners or checkbox listeners in here:

    ```java
      @Override
      public void onDestroyView() {
          super.onDestroyView();
      }

    ```
    - `onDetach()` onDetach() is the last lifecycle method before a fragment is fully detached from an activity. At this point, the fragment is no longer available, and any type of call to get to that parent activity’s context through getActivity will return null, and cause a NullPointerException.

    ```java
      @Override
      public void onDetach() {
        super.onDetach();
      }
    ```
    - `newInstance()`One additional part of the setup for a fragment is to create a newInstance() method. That allows the creation of a fragment. It is where you set some default data in order to use, for example: title strings, or various pieces of primitive data, which you want to make sure is always there.
      + To use newInstance(), you must always create an empty constructor. That is part of what goes into creating a fragment; similar to activity in some sense. newInstance() takes a bundle, and uses key-value pairs to set and later get these various pieces of data in your onCreateView() method:

        ```java
        public static MyFragment newInstance() {
          Bundle args = new Bundle();
          MyFragment mFragment = new MyFragment();
          mFragment.setArguments(args);
          return mfragment;
        }
        ```

### Fragment Manager
A fragment manager is this encompassing method that helps manage fragments, pulling them in and out, moving them, adding them, removing and replacing them, and even helping in restoring them.

They manage a list of fragments with activity holding a list, and help perform transactions. The calls are changed using a fluent interface, which keeps you from creating unnecessary multiple fragment managers to do so.

```java
FragmentManager fm = getSupportFragmentManager();
```

Each FragmentManager has an equivalent with the type of fragment that you use. If you use framework fragments, it would be getFragmentManager(). In the case of support fragments, it is getSupportFragmentManager().

### Fragment ID, Fragment TAG
A fragment manager must know how to identify which fragment it is using, and it does that through either a fragment ID or tag. These work similar to findViewById(), to help determine which fragment itself to pull in for transactions.

`findFragmentById()` takes in the container layout’s ID, unlike what you may expect, like the fragment’s ID. The `findFragmentByTag()`, on the other hand, takes in a string constant, to determine which fragment is used (like the tag you use in debugging).

### Fragment Transaction
Transactions are the series of actions which bring your fragment in, out, or replacing them. They always start off with a beginTransaction() method, called by the FragmentManager. They are usually followed by a series of actions: add, replace, or others. There can be multiple actions as part of a transaction. They are always concluded with a commit() to ensure that every action has been committed.

```java
fm.beginTransaction()
        .add(R.id.fragment_list_container, mFragment)
        .commit();
```  

### Why not create one Activity with lots of Fragments?
* Increased complexity
* Harder Intent Handling
* Dfficult to read, maintain and savedInstanceState
* Risk of tight coupling
* Security concerns

### More to explore
* ViewPager
* Navigation Drawer.
* Headless fragments

### References
* [Tutorial on Vogella](http://www.vogella.com/tutorials/AndroidFragments/article.html)
* [Android Developer](
https://developer.android.com/guide/components/fragments.html)
* [Headless Fragments](http://luboganev.github.io/blog/headless-fragments/)
