package org.pursuit.app_hw_benrabaa_mohamed;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView imageView1;
    private  ImageView imageView2;
    private  ImageView imageView3;
    private  ImageView imageView4;
    private FragmentInterface fragmentInterface;
    private int[] myImageList=new int[]{R.drawable.dog1,R.drawable.dog2,R.drawable.dog3,R.drawable.dog4};


    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInterface) { //does activity implement/contain a FragmentInterface?
            fragmentInterface = (FragmentInterface) context; //if it does we are saving it in a field
        }
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_first, container, false);
        imageView1=view.findViewById(R.id.image1_image_view);
        imageView2=view.findViewById(R.id.image2_image_view);
        imageView3=view.findViewById(R.id.image3_image_view);
        imageView4=view.findViewById(R.id.image4_image_view);

        imageView1.setImageResource(myImageList[0]);
        imageView2.setImageResource(myImageList[1]);
        imageView3.setImageResource(myImageList[2]);
        imageView4.setImageResource(myImageList[3]);


        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentInterface.showResultFragment(myImageList[0],randomImage(myImageList));
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentInterface.showResultFragment(myImageList[1],randomImage(myImageList));
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentInterface.showResultFragment(myImageList[2],randomImage(myImageList));
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentInterface.showResultFragment(myImageList[3],randomImage(myImageList));
            }
        });

        return view;
    }

    public int randomImage(int[] myImageList){
        Random rand = new Random();

        int i =  rand.nextInt(4) + 0;

        return myImageList[i];

    }

}
