package com.tayara.dijkstra.presentation.ui.base.view.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;

import com.tayara.dijkstra.presentation.ui.base.view.IBaseView;
import com.tayara.dijkstraapplication.R;


abstract public class BaseActivity<DB extends ViewDataBinding> extends AppCompatActivity implements IBaseView {

    protected DB binding;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private int drawerGravity;

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
    }

    protected void setView() {
        try {
            binding = DataBindingUtil.setContentView(this, getLayoutId());
            binding.setLifecycleOwner(this);
            binding.executePendingBindings();
        } catch (Exception e) {
            setContentView(getLayoutId());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout != null && drawerLayout.isDrawerOpen(drawerGravity)) {
            drawerLayout.closeDrawer(drawerGravity);
            return;
        }
        super.onBackPressed();
    }


    // base methods

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(int stringId) {
        showMessage(getString(stringId));
    }

    @Override
    public void showDialogMessage(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, (dialog, id) -> dialog.dismiss())
                .setNegativeButton(android.R.string.cancel, (dialog, id) -> dialog.dismiss())
                .create().show();
    }

    @Override
    public void showDialogMessage(int title, int message) {
        showDialogMessage(getString(title), getString(message));
    }

    @Override
    public void showDialogMessage(String message) {
        showDialogMessage("", message);
    }

    @Override
    public void showDialogMessage(int message) {
        showDialogMessage("", getString(message));
    }

    @Override
    public void hideKeyboard() {
        View currentFocus = getCurrentFocus();
        if (currentFocus == null)
            currentFocus = new View(this);
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

    public void changeToolbarTitle(String title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    protected void changeHomeIndicator(@DrawableRes int drawable) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

    // drawer
    protected void setupDrawer(DrawerLayout drawerLayout, int drawerGravity) {
        this.drawerLayout = drawerLayout;
        this.drawerGravity = drawerGravity;
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        this.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout != null)
                openDrawer();
            else
                onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public ActionBarDrawerToggle getActionBarDrawerToggle() {
        return actionBarDrawerToggle;
    }

    public int getDrawerGravity() {
        return drawerGravity;
    }

    public void openDrawer() {
        if (drawerLayout != null)
            drawerLayout.openDrawer(drawerGravity);
    }

    public void closeDrawer() {
        if (drawerLayout != null)
            drawerLayout.closeDrawer(drawerGravity);
    }
}
