package rs.in.raf1.test;

import androidx.annotation.NonNull;

import androidx.appcompat.widget.Toolbar;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ListActivity {

    private Toolbar toolbar;

    public static final String TAG = "Test";

    ListView lv;
    ListAdapter adapter;
    TextView termId;

    static {
        termsList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
    }

    public static List<Map<String, Object>> termsList;
    public static FirebaseFirestore db;
    public static DocumentReference docRef = db.collection("terms").document();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        termsList.clear();

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.addTerm) {
                    Intent i = new Intent(getApplicationContext(), NewTerm.class);
                    startActivity(i);
                    finish();
                }
                return false;
            }
        });

        db.collection("terms")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot docRef: task.getResult()) {
                                String docId = docRef.getId();
                                Map<String, Object> map = docRef.getData();
                                map.put("id", docId);
                                termsList.add(map);

                                Log.d(TAG, "dodat");

                            }

                            lv = getListView();

                            adapter = new SimpleAdapter
                                    (MainActivity.this, termsList, R.layout.view_term_entry,
                                            new String[]{"id", "english", "serbian", "description"},
                                            new int[]{R.id.termId, R.id.termEnglish, R.id.termSerbian, R.id.termDescription});


                            lv.setAdapter(adapter);



                        } else {
                            Log.d(TAG, "Error");
                        }

                    }
        });

    }




    @Override
        protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent  i = new Intent(getApplicationContext(),EditTerm.class);
        i.putExtra("id", position);
        startActivity(i);
        finish();
    }



}
