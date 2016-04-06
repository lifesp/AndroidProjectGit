package com.example.mael.androidproject2.formulaire;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.mael.androidproject2.MainActivity;
import com.example.mael.androidproject2.OnFragmentInteractionListener;
import com.example.mael.androidproject2.R;
import com.example.mael.androidproject2.formulaire.BaseDonne.AddBaseJSON;
import com.example.mael.androidproject2.liste.BaseDonne.AddBase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import traitement.Produit;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *  interface
 * to handle interaction events.
 * Use the {@link FormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FormFragment newInstance(String param1, String param2) {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private EditText type;
    private EditText nom;
    private EditText quantiter;
    private Button bouton;
    private AddBase bs;
    private String item;
    private Button recherche;
    private ListView listType;
    private AddBaseJSON adJson;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_form, container, false);
        this.nom = (EditText) v
                .findViewById(R.id.nom);
        this.type = (EditText) v.findViewById(R.id.type);
        this.quantiter = (EditText) v.findViewById(R.id.quantiter);
        this.bouton = (Button) v.findViewById(R.id.button);
        List<String> categories = new ArrayList<String>();
        adJson = AddBaseJSON.getInstance(MainActivity.CONTEXT);
        this.item = "Automobile";
        bs = AddBase.getInstance(MainActivity.CONTEXT);
        this.bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(nom.getText().toString());
                System.out.println(Integer.valueOf(quantiter.getText().toString()));
                Produit p = new Produit(nom.getText().toString(), Integer.valueOf(quantiter.getText().toString()), "test");
                System.out.println(p.toString());
            }
        });
        this.recherche = (Button) v.findViewById(R.id.recherche);
        this.listType = (ListView) v.findViewById(R.id.listType);
        this.recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> lType = adJson.getProduit();
                ArrayList<String> string = new ArrayList<String>();
                String typeAliment = type.getText().toString();
                int index = 0;
                for(int i =0; i<lType.size(); ++i){
                    //System.out.println(lType.get(i) + " / " +typeAliment+ " / "+lType.get(i).indexOf(typeAliment));
                    if(lType.get(i).indexOf(typeAliment)>-1){
                        string.add(lType.get(i));
                        System.out.println(lType.get(i));
                    }
                }
                ArrayAdapter<String> ad = new ArrayAdapter<String>(MainActivity.CONTEXT, android.R.layout.simple_expandable_list_item_1, string);
                listType.setAdapter(ad);
            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri.toString());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
}
