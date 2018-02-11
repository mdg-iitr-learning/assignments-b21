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

    
- `onCreate()` is called when the system first creates the activity.
    
```java
@Override
public void onCreate(Bundle savedInstanceState) {
    // call the super class onCreate to complete the creation of activity like
    // the view hierarchy
    super.onCreate(savedInstanceState);

    // recovering the instance state
    if (savedInstanceState != null) {
         GameState = savedInstanceState.getString(GAME_STATE_KEY);
    }

    // set the user interface layout for this Activity
    // the layout file is defined in the project res/layout/main_activity.xml file
    setContentView(R.layout.main_activity);
}
```
    
- `onStart()` is called as the app prepares for the activity to enter the foreground and become interactive. 

```java
 @Override
 protected void onStart() {
     super.onStart();
 }
```
 
- `onResume()`  is called when the activity enters the resumed state, and comes to the foreground. This is the state in        which the app interacts with the user.
    
```java
@Override
public void onResume() {
    super.onResume();  // Always call the superclass method first
}
```
- `onPause()` is called when the activity loses focus but is partially visible.

```java
@Override
public void onPause() {
    super.onPause();  // Always call the superclass method first
}
```    

- `onStop()` When your activity is no longer visible to the user, it has entered the Stopped state, and the system invokes the onStop() callback. You should also use onStop() to perform relatively CPU-intensive shutdown operations. 

```java
@Override
protected void onStop() {
    // call the superclass method first
    super.onStop();

    // save the note's current draft, because the activity is stopping
    // and we want to be sure the current note progress isn't lost.
    saveToSharedPreferences();

    // do this update in background on an AsyncQueryHandler or equivalent
    someBackgroundTask();
}
```

- `onDestroy()` Called before the activity is destroyed. This is the final call that the activity receives.

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

### Loaders
Benefits:
* Loaders run on separate threads to prevent janky or unresponsive UI.
* Loaders simplify thread management by providing callback methods when events occur.
* Loaders persist and cache results across configuration changes to prevent duplicate queries.
* Loaders can implement an observer to monitor for changes in the underlying data source. For example, CursorLoader    automatically registers a ContentObserver to trigger a reload when data changes.

Built in sub-classes:
* AsyncTaskLoader - an abstract loader that provides an AsyncTask to perform load operations on a separate thread.
* CursorLoader - a concrete subclass of AsyncTaskLoader for asynchronously loading data from a ContentProvider. It queries ContentResolver and returns a Cursor.

```java
public static class JsonAsyncTaskLoader extends AsyncTaskLoader<List<String>> {
  
  private List<String> mData;
  private FileObserver mFileObserver;
  public JsonAsyncTaskLoader(Context context) {
    super(context);
  }
  @Override
  protected void onStartLoading() {
    if (mData != null) {
      // Use cached data
      deliverResult(mData);
    }
    if (mFileObserver == null) {
      String path = new File(
          getContext().getFilesDir(), "downloaded.json").getPath();
      mFileObserver = new FileObserver(path) {
          @Override
          public void onEvent(int event, String path) {
            // Notify the loader to reload the data
            onContentChanged();
          }
      };
      mFileObserver.startWatching();
    }
    if (takeContentChanged() || mData == null) {
      // Something has changed or we have no data,
      // so kick off loading it
      forceLoad();
    }
  }
  @Override
  public List<String> loadInBackground() {
    // This is on a background thread
    // store the results of the background operation in a variable named data
    return data;
  }
  @Override
  public void deliverResult(List<String> data) {
    // We’ll save the data for later retrieval
    mData = data;
    super.deliverResult(data);
  }
  protected void onReset() {
    // Stop watching for file changes
    if (mFileObserver != null) {
      mFileObserver.stopWatching();
      mFileObserver = null;
    }
  }
}
```

### Loader Manager
```java
// Prepare the loader.  Either re-connect with an existing one,
// or start a new one.
getSupportLoaderManager().initLoader(0, null, this);
```

The `initLoader()` call ensures that a loader is initialized and active. This is generally called in onCreate() or onActivityCreated() . 

`restartLoader()` method in LoaderManager which gives you the ability to force a reload. In most cases, this shouldn’t be necessary if the Loader is managing its own listeners, but it is useful in cases where you want to pass in a different Bundle — you’ll find your existing Loader is destroyed and a new call to onCreateLoader() is done.

LoaderCallbacks is where everything actually happens. And by ‘everything’, we mean three callbacks:
* onCreateLoader() — here’s where you construct the actual Loader instance
* onLoadFinished() — this is where the results you deliver appear
* onLoaderReset() — your chance to clean up any references to the now reset Loader data

![](https://i.stack.imgur.com/6RauY.png)

### ViewModel
The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.

```java
public class MyViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;
    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<List<Users>>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // Do an asyncronous operation to fetch users.
    }
}
```

You can then access the list from an activity as follows:

```java
public class MyActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.

        MyViewModel model = ViewModelProviders.of(this).get(MyViewModel.class);
        model.getUsers().observe(this, users -> {
            // update UI
        });
    }
}
```

If the activity is re-created, it receives the same MyViewModel instance that was created by the first activity. When the owner activity is finished, the framework calls the ViewModel objects's onCleared() method so that it can clean up resources.

![](https://developer.android.com/images/topic/libraries/architecture/viewmodel-lifecycle.png)

View Model is also very helpful for sharing data between fragments.

### Live Data

LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.

```java
public class NameViewModel extends ViewModel {

// Create a LiveData with a String
private MutableLiveData<String> mCurrentName;

    public MutableLiveData<String> getCurrentName() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<String>();
        }
        return mCurrentName;
    }

// Rest of the ViewModel...
}
```

Generally, LiveData delivers updates only when data changes, and only to active observers. An exception to this behavior is that observers also receive an update when they change from an inactive to an active state.

```java
public class NameActivity extends AppCompatActivity {

    private NameViewModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the ViewModel.
        mModel = ViewModelProviders.of(this).get(NameViewModel.class);

        // Create the observer which updates the UI.
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                // Update the UI, in this case, a TextView.
                mNameTextView.setText(newName);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mModel.getCurrentName().observe(this, nameObserver);
    }
}
```

### Saving States
When it's time for the user to return to the activity, there are two possible scenarios for recreating the activity:

The activity is recreated after having been stopped by the system. The activity has the query saved in an onSaveInstanceState() bundle, and should pass the query to the ViewModel. The ViewModel sees that it has no search results cached, and delegates loading the search results, using the given search query.

The activity is created after a configuration change. The activity has the query saved in an onSaveInstanceState() bundle, and the ViewModel already has the search results cached. You pass the query from the onSaveInstanceState() bundle to the ViewModel, which determines that it already has loaded the necessary data and that it does not need to re-query the database.

### More to explore
* ViewPager
* Navigation Drawer.
* Headless fragments

### References
* [Tutorial on Vogella](http://www.vogella.com/tutorials/AndroidFragments/article.html)
* [Android Developer](
https://developer.android.com/guide/components/fragments.html)
* [Headless Fragments](http://luboganev.github.io/blog/headless-fragments/)
