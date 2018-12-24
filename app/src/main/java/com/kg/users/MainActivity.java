package com.kg.users;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.kg.users.FormActivity.EXTRA_USER;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_CREATE_USER = 1001;

    private List<User> users;

    private RecyclerView usersRecycler;
    private UsersAdapter adapter;
    private Button addBtn;

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gson = new Gson();
        users = new ArrayList<>();

        usersRecycler = findViewById(R.id.usersRecycler);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        usersRecycler.setLayoutManager(manager);

        adapter = new UsersAdapter(users);
        usersRecycler.setAdapter(adapter);


        addBtn = findViewById(R.id.addUserBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CREATE_USER);
            }
        });

        loadUsers();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_CREATE_USER) {
            if(resultCode == RESULT_OK) {
                if(data != null) {
                    User user = gson.fromJson(data.getStringExtra(EXTRA_USER), User.class);
                    addUser(user);
                    saveUser(user);
                }
            }
        }
    }

    private void addUser(User user) {
        Log.i("USER", "addUser: " + user.toString());
        users.add(0, user);
        adapter.notifyItemInserted(0);
    }

    private void saveUser(User user) {
        try {
            File file = new File(getFilesDir(), "user_" + user.getUserId() + "_" + System.currentTimeMillis() + ".user");
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(gson.toJson(user).getBytes());
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUsers() {
        File dir = getFilesDir();
        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".user");
            }
        });
        for (File f : files) {
            try {
                FileInputStream fis = new FileInputStream(f);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                User user = gson.fromJson(sb.toString(), User.class);
                addUser(user);
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
