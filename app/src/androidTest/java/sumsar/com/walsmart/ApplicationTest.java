package sumsar.com.walsmart;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;

import sumsar.com.walsmart.productlist.view.ProductListActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ApplicationTest extends ActivityInstrumentationTestCase2<ProductListActivity> {


    public ApplicationTest() {
        super(ProductListActivity.class);
    }

    public ApplicationTest(Class<ProductListActivity> activityClass) {
        super(activityClass);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    @MediumTest
    public void testSelectProduct() throws InterruptedException {
        Thread.sleep(4000);

        onView(withId(R.id.product_list_recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.product_detail_name)).check(matches(withText("Ellerton TV Console")));


    }

}