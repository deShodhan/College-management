

package com.dpkpranay.myproject;

        import android.content.Intent;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.google.firebase.database.ChildEventListener;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;

        import java.sql.Ref;
        import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    Button btn;
    DatabaseReference mDatabase;
    ListView lv;
    ArrayList<String> arr=new ArrayList<>();
    ArrayList<String> keylist=new ArrayList<>();
    Button but;
    String key;
    String value;
    String val;
    EditText enter;
    Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("user");
        lv=(ListView)findViewById(R.id.user);



        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr);
        lv.setAdapter(arrayAdapter);
            mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                value=dataSnapshot.getValue(String.class);
                arr.add(value);
                keylist.add(dataSnapshot.getKey());
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                arr.remove(value);
                keylist.remove(dataSnapshot.getKey());
                arrayAdapter.notifyDataSetChanged();

                }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "long click to delete", Toast.LENGTH_SHORT).show();
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                val=keylist.get(i);
                final String m=lv.getItemAtPosition(i).toString();
                mDatabase.child(val).removeValue();
                arrayAdapter.notifyDataSetChanged();
                Snackbar snak=Snackbar.make(lv,"deleted!",Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mDatabase.push().setValue(m);
                                Toast.makeText(getApplicationContext(),"UNDO successfull",Toast.LENGTH_SHORT).show();
                            }
                        });

                snak.show();
                return true;
            }
        });
    }
}



