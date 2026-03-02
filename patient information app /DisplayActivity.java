package com.example.patientinfo;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    TextView tvName, tvAge, tvGender, tvIllness, tvDate;
    Button btnCall, btnSMS, btnEmail;

    String doctorPhone = "9876543210";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Patient Summary - REG123");
        setContentView(R.layout.activity_display);

        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        tvGender = findViewById(R.id.tvGender);
        tvIllness = findViewById(R.id.tvIllness);
        tvDate = findViewById(R.id.tvDate);

        btnCall = findViewById(R.id.btnCall);
        btnSMS = findViewById(R.id.btnSMS);
        btnEmail = findViewById(R.id.btnEmail);

        // Receiving data from MainActivity
        Intent intent = getIntent();
        tvName.setText("Name : " + intent.getStringExtra("name"));
        tvAge.setText("Age : " + intent.getStringExtra("age"));
        tvGender.setText("Gender : " + intent.getStringExtra("gender"));
        tvIllness.setText("Illness : " + intent.getStringExtra("illness"));
        tvDate.setText("Appointment Date : " + intent.getStringExtra("date"));

        btnCall.setOnClickListener(v -> showDialog("call"));
        btnSMS.setOnClickListener(v -> showDialog("sms"));

        btnEmail.setOnClickListener(v -> {
            Intent email = new Intent(Intent.ACTION_SENDTO);
            email.setData(Uri.parse("mailto:doctor@gmail.com"));
            email.putExtra(Intent.EXTRA_SUBJECT, "Doctor Appointment");
            startActivity(email);
        });
    }

    private void showDialog(String type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", (dialog, which) -> {

            if (type.equals("call")) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + doctorPhone));
                startActivity(callIntent);
            } else {
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO,
                        Uri.parse("smsto:" + doctorPhone));
                smsIntent.putExtra("sms_body", "Appointment request");
                startActivity(smsIntent);
            }
        });

        builder.setNegativeButton("No", null);
        builder.show();
    }
}
