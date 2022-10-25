package vcpe.st10118615.mooiste.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vcpe.st10118615.mooiste.R;
import vcpe.st10118615.mooiste.activity.SearchFiltersActivity;
import vcpe.st10118615.mooiste.adapter.ProductsAdapter;
import vcpe.st10118615.mooiste.model.Product;

public class HomeFragment extends Fragment {
    private View view;

    private static ProductsAdapter mAdapter;
    private static RecyclerView recyclerView;
    private static ArrayList<Product> productArrayList;
    private ArrayList<Product> tempList;

    DatabaseReference myRootRef;
    private ProgressBar progressBar;
    private TextView noProductText;
    private ImageView filtersBtn;

    public static String category = "";
    public static String brand = "";
    public static String sizeType = "";

    private static EditText nameInput;
    public static boolean isCategorySelected = false;
    public static boolean isBrandSelected = false;
    public static boolean iSizeTypeSelected = false;
    public static boolean isFiltersApplied = false;

    public static Context context;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        productArrayList = new ArrayList<Product>();
        tempList = new ArrayList<Product>();
        recyclerView = view.findViewById(R.id.product_list);
        progressBar = view.findViewById(R.id.spin_progress_bar);
        noProductText = view.findViewById(R.id.no_product);
        nameInput = view.findViewById(R.id.name_input);
        filtersBtn = view.findViewById(R.id.filters_btn);

        context = getActivity();

        myRootRef = FirebaseDatabase.getInstance().getReference();

        mAdapter = new ProductsAdapter(productArrayList, getActivity(), false);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        settingClickListners();
        getDataFromFirebase();
        searchFunc();
        return view;
    }

    private void ApplyFilters() {
        Log.d("TestArraySize", productArrayList.size() + "");
        if (tempList.size() > 0) {
            tempList.clear();
            Log.d("listClear", tempList.size() + "");
        }
        if (isCategorySelected && isBrandSelected && iSizeTypeSelected) {
            for (Product element : productArrayList) {
                if (element.getCategory().equals(category) && element.getBrand().equals(brand) && element.getSizeType().equals(sizeType)) {
                    tempList.add(element);
                }
            }
        } else if (isCategorySelected && isBrandSelected) {
            for (Product element : productArrayList) {
                if (element.getCategory().equals(category) && element.getBrand().equals(brand)) {
                    tempList.add(element);
                }
            }
        } else if (isCategorySelected && iSizeTypeSelected) {
            for (Product element : productArrayList) {
                if (element.getCategory().equals(category) && element.getSizeType().equals(sizeType)) {
                    tempList.add(element);
                }
            }
        } else if (isBrandSelected && iSizeTypeSelected) {
            for (Product element : productArrayList) {
                if (element.getBrand().equals(brand) && element.getSizeType().equals(sizeType)) {
                    tempList.add(element);
                }
            }
        } else if (isCategorySelected) {
            for (Product element : productArrayList) {
                if (element.getCategory().equals(category)) {
                    tempList.add(element);
                }
            }
        } else if (isBrandSelected) {
            for (Product element : productArrayList) {
                if (element.getBrand().equals(brand)) {
                    tempList.add(element);
                }
            }
        } else if (iSizeTypeSelected) {
            for (Product element : productArrayList) {
                if (element.getSizeType().equals(sizeType)) {
                    tempList.add(element);
                }
            }
        }

        if (tempList.size() != 0) {
            recyclerView.setVisibility(View.VISIBLE);
            noProductText.setVisibility(View.GONE);

            mAdapter = new ProductsAdapter(tempList, getActivity(), false);
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        } else {
            recyclerView.setVisibility(View.GONE);
            noProductText.setVisibility(View.VISIBLE);
        }


    }

    private void searchFunc() {
        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() == 0) {
                    if (productArrayList.size() != 0) {
                        recyclerView.setVisibility(View.VISIBLE);
                        noProductText.setVisibility(View.GONE);
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        noProductText.setVisibility(View.VISIBLE);
                    }
                    mAdapter = new ProductsAdapter(productArrayList, getActivity(), false);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                } else {
                    ArrayList<Product> clone = new ArrayList<>();
                    for (Product element : productArrayList) {
                        if (element.getName().toLowerCase().contains(s.toString().toLowerCase())) {
                            clone.add(element);
                        }
                    }
                    if (clone.size() != 0) {
                        recyclerView.setVisibility(View.VISIBLE);
                        noProductText.setVisibility(View.GONE);
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        noProductText.setVisibility(View.VISIBLE);
                    }

                    mAdapter = new ProductsAdapter(clone, getActivity(), false);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void settingClickListners() {
        filtersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchFiltersActivity.class));
            }
        });
    }

    public void getDataFromFirebase() {
        progressBar.setVisibility(View.VISIBLE);
        final int[] counter = {0};
        myRootRef.child("Products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        Product product = new Product();
                        product = child.getValue(Product.class);
                        productArrayList.add(product);
                        counter[0]++;
                        if (counter[0] == dataSnapshot.getChildrenCount()) {
                            setData();
                            progressBar.setVisibility(View.GONE);
                        }
                        Log.d("ShowEventInfo:", product.toString());
                    }
                } else {
                    noProductText.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    public static void clearClicked() {
        isCategorySelected = false;
        isBrandSelected = false;
        iSizeTypeSelected = false;
        isFiltersApplied = false;

        mAdapter = new ProductsAdapter(productArrayList, (Activity) context, false);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        nameInput.setText("");
    }

    private void setData() {
        if (productArrayList.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            noProductText.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            noProductText.setVisibility(View.VISIBLE);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFiltersApplied) {
            ApplyFilters();
        }
    }
}
