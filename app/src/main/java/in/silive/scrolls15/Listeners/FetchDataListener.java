package in.silive.scrolls15.Listeners;

import org.json.JSONException;

/**
 * Created by AKG002 on 09-06-2016.
 */
public interface FetchDataListener {
    public int STATUS_CODE =0;
    void preExecute();

    void postExecute(String result, int id) throws JSONException;
}