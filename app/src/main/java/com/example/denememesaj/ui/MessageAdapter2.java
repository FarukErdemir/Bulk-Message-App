package com.example.denememesaj.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.denememesaj.R;

import java.util.List;

public class MessageAdapter2 extends RecyclerView.Adapter<MessageAdapter2.MessageViewHolder> {

    List<MessageModel> messageModelList;
    OnClickItemEventListener onClickItemEventListener;

    public MessageAdapter2(List<MessageModel> messageModelList, OnClickItemEventListener onClickItemEventListener) {
        this.messageModelList = messageModelList;
        this.onClickItemEventListener = onClickItemEventListener;
    }
    //layout inflator ile içine mesajı oluşturalım viewholdera
    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sendmessage_message, parent, false), onClickItemEventListener);
    }
    //viewholdera veri çekemek için fonkisyon yazıyoruz.
    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        MessageModel messageModel = messageModelList.get(position);
        holder.setData(messageModel);
    }
    //item count ise genişliği kadar olacak
    @Override
    public int getItemCount() {
        return messageModelList.size();
    }
    //viewholderda gösterilcekler için gerekli fonksiyonu yazıyoruz.
    public class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView, descriptionTextView;
        OnClickItemEventListener onClickItemEventListener;
        public MessageViewHolder( View itemView, OnClickItemEventListener onClickItemEventListener) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.item_sendmessage_message_nameTextView);
            descriptionTextView = itemView.findViewById(R.id.item_sendmessage_message_descriptionTextView);
            this.onClickItemEventListener = onClickItemEventListener;

            itemView.setOnClickListener(this);
        }
        //verilerimizi set ediyoruz.
        public void setData(MessageModel messageModel){
            nameTextView.setText(messageModel.getName());
            descriptionTextView.setText(messageModel.getDescription());
        }


















        @Override
        public void onClick(View view) {
            onClickItemEventListener.onClickItemEvent(getAdapterPosition());
        }
    }
}