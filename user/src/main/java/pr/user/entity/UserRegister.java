package pr.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user_register
 */
@TableName(value ="user_register")
@Data
public class UserRegister implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "pi_name")
    private String piName;

    /**
     * 登录密码,tocys插入的用户默认密码是123456
     */
    @TableField(value = "password")
    private String password;

    /**
     * pi的用户id
     */
    @TableField(value = "uid")
    private String uid;

    /**
     * 0:正常1：逻辑删除
     */
    @TableField(value = "del_flag")
    private Integer delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}