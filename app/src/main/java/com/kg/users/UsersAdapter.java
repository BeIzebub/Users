package com.kg.users;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;
import java.util.zip.Inflater;

class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private List<User> users;

    UsersAdapter(List<User> users){
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_user, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        User currentUser = users.get(viewHolder.getAdapterPosition());

        viewHolder.image.setImageURI(Uri.fromFile(new File(currentUser.getImageUri())));
        viewHolder.nameTextView.setText(currentUser.getName()
                + " "
                + currentUser.getLastName());
        viewHolder.dateTextView.setText(currentUser.getDate().toString());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView nameTextView;
        public TextView dateTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }
}
