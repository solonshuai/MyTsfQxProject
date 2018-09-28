package com.zxy.mytsfqxproject.recyclerView;

/**
 * 描述:通用RecyclerView适配器的条目
 *
 * @version 1.0
 */
public interface BaseRecyclerItem {
    /**
     * 条目的视图类型
     *
     * @return
     */
    int getViewType();

    /**
     * 条目类型的Id<br>
     * 一旦使用的条目数据列表有插入的可能，一定要给条目设置一个静态ID，否则会出现数据混乱。
     *
     * @return
     */
    long getItemId();
}
