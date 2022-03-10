package com.example.practicaandroid.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.example.practicaandroid.FragmentComunication;
import com.example.practicaandroid.R;

public class DialogAddCategoria extends DialogFragment {
    private View dialogView;
    //Atributos
    private EditText nameET;
    private FragmentComunication fragmentComunication;

    public DialogAddCategoria() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_categoria, null);
        nameET= dialogView.findViewById(R.id.nameAddET);

        builder.setView(dialogView)
                        .setMessage("Add category")
                                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(getActivity(), "Category added", Toast.LENGTH_SHORT).show();
                                        String titulo = nameET.getText().toString();
                                        if (!titulo.equals("")) {
                                            fragmentComunication.onCategoriaInsertItemSelected(titulo);
                                            dialogInterface.dismiss();
                                        }else{
                                            Toast.makeText(getActivity(), "Introduce todos los datos", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        builder.setView(dialogView);
        return builder.show();
    }
}
