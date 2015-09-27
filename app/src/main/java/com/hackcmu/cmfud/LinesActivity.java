package com.hackcmu.cmfud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

public class LinesActivity extends AppCompatActivity {

    private Pubnub pubnub;
    final String pubKey = "pub-c-51f1e02f-37f7-40cc-a7c4-055479877b40";
    final String subKey = "sub-c-af5f4710-640d-11e5-bad4-02ee2ddab7fe";
    final String channel = "b";

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lines);
        getSupportActionBar().hide();

        imageView = (ImageView) findViewById(R.id.lines_screen_view);

        pubnub = new Pubnub(pubKey, subKey);
        try {
            pubnub.subscribe(channel, new Callback() {

                        @Override
                        public void connectCallback(String channel, Object message) {
                            System.out.println("SUBSCRIBE : CONNECT on channel:" + channel
                                    + " : " + message.getClass() + " : "
                                    + message.toString());
                        }

                        @Override
                        public void disconnectCallback(String channel, Object message) {
                            System.out.println("SUBSCRIBE : DISCONNECT on channel:" + channel
                                    + " : " + message.getClass() + " : "
                                    + message.toString());
                        }

                        public void reconnectCallback(String channel, Object message) {
                            System.out.println("SUBSCRIBE : RECONNECT on channel:" + channel
                                    + " : " + message.getClass() + " : "
                                    + message.toString());
                        }

                        @Override
                        public void successCallback(String channel, Object message) {
                            System.out.println("SUBSCRIBE : " + channel + " : "
                                    + message.getClass() + " : " + message.toString());
                            final String status = message.toString();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    switch (status) {
                                        case "2": {
                                            updateImage(R.drawable.linebusy);
                                            break;
                                        }
                                        case "1": {
                                            updateImage(R.drawable.linebustling);
                                            break;
                                        }
                                        case "0":
                                        default: {
                                            updateImage(R.drawable.linequiet);
                                        }
                                    }
                                }
                            });
                        }

                        @Override
                        public void errorCallback(String channel, PubnubError error) {
                            System.out.println("SUBSCRIBE : ERROR on channel " + channel
                                    + " : " + error.toString());
                        }
                    }
            );
        } catch (PubnubException e) {
            e.printStackTrace();
        }
        updateImage(R.drawable.linequiet);
    }

    private void updateImage(int resId) {
        imageView.setImageResource(resId);
        imageView.requestLayout();
        imageView.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lines, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
