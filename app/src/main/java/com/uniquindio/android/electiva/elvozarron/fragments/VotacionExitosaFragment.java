package com.uniquindio.android.electiva.elvozarron.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import com.uniquindio.android.electiva.elvozarron.R;
import com.uniquindio.android.electiva.elvozarron.util.Utilidades;
import com.uniquindio.android.electiva.elvozarron.vo.Participante;

import java.net.MalformedURLException;
import java.net.URL;

import io.fabric.sdk.android.Fabric;

/**
 * Este DialogFragment permite mostra al usuario que su voto ha sido exitoso,
 * ademas de brinda la opcion para compartir su voto en facebool o twitter
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class VotacionExitosaFragment extends DialogFragment implements View.OnClickListener {


    /**
     * Atributo callbackManager del DialogFragment
     */
    private CallbackManager callbackManager;

    /**
     * Interface para la comunicacion entre el DialogFragment y el fragmento
     */
    public interface OnBotonSeleccionadoListener {
        void onBotonSeleccionado(LoginButton v);
    }

    /**
     * Atributo listener del DialogFragment VotacionExitosaFragment
     */
    private static OnBotonSeleccionadoListener listener;

    /**
     * Atributo btnFacebook del DialogFragment VotacionExitosaFragment
     */
    private ImageButton btnFacebook;

    /**
     * Atributo  btnTwitter del DialogFragment VotacionExitosaFragment
     */
    private ImageButton btnCompartirTwitter;

    /**
     * Atributo btnloginTwitter del DialogFragment
     */
    private TwitterLoginButton btnloginTwitter;

    /**
     * Atributo shareDialog de facebook
     */
    private ShareDialog shareDialog;

    /**
     * Atributo  btnSalir del DialogFragment VotacionExitosaFragment
     */
    private ImageButton btnSalir;


    /**
     * Atributo Participante
     */
    private Participante participante;


    /**
     * Pemite mostrar el dialogFragment
     *
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.votacion_exitosa_fragment, new LinearLayout(getActivity()), false);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(Utilidades.TWITTER_KEY, Utilidades.TWITTER_SECRET);
        Fabric.with(super.getActivity(), new Twitter(authConfig), new TweetComposer());

        Bundle bundle = getArguments();
        participante = bundle.getParcelable("key_participante");

        btnFacebook = (ImageButton) view.findViewById(R.id.btnFacebook);
        btnFacebook.setOnClickListener(this);

        btnCompartirTwitter = (ImageButton) view.findViewById(R.id.btnCompartirTwitter);
        btnCompartirTwitter.setOnClickListener(this);

        btnloginTwitter = (TwitterLoginButton) view.findViewById(R.id.twitter_login_button);
        btnloginTwitter.setCallback(new Callback<TwitterSession>() {

            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = result.data;
                Log.i("mensaje",session.getUserName());
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });
        btnSalir = (ImageButton) view.findViewById(R.id.imagenSalir);
        btnSalir.setOnClickListener(this);

        Dialog builder = new Dialog(getActivity());
        builder.setContentView(view);

        callbackManager = CallbackManager.Factory.create();
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.i("facebook", "onSucess");
                        Toast.makeText(getContext(), "Su voto ha sido compartido", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onCancel() {// App code
                        Log.i("facebook", "onSCancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.i("facebook", "onError");
                    }
                });

        return builder;
    }


    /**
     * Callback del DialogFragment
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        btnloginTwitter.onActivityResult(requestCode,resultCode,data);
    }


    /**
     * Metodo CallBack del DialogFragment
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Facebook
        shareDialog = new ShareDialog(getActivity());

        TwitterSession session = Twitter.getSessionManager().getActiveSession();
        if (session != null) {
            btnloginTwitter.setVisibility(View.INVISIBLE);
        } else {
            Log.i("MENSAJE", "No se inicio sesicion");
        }
    }

    /**
     * Permite realizar el evento segun el id del boton
     *
     * @param v vista la cual se va producir el evento
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnFacebook:
                if (ShareDialog.canShow(ShareLinkContent.class)) {

                    if (ShareDialog.canShow(ShareLinkContent.class)) {

                        ShareLinkContent content = new ShareLinkContent.Builder()
                                .setContentTitle(participante.getNombre())
                                .setContentUrl(Uri.parse(participante.getUrl()))
                                .setContentDescription("Participante de ElVozarron")
                                .build();
                        shareDialog.show(content);
                    }
                }
                break;

            case R.id.btnCompartirTwitter:
                try {
                    URL url = new URL(participante.getUrl());
                    TweetComposer.Builder builder = new TweetComposer.Builder(getContext()).
                            text(participante.getNombre()).
                            url(url);
                    builder.show();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.imagenSalir:
                getDialog().dismiss();
                break;
        }
    }
}
