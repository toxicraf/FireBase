package rs.in.raf1.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

import static rs.in.raf1.test.MainActivity.db;
import static rs.in.raf1.test.MainActivity.docRef;


public class NewTerm extends Activity {
	EditText termEnglish;
	EditText termSerbian;
	EditText termDescription;

	Button btnAdd;
	
	@Override
	 public void onBackPressed() {
		finish();
	 }
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.add_new_term);
	        termEnglish = (EditText) findViewById(R.id.termEnglish);
	        termSerbian = (EditText) findViewById(R.id.termSerbian);
	        termDescription = (EditText) findViewById(R.id.termDescription);

	        Button btnAdd = findViewById(R.id.btnAdd);


	        btnAdd.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Map<String, Object> addValues =  new HashMap<String, Object>();
					addValues.put("english", termEnglish.getText().toString());
					addValues.put("serbian", termSerbian.getText().toString());
					addValues.put("description", termDescription.getText().toString());

					db.collection("terms")
							.add(addValues)
							.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
								@Override
								public void onSuccess(DocumentReference documentReference) {
									callHome();
								}
							});

				}
			});

	 }

	private void callHome() {
		Intent i = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(i);
		finish();
	}

}
