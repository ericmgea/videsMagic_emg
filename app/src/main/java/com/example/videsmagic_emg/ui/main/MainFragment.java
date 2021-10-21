package com.example.videsmagic_emg.ui.main;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.videsmagic_emg.R;
import com.google.android.material.snackbar.Snackbar;

public class MainFragment extends Fragment {

    private int life1 = 20;
    private int life2 = 20;
    private int poison1 = 0;
    private int poison2 = 0;

    private ImageButton lifetwotoone;
    private ImageButton lifeonetotwo;
    private Button p1poisonmore;
    private Button p2poisonmore;
    private Button p1poisonless;
    private Button p2poisonless;
    private ImageButton p1lifemore;
    private ImageButton p2lifemore;
    private ImageButton p1lifeless;
    private ImageButton p2lifeless;
    private TextView counterp1;
    private TextView counterp2;



    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment, container, false);


        lifeonetotwo = (ImageButton) view.findViewById(R.id.lifeonetotwo);
        lifetwotoone = (ImageButton) view.findViewById(R.id.lifetwotoone);
        p1poisonmore = (Button) view.findViewById(R.id.p1poisonmore);
        p2poisonmore = (Button) view.findViewById(R.id.p2poisonmore);
        p1poisonless = (Button) view.findViewById(R.id.p1poisonless);
        p2poisonless = (Button) view.findViewById(R.id.p2poisonless);
        p1lifemore = (ImageButton) view.findViewById(R.id.p1lifemore);
        p2lifemore = (ImageButton) view.findViewById(R.id.p2lifemore);
        p1lifeless = (ImageButton) view.findViewById(R.id.p1lifeless);
        p2lifeless = (ImageButton) view.findViewById(R.id.p2lifeless);
        counterp1 = (TextView) view.findViewById(R.id.counterp1);
        counterp2 = (TextView) view.findViewById(R.id.counterp2);

        if (savedInstanceState != null) {
            System.out.println(life1);
            life1 = savedInstanceState.getInt("life1");
            life2 = savedInstanceState.getInt("life2");
            poison1 = savedInstanceState.getInt("poison1");
            poison2 = savedInstanceState.getInt("poison2");

            updateViews();
        }

        View.OnClickListener listener = view -> {
            switch(view.getId()){
                case R.id.lifeonetotwo:
                    decLife1();
                    if(life1>0){
                        life2++;
                    }
                    break;

                case R.id.lifetwotoone:
                    if(life2>0){
                        life1++;
                    }
                    decLife2();
                    break;

                case R.id.p1lifeless:
                    decLife1();
                    break;

                case R.id.p1lifemore:
                    life1++;
                    break;

                case R.id.p1poisonless:
                    decPoison1();
                    break;

                case R.id.p1poisonmore:
                    poison1++;
                    break;

                case R.id.p2lifeless:
                    decLife2();
                    break;

                case R.id.p2lifemore:
                    life2++;
                    break;

                case R.id.p2poisonless:
                    decPoison2();
                    break;

                case R.id.p2poisonmore:
                    poison2++;
                    break;

            }
            updateViews();
        };


        reset();

        lifetwotoone.setOnClickListener(listener);
        lifeonetotwo.setOnClickListener(listener);
        p1poisonmore.setOnClickListener(listener);
        p1poisonless.setOnClickListener(listener);
        p2poisonmore.setOnClickListener(listener);
        p2poisonless.setOnClickListener(listener);
        p1lifemore.setOnClickListener(listener);
        p2lifemore.setOnClickListener(listener);
        p2lifeless.setOnClickListener(listener);
        p1lifeless.setOnClickListener(listener);
        counterp1.setOnClickListener(listener);
        counterp2.setOnClickListener(listener);



        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("life1", life1);
        outState.putInt("life2", life2);
        outState.putInt("poison1", poison1);
        outState.putInt("poison2", poison2);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle action bar items clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.reset) {

            reset();
            Snackbar.make(view, "New Game!", Snackbar.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    private void reset(){
        poison1 = 0;
        poison2 = 0;
        life1 = 20;
        life2 = 20;
        updateViews();
    }

    private void updateViews() {
        counterp1.setText(String.format("%d/%d", life1, poison1));
        counterp2.setText(String.format("%d/%d", life2, poison2));
    }

    public void incLife1(){
        life1++;
    }

    public void incLife2(){
        life2++;
    }

    public void decLife1(){
        if(life1>0){
            life1--;
        }

    }

    public void decLife2(){
        if(life2>0){
            life2--;
        }
    }

    public void incPoison1(){
        poison1++;
    }

    public void incPoison2(){
        poison2++;
    }

    public void decPoison1(){
        if(poison1>0){
            poison1--;
        }
    }

    public void decPoison2(){
        if(poison2>0){
            poison2--;
        }
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            System.out.println(life1);
            life1 = savedInstanceState.getInt("life1");
            life2 = savedInstanceState.getInt("life2");
            poison1 = savedInstanceState.getInt("poison1");
            poison2 = savedInstanceState.getInt("poison2");

            updateViews();
        }
    }

}