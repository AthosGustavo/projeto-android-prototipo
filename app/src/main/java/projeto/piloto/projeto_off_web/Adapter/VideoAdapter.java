package projeto.piloto.projeto_off_web.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import projeto.piloto.projeto_off_web.R;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
  public interface OnItemClickListener {
    void onItemClick(String assetFileName);
  }

  private List<String> videos;
  private OnItemClickListener listener;

  public VideoAdapter(List<String> videos, OnItemClickListener listener) {
    this.videos = videos;
    this.listener = listener;
  }

  @NonNull
  @Override
  public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
    return new VideoViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
    String video = videos.get(position);
    holder.textView.setText(video);
    holder.itemView.setOnClickListener(v -> listener.onItemClick(video));
  }

  @Override
  public int getItemCount() {
    return videos.size();
  }

  static class VideoViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    VideoViewHolder(View itemView) {
      super(itemView);
      textView = itemView.findViewById(R.id.videoName);
    }
  }
}