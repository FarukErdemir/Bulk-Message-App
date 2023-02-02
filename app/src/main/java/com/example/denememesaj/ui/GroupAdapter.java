package com.example.denememesaj.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.denememesaj.ui.GroupModel;
import com.example.denememesaj.R;

import java.util.List;


public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {

    //recylce view için view holder yazıyoruz.
    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GroupViewHolder groupViewHolder = new GroupViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_creategroup_group, parent, false));
        return groupViewHolder;
    }
    //viewholdera veri çekemk için fonkisyon yazıyoruz.
    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        GroupModel groupModel = groupModelList.get(position);
        holder.setData(groupModel);
    }














    @Override
    public int getItemCount() {
        return groupModelList.size();
    }
    //viewholderda gösterilcekler için gerekli fonksiyonu yazıyoruz.
    public class GroupViewHolder extends RecyclerView.ViewHolder {
        ImageView groupImageView;
        TextView groupNameTextView, groupDescriptionTextView;
        public GroupViewHolder( View itemView) {
            super(itemView);

            groupImageView = itemView.findViewById(R.id.item_creategroup_group_image);
            groupNameTextView = itemView.findViewById(R.id.item_creategroup_group_name);
            groupDescriptionTextView = itemView.findViewById(R.id.item_creategroup_group_description);
        }
        //verilerimizi set ediyoruz.
        public void setData(GroupModel groupModel) {
            groupNameTextView.setText(groupModel.getName());
            groupDescriptionTextView.setText(groupModel.getDescription());
        }
    }












    //verileri listeye atıyoruz.
    List<GroupModel> groupModelList;
    public GroupAdapter(List<GroupModel> groupModelList) {
        this.groupModelList = groupModelList;
    }
}