/*ackage com.lenovo.tripnote.webchat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lenovo.tripnote.webchat.entity.User;
import com.lenovo.tripnote.webchat.mapper.UserMapper;
import com.lenovo.tripnote.webchat.service.UserService;

*//**
 * Created by Administrator on 2017/8/16.
 *//*
@Service
public class UserServiceImpl implements UserService<User> {

    @Autowired
    private UserMapper userMapper;//这里会报错，但是并不会影响

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int addUser(User user) {

        return userMapper.insertSelective(user);
    }

    
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * 
    @Override
    public Page<User> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        Page<User>  page1 = PageHelper.startPage(pageNum, pageSize);
        
        userMapper.selectAllUser();

        return page1;
    }
}

*/