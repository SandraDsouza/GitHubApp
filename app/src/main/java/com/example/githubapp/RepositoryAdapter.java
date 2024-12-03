package com.example.githubapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {

    private final List<Repository> repositories = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Repository repository);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    /**
     * Clears the current list of repositories and notifies the adapter.
     */
    public void clearData() {
        repositories.clear();
        notifyDataSetChanged();
    }

    /**
     * Appends new repositories to the list and notifies the adapter.
     *
     * @param newRepositories List of repositories to be added.
     */
    public void addRepositories(List<Repository> newRepositories) {
        repositories.addAll(newRepositories);
        notifyDataSetChanged();
    }

    /**
     * Sets the list of repositories and replaces the old data.
     *
     * @param repositories List of repositories to be set.
     */
    public void setRepositories(List<Repository> repositories) {
        this.repositories.clear();
        this.repositories.addAll(repositories);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repository, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position) {
        Repository repository = repositories.get(position);
        holder.bind(repository);
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    class RepositoryViewHolder extends RecyclerView.ViewHolder {

        private final TextView repoName;
        private final TextView repoStars;
        private final ImageView repoAvatar;

        public RepositoryViewHolder(@NonNull View itemView) {
            super(itemView);
            repoName = itemView.findViewById(R.id.repoName);
            repoStars = itemView.findViewById(R.id.repoStars);
            repoAvatar = itemView.findViewById(R.id.repoAvatar);
        }

        public void bind(Repository repository) {
            repoName.setText(repository.getName());
            repoStars.setText("Stars: " + repository.getStars());

            Glide.with(itemView.getContext())
                    .load(repository.getAvatarUrl())
                    .placeholder(R.drawable.ic_launcher_background) // Placeholder image
                    .error(R.drawable.ic_launcher_background) // Error fallback image
                    .into(repoAvatar);

            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(repository);
                }
            });
        }
    }
}
