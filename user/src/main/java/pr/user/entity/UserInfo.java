package pr.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户数据表
 * @TableName user_info
 */
@TableName(value ="user_info")
@Data
public class UserInfo implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * pi的用户id
     */
    @TableField(value = "uid")
    private String uid;

    /**
     * pi的用户名（唯一）
     */
    @TableField(value = "pi_name")
    private String piName;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 登录令牌
     */
    @TableField(value = "access_token")
    private String accessToken;

    /**
     * 上次登录时间
     */
    @TableField(value = "last_login")
    private Date lastLogin;

    /**
     * 个人简介
     */
    @TableField(value = "personal_profile")
    private String personalProfile;

    /**
     * 国家代码
     */
    @TableField(value = "country")
    private String country;

    /**
     * 语言
     */
    @TableField(value = "language")
    private String language;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 联系电话，用作先花未来pi币吧
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 真实姓名，用作先花未来pi币吧
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     * 0:普通用户1:商家用户2:业务员
     */
    @TableField(value = "level")
    private Integer level;

    /**
     * 年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 0:正常使用1:禁用
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 用户头像的图片id
     */
    @TableField(value = "user_image")
    private String userImage;

    /**
     * 剩余可绑定的实体店数量
     */
    @TableField(value = "shop_balance")
    private Integer shopBalance;

    /**
     * 0：女1：男
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 用户ip地址
     */
    @TableField(value = "user_ip")
    private String userIp;

    /**
     * 0:不是pi商 1:是pi商
     */
    @TableField(value = "pi_shoper")
    private Integer piShoper;

    /**
     * 0:不是供应商 1:是供应商商
     */
    @TableField(value = "supplier")
    private Integer supplier;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}