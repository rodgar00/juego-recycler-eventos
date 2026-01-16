package com.rodgar00.recyclerentrega;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import com.google.android.material.textfield.TextInputEditText;

public class AdaptadorEventosRV extends RecyclerView.Adapter<AdaptadorEventosRV.SostenDeVistas> {
    int fallos = 0;
    Context context;
    ArrayList<EventModel> events;
    ArrayList<EventModel> cardsFalladas = new ArrayList<>();

    public AdaptadorEventosRV(Context context, ArrayList<EventModel> events) {
        this.context = context;
        this.events = events;
    }

    @NonNull
    @Override
    public SostenDeVistas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cv_row, parent, false);
        return new SostenDeVistas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SostenDeVistas holder, int position) {
        EventModel event = events.get(position);

        holder.tvName.setText(event.getEventName());

        switch (event.getFailCount()) {
            case 1:
                holder.card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.rojo1));
                break;
            case 2:
                holder.card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.rojo2));
                break;
            case 3:
                holder.card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.rojo3));
                break;
            case 4:
                holder.card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.rojo4));
                break;
            case 5:
                holder.card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.rojo5));
                break;
            default:
                holder.card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.coffee));
                break;
        }

        holder.itemView.setOnClickListener(v -> {
            AlertDialog.Builder alertaAdivinar = new AlertDialog.Builder(context);
            View alertPopUpView = LayoutInflater.from(context)
                    .inflate(R.layout.event_popup, null);

            TextInputEditText inputFecha = alertPopUpView.findViewById(R.id.inputFecha);

            alertaAdivinar.setTitle(event.getEventName());
            alertaAdivinar.setView(alertPopUpView);
            alertaAdivinar.setPositiveButton("Comprobar", (dialog, which) -> {
                String respuesta = inputFecha.getText().toString().trim();

                if (respuesta.equals(event.getEventDate())) {
                    events.remove(event);
                    notifyDataSetChanged();
                } else {
                    event.incrementFailCount();
                    Toast.makeText(context, "Fallos de esta pregunta: " + event.getFailCount(), Toast.LENGTH_SHORT).show();
                    notifyItemChanged(position);
                }

            }).setNegativeButton("Cancelar", null);
            alertaAdivinar.show();
        });
    }


    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class SostenDeVistas extends RecyclerView.ViewHolder {
        TextView tvName;
        CardView card;

        public SostenDeVistas(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvEventName);
            card = itemView.findViewById(R.id.eventCard);
        }
    }
}
