package com.github.florent37.myyoutube.videodetail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.myyoutube.R;
import com.github.florent37.myyoutube.model.ItemId;
import com.github.florent37.myyoutube.model.Video;
import com.github.florent37.myyoutube.task.SearchVideoTask;
import com.github.florent37.myyoutube.videolist.VideoAdapter;
import com.github.florent37.myyoutube.videoplay.IntroVideoActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import info.androidhive.youtubeplayer.YoutubeActivity;


/**
 * Created by florentchampigny on 17/06/15.
 */
public class VideoDetailFragment extends Fragment{

    public static final String VIDEO = "VIDEO";

    @InjectView(R.id.image)
    ImageView imageView;

    @InjectView(R.id.title)
    TextView title;

    @InjectView(R.id.subTitle)
    TextView subtitle;

    @InjectView(R.id.description)
    TextView description;

    Video video;

    public static VideoDetailFragment newInstance(Video video) {
        VideoDetailFragment videoListFragment = new VideoDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(VIDEO,video);

        videoListFragment.setArguments(bundle);

        return videoListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_videos, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);

        this.video = getArguments().getParcelable(VIDEO);

        title.setText(this.video.getSnippet().getTitle());
        subtitle.setText(this.video.getSnippet().getChannelTitle());
        description.setText(this.video.getSnippet().getDescription());

        Picasso.with(imageView.getContext())
                .load(video.getSnippet().getThumbnails().getMedium().getUrl())
                .fit().centerCrop()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        getActivity().supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onError() {

                    }
                });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Video Id.  By including a URL in the Intent data of the form ytv://videoid, one can play a specific video.
               // Playlist Id.  By including a URL in the Intent data of the form ytpl://playlistid, one can play the latest video added to a YouTube playlist.  This is great for
                ItemId itemId = video.getId();
                if (itemId == null){
                    return;
                }
                String videoId = itemId.getVideoId();
//                Intent lVideoIntent = new Intent(null, Uri.parse("ytv://" + videoid), getActivity(), IntroVideoActivity.class);

//                Intent lVideoIntent = new Intent(getActivity(), YoutubeActivity.class);
//                lVideoIntent.putExtra("VIDEO_ID_KEY", videoId);
//                startActivity(lVideoIntent);


                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
                intent.putExtra("VIDEO_ID_KEY", videoId);
                startActivity(intent);
            }
        });

    }


}
