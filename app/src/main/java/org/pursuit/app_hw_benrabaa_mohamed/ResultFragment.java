package org.pursuit.app_hw_benrabaa_mohamed;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_USER_IMAGE = "userImage";
    private static final String ARG_RANDOM_IMAGE = "randomImage";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;
    private long result;
    private ImageView userImage;
    private ImageView randomImage;
    private TextView resultTextView;
    private View view;
    private HashMap<Integer,Boolean> gameHashMap=MainActivity.game;
    public   ResultDataBaseHelper myResultDataBaseHelper;





    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment ResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultFragment newInstance(int selectedImage,int randomImage ) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_USER_IMAGE,selectedImage);
        args.putInt(ARG_RANDOM_IMAGE,randomImage);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.game=gameHashMap;
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_USER_IMAGE);
            mParam2 = getArguments().getInt(ARG_RANDOM_IMAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_result, container, false);
        MainActivity.gameCounter++;
        userImage=view.findViewById(R.id.user_image_view);
        randomImage=view.findViewById(R.id.computer_image_view);
        resultTextView=view.findViewById(R.id.result_text_view);
        myResultDataBaseHelper=ResultDataBaseHelper.getInstance(getContext().getApplicationContext());
        Result result=new Result();


        if(mParam1==mParam2){
            result.setPass(true);
            gameHashMap.put(MainActivity.gameCounter,true);
        }else{
            result.setPass(false);
            gameHashMap.put(MainActivity.gameCounter,false);
        }

        result.setPercentage(getResult());
        myResultDataBaseHelper.addReasult(result);

        resultTextView.setText(String.valueOf(getResult())+"%");
        userImage.setImageResource(mParam1);
        randomImage.setImageResource(mParam2);
        return view;
    }

    private long getResult() {
        //myResultDataBaseHelper.deleteAllRecords();
        Log.d("ben", "getResult: "+"total is "+myResultDataBaseHelper.totalCounter()+" true is "+myResultDataBaseHelper.trueCounter());

        //return result = (myResultDataBaseHelper.trueCounter()/myResultDataBaseHelper.totalCounter()) *100 ;
        if(myResultDataBaseHelper.totalCounter()!=0)
            return result = Math.round(100.00 / myResultDataBaseHelper.totalCounter()) * myResultDataBaseHelper.trueCounter();
        return 0;


//        long result;
//        int count=0;
//        for (int i:gameHashMap.keySet() ) {
//            if(gameHashMap.get(i)==true){
//                count++;
//            }
//        }
//
//        for (int i:gameHashMap.keySet() ) {
//            Log.d("ben2", "onCreateView: "+gameHashMap.get(i));
//        }
//
//        Log.d("ben", "MainActivity.gameCounter "+ MainActivity.gameCounter);
//
//
//        result=(100/gameHashMap.size())*count;
//        return result;
    }

}
