package net.peyrache.marketplace.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.peyrache.marketplace.R;
import net.peyrache.marketplace.model.UtilisateurAc;

public class Fragment_Home_UtilAc extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemsContainer = inflater.inflate(R.layout.fragment_home_utilac, container, false);
        return itemsContainer;
    }
}

