package com.richie.expandable.adapter;

/**
 * @author Richie on 2017.07.31
 *         分组展开监听器
 */
public interface OnGroupExpandedListener {
    /**
     * 分组展开
     *
     * @param groupPosition 分组的位置
     */
    void onGroupExpanded(int groupPosition);
}
