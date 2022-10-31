package vcpe.st10118615.mooiste.activity;

import static vcpe.st10118615.mooiste.fragment.HomeFragment.clearClicked;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import vcpe.st10118615.mooiste.R;
import vcpe.st10118615.mooiste.fragment.HomeFragment;
import vcpe.st10118615.mooiste.util.Utils;

// TODO CHANGE CATEGORIES
// TODO REMOVE BRAND AND ADJUST SIZE
public class SearchFiltersActivity extends AppCompatActivity implements View.OnClickListener {
    TextView clearFilters;
    LinearLayout categoryAccessories, categoryJewellery, categoryClothing;
    LinearLayout sizeRegular, sizePlus, sizeJunior, sizeTall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filters);
        Utils.statusBarColor(SearchFiltersActivity.this);

        initAll();

        clearFilters.setOnClickListener(this);

        categoryAccessories.setOnClickListener(this);
        categoryJewellery.setOnClickListener(this);
        categoryClothing.setOnClickListener(this);

        sizeRegular.setOnClickListener(this);
        sizePlus.setOnClickListener(this);
//        sizeJunior.setOnClickListener(this);
        sizeTall.setOnClickListener(this);

    }

    private void initAll() {
        clearFilters = findViewById(R.id.id_clear_btn);
        categoryAccessories = findViewById(R.id.cat_accessory);
        categoryJewellery = findViewById(R.id.cat_jewellery);
        categoryClothing = findViewById(R.id.cat_clothing);

        sizeRegular = findViewById(R.id.size_regular);
        sizePlus = findViewById(R.id.size_plus);
//        sizeJunior = findViewById(R.id.size_junior);
        sizeTall = findViewById(R.id.size_tall);
    }

    public void goBack(View view) {
        finish();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.cat_accessory:
                HomeFragment.category = "Accessory";
                HomeFragment.isCategorySelected = true;
                HomeFragment.isFiltersApplied = true;
                finish();
                break;
            case R.id.cat_jewellery:
                HomeFragment.category = "Jewellery";
                HomeFragment.isCategorySelected = true;
                HomeFragment.isFiltersApplied = true;
                finish();
                break;
            case R.id.cat_clothing:
                HomeFragment.category = "Clothing";
                HomeFragment.isCategorySelected = true;
                HomeFragment.isFiltersApplied = true;
                finish();
                break;
            case R.id.size_regular:
                HomeFragment.sizeType = "Regular";
                HomeFragment.iSizeTypeSelected = true;
                HomeFragment.isFiltersApplied = true;
                finish();
            case R.id.size_plus:
                HomeFragment.sizeType = "Plus";
                HomeFragment.iSizeTypeSelected = true;
                HomeFragment.isFiltersApplied = true;
                finish();
//            case R.id.size_junior:
//                HomeFragment.sizeType = "Juniors";
//                HomeFragment.iSizeTypeSelected = true;
//                HomeFragment.isFiltersApplied = true;
//                finish();
            case R.id.size_tall:
                HomeFragment.sizeType = "Tall";
                HomeFragment.iSizeTypeSelected = true;
                HomeFragment.isFiltersApplied = true;
                finish();
                break;
            case R.id.id_clear_btn:
                clearClicked();
                finish();
                break;
        }
    }
}
