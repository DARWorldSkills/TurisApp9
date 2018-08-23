package com.aprendiz.ragp.turisapp9.fragmets;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aprendiz.ragp.turisapp9.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotelesFragment extends Fragment {


    public HotelesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_hoteles, container, false);

        return view;
    }

}
