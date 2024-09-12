package pr.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pr.user.entity.UserInfo;
import pr.user.service.UserInfoService;
import pr.user.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 30786
* @description 针对表【user_info(用户数据表)】的数据库操作Service实现
* @createDate 2024-09-12 15:53:48
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

}




