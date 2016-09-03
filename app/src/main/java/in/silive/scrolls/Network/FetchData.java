package in.silive.scrolls.Network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import in.silive.scrolls.Listeners.FetchDataListener;
import in.silive.scrolls.Listeners.UploaderListener;
import in.silive.scrolls.Util.Config;

/**
 * Created by AKG002 on 09-06-2016.
 */
public class FetchData extends AsyncTask<Void, Integer, String> {
    private FetchDataListener listener;
    private String urlString, entity = null;
    private int id;
    private String token = null;

    public FetchData() {
        super();
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (entity != null) {
                connection.setRequestMethod("POST");
//                connection.setDoInput(true);
//                connection.setDoOutput(true);
            }
//            connection.setConnectTimeout(60000);
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            connection.setDoInput(true);
//            connection.setDoOutput(true);

            connection.connect();
            if (entity != null) {
                OutputStream outputStream = connection.getOutputStream();
                byte[] bytes = entity.getBytes();
                int bufferLength = 1024;
                for (int i = 0; i < bytes.length; i += bufferLength) {
                    int progress = (int)((i / (float) bytes.length) * 100);
                    publishProgress(progress);
                    if (bytes.length - i >= bufferLength) {
                        outputStream.write(bytes, i, bufferLength);
                    } else {
                        outputStream.write(bytes, i, bytes.length - i);
                    }
                }
                publishProgress(100);
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(entity);
                writer.close();
                Log.d(Config.LOG, "Entity " + entity);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
                sb.append(line);
            String result = sb.toString();
            connection.disconnect();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void setArgs(String url, FetchDataListener listener, int id) {
        this.urlString = url;
        this.listener = listener;
        this.id = id;
    }

    public void setArgs(String url, FetchDataListener listener) {
        this.urlString = url;
        this.listener = listener;
    }

    public void setArgs(String url, String entity, FetchDataListener listener) {
        this.urlString = url;
        this.entity = entity;
        this.listener = listener;
    }

    public void setArgs(String url, String entity, FetchDataListener listener, int id) {
        this.urlString = url;
        this.listener = listener;
        this.entity = entity;
        this.id = id;
    }

    @Override
    protected void onPreExecute() {
        Log.i(Config.LOG + " url ", urlString);
        listener.preExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        if (result == null)
            result = "";
        Log.d(Config.LOG, " response : " + result);
        try {
            listener.postExecute(result, id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        if (listener instanceof UploaderListener)
        {
            ((UploaderListener) listener).setProgress(values[0]);
        }
        super.onProgressUpdate(values);
    }

    public void setArgs(String url, boolean b, String token, FetchDataListener listener) {
        this.urlString = url;
        this.token = token;
        this.listener = listener;
    }
}