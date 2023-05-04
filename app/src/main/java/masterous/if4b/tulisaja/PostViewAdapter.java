package masterous.if4b.tulisaja;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import masterous.if4b.tulisaja.databinding.PostItemBinding;

public class PostViewAdapter extends RecyclerView.Adapter<PostViewAdapter.ViewHolder> {
    private List<Post> data = new ArrayList<>();

    public void setData(List<Post> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewAdapter.ViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();
        Post post = data.get(pos);
        holder.postItemBinding.tvUsername.setText(post.getUsername());
        holder.postItemBinding.tvContent.setText(post.getContent());
        holder.postItemBinding.tvCreatedDate.setText(post.getCreated_date());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private PostItemBinding postItemBinding;

        public ViewHolder(@NonNull PostItemBinding itemView) {
            super(itemView.getRoot());
            postItemBinding = itemView;
        }
    }
}