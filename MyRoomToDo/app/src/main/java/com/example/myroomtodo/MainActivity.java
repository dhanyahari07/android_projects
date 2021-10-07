package com.example.myroomtodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    Button insert_sigle,delete,get_all,update,insert_multiple,find_all,live_getall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert_sigle=findViewById(R.id.insert_single);
        insert_sigle.setOnClickListener(this);

        delete=findViewById(R.id.delete_single);
        delete.setOnClickListener(this);

        get_all=findViewById(R.id.get_all);
        get_all.setOnClickListener(this);

        update=findViewById(R.id.update_single);
        update.setOnClickListener(this);

        insert_multiple=findViewById(R.id.insert_multiple);
        insert_multiple.setOnClickListener(this);

        find_all=findViewById(R.id.find_complted);
        find_all.setOnClickListener(this);

        live_getall=findViewById(R.id.live_getall);
        live_getall.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.insert_single)
        {
            Todo todo = new Todo("make a video on swift ...", false);
            InsertAsyncTask insertAsyncTask = new InsertAsyncTask();
            insertAsyncTask.execute(todo);

        }
        else if(v.getId()==R.id.delete_single)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Todo todo = TodoRoomDatabase.getInstance(getApplicationContext())
                            .todoDao()
                            .findTodoById(2);

                    Log.d(TAG, "run: " + todo.toString());
                    if (todo != null) {
                        TodoRoomDatabase.getInstance(getApplicationContext())
                                .todoDao()
                                .deleteTodo(todo);

                        Log.d(TAG, "run: todo has been deleted...");
                    }

                }
            }).start();

        }
        else if(v.getId()==R.id.get_all)
        {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    List<Todo> todoList = TodoRoomDatabase.getInstance(getApplicationContext())
                            .todoDao()
                            .getAllTodos();

                    Log.d(TAG, "run: " + todoList.toString());
                }
            });

            thread.start();

        }
        else if(v.getId()==R.id.update_single)
        {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Todo todo = TodoRoomDatabase.getInstance(getApplicationContext())
                            .todoDao()
                            .findTodoById(1);

                    if (todo != null) {
                        todo.setCompleted(true);

                        TodoRoomDatabase.getInstance(getApplicationContext())
                                .todoDao()
                                .updateTodo(todo);

                        Log.d(TAG, "run: todo has been updated..." );
                    }
                }
            }).start();

        }
        else if(v.getId()==R.id.insert_multiple)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<Todo> todoList = new ArrayList<>();
                    todoList.add(new Todo("make a video on kotlin", false));
                    todoList.add(new Todo("watch black panther", true));
                    todoList.add(new Todo("watch marvel movies seris", true));
                    todoList.add(new Todo("make a beginner video on pyhton", false));

                    TodoRoomDatabase.getInstance(getApplicationContext())
                            .todoDao()
                            .insertMultipleTodos(todoList);

                    Log.d(TAG, "run: todos has been inserted...");
                }
            }).start();
        }
        else if(v.getId()==R.id.find_complted)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<Todo> todoList = TodoRoomDatabase.getInstance(getApplicationContext())
                            .todoDao()
                            .getAllCompletedTodos();

                    Log.d(TAG, "run: " + todoList.toString());
                }
            }).start();

        }
        else if(v.getId()==R.id.live_getall)
        {

            LiveData<List<Todo>> todoList=TodoRoomDatabase.getInstance(getApplicationContext())
                    .todoDao()
                    .findtodousingLiveDataOnly();

            todoList.observe(this, new Observer<List<Todo>>() {
                @Override
                public void onChanged(List<Todo> todos) {
                    //todoList.getValue();
                    Log.d(TAG, "onChanged: " + todoList.getValue().toString());
                   Log.d(TAG, "onChanged: " + todoList.getValue().size());

                }
            });
         // todoList.removeObservers(this);
        }


    }

    class InsertAsyncTask extends AsyncTask<Todo, Void, Void> {

        @Override
        protected Void doInBackground(Todo... todos) {

            TodoRoomDatabase.getInstance(getApplicationContext())
                    .todoDao()
                    .insertTodo(todos[0]);

            return null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
      //  todoList.removeObservers(this);
    }
}