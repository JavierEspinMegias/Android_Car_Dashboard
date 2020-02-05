package com.android.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class Fragment2 extends Fragment implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInterfaceCom mListener;

    public GestureDetectorCompat mDetector;

    public Fragment2() {
        // Required empty public constructor
    }

    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment2, container, false);


        mDetector = new GestureDetectorCompat(v.getContext(), this);
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(getContext(), "asdasdasd", Toast.LENGTH_SHORT).show();
                return true;
            }
        });



        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String info, Object data) {
        if (mListener != null) {
            mListener.onFragmentMessage(info, data);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInterfaceCom) {
            mListener = (OnFragmentInterfaceCom) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        Toast.makeText(getContext(), "asdasdas", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        Toast.makeText(getContext(), "asdasdas", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        Toast.makeText(getContext(), "asdasdas", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Toast.makeText(getContext(), "asdasdas", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}
