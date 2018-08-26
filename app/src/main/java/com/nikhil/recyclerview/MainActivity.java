package com.nikhil.recyclerview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_LIST = 1;
    //
    RecyclerView recyclerView;
    ArrayList<ListVO> arrayList;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();
        adapter = new CustomAdapter();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        getListData();
    }

    private void getListData() {

        // added only to make space for 1 header view
        // will be ignored by adapter,
        arrayList.add(new ListVO("Header", "Header"));

        // List Items
        arrayList.add(new ListVO("A", "city-A"));
        arrayList.add(new ListVO("B", "city-B"));
        arrayList.add(new ListVO("C", "city-C"));
        arrayList.add(new ListVO("D", "city-D"));
        arrayList.add(new ListVO("E", "city-E"));
        arrayList.add(new ListVO("AA", "city-AA"));
        arrayList.add(new ListVO("BB", "city-BB"));
        arrayList.add(new ListVO("CC", "city-CC"));
        arrayList.add(new ListVO("DD", "city-DD"));

        adapter.notifyDataSetChanged();

        arrayList.add(new ListVO("EE", "city-EE"));
        adapter.notifyItemInserted(arrayList.size()); // gives a slide in animation
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView headerTextView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            headerTextView = itemView.findViewById(R.id.headerTextView);
        }
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView profile_ImageView;
        TextView name_TextView, details_TextView;

        public ListViewHolder(View itemView) {
            super(itemView);
            profile_ImageView = itemView.findViewById(R.id.profile_ImageView);
            name_TextView = itemView.findViewById(R.id.name_TextView);
            details_TextView = itemView.findViewById(R.id.details_TextView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ListVO vo = arrayList.get(position);
            Toast.makeText(MainActivity.this, vo.name + " clicked", Toast.LENGTH_SHORT).show();
        }
    }

    class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return VIEW_TYPE_HEADER;
            } else {
                return VIEW_TYPE_LIST;
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_HEADER) {
                View view = getLayoutInflater().inflate(R.layout.layout_header, null, false);
                return new HeaderViewHolder(view);
            } else { // if (viewType == VIEW_TYPE_LIST) {
                View view = getLayoutInflater().inflate(R.layout.layout_list_item, null, false);
                return new ListViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof HeaderViewHolder) {
                // position => 0 // hence never used by recycler view
                ((HeaderViewHolder) holder).headerTextView.setText("Custom Header");
            } else if (holder instanceof ListViewHolder) {
                ListVO vo = arrayList.get(position); // will start from 1
                ((ListViewHolder) holder).name_TextView.setText(vo.getName());
                ((ListViewHolder) holder).details_TextView.setText(vo.getCity());
            }

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }

}
