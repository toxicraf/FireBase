package rs.in.raf1.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static rs.in.raf1.test.MainActivity.db;
import static rs.in.raf1.test.MainActivity.docRef;
import static rs.in.raf1.test.MainActivity.termsList;


public class EditTerm extends Activity {
	EditText termEnglish;
	EditText termSerbian;
	EditText termDescription;
	Button btnEdit;
	Button btnDel;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_term);

		termEnglish = (EditText) findViewById(R.id.termEnglish);
		termSerbian = (EditText) findViewById(R.id.termSerbian);
		termDescription = (EditText) findViewById(R.id.termDescription);
		btnEdit = (Button) findViewById(R.id.btnEdit);
		btnDel = (Button) findViewById(R.id.btnDelete);


		Intent i = getIntent();
		int position = i.getIntExtra("id", 0);

		final Map<String, Object> row  = termsList.get(position);
		final String id1 = row.get("id").toString();

		termEnglish.setText(row.get("english").toString());
		termSerbian.setText(row.get("serbian").toString());
		termDescription.setText(row.get("description").toString());

		btnEdit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String eng = termEnglish.getText().toString();
				String srb = termSerbian.getText().toString();
				String dsc = termDescription.getText().toString();

				db.collection("terms").document(id1)
						.update("english", eng)
						.addOnSuccessListener(new OnSuccessListener<Void>() {
							@Override
							public void onSuccess(Void aVoid) {
								Log.d("Raf", row.toString());

							}
						});

				db.collection("terms").document(id1)
						.update("serbian", srb)
						.addOnSuccessListener(new OnSuccessListener<Void>() {
							@Override
							public void onSuccess(Void aVoid) {
								Log.d("Raf", row.toString());

							}
						});


				db.collection("terms").document(id1)
						.update("description", dsc)
						.addOnSuccessListener(new OnSuccessListener<Void>() {
							@Override
							public void onSuccess(Void aVoid) {
								Log.d("Raf", row.toString());

							}
						});

				callHome();

			}
		});


		btnDel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				db.collection("terms").document(id1)
						.delete()
						.addOnSuccessListener(new OnSuccessListener<Void>() {
							@Override
							public void onSuccess(Void aVoid) {
								callHome();
							}
						});
			}
		});


}

	@Override
	public void onBackPressed() {
		callHome();
	}

	private void callHome() {
		Intent i = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(i);
		finish();
	}


}