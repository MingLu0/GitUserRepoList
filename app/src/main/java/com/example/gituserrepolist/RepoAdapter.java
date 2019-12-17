package com.example.gituserrepolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 *  This customised Adapter class binds the data to the list
 */
public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    private List<SimpleRepo> repoList;

    /**
     * Constructor
     * @param repoList a list of SimpleRepo objects to provide data for each row view
     */
    public RepoAdapter(@Nullable @NonNull List<SimpleRepo>repoList){
        this.repoList = repoList;
    }

    /**
     *  This overridden method inflates a  row view layout to create a new view
     * @param parent the parent of the single row view
     * @param viewType the type of the view
     * @return a new row view
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.repo_row,parent,false);
        return new ViewHolder(view);

    }

    /**
     *  Bind the data of a given index in the data source list with a provided view holder
     * @param holder the row view to be replaced with new content data
     * @param position the position of the element in the data list
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txt_row_name.setText(repoList.get(position).getName());

    }

    /**
     * This methods gets the number of items in the data list or 0 if the list is null
     * @return number of elements in the data list
     */
    @Override
    public int getItemCount() {
        if(repoList != null){
            return repoList.size();
        }
        return 0;
    }

    /**
     *  The class provides a reference to the views for each data items.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_row_name;

        public ViewHolder(View itemView){
            super(itemView);
            txt_row_name = itemView.findViewById(R.id.text_row_name);
        }
    }

}
