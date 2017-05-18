package com.camilomontoya.lifekeeper.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camilomontoya.lifekeeper.R;
import com.camilomontoya.lifekeeper.other.ControlTipografia;
import com.camilomontoya.lifekeeper.other.DividerItemDecoration;
import com.camilomontoya.lifekeeper.other.ReaderBanner;
import com.camilomontoya.lifekeeper.other.ReaderBannerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<ReaderBanner> readerBanners = new ArrayList<>();
    private RecyclerView recyclerView;
    private ReaderBannerAdapter readerBannerAdapter;

    private String[] readerSubtitles = {
            "Paro cardíaco - RCP",
            "Ataque epiléptico",
            "Ahogo por objeto ingerido",
            "Hueso roto",
            "Hemorragias externas"
    };

    private Drawable[] readDraw;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        if(readDraw==null){
            readDraw = new Drawable[5];
            readDraw[0] = this.getActivity().getDrawable(R.drawable.ic_reader);
            readDraw[1] = this.getActivity().getDrawable(R.drawable.ic_reader);
            readDraw[2] = this.getActivity().getDrawable(R.drawable.ic_reader);
            readDraw[3] = this.getActivity().getDrawable(R.drawable.ic_reader);
            readDraw[4] = this.getActivity().getDrawable(R.drawable.ic_reader);
        }

        recyclerView = (RecyclerView) v.findViewById(R.id.banners);

        readerBannerAdapter = new ReaderBannerAdapter(readerBanners);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(readerBannerAdapter);

        ControlTipografia.getInstance().setTypeTitle(Typeface.createFromAsset(((AppCompatActivity) getActivity()).getAssets(), "fonts/Montserrat-Bold.ttf"));
        ControlTipografia.getInstance().setTypeSubtitle(Typeface.createFromAsset(((AppCompatActivity) getActivity()).getAssets(), "fonts/Montserrat-Regular.ttf"));
        ControlTipografia.getInstance().setTypeMsg(Typeface.createFromAsset(((AppCompatActivity) getActivity()).getAssets(), "fonts/Catamaran-Light.ttf"));

        if(readerBanners.size()==0) {
            for (int i = 0; i < readDraw.length; i++) {
                addBanner(i);
            }

            ((AppCompatActivity) getActivity()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    readerBannerAdapter.notifyDataSetChanged();
                }
            });
        }

        return v;
    }

    private void addBanner(int position){
        final ReaderBanner readerBanner = new ReaderBanner("Escenario "+(position+1), readerSubtitles[position], readDraw[position]);
        readerBanners.add(readerBanner);
        System.out.println("Cantidad de readerBanners " + readerBanners.size());
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
