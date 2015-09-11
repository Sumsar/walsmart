package sumsar.com.walsmart.productlist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import sumsar.com.walsmart.R;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductListActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setIcon(R.drawable.walmart);
        setTitle(R.string.app_name);
        initFragments();
    }


    private void initFragments() {
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (getSupportFragmentManager().findFragmentByTag(ProductListFragment.TAG) == null) {
            fragmentTransaction.replace(R.id.container, ProductListFragment.getInstance()).commit();
            getSupportFragmentManager().executePendingTransactions();
        }
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
}
