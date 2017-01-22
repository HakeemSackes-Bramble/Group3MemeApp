package nyc.c4q.akashaarcher.group3memestudio.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nyc.c4q.akashaarcher.group3memestudio.R;

/**
 * Created by shawnspeaks on 1/21/17.
 */

public class ColorPickerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.color_palette, container, false);
    }

}
