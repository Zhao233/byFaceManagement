//package serviceImp;
//
//import com.example.demo.service.UserService;
//import com.example.mapper.UserMapper;
//import com.example.model.User;
//import com.github.pagehelper.PageHelper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service(value = "userService")
//public class UserServiceImp implements UserService {
//    @Autowired
//    private UserMapper userMapper;
//
//    @Override
//    public int addUser(User user){
//        return userMapper.insertSelective(user);
//    }
//
//    @Override
//    public List<User> findAllUser(int pageNum, int pageSize) {
//        //将参数传给这个方法就可以实现物理分页了，非常简单。
//        PageHelper.startPage(pageNum, pageSize);
//
//        return userMapper.selectAllUser();
//    }
//}