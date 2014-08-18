package com.litz.Tools;

import android.util.SparseArray;
import android.view.View;

/**
 * ViewHolder模式超简洁写法
 * 
 * ImageView bananaView = ViewHolder.get(convertView, R.id.banana);
 * BananaPhone bananaPhone = getItem(position);
 * bananaView.setImageResource(bananaPhone.getBanana());
 * 
 * @author Administrator
 *
 */
public class ViewHolder {
    // I added a generic return type to reduce the casting noise in client code
    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
