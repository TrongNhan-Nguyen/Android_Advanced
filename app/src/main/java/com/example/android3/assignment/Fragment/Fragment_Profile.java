package com.example.android3.assignment.Fragment;


import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.android3.assignment.R;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Profile extends Fragment {
    private View view;
    private CircleImageView imgAvatar;
    private TextView txtDisplayName, txtEmail, txtType, txtId;
    private LinearLayout containerId;
    public Fragment_Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initView();

        return view;
    }

    public void initView() {
        txtId = (TextView) view.findViewById(R.id.fProfile_TxtId);
        txtDisplayName = (TextView) view.findViewById(R.id.fProfile_TxtDisPlayName);
        txtEmail = (TextView) view.findViewById(R.id.fProfile_TxtEmail);
        txtType = (TextView) view.findViewById(R.id.fProfile_TxtType);
        containerId = (LinearLayout) view.findViewById(R.id.fProfile_ContainerId);
        imgAvatar = (CircleImageView) view.findViewById(R.id.fProfile_ImgAvatar);
        getInfo();
    }
    private void getInfo(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (accessToken != null) {
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken,
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {

                            try {
                                String email = object.getString("email");
                                String id = object.getString("id");
                                String name = object.getString("name");
                                String url = "https://graph.facebook.com/" + id + "/picture?type=large";
                                txtDisplayName.setText(name);
                                txtEmail.setText(email);
                                txtId.setText(id);
                                txtType.setText("Facebook");
                                Picasso.get().load(Uri.parse(url)).into(imgAvatar);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            Bundle bundle = new Bundle();
            bundle.putString("fields", "email,name,id");
            graphRequest.setParameters(bundle);
            graphRequest.executeAsync();
        } else if (acct != null) {
            txtDisplayName.setText(acct.getDisplayName());
            txtEmail.setText(acct.getEmail());
            txtId.setText(acct.getId());
            Picasso.get().load(acct.getPhotoUrl()).into(imgAvatar);
            txtType.setText("Google");
        }else {
            containerId.setVisibility(View.GONE);
        }
    }

}
