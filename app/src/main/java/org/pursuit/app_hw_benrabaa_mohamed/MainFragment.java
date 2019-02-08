package org.pursuit.app_hw_benrabaa_mohamed;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private Spinner spinner;
    private Button nextButton;
    private Button newGameButton;
    private TextView resultTextView;
    public String selectedCategory;
    private FragmentInterface fragmentInterface;
    public static ResultDataBaseHelper myResultDataBaseHelper;
    public static long result;


    String[] lengthUnits = {
            "Please select from these category", "Whales", "Dogs", "Cards"};


    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
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
        view = inflater.inflate(R.layout.fragment_main, container, false);
        nextButton = view.findViewById(R.id.next_fragment_button);
        newGameButton = view.findViewById(R.id.new_game_button);
        resultTextView = view.findViewById(R.id.result_text_viewmain);
        myResultDataBaseHelper = ResultDataBaseHelper.getInstance(getContext().getApplicationContext());

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myResultDataBaseHelper.deleteAllRecords();
                result = 0;
                resultTextView.setText(String.format("You are %s%%", String.valueOf(result))+" psychic");

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner.getSelectedItem() == "Please select from these category") {
                    Toast.makeText(getContext(), "Please select from these category ", Toast.LENGTH_SHORT).show();
                } else {
                    fragmentInterface.showFirstFragment(selectedCategory);
                }
            }
        });
        spinner = view.findViewById(R.id.select_catagory_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                selectedCategory = parent.getItemAtPosition(position).toString();
                if (spinner.getSelectedItem() != "Please select from these category") {
                    Toast.makeText(getContext(), "" + selectedCategory, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_layout, lengthUnits);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(adapter);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        resultTextView.setText(String.format("You are %s%%", String.valueOf(result))+" psychic");

    }
}
