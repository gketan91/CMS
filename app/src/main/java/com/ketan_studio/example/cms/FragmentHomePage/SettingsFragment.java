package com.ketan_studio.example.cms.FragmentHomePage;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ketan_studio.example.cms.R;


public class SettingsFragment extends Fragment {
    ImageButton copy_btn;
    Context context;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        copy_btn = (ImageButton)v.findViewById(R.id.copy_btn);
        Button openLink = (Button)v.findViewById(R.id.open_link);
        Button ShareLink = (Button)v.findViewById(R.id.share_link);

        openLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://rb.gy/vxglim")));
            }
        });

        ShareLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,"https://rb.gy/vxglim");
                sendIntent.setType("text/plain");
                Intent.createChooser(sendIntent,"Share via");
                startActivity(sendIntent);
            }
        });

        copy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", "https://rb.gy/vxglim");
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getActivity(), "Copy code copied to clickboard!", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }


}