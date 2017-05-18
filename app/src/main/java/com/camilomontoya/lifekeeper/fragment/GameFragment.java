package com.camilomontoya.lifekeeper.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.camilomontoya.lifekeeper.R;
import com.camilomontoya.lifekeeper.other.ControlTipografia;
import com.camilomontoya.lifekeeper.other.DividerItemDecoration;
import com.camilomontoya.lifekeeper.other.EmergencyBanner;
import com.camilomontoya.lifekeeper.other.EmergencyBannerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GameFragment extends Fragment {

    private List<EmergencyBanner> banners = new ArrayList<>();
    private RecyclerView recyclerView;
    private EmergencyBannerAdapter bannerAdapter;

    private String[] bannerSubtitles= {
            "Paro cardíaco - RCP",
            "Ataque epiléptico",
            "Ahogo por objeto ingerido",
            "Hueso roto",
            "Hemorragias externas"
    };

    private boolean[] bannerAvailabe = {
            true,
            false,
            false,
            false,
            false
    };

    private Bitmap[] bannerMaps;

    private OnFragmentInteractionListener mListener;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        if(bannerMaps == null) {
            bannerMaps = new Bitmap[5];
            bannerMaps[0] = BitmapFactory.decodeResource(((AppCompatActivity) this.getActivity()).getResources(), R.drawable.thumb_rcp);
            bannerMaps[1] = BitmapFactory.decodeResource(((AppCompatActivity) this.getActivity()).getResources(), R.drawable.thumb_epilepsia);
            bannerMaps[2] = BitmapFactory.decodeResource(((AppCompatActivity) this.getActivity()).getResources(), R.drawable.thumb_ahogo);
            bannerMaps[3] = BitmapFactory.decodeResource(((AppCompatActivity) this.getActivity()).getResources(), R.drawable.thumb_bone);
            bannerMaps[4] = BitmapFactory.decodeResource(((AppCompatActivity) this.getActivity()).getResources(), R.drawable.thumb_blood);
        }
        recyclerView = (RecyclerView) v.findViewById(R.id.banners);

        bannerAdapter = new EmergencyBannerAdapter(banners);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(bannerAdapter);

        if(banners.size()==0) {
            for (int i = 0; i < bannerMaps.length; i++) {
                addBanner(i);
            }

            ((AppCompatActivity) getActivity()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    bannerAdapter.notifyDataSetChanged();
                }
            });
        }

        return v;
    }

    private void addBanner(int position){
        final EmergencyBanner emergencyBanner = new EmergencyBanner("Escenario "+(position+1), bannerSubtitles[position], bannerAvailabe[position], bannerMaps[position]);
        banners.add(emergencyBanner);
        System.out.println("Cantidad de banners " + banners.size());
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
