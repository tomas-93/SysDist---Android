package tomas.com.sysdist.controller.view.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * Tomas Yussef Galicia Guzman
 */
public abstract class ListenerAdapter extends RecyclerView.OnScrollListener
{
    private boolean flag = false;
    @Override
    public void onScrollStateChanged(RecyclerView view, int newState)
    {
        super.onScrollStateChanged(view, newState);
        if(newState == RecyclerView.SCROLL_STATE_SETTLING && this.flag)
        {
           this.show();
        }else if(newState == RecyclerView.SCROLL_STATE_SETTLING && !this.flag)
        {
            this.hide();
        }

    }
    @Override
    public void onScrolled(RecyclerView view, int dx, int dy)
    {
        super.onScrolled(view, dx, dy);
        if(dy < 0)
        {
            this.flag = true;
        }else if(dy > 0)
        {
            this.flag = false;
        }


    }

    public abstract void show();

    public abstract void hide();

}
