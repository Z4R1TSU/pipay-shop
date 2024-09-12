package pr.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pr.user.entity.UserRegister;
import pr.user.service.UserRegisterService;
import pr.user.mapper.UserRegisterMapper;
import org.springframework.stereotype.Service;

/**
* @author 30786
* @description 针对表【user_register】的数据库操作Service实现
* @createDate 2024-09-12 15:54:25
*/
@Service
public class UserRegisterServiceImpl extends ServiceImpl<UserRegisterMapper, UserRegister>
    implements UserRegisterService{

}




