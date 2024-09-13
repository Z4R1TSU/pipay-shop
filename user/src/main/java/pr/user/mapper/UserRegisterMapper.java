package pr.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import pr.user.entity.UserRegister;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 30786
* @description 针对表【user_register】的数据库操作Mapper
* @createDate 2024-09-12 15:54:25
* @Entity pr.user.entity.UserRegister
*/
@Mapper
public interface UserRegisterMapper extends BaseMapper<UserRegister> {

}




