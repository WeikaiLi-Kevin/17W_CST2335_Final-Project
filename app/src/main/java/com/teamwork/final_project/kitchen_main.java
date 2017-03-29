package com.teamwork.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class kitchen_main extends AppCompatActivity {

    protected ListView list_kitchen;
    protected Button b_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchen_fragment_listviewkitchen);

        list_kitchen = (ListView)findViewById(R.id.list_kitchen);
        b_add = (Button)findViewById(R.id.add_device);
//        CharSequence cs[] = new CharSequence[]{"kitchenFridge", "Light", "kitchenMicrowave"};
/*            @Override
            public int length() {
                return 0;
            }

            @Override
            public char charAt(int index) {
                return 0;
            }

            @Override
            public CharSequence subSequence(int start, int end) {
                return null;
            }
        }
*/
        String[] option = new String[]{"kitchenMicrowave",
                "kitchenFridge", "Main Light"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.kitchen_main_list, option);
        list_kitchen.setAdapter(adapter);
        list_kitchen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemIndex = position;
                if(itemIndex == 0){
                    Intent intent1 = new Intent(kitchen_main.this, kitchenMicrowave.class);
                    startActivity(intent1);
                }
                if(itemIndex == 1){
                    Intent intent2 = new Intent(kitchen_main.this, kitchenFridge.class);
                    startActivity(intent2);
                    }
                if(itemIndex==2){
                    Intent intent3 = new Intent(kitchen_main.this, kitchen_main_light.class);
                    startActivity(intent3);
                }

            }
        });



    }
}
