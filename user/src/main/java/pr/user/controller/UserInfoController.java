package pr.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import pr.common.dto.LoginDTO;
import pr.common.vo.Result;
import pr.user.entity.UserInfo;
import pr.user.service.UserInfoService;

@RestController
@RequestMapping("/pipayshopapi/user-info")
@Tag(name = "用于用户信息相关操作的接口")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @PostMapping("login")
    @Operation(summary = "用户登录")
    public Result login(@RequestBody LoginDTO loginDTO) {
        return userInfoService.login(loginDTO);
    }

    @GetMapping("selectUserInfoByUid/{uid}")
    @Operation(summary = "根据用户ID查询用户信息")
    public Result selectUserInfoByUid(@PathVariable String uid) {
        return Result.success("查询成功");
    }

    @PostMapping("updateUserInfoByUid")
    @Operation(summary = "根据用户ID更改用户基本信息")
    public Result updateUserInfoByUid(@RequestBody UserInfo userInfo) {
        return Result.success(userInfo.getUserName());
    }

    @PostMapping("updateLanguageByUid/{uid}/{language}")
    @Operation(summary = "修改用户语言")
    public Result updateLanguageByUid(@PathVariable String uid, @PathVariable String language) {
        return Result.success(language);
    }

    @PostMapping("updateCountryByUid/{uid}/{country}")
    @Operation(summary = "修改用户国家")
    public Result updateCountryByUid(@PathVariable String uid,@PathVariable String country) {
        return Result.success(country);
    }

//    @PostMapping("uploadUserImage")
//    @Operation("根据用户ID上传头像")
//    public Result uploadUserImage(String userId, MultipartFile file) {
//
//    }

    @GetMapping("getItemInfoByUid")
    @Operation(summary = "根据用户ID获取网店ID和商品数量")
    public Result getItemInfoByUid(String userId) {
        return Result.success(userId);
    }

    @GetMapping("getItemIdByUserId/{userId}")
    @Operation(summary = "根据用户id查询网店id")
    public Result getItemIdByUserId(@PathVariable  String userId) {
        return Result.success(userId);
    }

    @GetMapping("shopBalance/{uid}")
    @Operation(summary = "根据用户Id判断用户能绑定实体店的数量余额")
    public Result shopBalance(@PathVariable String uid) {
        return Result.success(uid);
    }

    @PostMapping("upToVipUser/{userId}")
    @Operation(summary = "升级为商家用户")
    public Result upToVipUser(@PathVariable String userId) {
        return Result.success(userId);
    }

    @GetMapping("isVipUser/{uid}")
    @Operation(summary = "查看一个用户是否是VIP用户")
    public Result isVipUser(@PathVariable String uid) {
        return Result.success(uid);
    }

    @GetMapping("getCountryList")
    @Operation(summary = "获取国家列表数据")
    public Result getCountryList() {
        return Result.success();
    }

//    @PostMapping("insertRegisterData")
//    @Operation(summary = "首次注册pi账号登录后，强制要求其设置一个账号密码实现普通浏览器登录效果")
//    public Result insertRegisterData(UserRegisterDTO userRegisterDTO) {
//        return Result.success();
//    }

//    @PostMapping("userRegister")
//    @Operation(summary = "普通浏览器登录")
//    public Result userRegister(HttpServletRequest request, RegisterDTO registerDTO) {
//        return Result.success();
//    }

//    @GetMapping("/checkCode")
//    @Operation(summary = "生成验证码，并保存值到redis中用于校验")
//    public Result checkCode(HttpServletRequest request, HttpServletResponse response) {
//        return Result.success();
//    }

}
