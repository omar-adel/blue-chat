package com.alexkang.btchatroom;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    public static final int REQUEST_ENABLE_BT = 0;

    private Button mHostButton;
    private Button mJoinButton;

    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHostButton = (Button) findViewById(R.id.host_button);
        mJoinButton = (Button) findViewById(R.id.join_button);

        mHostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hostRoom();
            }
        });

        mJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joinRoom();
            }
        });
    }

    private void hostRoom() {
        Intent i = new Intent(this, HostActivity.class);
        startActivity(i);
    }

    private void joinRoom() {

    }

    @Override
    public void onResume() {
        super.onResume();

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth not supported on this device, now exiting.", Toast.LENGTH_LONG).show();
            finish();
        }

        if (!mBluetoothAdapter.isEnabled()) {
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(i, REQUEST_ENABLE_BT);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_ENABLE_BT) {
            Toast.makeText(this, "Bluetooth successfully enabled!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Something went wrong, now exiting.", Toast.LENGTH_LONG).show();
            finish();
        }
    }

}
