# Networking in Android
This class explains the basic tasks involved in connecting to the network, monitoring the network connection (including connection changes), and giving users control over an app's network usage. It also describes how to parse and consume XML data.
## Connecting to the Network
In order to perform network operations in your application, your manifest must include the following permissions:

```XML
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

```

## Introducing Network Operations on a Separate Thread

To avoid creating an unresponsive UI, don't perform network operations on the UI thread. By default, Android 3.0 (API level 11) and higher requires you to perform network operations on a thread other than the main UI thread; if you don't, a NetworkOnMainThreadException is thrown.

The following Activity snippet uses a headless Fragment (headless fragments are fragments without a UI part, just google them) to encapsulate asynchronous network operations. Later, you will see how the Fragment implementation, NetworkFragment, accomplishes this. Your Activity should also implement the DownloadCallback interface, allowing the fragment to call back to the Activity in case it needs connectivity status or needs to send an update back to the UI.

```java
public class MainActivity extends FragmentActivity implements DownloadCallback {

    ...

    // Keep a reference to the NetworkFragment, which owns the AsyncTask object
    // that is used to execute network ops.
    private NetworkFragment mNetworkFragment;

    // Boolean telling us whether a download is in progress, so we don't trigger overlapping
    // downloads with consecutive button clicks.
    private boolean mDownloading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ...
        mNetworkFragment = NetworkFragment.getInstance(getSupportFragmentManager(), "https://www.google.com");
    }

    private void startDownload() {
        if (!mDownloading && mNetworkFragment != null) {
            // Execute the async download.
            mNetworkFragment.startDownload();
            mDownloading = true;
        }
    }
}
```

At the minimum, your DownloadCallback interface can consist of the following:

```java
public interface DownloadCallback<T> {
    interface Progress {
        int ERROR = -1;
        int CONNECT_SUCCESS = 0;
        int GET_INPUT_STREAM_SUCCESS = 1;
        int PROCESS_INPUT_STREAM_IN_PROGRESS = 2;
        int PROCESS_INPUT_STREAM_SUCCESS = 3;
    }

    /**
     * Indicates that the callback handler needs to update its appearance or information based on
     * the result of the task. Expected to be called from the main thread.
     */
    void updateFromDownload(T result);

    /**
     * Get the device's active network status in the form of a NetworkInfo object.
     */
    NetworkInfo getActiveNetworkInfo();

    /**
     * Indicate to callback handler any progress update.
     * @param progressCode must be one of the constants defined in DownloadCallback.Progress.
     * @param percentComplete must be 0-100.
     */
    void onProgressUpdate(int progressCode, int percentComplete);

    /**
     * Indicates that the download operation has finished. This method is called even if the
     * download hasn't completed successfully.
     */
    void finishDownloading();
}
```

Now, add the following implementations of the DownloadCallback interface methods to your Activity:

```java
@Override
public void updateFromDownload(String result) {
    // Update your UI here based on result of download.
}

@Override
public NetworkInfo getActiveNetworkInfo() {
    ConnectivityManager connectivityManager =
            (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    return networkInfo;
}

@Override
public void onProgressUpdate(int progressCode, int percentComplete) {
    switch(progressCode) {
        // You can add UI behavior for progress updates here.
        case Progress.ERROR:
            ...
            break;
        case Progress.CONNECT_SUCCESS:
            ...
            break;
        case Progress.GET_INPUT_STREAM_SUCCESS:
            ...
            break;
        case Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:
            ...
            break;
        case Progress.PROCESS_INPUT_STREAM_SUCCESS:
            ...
            break;
    }
}

@Override
public void finishDownloading() {
    mDownloading = false;
    if (mNetworkFragment != null) {
        mNetworkFragment.cancelDownload();
    }
}

```

## Implementing a Headless Fragment to Encapsulate Network Operations

Since the NetworkFragment runs on the UI thread by default, it uses an AsyncTask to run the network operations on a background thread. This Fragment is considered headless because it doesn't reference any UI elements. Instead, it is only used to encapsulate logic and handle lifecycle events, leaving the host Activity to update the UI.

When using a subclass of AsyncTask to run network operations, you must be cautious that you don't create a memory leak in the case where the Activity that is referenced by the AsyncTask is destroyed before the AsyncTask finishes its background work. To ensure this doesn't happen, the following snippet clears any references to the Activity in the Fragment's onDetach() method.

```java
public class NetworkFragment extends Fragment {
    public static final String TAG = "NetworkFragment";

    private static final String URL_KEY = "UrlKey";

    private DownloadCallback mCallback;
    private DownloadTask mDownloadTask;
    private String mUrlString;

    /**
     * Static initializer for NetworkFragment that sets the URL of the host it will be downloading
     * from.
     */
    public static NetworkFragment getInstance(FragmentManager fragmentManager, String url) {
        NetworkFragment networkFragment = new NetworkFragment();
        Bundle args = new Bundle();
        args.putString(URL_KEY, url);
        networkFragment.setArguments(args);
        fragmentManager.beginTransaction().add(networkFragment, TAG).commit();
        return networkFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUrlString = getArguments().getString(URL_KEY);
        ...
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Host Activity will handle callbacks from task.
        mCallback = (DownloadCallback) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // Clear reference to host Activity to avoid memory leak.
        mCallback = null;
    }

    @Override
    public void onDestroy() {
        // Cancel task when Fragment is destroyed.
        cancelDownload();
        super.onDestroy();
    }

    /**
     * Start non-blocking execution of DownloadTask.
     */
    public void startDownload() {
        cancelDownload();
        mDownloadTask = new DownloadTask();
        mDownloadTask.execute(mUrlString);
    }

    /**
     * Cancel (and interrupt if necessary) any ongoing DownloadTask execution.
     */
    public void cancelDownload() {
        if (mDownloadTask != null) {
            mDownloadTask.cancel(true);
        }
    }

    ...
}
```

Now, you should implement a subclass of AsyncTask as a private inner class inside your Fragment:

```java
/**
 * Implementation of AsyncTask designed to fetch data from the network.
 */
private class DownloadTask extends AsyncTask<String, Integer, DownloadTask.Result> {

    private DownloadCallback<String> mCallback;

    DownloadTask(DownloadCallback<String> callback) {
        setCallback(callback);
    }

    void setCallback(DownloadCallback<String> callback) {
        mCallback = callback;
    }

     /**
     * Wrapper class that serves as a union of a result value and an exception. When the download
     * task has completed, either the result value or exception can be a non-null value.
     * This allows you to pass exceptions to the UI thread that were thrown during doInBackground().
     */
    static class Result {
        public String mResultValue;
        public Exception mException;
        public Result(String resultValue) {
            mResultValue = resultValue;
        }
        public Result(Exception exception) {
            mException = exception;
        }
    }

    /**
     * Cancel background network operation if we do not have network connectivity.
     */
    @Override
    protected void onPreExecute() {
        if (mCallback != null) {
            NetworkInfo networkInfo = mCallback.getActiveNetworkInfo();
            if (networkInfo == null || !networkInfo.isConnected() ||
                    (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                            && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
                // If no connectivity, cancel task and update Callback with null data.
                mCallback.updateFromDownload(null);
                cancel(true);
            }
        }
    }

    /**
     * Defines work to perform on the background thread.
     */
    @Override
    protected DownloadTask.Result doInBackground(String... urls) {
        Result result = null;
        if (!isCancelled() && urls != null && urls.length > 0) {
            String urlString = urls[0];
            try {
                URL url = new URL(urlString);
                String resultString = downloadUrl(url);
                if (resultString != null) {
                    result = new Result(resultString);
                } else {
                    throw new IOException("No response received.");
                }
            } catch(Exception e) {
                result = new Result(e);
            }
        }
        return result;
    }

    /**
     * Updates the DownloadCallback with the result.
     */
    @Override
    protected void onPostExecute(Result result) {
        if (result != null && mCallback != null) {
            if (result.mException != null) {
                mCallback.updateFromDownload(result.mException.getMessage());
            } else if (result.mResultValue != null) {
                mCallback.updateFromDownload(result.mResultValue);
            }
            mCallback.finishDownloading();
        }
    }

    /**
     * Override to add special behavior for cancelled AsyncTask.
     */
    @Override
    protected void onCancelled(Result result) {
    }
    ...
}

```


## Using HttpsUrlConnection to Fetch Data

In the snippet above, the doInBackground() method runs in a background thread and calls the helper method downloadUrl(). The downloadUrl() method should take the given URL and use it to perform an HTTP GET request. Once a connection has been established, you should use the method getInputStream() to retrieve the data as an InputStream. The following snippet uses the HttpsURLConnection API to accomplish this:

```java
/**
 * Given a URL, sets up a connection and gets the HTTP response body from the server.
 * If the network request is successful, it returns the response body in String form. Otherwise,
 * it will throw an IOException.
 */
private String downloadUrl(URL url) throws IOException {
    InputStream stream = null;
    HttpsURLConnection connection = null;
    String result = null;
    try {
        connection = (HttpsURLConnection) url.openConnection();
        // Timeout for reading InputStream arbitrarily set to 3000ms.
        connection.setReadTimeout(3000);
        // Timeout for connection.connect() arbitrarily set to 3000ms.
        connection.setConnectTimeout(3000);
        // For this use case, set HTTP method to GET.
        connection.setRequestMethod("GET");
        // Already true by default but setting just in case; needs to be true since this request
        // is carrying an input (response) body.
        connection.setDoInput(true);
        // Open communications link (network traffic occurs here).
        connection.connect();
        publishProgress(DownloadCallback.Progress.CONNECT_SUCCESS);
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpsURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code: " + responseCode);
        }
        // Retrieve the response body as an InputStream.
        stream = connection.getInputStream();
        publishProgress(DownloadCallback.Progress.GET_INPUT_STREAM_SUCCESS, 0);
        if (stream != null) {
            // Converts Stream to String with max length of 500.
            result = readStream(stream, 500);
        }
    } finally {
        // Close Stream and disconnect HTTPS connection.
        if (stream != null) {
            stream.close();
        }
        if (connection != null) {
            connection.disconnect();
        }
    }
    return result;
}
```

Note that the method getResponseCode() returns the connection's status code. This is a useful way of getting additional information about the connection. A status code of 200 indicates success.

## Convert the InputStream to a String

An InputStream is a readable source of bytes. Once you get an InputStream, it's common to decode or convert it into a target data type. For example, if you were downloading image data, you might decode and display it like this:

```java
InputStream is = null;
...
Bitmap bitmap = BitmapFactory.decodeStream(is);
ImageView imageView = (ImageView) findViewById(R.id.image_view);
imageView.setImageBitmap(bitmap);
```

In the example shown above, the InputStream represents the text of the response body. This is how you would convert the InputStream to a string so that the Activity can display it in the UI:

```java
/**
 * Converts the contents of an InputStream to a String.
 */
public String readStream(InputStream stream, int maxReadSize)
        throws IOException, UnsupportedEncodingException {
    Reader reader = null;
    reader = new InputStreamReader(stream, "UTF-8");
    char[] rawBuffer = new char[maxReadSize];
    int readSize;
    StringBuffer buffer = new StringBuffer();
    while (((readSize = reader.read(rawBuffer)) != -1) && maxReadSize > 0) {
        if (readSize > maxReadSize) {
            readSize = maxReadSize;
        }
        buffer.append(rawBuffer, 0, readSize);
        maxReadSize -= readSize;
    }
    return buffer.toString();
}
```

The sequence of events in the code so far is as follows:

1. The Activity starts a NetworkFragment and passes in a specified URL.
2. When a user action triggers the Activity's downloadData() method, the NetworkFragment executes the DownloadTask.
3. The AsyncTask method onPreExecute() runs first (on the UI thread) and cancels the task if the device is not connected to the Internet.
4. The AsyncTask method doInBackground() then runs on the background thread and calls the downloadUrl() method.
5. The downloadUrl() method takes a URL string as a parameter and uses an HttpsURLConnection object to fetch the web content as an InputStream.
6. The InputStream is passed to the readStream() method, which converts the stream to a string.
7. Finally, once the background work is complete, the AsyncTask's onPostExecute() method runs on the UI thread and uses the DownloadCallback to send the result back to the UI as a String.

## Surviving Configuration Changes

So far, you have successfully implemented an Activity that performs a network operation. But, if the user decides to change the device configuration (i.e. rotate the screen 90 degrees) while doInBackground() is running on the background thread, the Activity destroys and recreates itself, causing it to re-run onCreate() and reference a new NetworkFragment (see Runtime Changes guide). Therefore, the AsyncTask living in the original NetworkFragment will have a DownloadCallback that references the original Activity that can no longer update the UI. Thus, the network work done on the background thread will have been wasted.

To persist through these configuration changes, you need to retain your original Fragment and ensure that the reconstructed Activity references it. To accomplish this, you should make the following modifications to your code:

First, your NetworkFragment should call setRetainInstance(true) in its onCreate() method:

```java
@Override
public void onCreate(@Nullable Bundle savedInstanceState) {
    ...
    // Retain this Fragment across configuration changes in the host Activity.
    setRetainInstance(true);
}
```

Then, modify how you initialize the NetworkFragment in your static getInstance() method:

```java
public static NetworkFragment getInstance(FragmentManager fragmentManager, String url) {
    // Recover NetworkFragment in case we are re-creating the Activity due to a config change.
    // This is necessary because NetworkFragment might have a task that began running before
    // the config change occurred and has not finished yet.
    // The NetworkFragment is recoverable because it calls setRetainInstance(true).
    NetworkFragment networkFragment = (NetworkFragment) fragmentManager
            .findFragmentByTag(NetworkFragment.TAG);
    if (networkFragment == null) {
        networkFragment = new NetworkFragment();
        Bundle args = new Bundle();
        args.putString(URL_KEY, url);
        networkFragment.setArguments(args);
        fragmentManager.beginTransaction().add(networkFragment, TAG).commit();
    }
    return networkFragment;
}
```
Your app can now successfully pull data from the Internet!

Note that there are several other background thread management tools that can help you achieve this same goal. As your app grows in complexity, you might find that these other tools are better suited for your app. Instead of AsyncTask, options worth investigating are IntentService and AsyncTaskLoader.

Huh ! that was too much. I hope that now you will appreciate the beauty of networking libraries such as Retrofit and OKHTTP.
