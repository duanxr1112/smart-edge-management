package com.lenovo.ailab.smartedge.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lenovo.ailab.smartedge.dao.po.TreeType;
import com.lenovo.ailab.smartedge.dao.vo.EdgeServerDetail;

import java.util.List;
import java.util.Map;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/1/8
 * @About:  drop-down list
 */
public class TreeConfig {
    /**
     * @about back tree for system type
     * @param nodes
     * @return
     */
    public static List<TreeType> getTree(List<TreeType> nodes) {
        Map<Integer, TreeType> nodeMap = Maps.newHashMap();
        List<TreeType> rootList = Lists.newArrayList();
        for (TreeType node : nodes) {
            nodeMap.put(node.getId(), node);
            int parentId = node.getParentId();
            if (parentId <= 0) {
                rootList.add(node);
            }
        }
        for (TreeType node : nodes) {
            int parentId = node.getParentId();
            if (parentId <=0) {
                continue;
            }
            TreeType pnode = nodeMap.get(parentId);
            if (pnode == null) {
                continue;
            }
            List<TreeType> children = pnode.getChildren();
            if (children == null) {
                children = Lists.newArrayList();
                pnode.setChildren(children);
            }
            children.add(node);
        }
        return rootList;
    }

    /**
     * @param nodes
     * @return
     */
    public static List<EdgeServerDetail> getTree2(List<EdgeServerDetail> nodes) {
        Map<Integer, EdgeServerDetail> nodeMap = Maps.newHashMap();
        List<EdgeServerDetail> rootList = Lists.newArrayList();
        for (EdgeServerDetail node : nodes) {
            nodeMap.put(node.getId(), node);
            int parentId = node.getParentId();
            if (parentId == 0) {
                rootList.add(node);
            }
        }
        for (EdgeServerDetail node : nodes) {
            int parentId = node.getParentId();
            if (parentId ==0) {
                continue;
            }
            EdgeServerDetail pnode = nodeMap.get(parentId);
            if (pnode == null) {
                continue;
            }
            List<EdgeServerDetail> children = pnode.getChildren();
            if (children == null) {
                children = Lists.newArrayList();
                pnode.setChildren(children);
            }
            children.add(node);
        }
        return rootList;
    }
}
