package com.example.mymusic.mvp.model;

import java.util.List;

public class MusicSourceModel {

    private List<PlayListBean> playList;
    private List<AlbumModel> album;
    private List<HotModel> hot;

    public List<PlayListBean> getPlayList() {
        return playList;
    }

    public void setPlayList(List<PlayListBean> playList) {
        this.playList = playList;
    }

    public List<AlbumModel> getAlbum() {
        return album;
    }

    public void setAlbum(List<AlbumModel> album) {
        this.album = album;
    }

    public List<HotModel> getHot() {
        return hot;
    }

    public void setHot(List<HotModel> hot) {
        this.hot = hot;
    }

    public static class PlayListBean {

        /**
         * playListId : 1
         * name : SHONEN CHRONICLE
         * title : N.Flying第六章迷你专辑《夜好》
         * intro : 凉爽的秋日夜晚，无法入睡的我们，“We like，We love，晚安
         * poster : http://p3.music.126.net/Fs0DjAvcAAyAZa1dgXzFfQ==/109951163571833739.jpg?param=200y200
         * playNum : 436.5万
         * list : [{"musicId":"101","name":"그만 말해","poster":"http://res.lgdsunday.club/poster-1.png","path":"http://music.163.com/song/media/outer/url?id=481853043.mp3","author":"Gummy,Cheetah"},{"musicId":"102","name":"那就这样吧","poster":"http://res.lgdsunday.club/poster-2.png","path":"http://music.163.com/song/media/outer/url?id=548413072.mp3","author":"王超群"},{"musicId":"103","name":"音乐3","poster":"http://res.lgdsunday.club/poster-3.png","path":"http://music.163.com/song/media/outer/url?id=1379354379.mp3","author":"陈露"},{"musicId":"104","name":"音乐4","poster":"http://res.lgdsunday.club/poster-4.png","path":"http://music.163.com/song/media/outer/url?id=1337814185.mp3","author":"陈露"},{"musicId":"105","name":"音乐5","poster":"http://res.lgdsunday.club/poster-5.png","path":"http://music.163.com/song/media/outer/url?id=1338980245.mp3","author":"陈露"},{"musicId":"106","name":"音乐6","poster":"http://res.lgdsunday.club/poster-6.png","path":"http://music.163.com/song/media/outer/url?id=1430488057.mp3","author":"陈露"},{"musicId":"107","name":"音乐7","poster":"http://res.lgdsunday.club/poster-7.png","path":"http://music.163.com/song/media/outer/url?id=1379354379.mp3","author":"假人"},{"musicId":"108","name":"音乐8","poster":"http://res.lgdsunday.club/poster-8.png","path":"http://music.163.com/song/media/outer/url?id=1338211987.mp3","author":"不知名歌手"}]
         */


        private String playListId;
        private String name;
        private String title;
        private String intro;
        private String poster;
        private String playNum;
        private List<ListBean> list;

        public String getPlayListId() {
            return playListId;
        }

        public void setPlayListId(String playListId) {
            this.playListId = playListId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getPlayNum() {
            return playNum;
        }

        public void setPlayNum(String playNum) {
            this.playNum = playNum;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }
        @Override
        public String toString() {
            return "PlayListBean{" +
                    "playListId='" + playListId + '\'' +
                    ", name='" + name + '\'' +
                    ", title='" + title + '\'' +
                    ", intro='" + intro + '\'' +
                    ", poster='" + poster + '\'' +
                    ", playNum='" + playNum + '\'' +
                    ", list=" + list +
                    '}';
        }

        public static class ListBean {
            /**
             * musicId : 101
             * name : 그만 말해
             * poster : http://res.lgdsunday.club/poster-1.png
             * path : http://music.163.com/song/media/outer/url?id=481853043.mp3
             * author : Gummy,Cheetah
             */

            private String musicId;
            private String name;
            private String poster;
            private String path;
            private String author;

            public String getMusicId() {
                return musicId;
            }

            public void setMusicId(String musicId) {
                this.musicId = musicId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPoster() {
                return poster;
            }

            public void setPoster(String poster) {
                this.poster = poster;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }
            @Override
            public String toString() {
                return "ListBean{" +
                        "musicId='" + musicId + '\'' +
                        ", name='" + name + '\'' +
                        ", poster='" + poster + '\'' +
                        ", path='" + path + '\'' +
                        ", author='" + author + '\'' +
                        '}';
            }
        }
    }

    public static class AlbumModel {
        @Override
        public String toString() {
            return "AlbumModel{" +
                    "albumId='" + albumId + '\'' +
                    ", name='" + name + '\'' +
                    ", title='" + title + '\'' +
                    ", intro='" + intro + '\'' +
                    ", poster='" + poster + '\'' +
                    ", playNum='" + playNum + '\'' +
                    ", list=" + list +
                    '}';
        }

        /**
         * albumId : 1
         * name : 伤感话语
         * title : 『伤感华语』关于我们，你遗憾吗
         * intro : 最最害怕隔着屏幕说分手
         连最后抱抱你的机会都没有
         明明一丁点的小事...
         * poster : http://res.lgdsunday.club/poster-9.png
         * playNum : 203.3万
         * list : [{"musicId":"101","name":"그만 말해","poster":"http://res.lgdsunday.club/poster-1.png","path":"http://music.163.com/song/media/outer/url?id=481853043.mp3","author":"Gummy,Cheetah"},{"musicId":"102","name":"那就这样吧","poster":"http://res.lgdsunday.club/poster-2.png","path":"http://music.163.com/song/media/outer/url?id=548413072.mp3","author":"王超群"},{"musicId":"103","name":"音乐3","poster":"http://res.lgdsunday.club/poster-3.png","path":"http://music.163.com/song/media/outer/url?id=1379354379.mp3","author":"陈露"},{"musicId":"104","name":"音乐4","poster":"http://res.lgdsunday.club/poster-4.png","path":"http://music.163.com/song/media/outer/url?id=1337814185.mp3","author":"陈露"},{"musicId":"105","name":"音乐5","poster":"http://res.lgdsunday.club/poster-5.png","path":"http://music.163.com/song/media/outer/url?id=1338980245.mp3","author":"陈露"},{"musicId":"106","name":"音乐6","poster":"http://res.lgdsunday.club/poster-6.png","path":"http://music.163.com/song/media/outer/url?id=1430488057.mp3","author":"陈露"},{"musicId":"107","name":"音乐7","poster":"http://res.lgdsunday.club/poster-7.png","path":"http://music.163.com/song/media/outer/url?id=1379354379.mp3","author":"曹璐韬"},{"musicId":"108","name":"音乐8","poster":"http://res.lgdsunday.club/poster-8.png","path":"http://music.163.com/song/media/outer/url?id=1338211987.mp3","author":"曹璐韬"}]
         */

        private String albumId;
        private String name;
        private String title;
        private String intro;
        private String poster;
        private String playNum;
        private List<ListBeanX> list;

        public String getAlbumId() {
            return albumId;
        }

        public void setAlbumId(String albumId) {
            this.albumId = albumId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getPlayNum() {
            return playNum;
        }

        public void setPlayNum(String playNum) {
            this.playNum = playNum;
        }

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class ListBeanX {
            @Override
            public String toString() {
                return "ListBeanX{" +
                        "musicId='" + musicId + '\'' +
                        ", name='" + name + '\'' +
                        ", poster='" + poster + '\'' +
                        ", path='" + path + '\'' +
                        ", author='" + author + '\'' +
                        '}';
            }

            /**
             * musicId : 101
             * name : 그만 말해
             * poster : http://res.lgdsunday.club/poster-1.png
             * path : http://music.163.com/song/media/outer/url?id=481853043.mp3
             * author : Gummy,Cheetah
             */

            private String musicId;
            private String name;
            private String poster;
            private String path;
            private String author;

            public String getMusicId() {
                return musicId;
            }

            public void setMusicId(String musicId) {
                this.musicId = musicId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPoster() {
                return poster;
            }

            public void setPoster(String poster) {
                this.poster = poster;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }
        }
    }

    public static class HotModel {
        /**
         * musicId : 7
         * name : 音乐1
         * poster : http://res.lgdsunday.club/poster-1.png
         * path : http://music.163.com/song/media/outer/url?id=481853043.mp3
         * author : 曹璐韬
         */

        private String musicId;
        private String name;
        private String poster;
        private String path;
        private String author;

        public String getMusicId() {
            return musicId;
        }

        public void setMusicId(String musicId) {
            this.musicId = musicId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        @Override
        public String toString() {
            return "HotModel{" +
                    "musicId='" + musicId + '\'' +
                    ", name='" + name + '\'' +
                    ", poster='" + poster + '\'' +
                    ", path='" + path + '\'' +
                    ", author='" + author + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MusicSourceModel{" +
                "playList=" + playList +
                ", album=" + album +
                ", hot=" + hot +
                '}';
    }
}
