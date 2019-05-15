package utility.castles.getfile.module.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.json.JSONObject;

public class ListviewAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;

    private Context mContext;
//    private String strAppID;
    private ViewHolder holder;
    private JSONObject mJSONObject;

    public ListviewAdapter(Context mContext,JSONObject mInputJSON){
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        mJSONObject = mInputJSON;

    }
    @Override
    public int getCount() {
        return mJSONObject.length();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
    private static class ViewHolder {

//        ImageView thumbImageView;
//        TextView titleTextView;
//        TextView descTextView;
    }
}

