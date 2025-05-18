package projeto.piloto.projeto_off_web.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(String assetFileName);
    }

    private List<String> pdfFiles;
    private OnItemClickListener listener;

    public PdfAdapter(List<String> pdfFiles, OnItemClickListener listener) {
        this.pdfFiles = pdfFiles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String fileName = pdfFiles.get(position);
        holder.textView.setText(fileName);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(fileName));
    }

    @Override
    public int getItemCount() {
        return pdfFiles.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}