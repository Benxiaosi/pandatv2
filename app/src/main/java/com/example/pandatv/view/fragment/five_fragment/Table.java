package com.example.pandatv.view.fragment.five_fragment;

import java.util.List;

/**
 * Created by 本丶小丝 on 2018/3/13.
 */

public class Table {
    private List<AlllistBean> alllist;
    private List<TablistBean> tablist;

    public List<AlllistBean> getAlllist() {
        return alllist;
    }

    public void setAlllist(List<AlllistBean> alllist) {
        this.alllist = alllist;
    }

    public List<TablistBean> getTablist() {
        return tablist;
    }

    public void setTablist(List<TablistBean> tablist) {
        this.tablist = tablist;
    }

    public static class AlllistBean {
        /**
         * order : 1
         * title : 凤凰古城
         * type :
         * url : http://www.ipanda.com/kehuduan/liebiao/fenghuanggucheng/index.json
         */

        private String order;
        private String title;
        private String type;
        private String url;

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class TablistBean {
        /**
         * order : 1
         * title : 精编直播
         * type :
         * url : http://www.ipanda.com/kehuduan/liebiao/shanhaiguan/index.json
         */

        private String order;
        private String title;
        private String type;
        private String url;

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
