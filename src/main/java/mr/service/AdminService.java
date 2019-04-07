package mr.service;

import mr.exception.NotFoundException;
import mr.mapper.AdminMapper;
import mr.vo.AdminForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LiChuan on 2019/3/25.
 */
@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public void findPswdByName(AdminForm admin) throws Exception{
        String pswd = adminMapper.findPswdByName(admin.getName());
        if(pswd == null ){
            throw new NotFoundException("用户名不存在");
        }
        if(!pswd.equals(admin .getPassword())){
            throw new Exception("密码错误");
        }
    }
}
