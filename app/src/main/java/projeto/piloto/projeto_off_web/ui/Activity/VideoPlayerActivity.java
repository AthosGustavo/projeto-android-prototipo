package projeto.piloto.projeto_off_web.ui.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import projeto.piloto.projeto_off_web.R;

public class VideoPlayerActivity extends AppCompatActivity {
    private VideoView videoView;
    private ImageButton btnPlayPause;
    private boolean isPrepared = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        String videoPath = getIntent().getStringExtra("videoPath");
        videoView = findViewById(R.id.videoView);
        btnPlayPause = findViewById(R.id.btnPlayPause);

        videoView.setVideoURI(Uri.parse(videoPath));
        videoView.setOnPreparedListener(mp -> {
            isPrepared = true;
            btnPlayPause.setImageResource(android.R.drawable.ic_media_play);
        });

        btnPlayPause.setOnClickListener(v -> {
            if (!isPrepared) return;
            if (videoView.isPlaying()) {
                videoView.pause();
                btnPlayPause.setImageResource(android.R.drawable.ic_media_play);
            } else {
                videoView.start();
                btnPlayPause.setImageResource(android.R.drawable.ic_media_pause);
            }
        });
    }
}