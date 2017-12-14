package hai.com.myapp.fragment;

import android.animation.Animator;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import hai.com.myapp.R;

/**
 * Created by Administrator on 2017/6/10.
 */

public class FirstFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println(getClass().getName() + ".onCreate...");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        System.out.println(getClass().getName() + ".onCreateView...");
        return inflater.inflate(R.layout.fragment_first, null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println(getClass().getName() + ".onActivityResult...");
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        System.out.println(getClass().getName() + ".onRequestPermissionsResult...");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        System.out.println(getClass().getName() + ".onInflate...");
        super.onInflate(context, attrs, savedInstanceState);
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        System.out.println(getClass().getName() + ".onAttachFragment...");
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onAttach(Context context) {
        System.out.println(getClass().getName() + ".onAttach...");
        super.onAttach(context);
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        System.out.println(getClass().getName() + ".onCreateAnimator...");
        return super.onCreateAnimator(transit, enter, nextAnim);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        System.out.println(getClass().getName() + ".onViewCreated...");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        System.out.println(getClass().getName() + ".onActivityCreated...");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        System.out.println(getClass().getName() + ".onViewStateRestored...");
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onStart() {
        System.out.println(getClass().getName() + ".onStart...");
        super.onStart();
    }

    @Override
    public void onResume() {
        System.out.println(getClass().getName() + ".onResume...");
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        System.out.println(getClass().getName() + ".onSaveInstanceState...");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        System.out.println(getClass().getName() + ".onMultiWindowModeChanged...");
        super.onMultiWindowModeChanged(isInMultiWindowMode);
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        System.out.println(getClass().getName() + ".onPictureInPictureModeChanged...");
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        System.out.println(getClass().getName() + ".onConfigurationChanged...");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPause() {
        System.out.println(getClass().getName() + ".onPause...");
        super.onPause();
    }

    @Override
    public void onStop() {
        System.out.println(getClass().getName() + ".onStop...");
        super.onStop();
    }

    @Override
    public void onLowMemory() {
        System.out.println(getClass().getName() + ".onLowMemory...");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        System.out.println(getClass().getName() + ".onTrimMemory...");
        super.onTrimMemory(level);
    }

    @Override
    public void onDestroyView() {
        System.out.println(getClass().getName() + ".onDestroyView...");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        System.out.println(getClass().getName() + ".onDestroy...");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        System.out.println(getClass().getName() + ".onDetach...");
        super.onDetach();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        System.out.println(getClass().getName() + ".onCreateOptionsMenu...");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        System.out.println(getClass().getName() + ".onPrepareOptionsMenu...");
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onDestroyOptionsMenu() {
        System.out.println(getClass().getName() + ".onDestroyOptionsMenu...");
        super.onDestroyOptionsMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println(getClass().getName() + ".onOptionsItemSelected...");
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        System.out.println(getClass().getName() + ".onOptionsMenuClosed...");
        super.onOptionsMenuClosed(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        System.out.println(getClass().getName() + ".onCreateContextMenu...");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        System.out.println(getClass().getName() + ".onContextItemSelected...");
        return super.onContextItemSelected(item);
    }
}
