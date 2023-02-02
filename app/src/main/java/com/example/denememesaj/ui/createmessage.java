package com.example.denememesaj.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.denememesaj.ui.MessageModel;
import com.example.denememesaj.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class createmessage extends Fragment {

    FirebaseAuth mAuth;
    FirebaseFirestore mStore;

    EditText messageNameEditText, messageDescriptionEditText;
    Button createMessageButton;
    RecyclerView messagesRecyclerView;

    ArrayList<MessageModel> messageModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_createmessage, container, false);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        messageModelList = new ArrayList<>();

        messageNameEditText = view.findViewById(R.id.amemes);
        messageDescriptionEditText = view.findViewById(R.id.mesmes);
        createMessageButton = view.findViewById(R.id.crtmes);
        messagesRecyclerView = view.findViewById(R.id.mesrv);

        //butonumuza basıldığında alanların boş olup olmadoğını kontrol eden fonksiyonu yazıyoruz
        createMessageButton.setOnClickListener(v ->{
            String messageName = messageNameEditText.getText().toString();
            String messageDescription = messageDescriptionEditText.getText().toString();

            if (messageName.isEmpty()||messageDescription.isEmpty()){
                Toast.makeText(getContext(), "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show();
                return;
            }
            CreateMessage(messageName, messageDescription);
        });
        //mesaj çekme
        FetchMessage();
        return view;
    }
    //mesajları firebase kaydeden fonksiyon toast mesajlı
    private void CreateMessage(String messageName,String messageDescription){
        String userId=mAuth.getCurrentUser().getUid();
        mStore.collection("/userdata/" + userId + "/messages").add(new HashMap<String, String>(){{
                    put("name", messageName);
                    put("description", messageDescription);
                }})
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(getContext(), "Mesaj başarıyla oluşturuldu", Toast.LENGTH_SHORT).show();

                    documentReference.get().addOnSuccessListener(documentSnapshot -> {
                        MessageModel messageModel = new MessageModel(messageName, messageDescription, documentSnapshot.getId());
                        messageModelList.add(messageModel);
                        messagesRecyclerView.getAdapter().notifyItemInserted(messageModelList.size() - 1);
                    });
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Mesaj oluşturulurken bir hata oluştu", Toast.LENGTH_SHORT).show();
                });
    }
    //mesajları firebaseden çekmek için gerekli fonksiyon
    private void FetchMessage(){
        String userId =mAuth.getCurrentUser().getUid();
        mStore.collection("userdata/"+userId+"/messages").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    messageModelList.clear();
                    for (DocumentSnapshot documentSnapshot:queryDocumentSnapshots.getDocuments()){
                        MessageModel messageModel=new MessageModel(documentSnapshot.getString("name"),documentSnapshot.getString("description"),documentSnapshot.getId());
                        messageModelList.add(messageModel);
                    }


                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Mesajlar alınırken bir hata oluştu", Toast.LENGTH_SHORT).show();
                });
    }
}