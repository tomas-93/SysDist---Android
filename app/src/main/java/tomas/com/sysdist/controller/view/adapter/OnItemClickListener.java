package tomas.com.sysdist.controller.view.adapter;

import android.view.View;

import tomas.com.sysdist.controller.view.adapter.AdapterList;

/**
 * Created by Tomas on 11/10/2015.
 */
public interface OnItemClickListener
{
    void onItecmClick(AdapterList.AdapterObject adapterObject, View view, int position, int list);
}
