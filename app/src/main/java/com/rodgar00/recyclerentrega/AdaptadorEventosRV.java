package com.rodgar00.recyclerentrega;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.google.android.material.textfield.TextInputEditText;

public class AdaptadorEventosRV extends RecyclerView.Adapter<AdaptadorEventosRV.SostenDeVistas> {

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

        if (cardsFalladas.contains(event)) {
            holder.card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.rojito));
        } else {
            holder.card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.coffee));
        }

        holder.itemView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View alertPopUpView = LayoutInflater.from(context)
                    .inflate(R.layout.event_popup, null);

            TextInputEditText inputFecha = alertPopUpView.findViewById(R.id.inputFecha);

            builder.setTitle(event.getEventName())
                    .setView(alertPopUpView)
                    .setPositiveButton("Comprobar", (dialog, which) -> {
                        String respuesta = inputFecha.getText().toString().trim();

                        if (respuesta.equals(event.getEventDate())) {
                            events.remove(event);
                            notifyDataSetChanged();
                        } else {
                            if (!cardsFalladas.contains(event)) {
                                cardsFalladas.add(event);
                            }
                            holder.card.setCardBackgroundColor((ContextCompat.getColor(context, R.color.rojito))
                            );
                        }

                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
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
