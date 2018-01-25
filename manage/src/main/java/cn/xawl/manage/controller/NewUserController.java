package cn.xawl.manage.controller;

import cn.xawl.manage.pojo.User;
import cn.xawl.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequestMapping("new/user")
@Controller
public class NewUserController {

    @Autowired
    private UserService userService;

    // 根据用户id查询用户信息
    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<User> queryUserById(@PathVariable("id") Long id) {
        try {
            User user = this.userService.queryUserById(id);
            if (null == user) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveUser(User user) {
        try {
            Boolean bool = this.userService.saveUser(user);
            if (bool) {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateUser(User user) {
        try {
            Boolean bool = this.userService.updateUser(user);
            if (bool) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestParam(value = "id", defaultValue = "0") Long id) {
        try {
            if (id.longValue() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            Boolean bool = this.userService.deleteUser(id);
            if (bool) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
