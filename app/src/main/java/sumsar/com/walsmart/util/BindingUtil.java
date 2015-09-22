package sumsar.com.walsmart.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import sumsar.com.walsmart.R;

/**
 * Created by rasmusgohs on 22/09/15.
 */
public class BindingUtil {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).crossFade().placeholder(R.drawable.walmart).into(imageView);
    }
}



