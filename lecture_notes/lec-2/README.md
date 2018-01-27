# Android Basics, ViewGroups and common attributes
<center>![ViewStructure](./view_structure.png)</center><br>

## What is a ViewGroup?
A viewgroup is a parent class of all the views. It holds all the children views (and viewgroups) within, as depicted by the structure above.<br>

## Types of ViewGroups
- **Absolute Layout**
    - By using an Absolute Layout, you can specify the exact locations (x/y coordinates) of its children.
    - They are less flexible and harder to maintain, rarely used nowadays.
    - One needs to remember too many coordinate values for placing a view at a position, it would rather be much easier to remember a view with respect to which one needs to place a view on screen.<br>
    
**Usage**  

        
        <AbsoluteLayout xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="fill_parent"
            android:layout_height="fill_parent">
            <Button
               android:layout_width="100dp"
               android:layout_height="wrap_content"
               android:text="OK"
               android:layout_x="50px"
               android:layout_y="361px" />
            <Button
               android:layout_width="100dp"
               android:layout_height="wrap_content"
               android:text="Cancel"
               android:layout_x="225px"
               android:layout_y="361px" />
        </AbsoluteLayout> 

<br>
- **Frame Layout**
    - It is usually used to block out an area on the screen and display only a single child on screen.
    - If multiple children are used within it then all the children are placed on top of each other.
    - Position of children can only be controlled by assigning gravity to them.
    - Usually used to display single fragments on screen.<br>

**Usage**  

        
        <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android" 
            android:layout_width="match_parent"
            android:layout_height="match_parent">
   
            <ImageView 
               android:src="@drawable/ic_launcher"
               android:layout_height="250px"
               android:layout_width="250px"/>
            
            <TextView
               android:text="Frame Demo"
               android:layout_height="match_parent"
               android:layout_width="match_parent"
               android:gravity="center"/>
        </FrameLayout>

<br>

- **Linear Layout**
    - Aligns the children views either horizontally or vertically.
    - The attribute `android:orientation` specifies wheher to horizontally or vertically align children views.
    - We usually use the attribute `android:weight` in the children views/viewgroups to decide what percentage of the available space they should occupy.
    - An attribute `android:weightSum` defines the maximum weight sum, and is calculated as the sum of the `layout_weight` of all the children if not specified explicitly.<br><br>
        
        > **TRIVIA**: What would happen if the `weightSum` is less than the sum of weights given to children explicitly?  

- **Relative Layout**
    - Relative Layout enables you to specify how child views are positioned relative to each other.
    - The position of each view can be specified as relative to sibling elements or relative to the parent.<br><br>
        
        > Some common attribute usages in relative layout:
        - Difference between `android:layout_alignRight` and `android:layout_toRightOf` :
        `android:layout_alignRight` is used to align a view's rightmost edge to the rightmost edge of the specified view, whereas `android:layout_toRightOf` is used to place a view to the right of the specified view *ie* the left edge of a view is postioned to the right of the specified view.
        - Why to prefer `android:layout_toEndOf` instead of `android:layout_toRightOf`:
        The views have LTR(left-to-right) orientation by default *ie* they start from left and end towards their right, but this orientation can be changed to RTL(right-to-left) where views start from right and end towards left. In such cases, the views with the attribute `android:layout_toEndOf` will correctly align to the end w.r.t the view specified whereas `android:layout_toRightOf` will still align it towards the right.
    - Read more about Relative Layout [here](https://developer.android.com/guide/topics/ui/layout/relative.html) and [here](https://developer.android.com/reference/android/widget/RelativeLayout.html).<br><br>

        > **TRIVIA**: Relative Layout measures a view twice, whereas Linear Layout measures only once (if weights are not used)!<br>
        Sources: [Stack Overflow](https://stackoverflow.com/questions/4069037/is-a-relativelayout-more-expensive-than-a-linearlayout/17496262#17496262) and [Medium](https://medium.com/@vatsalbajpai/android-relative-layout-vs-linear-layout-f27bf3d8f74e)

<br>

- **


