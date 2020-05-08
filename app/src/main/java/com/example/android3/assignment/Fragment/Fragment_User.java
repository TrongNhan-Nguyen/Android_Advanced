package com.example.android3.assignment.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.android3.assignment.Acitivities.LoginActivity;
import com.example.android3.assignment.Acitivities.MainActivity;
import com.example.android3.assignment.R;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_User extends Fragment {
    View view;
    ImageView imgAvatar;
    TextView txtName, txtEmail, txtId, txtType;
    Button btnSignOut;
    GoogleSignInClient mGoogleSignInClient;
    String name, email, id;

    public Fragment_User() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user, container, false);
        initView();

        return view;
    }

    public void initView() {


        imgAvatar = (ImageView) view.findViewById(R.id.fUser_Img);
        txtName = (TextView) view.findViewById(R.id.fUser_TxtName);
        txtEmail = (TextView) view.findViewById(R.id.fUser_TxtEmail);
        txtId = (TextView) view.findViewById(R.id.fUser_TxtId);
        txtType = (TextView) view.findViewById(R.id.fUser_TxtType);
        btnSignOut = (Button) view.findViewById(R.id.fUser_BtnSignOut);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            name = acct.getDisplayName();
             email = acct.getEmail();
             id = acct.getId();
//            Uri personPhoto = acct.getPhotoUrl();
//            Picasso.get().load(personPhoto).into(imgAvatar);
            txtName.setText(name);
            txtEmail.setText(email);
            txtId.setText(id);
            txtType.setText("Google");
            imgAvatar.setImageResource(R.drawable.avatar_profile);

        }else {
            Intent intent = getActivity().getIntent();
            Bundle bundle = intent.getExtras();
            if (bundle!=null){
                name = bundle.getString("name");
                email = bundle.getString("email");
                id = bundle.getString("id");
                txtName.setText(name);
                txtEmail.setText(email);
                txtId.setText(id);
                txtType.setText("FaceBook");
                imgAvatar.setImageResource(R.drawable.avatar);
            }
        }

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    }
                });
        LoginManager.getInstance().logOut();
    }
}
