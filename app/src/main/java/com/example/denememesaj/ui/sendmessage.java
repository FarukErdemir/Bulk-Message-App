package com.example.denememesaj.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denememesaj.ui.GroupModel;
import com.example.denememesaj.ui.MessageModel;
import com.example.denememesaj.ui.OnClickItemEventListener;
import com.example.denememesaj.R;
import com.example.denememesaj.ui.GroupAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class sendmessage extends Fragment {

    RecyclerView groupsRecyclerView, messagesRecyclerView;
    TextView selectedGroupTextView, selectedMessageTextView;
    Button sendButton;

    FirebaseAuth mAuth;
    FirebaseFirestore mStore;

    ArrayList<GroupModel> groupModelList;
    ArrayList<MessageModel> messageModelList;

    GroupModel selectedGroupModel;
    MessageModel selectedMessageModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sendmessage, container, false);

        groupsRecyclerView = view.findViewById(R.id.groupsrv);
        messagesRecyclerView = view.findViewById(R.id.mesrv);

        selectedGroupTextView = view.findViewById(R.id.grptv);
        selectedMessageTextView = view.findViewById(R.id.mestv);

        sendButton = view.findViewById(R.id.sendmes);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        groupModelList = new ArrayList<>();
        messageModelList = new ArrayList<>();

        ActivityResultLauncher launcher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGrant -> {
            if (isGrant) {
                SendSms();
            }else{
                Toast.makeText(getContext(), "Toplu sms göndermek için izin gereklidir", Toast.LENGTH_SHORT).show();
            }
        });

        sendButton.setOnClickListener(v ->{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    getContext().checkSelfPermission(Manifest.permission.SEND_SMS) !=
                            PackageManager.PERMISSION_GRANTED) {
                launcher.launch(Manifest.permission.SEND_SMS);

            }
            else{
                SendSms();
            }
        });
        



        FetchGroups();
        FetchMessages();
        return view;
    }
    //firebaseden grupları çeken fonksiyon.
    private void FetchGroups(){
        String uid=mAuth.getCurrentUser().getUid();
        mStore.collection("/userdata/" + uid + "/groups").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                groupModelList.clear();
                for (DocumentSnapshot document : task.getResult()) {
                    GroupModel groupModel = new GroupModel(document.getString("name"), document.getString("description"), document.getString("image"), (List<String>)document.get("numbers"),document.getId());
                    groupModelList.add(groupModel);
                }
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                groupsRecyclerView.setLayoutManager(linearLayoutManager);
            }
        });
    }
    //firebaseden mesajları çekmek için fonksiyon
    private void FetchMessages(){
        String uid = mAuth.getCurrentUser().getUid();

        mStore.collection("/userdata/" + uid + "/messages").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                messageModelList.clear();
                for (DocumentSnapshot document : task.getResult()) {
                    MessageModel messageModel = new MessageModel(document.getString("name"), document.getString("description"), document.getId());
                    messageModelList.add(messageModel);
                }

                messagesRecyclerView.setAdapter(new MessageAdapter2(messageModelList, position -> {
                    selectedMessageModel = messageModelList.get(position);
                    selectedMessageTextView.setText("Seçili Mesaj: " + selectedMessageModel.getName());
                }));
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                messagesRecyclerView.setLayoutManager(linearLayoutManager);
            }
        });
    }
    //mesaj göndermek için fonksiyon.
    private void SendSms(){
        if (selectedGroupModel==null||selectedGroupModel==null){
            Toast.makeText(getContext(), "Lütfen bir grup ve mesaj seçin", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectedGroupModel.getNumbers()!=null&&selectedGroupModel.getNumbers().size()>0){
            SmsManager smsManager=SmsManager.getDefault();
            for (String number:selectedGroupModel.getNumbers()){
                smsManager.sendTextMessage(number,null,selectedMessageModel.getDescription(),null,null);
            }
            Toast.makeText(getContext(), "Mesajlar gönderildi", Toast.LENGTH_SHORT).show();
        }
    }
}