package com.UI;

import com.dao.UserInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Porgram {
    public static void main(String[] args) {
        try {
            // 读取配置文件 mybatis-config.xml
            InputStream config = Resources.getResourceAsStream("mybatis-config.xml");
            // 根据配置文件构建SqlSessionFactory
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder()
                    .build(config);
            // 通过 SqlSessionFactory 创建 SqlSession
            SqlSession ss = ssf.openSession();
            // SqlSession执行映射文件中定义的SQL，并返回映射结果
            /*
             * com.mybatis.mapper.UserMapper.selectUserById 为 UserMapper.xml
             * 中的命名空间+select 的 id
             */
            // 查询一个用户
//            UserInfo mu = ss.selectOne(
//                    "com.mapper.UserInfoMapper.selectUser", 1);
//            System.out.println(mu);
            // 添加一个用户
            UserInfo addmu = new UserInfo();
            addmu.setName("test");
            addmu.setSex("男");
            addmu.setBirthday(new Date());
            addmu.setAddress("杭州");
            ss.insert("com.mapper.UserInfoMapper.insertUser", addmu);

//            // 修改一个用户
//            UserInfo updatemu = new UserInfo();
//            updatemu.setId(1);
//            updatemu.setName("张三");
//            updatemu.setSex("女");
//            ss.update("com.mapper.UserInfoMapper.updateUser", updatemu);
//            // 删除一个用户
//            ss.delete("com.mapper.UserInfoMapper.deleteUser", 3);
            // 查询所有用户
            List<UserInfo> listMu = ss
                    .selectList("com.mapper.UserInfoMapper.selectAllUser");
            for (UserInfo myUser : listMu) {
                System.out.println(myUser);
            }
            // 提交事务
            ss.commit();
            // 关闭 SqlSession
            ss.close();
        } catch (
                IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
