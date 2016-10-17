package com.gao.toolbar.recyclerview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gao.android.util.ListUtils;
import com.gao.android.util.ScreenUtils;
import com.gao.android.util.SnackbarUtil;
import com.gao.android.retrofit.RetrofitClient;
import com.gao.toolbar.R;
import com.gao.toolbar.recyclerview.entity.Meizi;
import com.gao.toolbar.recyclerview.http.ApiMeiziService;
import com.gao.toolbar.recyclerview.http.HttpCallBackMeizi;
import com.gao.toolbar.recyclerview.http.HttpResponseMeizi;
import com.orhanobut.logger.Logger;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * Created by GaoMatrix on 2016/10/11.
 */
public class StaggeredLayoutActivity extends AppCompatActivity {
    private static final String TAG = "LinearLayoutActivity";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.coordiatorLayout)
    CoordinatorLayout mCoordiatorLayout;

    /**
     * 当前请求的页的索引
     */
    private int mPage = 1;
    private List<Meizi> mMeiziList;

    // TODO: 2016/10/12 没有实现流逝效果
    private StaggeredGridLayoutManager mLayoutManager = null;
    private ItemTouchHelper mItemTouchHelper;
    private MyAdapter mMyAdapter;
    private int mLastVisibleItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linearlayout);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        initView();
        initListener();
        getData(mPage);

    }

    private void initView() {
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
    }

    private void getData(int page) {
        mSwipeRefreshLayout.setRefreshing(true);
        Call<HttpResponseMeizi<List<Meizi>>> callMeizi = RetrofitClient.getClient(ApiMeiziService.class).getMeizi(page);
        callMeizi.enqueue(new HttpCallBackMeizi<HttpResponseMeizi<List<Meizi>>>() {
            @Override
            public void onSuccess(HttpResponseMeizi<List<Meizi>> listHttpResponseMeizi) {
                Logger.d("onSuccess");
                if (ListUtils.isEmpty(mMeiziList)) {
                    if (listHttpResponseMeizi != null && !ListUtils.isEmpty(listHttpResponseMeizi.getResults())) {
                        Logger.d(listHttpResponseMeizi.getResults());
                        mMeiziList = listHttpResponseMeizi.getResults();
                    }
                } else {
                    if (listHttpResponseMeizi != null && !ListUtils.isEmpty(listHttpResponseMeizi.getResults())) {
                        Logger.d(listHttpResponseMeizi.getResults());
                        mMeiziList.addAll(listHttpResponseMeizi.getResults());
                    }
                }
                if (mMyAdapter == null) {
                    mMyAdapter = new MyAdapter();
                    mRecyclerView.setAdapter(mMyAdapter);
                    mItemTouchHelper.attachToRecyclerView(mRecyclerView);
                } else {
                    mMyAdapter.notifyDataSetChanged();
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(int code, String message) {
                Logger.d(message);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                getData(mPage);
            }
        });

        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags = 0;
                int swipeFlags = 0;
                if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager
                        || recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    //设置侧滑方向为从左到右和从右到左都可以
                    swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                }
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mMeiziList, from, to);
                mMyAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mMyAdapter.removeItem(viewHolder.getAdapterPosition());
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(Color.WHITE);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                viewHolder.itemView.setAlpha(1 - Math.abs(dX) / ScreenUtils.getScreenWidth(StaggeredLayoutActivity.this));
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //0：当前屏幕停止滚动；
                // 1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；
                // 2时：随用户的操作，屏幕上产生的惯性滑动；
                //滑动状态停止并且剩余两个item时自动加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastVisibleItem + 2 >= mLayoutManager.getItemCount()) {
                    mPage++;
                    getData(mPage);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] positions= mLayoutManager.findLastVisibleItemPositions(null);
                mLastVisibleItem = Math.max(positions[0],positions[1]);
            }
        });
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnClickListener {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(StaggeredLayoutActivity.this).inflate(R.layout.item_activity_grid, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            view.setOnClickListener(this);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Glide.with(StaggeredLayoutActivity.this).load(mMeiziList.get(position).getUrl()).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return mMeiziList.size();
        }

        @Override
        public void onClick(View v) {
            int position = mRecyclerView.getChildAdapterPosition(v);
            SnackbarUtil.ShortSnackbar(mCoordiatorLayout, "点击第" + position + "个", SnackbarUtil.Info).show();
        }

        public void addItem(Meizi meizi, int position) {
            mMeiziList.add(position, meizi);
            notifyItemInserted(position);
        }

        public void removeItem(final int position) {
            final Meizi meizi = mMeiziList.get(position);
            mMeiziList.remove(position);
            notifyItemRemoved(position);
            SnackbarUtil.ShortSnackbar(mCoordiatorLayout, "你删除了第" + position + "个item", SnackbarUtil.Warning).setAction("撤销", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addItem(meizi, position);
                    SnackbarUtil.ShortSnackbar(mCoordiatorLayout, "撤销了删除第" + position + "个item", SnackbarUtil.Confirm).show();
                }
            }).setActionTextColor(Color.WHITE).show();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageView;

            public MyViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.imageView);
            }
        }
    }

}
