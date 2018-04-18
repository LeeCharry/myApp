package com.example.jack.myapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lcy on 2018/4/18.
 */

    public  class Artical implements Serializable{
        /**
         * curPage : 1
         * datas : [{"apkLink":"","author":"小鄧子","chapterId":227,"chapterName":"注解","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2820,"link":"https://www.jianshu.com/p/28751130c038","niceDate":"1天前","origin":"","projectLink":"","publishTime":1523887464000,"superChapterId":227,"superChapterName":"注解 & 反射 & AOP","tags":[],"title":"拦截控件点击 - 巧用ASM处理防抖","type":0,"visible":1,"zan":0},{"apkLink":"","author":"CHEN川","chapterId":346,"chapterName":"JVM","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2819,"link":"https://www.jianshu.com/p/f8d71e1e8821","niceDate":"1天前","origin":"","projectLink":"","publishTime":1523887411000,"superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"详解JVM内存管理与垃圾回收机制 (上)","type":0,"visible":1,"zan":0},{"apkLink":"","author":"张明云","chapterId":296,"chapterName":"阅读","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2818,"link":"https://www.jianshu.com/p/23c70f6df816","niceDate":"1天前","origin":"","projectLink":"","publishTime":1523887364000,"superChapterId":181,"superChapterName":"延伸技术","tags":[],"title":"关于技术团队导师带新人的一些经验分享","type":0,"visible":1,"zan":0},{"apkLink":"","author":"红橙Darren","chapterId":249,"chapterName":"干货资源","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2817,"link":"https://www.jianshu.com/p/c0ec2a7fc26a","niceDate":"1天前","origin":"","projectLink":"","publishTime":1523887339000,"superChapterId":249,"superChapterName":"干货资源","tags":[],"title":"Android进阶之旅与你同行","type":0,"visible":1,"zan":0},{"apkLink":"","author":"依然范特稀西 ","chapterId":335,"chapterName":"应用内更新","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2816,"link":"https://www.jianshu.com/p/85913ed97af5","niceDate":"1天前","origin":"","projectLink":"","publishTime":1523887288000,"superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"Android app 在线更新那点事儿（适配Android6.0、7.0、8.0）","type":0,"visible":1,"zan":0},{"apkLink":"","author":"JokAr","chapterId":345,"chapterName":"国际化","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2813,"link":"https://juejin.im/post/5ac8d62c518825557e78a514","niceDate":"2018-04-13","origin":"","projectLink":"","publishTime":1523588234000,"superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"Android国际化(多语言)实现，支持8.0","type":0,"visible":1,"zan":0},{"apkLink":"","author":"LawCoder","chapterId":134,"chapterName":"SurfaceView","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2812,"link":"https://blog.csdn.net/sdfsdfdfa/article/details/79862917","niceDate":"2018-04-13","origin":"","projectLink":"","publishTime":1523588009000,"superChapterId":134,"superChapterName":"自定义控件","tags":[],"title":"SurfaceView实战打造农药钻石夺宝","type":0,"visible":1,"zan":0},{"apkLink":"","author":"奇卓社","chapterId":269,"chapterName":"官方发布","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2811,"link":"http://mp.weixin.qq.com/s/4k3DBlxlSO2xNNKqjqUdaQ","niceDate":"2018-04-12","origin":"","projectLink":"","publishTime":1523533421000,"superChapterId":60,"superChapterName":"开发环境","tags":[],"title":"突破Android P(Preview 1)对调用隐藏API限制的方法","type":0,"visible":1,"zan":0},{"apkLink":"","author":"承香墨影","chapterId":67,"chapterName":"网络基础","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2810,"link":"https://www.jianshu.com/p/5008b707bd04","niceDate":"2018-04-12","origin":"","projectLink":"","publishTime":1523532290000,"superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"一大波 Android 刘海屏来袭，全网最全适配技巧！","type":0,"visible":1,"zan":0},{"apkLink":"","author":"叶应是叶","chapterId":67,"chapterName":"网络基础","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2809,"link":"https://www.jianshu.com/p/6d2f324c8f42","niceDate":"2018-04-12","origin":"","projectLink":"","publishTime":1523532264000,"superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"在 Android 设备上搭建 Web 服务器","type":0,"visible":1,"zan":0},{"apkLink":"","author":"水木飞雪","chapterId":67,"chapterName":"网络基础","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2808,"link":"https://www.jianshu.com/p/acbc7df5decd","niceDate":"2018-04-12","origin":"","projectLink":"","publishTime":1523532220000,"superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"Android Protobuf应用及原理","type":0,"visible":1,"zan":0},{"apkLink":"","author":"筑梦师Winston","chapterId":296,"chapterName":"阅读","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2807,"link":"https://www.jianshu.com/p/9c112722fa77","niceDate":"2018-04-12","origin":"","projectLink":"","publishTime":1523532189000,"superChapterId":181,"superChapterName":"延伸技术","tags":[],"title":"全栈开发自学路线","type":0,"visible":1,"zan":0},{"apkLink":"","author":"meituan","chapterId":98,"chapterName":"WebView","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2806,"link":"https://tech.meituan.com/WebViewPerf.html","niceDate":"2018-04-12","origin":"","projectLink":"","publishTime":1523515464000,"superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"WebView性能、体验分析与优化","type":0,"visible":1,"zan":0},{"apkLink":"","author":"那时青菜","chapterId":171,"chapterName":"binder","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2805,"link":"https://www.jianshu.com/p/63ad02ee266d","niceDate":"2018-04-11","origin":"","projectLink":"","publishTime":1523443955000,"superChapterId":178,"superChapterName":"framework","tags":[],"title":"Android 进程间的通信之AIDL","type":0,"visible":1,"zan":0},{"apkLink":"","author":"红脸书生","chapterId":321,"chapterName":"算法","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2804,"link":"http://www.cnblogs.com/steven_oyj/category/246990.html","niceDate":"2018-04-11","origin":"","projectLink":"","publishTime":1523431053000,"superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"五大常用算法","type":0,"visible":1,"zan":0},{"apkLink":"","author":"帥酥","chapterId":260,"chapterName":"RxJava & Retrofit & MVP","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2803,"link":"https://blog.csdn.net/s003603u/article/details/56670819","niceDate":"2018-04-10","origin":"","projectLink":"","publishTime":1523351642000,"superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"基于Activity、Fragment的生命周期避免MVP模式内存泄露的问题","type":0,"visible":1,"zan":0},{"apkLink":"","author":"RDuwan ","chapterId":26,"chapterName":"基础UI控件","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2802,"link":"https://www.jianshu.com/p/9f7f9213bff8","niceDate":"2018-04-10","origin":"","projectLink":"","publishTime":1523338539000,"superChapterId":26,"superChapterName":"常用控件","tags":[],"title":"TextView性能瓶颈，渲染优化，以及StaticLayout的一些用处","type":0,"visible":1,"zan":0},{"apkLink":"","author":"listenzz","chapterId":344,"chapterName":"Fragment","collect":false,"courseId":13,"desc":"方便管理Fragment、StatusBar 、Toolbar的库 AndroidNavigation","envelopePic":"http://www.wanandroid.com/blogimgs/01b8f237-4a80-4841-972c-de9a34d557c4.png","fresh":false,"id":2801,"link":"http://www.wanandroid.com/blog/show/2107","niceDate":"2018-04-09","origin":"","projectLink":"https://github.com/listenzz/AndroidNavigation","publishTime":1523276644000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=344"}],"title":"方便管理Fragment、StatusBar 、Toolbar的库 AndroidNavigation","type":0,"visible":1,"zan":0},{"apkLink":"","author":"GcsSloop","chapterId":314,"chapterName":"RV列表动效","collect":false,"courseId":13,"desc":"具有分页功能的 Recyclerview 布局管理器，主打分页，可以替代部分场景下的网格布局，线性布局，以及一些简单的ViewPager，但也有一定的局限性，请选择性使用。\r\n\r\n","envelopePic":"http://www.wanandroid.com/blogimgs/d65890f3-0a09-4b9c-93b0-778caa31b2aa.gif","fresh":false,"id":2800,"link":"http://www.wanandroid.com/blog/show/2106","niceDate":"2018-04-09","origin":"","projectLink":"https://github.com/GcsSloop/pager-layoutmanager","publishTime":1523276375000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=314"}],"title":"Android 网格分页布局 pager-layoutmanager","type":0,"visible":1,"zan":0},{"apkLink":"","author":"scsfwgy","chapterId":339,"chapterName":"K线图","collect":false,"courseId":13,"desc":"各种金融类的自定义View,基金走势图、分时图、蜡烛图、各种指标等，一步一步构建庞大的基金自定View... http://blog.csdn.net/wgyscsf\r\n","envelopePic":"http://www.wanandroid.com/blogimgs/3a9d2cbb-24d7-4c85-8497-9e1af6b64d23.png","fresh":false,"id":2799,"link":"http://www.wanandroid.com/blog/show/2105","niceDate":"2018-04-09","origin":"","projectLink":"https://github.com/scsfwgy/FinancialCustomerView","publishTime":1523265010000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=339"}],"title":"走势图、分时图、蜡烛图 FinancialCustomerView","type":0,"visible":1,"zan":0}]
         * offset : 0
         * over : false
         * pageCount : 61
         * size : 20
         * total : 1213
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean implements Serializable{
            /**
             * apkLink :
             * author : 小鄧子
             * chapterId : 227
             * chapterName : 注解
             * collect : false
             * courseId : 13
             * desc :
             * envelopePic :
             * fresh : false
             * id : 2820
             * link : https://www.jianshu.com/p/28751130c038
             * niceDate : 1天前
             * origin :
             * projectLink :
             * publishTime : 1523887464000
             * superChapterId : 227
             * superChapterName : 注解 & 反射 & AOP
             * tags : []
             * title : 拦截控件点击 - 巧用ASM处理防抖
             * type : 0
             * visible : 1
             * zan : 0
             */

            private String apkLink;
            private String author;
            private int chapterId;
            private String chapterName;
            private boolean collect;
            private int courseId;
            private String desc;
            private String envelopePic;
            private boolean fresh;
            private int id;
            private String link;
            private String niceDate;
            private String origin;
            private String projectLink;
            private long publishTime;
            private int superChapterId;
            private String superChapterName;
            private String title;
            private int type;
            private int visible;
            private int zan;
            private List<?> tags;

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public boolean isFresh() {
                return fresh;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public int getSuperChapterId() {
                return superChapterId;
            }

            public void setSuperChapterId(int superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

            public List<?> getTags() {
                return tags;
            }

            public void setTags(List<?> tags) {
                this.tags = tags;
            }
        }
    }
