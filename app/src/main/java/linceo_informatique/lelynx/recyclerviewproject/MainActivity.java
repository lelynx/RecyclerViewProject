package linceo_informatique.lelynx.recyclerviewproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity
        extends AppCompatActivity
        implements ExampleAdapter.OnItemClickListener {

    RecyclerView mRecyclerView;
    // NB:
    ExampleAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<ExampleItem> mExampleList = new ArrayList<>();
    // widget cache
    Button mBtnInsert;
    Button mBtnRemove;
    EditText mEdtInsert;
    EditText mEdtRemove;

    /**
     * exemple de commit#1
     * @param savedInstanceState état précédent
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // la source de données
        // remplir
//        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Line 1", "Line 2"));
//        mExampleList.add(new ExampleItem(R.drawable.ic_sun, "Line 3", "Line 4"));
//        mExampleList.add(new ExampleItem(R.drawable.ic_directions, "Line 5", "Line 6"));
        //
        loadDataIntoArrayList();
        initRecyclerView();
        initWidget();

        // pour suppriméer l'erreur du commit
        String name = returnName();
    }

    private void initWidget() {
        mEdtInsert = findViewById(R.id.edt_insert);
        mEdtRemove = findViewById(R.id.edt_remove);
        mBtnInsert = findViewById(R.id.button_insert);
        mBtnRemove = findViewById(R.id.button_remove);
    }

    void initRecyclerView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        // apparence
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // adapter
        mAdapter = new ExampleAdapter(mExampleList);

        // enregistrement de MainActivity auprès de l'Observer, pour être notifié
        mAdapter.setOnItemClickListener(this);


        // Pour pouvoir utiliser les méthodes spécifiques ajoutées à ExampleAdapter,
        // il faut que mAdapter soit de type ExampleAdapter, or elle est déclarée avec le type RecyclerView.Adapter
        // 2 solutions:
        // 1- le TypeCasting résoud ce problème
        // 2- ou alors il faut modifier la déclaration de mAdapter.(c'est plus simple!!!)
        // 1ère façon de faire: on enregistre MainActivity, comme listener

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this,
                        "Clicked "+position, Toast.LENGTH_SHORT).show();
                // get ref to ExampleItem
                ExampleItem item=mExampleList.get(position);
                item.setText1("Text Changed");
                mAdapter.notifyItemChanged(position);
            }
        });

        mRecyclerView.setAdapter(mAdapter);

    }

    void loadDataIntoArrayList() {
        for (int i = 1; i < 100; i += 7) {
            mExampleList.add(new ExampleItem(R.drawable.ic_android, "Line " + i, "Line " + i + 1));
            mExampleList.add(new ExampleItem(R.drawable.ic_sun, "Line " + i + 2, "Line " + i + 3));
            mExampleList.add(new ExampleItem(R.drawable.ic_directions, "Line " + i + 4, "Line " + i + 5));
        }
    }

    public void insert(View view) {
        // insert a new item, at position
        int position = Integer.parseInt(mEdtInsert.getText().toString());
        mExampleList.add(position, new ExampleItem(R.drawable.ic_directions, "Line 10", "Line 11"));
        mAdapter.notifyItemInserted(position);
    }

    public void remove(View view) {
        // remove an item at position
        int position = Integer.parseInt(mEdtRemove.getText().toString());
        mExampleList.remove(position);
        mAdapter.notifyItemRangeRemoved(position, 1);
    }

    @Override
    public void onClick(int position) {
        // Il faut que MainActivity implements ExampleAdapter.OnItemClickListener
        Toast.makeText(MainActivity.this, "Clicked " + position, Toast.LENGTH_SHORT).show();
        // get ref to ExampleItem
        ExampleItem item = mExampleList.get(position);
        item.setText1("Text Changed");
        mAdapter.notifyItemChanged(position);
    }

    // un changement pour le commit#2
    public String returnName() {
        return "Philippe PONS";
    }
    
    // un comment pour le pull process test--Date: 14/4/20

    // changement pour le commit pull process test
    // cette méthode n'a pas été commitée vers origin
    public String philMethod() {
        return "Hello";
    }

}
