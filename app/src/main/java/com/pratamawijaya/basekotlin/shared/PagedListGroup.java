package com.pratamawijaya.basekotlin.shared;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.paging.AsyncPagedListDiffer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;

import com.xwray.groupie.Group;
import com.xwray.groupie.GroupDataObserver;
import com.xwray.groupie.Item;

import java.util.List;

public class PagedListGroup<T extends Item> implements Group, GroupDataObserver {

    private GroupDataObserver parentObserver;

    private final ListUpdateCallback listUpdateCallback = new ListUpdateCallback() {
        @Override
        public void onInserted(int position, int count) {
            parentObserver.onItemRangeInserted(PagedListGroup.this, position, count);
        }

        @Override
        public void onRemoved(int position, int count) {
            parentObserver.onItemRangeRemoved(PagedListGroup.this, position, count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            parentObserver.onItemMoved(PagedListGroup.this, fromPosition, toPosition);
        }

        @Override
        public void onChanged(int position, int count, Object payload) {
            parentObserver.onItemRangeChanged(PagedListGroup.this, position, count);
        }
    };

    @SuppressWarnings("unchecked")
    private final AsyncPagedListDiffer<T> differ = new AsyncPagedListDiffer<T>(
            listUpdateCallback,
            new AsyncDifferConfig.Builder(new DiffUtil.ItemCallback<T>() {
                @Override
                public boolean areItemsTheSame(T oldItem, T newItem) {
                    return newItem.isSameAs(oldItem);
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(T oldItem, T newItem) {
                    return newItem.equals(oldItem);
                }
            }).build()
    );

    private Item placeHolder = null;

    public void setPlaceHolder(Item placeHolder) {
        this.placeHolder = placeHolder;
    }

    public void submitList(PagedList<T> newPagedList) {
        differ.submitList(newPagedList);
    }

    @Override
    public int getItemCount() {
        return differ.getItemCount();
    }

    @NonNull
    @Override
    public Item getItem(int position) {
        Item item = differ.getItem(position);
        if (item != null) {
            // TODO find more efficiency registration timing, and removing observer
            item.registerGroupDataObserver(this);
            return item;
        }
        return placeHolder;
    }

    @Override
    public int getPosition(@NonNull Item item) {
        List<T> currentList = differ.getCurrentList();
        if (currentList == null) {
            return -1;
        }
        //noinspection SuspiciousMethodCalls
        return currentList.indexOf(item);
    }

    @Override
    public void registerGroupDataObserver(@NonNull GroupDataObserver groupDataObserver) {
        parentObserver = groupDataObserver;
    }

    @Override
    public void unregisterGroupDataObserver(@NonNull GroupDataObserver groupDataObserver) {
        parentObserver = null;
    }

    @Override
    public void onChanged(@NonNull Group group) {
        parentObserver.onChanged(this);
    }

    @Override
    public void onItemInserted(@NonNull Group group, int position) {
        int index = getItemPosition(group);
        if (index >= 0 && parentObserver != null) {
            parentObserver.onItemInserted(this, index);
        }
    }

    @Override
    public void onItemChanged(@NonNull Group group, int position) {
        int index = getItemPosition(group);
        if (index >= 0 && parentObserver != null) {
            parentObserver.onItemChanged(this, index);
        }
    }

    @Override
    public void onItemChanged(@NonNull Group group, int position, Object payload) {
        int index = getItemPosition(group);
        if (index >= 0 && parentObserver != null) {
            parentObserver.onItemChanged(this, index, payload);
        }
    }

    @Override
    public void onItemRemoved(@NonNull Group group, int position) {
        int index = getItemPosition(group);
        if (index >= 0 && parentObserver != null) {
            parentObserver.onItemRemoved(this, index);
        }
    }

    @Override
    public void onItemRangeChanged(@NonNull Group group, int positionStart, int itemCount) {
        int index = getItemPosition(group);
        if (index >= 0 && parentObserver != null) {
            parentObserver.onItemRangeChanged(this, index, itemCount);
        }
    }

    @Override
    public void onItemRangeChanged(@NonNull Group group, int positionStart, int itemCount, Object payload) {
        int index = getItemPosition(group);
        if (index >= 0 && parentObserver != null) {
            parentObserver.onItemRangeChanged(this, index, itemCount, payload);
        }
    }

    @Override
    public void onItemRangeInserted(@NonNull Group group, int positionStart, int itemCount) {
        int index = getItemPosition(group);
        if (index >= 0 && parentObserver != null) {
            parentObserver.onItemRangeInserted(this, index, itemCount);
        }
    }

    @Override
    public void onItemRangeRemoved(@NonNull Group group, int positionStart, int itemCount) {
        int index = getItemPosition(group);
        if (index >= 0 && parentObserver != null) {
            parentObserver.onItemRangeRemoved(this, index, itemCount);
        }
    }

    @Override
    public void onItemMoved(@NonNull Group group, int fromPosition, int toPosition) {
        int index = getItemPosition(group);
        if (index >= 0 && parentObserver != null) {
            parentObserver.onItemRangeChanged(this, index, toPosition);
        }
    }

    private int getItemPosition(@NonNull Group group) {
        List<T> currentList = differ.getCurrentList();
        if (currentList == null) {
            return -1;
        }
        //noinspection SuspiciousMethodCalls
        return currentList.indexOf(group);
    }
}