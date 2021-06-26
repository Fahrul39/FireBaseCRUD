package com.example.firebasecrud;

import android.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebasecrud.model.model;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.HashMap;
import java.util.Map;

public class MusikAdapter extends FirebaseRecyclerAdapter <model, MusikAdapter.myViewHolder>{

    public MusikAdapter(@NonNull FirebaseRecyclerOptions<model> options) { super(options); }

    @Override
    protected void onBindViewHolder(@NonNull final myViewHolder holder, final int position, @NonNull final model model) {
        holder.judul.setText(model.getJudul());
        holder.desc.setText(model.getDesc());
        Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);

            holder.edit.setOnClickListener(v -> {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_musik))
                        .setExpanded(true, 1100)
                        .create();

                View myView = dialogPlus.getHolderView();
                final EditText purl = myView.findViewById(R.id.posturl);
                final EditText judul = myView.findViewById(R.id.jtext);
                final EditText deskripsi = myView.findViewById(R.id.dtext);
                Button submit = myView.findViewById(R.id.usubmit);

                purl.setText(model.getPurl());
                judul.setText(model.getJudul());
                deskripsi.setText(model.getDesc());

                dialogPlus.show();

                submit.setOnClickListener(view -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("purl", purl.getText().toString());
                    map.put("judul", judul.getText().toString());
                    map.put("desc", deskripsi.getText().toString());

                    FirebaseDatabase.getInstance().getReference().child("musik")
                            .child(getRef(position).getKey()).updateChildren(map)

                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(holder.img.getContext(), "Update Data Berhasil", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            })
                            .addOnFailureListener(e -> dialogPlus.dismiss());
                });


            });

            holder.delete.setOnClickListener(view -> {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Hapus Lagu");
                builder.setMessage("Apakah Anda Yakin?");

                builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                    FirebaseDatabase.getInstance().getReference().child("musik")
                            .child(getRef(position).getKey()).removeValue();
                    Toast.makeText(holder.img.getContext(), "Data Terhapus", Toast.LENGTH_SHORT).show();
                });

                builder.setNegativeButton("No", (dialogInterface, i) -> {
                });
                builder.show();
            });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        return new myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        Button edit, delete;
        TextView judul, desc;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.posterurl);
            judul = (TextView) itemView.findViewById(R.id.judultext);
            desc = (TextView) itemView.findViewById(R.id.desctext);
            desc.setMovementMethod(new ScrollingMovementMethod());
            edit = (Button)itemView.findViewById(R.id.editbtn);
            delete = (Button)itemView.findViewById(R.id.deletebtn);
        }
    }
}