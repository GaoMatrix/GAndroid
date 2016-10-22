package com.gao.android.rxjavaretrofit.model;

/**
 * Created by GaoMatrix on 2016/10/21.
 */
public class ZhuangbiImage {

    /**
     * id : 2511
     * description : 110吗？
     * path :
     * size : 0
     * width : 53
     * height : 72
     * created_at : 2016-08-09 14:02:47
     * updated_at : 2016-08-09 17:36:30
     * user_id : null
     * permitted_at : 2016-08-09 17:36:30
     * disk :
     * hotpoint : 138
     * channel : website
     * upload_id : 5976
     * image_url : http://zhuangbi.idagou.com/i/2016-08-09-4a2945be4bef9abcff147684310b11e5.jpg
     * file_size : 2.02 KB
     * upload : {"id":5976,"name":null,"description":"110吗？","disk":"qiniu","path":"i/2016-08-09-4a2945be4bef9abcff147684310b11e5.jpg","size":2071,"user_id":null,"created_at":"2016-08-09 14:02:47","updated_at":"2016-08-09 17:36:29","uploadable_id":null,"uploadable_type":null,"url":"http://zhuangbi.idagou.com/i/2016-08-09-4a2945be4bef9abcff147684310b11e5.jpg"}
     */

    private int id;
    private String description;
    private String path;
    private int size;
    private int width;
    private int height;
    private String created_at;
    private String updated_at;
    private Object user_id;
    private String permitted_at;
    private String disk;
    private int hotpoint;
    private String channel;
    private int upload_id;
    private String image_url;
    private String file_size;
    /**
     * id : 5976
     * name : null
     * description : 110吗？
     * disk : qiniu
     * path : i/2016-08-09-4a2945be4bef9abcff147684310b11e5.jpg
     * size : 2071
     * user_id : null
     * created_at : 2016-08-09 14:02:47
     * updated_at : 2016-08-09 17:36:29
     * uploadable_id : null
     * uploadable_type : null
     * url : http://zhuangbi.idagou.com/i/2016-08-09-4a2945be4bef9abcff147684310b11e5.jpg
     */

    private UploadBean upload;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Object getUser_id() {
        return user_id;
    }

    public void setUser_id(Object user_id) {
        this.user_id = user_id;
    }

    public String getPermitted_at() {
        return permitted_at;
    }

    public void setPermitted_at(String permitted_at) {
        this.permitted_at = permitted_at;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public int getHotpoint() {
        return hotpoint;
    }

    public void setHotpoint(int hotpoint) {
        this.hotpoint = hotpoint;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getUpload_id() {
        return upload_id;
    }

    public void setUpload_id(int upload_id) {
        this.upload_id = upload_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getFile_size() {
        return file_size;
    }

    public void setFile_size(String file_size) {
        this.file_size = file_size;
    }

    public UploadBean getUpload() {
        return upload;
    }

    public void setUpload(UploadBean upload) {
        this.upload = upload;
    }

    public static class UploadBean {
        private int id;
        private Object name;
        private String description;
        private String disk;
        private String path;
        private int size;
        private Object user_id;
        private String created_at;
        private String updated_at;
        private Object uploadable_id;
        private Object uploadable_type;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDisk() {
            return disk;
        }

        public void setDisk(String disk) {
            this.disk = disk;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public Object getUser_id() {
            return user_id;
        }

        public void setUser_id(Object user_id) {
            this.user_id = user_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public Object getUploadable_id() {
            return uploadable_id;
        }

        public void setUploadable_id(Object uploadable_id) {
            this.uploadable_id = uploadable_id;
        }

        public Object getUploadable_type() {
            return uploadable_type;
        }

        public void setUploadable_type(Object uploadable_type) {
            this.uploadable_type = uploadable_type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
