package cn.xawl.manage.service;

import cn.xawl.manage.dao.UserMapper;
import cn.xawl.manage.pojo.User;
import cn.xawl.manage.utils.EasyUIResult;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public EasyUIResult queryUserList(Integer page, Integer rows) {

        PageHelper.startPage(page, rows);

        Example example = new Example(User.class);
        example.setOrderByClause("updated DESC"); // 设置排序条件
        List<User> users = this.userMapper.selectByExample(example);

        PageInfo<User> pageInfo = new PageInfo<User>(users);

        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
    }

    public User queryUserById(Long id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    public Boolean saveUser(User user) {
        return this.userMapper.insert(user) == 1;
    }

    public Boolean updateUser(User user) {
        return this.userMapper.updateByPrimaryKeySelective(user) == 1;
    }

    public Boolean deleteUser(Long id) {
        return this.userMapper.deleteByPrimaryKey(id) == 1;
    }

}
