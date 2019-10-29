package com.my.bielik.pecodetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import static com.my.bielik.pecodetest.MyApplication.NOTIFICATION_CHANNEL_ID;

public class BlankFragment extends Fragment {

    private static final String TAG = "BlankFragment";

    private static final String ARG_PAGE_NUMBER = "page_number";
    public static final String EXTRA_PAGE_TO_OPEN = "page_to_open";

    private int pageNumber;

    private Button btnCreateNotification;
    private ImageButton btnAddFragment;
    private ImageButton btnRemoveFragment;
    private TextView tvPageInfo;

    private OnFragmentInteractionListener listener;

    public BlankFragment() {
    }

    public static BlankFragment newInstance(int pageNumber) {
        Log.e(TAG, "newInstance, page: " + pageNumber);
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pageNumber = getArguments().getInt(ARG_PAGE_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        btnCreateNotification = view.findViewById(R.id.btn_create_notification);
        btnRemoveFragment = view.findViewById(R.id.btn_remove_fragment);
        btnAddFragment = view.findViewById(R.id.btn_add_fragment);
        tvPageInfo = view.findViewById(R.id.tv_page_info);

        tvPageInfo.setText(String.valueOf(pageNumber));

        btnCreateNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotification();
            }
        });

        btnAddFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAddBtnClick(pageNumber);
            }
        });

        btnRemoveFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRemoveBtnClick();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    private void createNotification() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtra(EXTRA_PAGE_TO_OPEN, pageNumber);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(getContext(), NOTIFICATION_CHANNEL_ID)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_text) + " " + pageNumber)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity());
        notificationManagerCompat.notify(pageNumber, notification);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener {
        void onAddBtnClick(int currentPage);

        void onRemoveBtnClick();
    }
}
