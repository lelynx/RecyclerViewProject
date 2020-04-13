package linceo_informatique.lelynx.recyclerviewproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private ArrayList<ExampleItem> mExamplist;

    private OnItemClickListener mListener;

    // interface pour gérer le click sur un item
    interface OnItemClickListener {
        void onClick(int position);
    }

    // on créé une méthode qui sera utilisée par MainActivity
    void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    static class ExampleViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView1;
        TextView mTextView2;
        ImageView mIVDelete;
        // CTor de la classe ExampleViewHolder
        // ici, on gère aussi le click sur un item
        ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            mIVDelete = itemView.findViewById(R.id.iv_delete_item);
            //
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION) {
                            // onClick devra être exécuté par MainActivity
                            listener.onClick(position);
                        }
                    }
                }
            });
            // interception du clic sur delete
            mIVDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Click sur l'Item "+getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    // pour intercepter le clic, et éviter qu'il se propage au parent.
                    view.getParent().requestDisallowInterceptTouchEvent(true);

                }
            });
        } //
    } //

    // le CTor de la classe ExampleAdapter
    public ExampleAdapter(ArrayList<ExampleItem> examplist) {
        // récupère la data source
        mExamplist = examplist;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.example_item, parent, false);
        // ici, on passe le listener en 2ième paramètre
        return new ExampleViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        //Called by RecyclerView to display the data at the specified position.
        //This method should update the contents of the RecyclerView.ViewHolder.itemView to reflect
        // the item at the given position.
        ExampleItem exampleItem = mExamplist.get(position);
        holder.mImageView.setImageResource(exampleItem.getImageResource());
        holder.mTextView1.setText(exampleItem.getText1());
        holder.mTextView2.setText(exampleItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mExamplist.size();
    }

    public ArrayList<ExampleItem> getListOfItems() {
        return mExamplist;
    }
}

