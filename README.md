## MyBatis 

### 数据库配置文件 `mybatis-config.xml`

> 数据库环境配置
```xml
<environments default="mysql">
        <!--配置mysql的环境-->
        <environment id="mysql">
            <!--配置事务的类型-->
            <transactionManager type="JDBC"></transactionManager>
            <!--配置连接池-->
            <dataSource type="POOLED">
                <!--配置连接数据库的4个基本信息-->
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/testdb?useUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=UTC"/>
                <property name="username" value="root"/>
                <property name="password" value="sa123"/>
            </dataSource>
        </environment>
    </environments>
```
> 映射文件配置
```xml
<!--指定映射配置文件的位置，映射配置文件指的是每个dao独立的配置文件-->
<mappers>
   <mapper resource="mapper/UserInfoMapper.xml"/>
</mappers>
```

### 数据库实体映射对象 `javabean`
 实体对象的成员字段需要与 数据库字段名称保持一致，否则需要通过 实体字段与数据库字段映射配置实现 映射
```java
public class UserInfo implements Serializable
{
    private int Id; 
    private String name;
    private String sex;
    private Date birthday;
    private String address;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString()
    {
        return "User [id=" + Id + ", username=" + name + ", sex=" + sex
                + ", birthday=" + birthday + ", address=" + address + "]";
    }
}
```

### xml映射文件 `mapper`
mapper xml 文件是用来配置sql指令的文件，每条指令都有对应的id、参数类型、返回类型，参数类型可以是`parameterType`也可以是`parameterMap`也可以是其它类型,
返回类型亦可以是`resultType` 也可以是 其它类型，#{param}是参数对应的值，外部通过传值的形式调用对应的sql，然后去执行即可。


```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.mapper.UserInfoMapper">
    <select id="selectAllUser"  resultType="com.dao.UserInfo">
        SELECT Id, name, sex, birthday, address FROM testdb.userinfo;
    </select>

    <select id="selectUser" parameterType="int" resultType="com.dao.UserInfo">
        SELECT Id, name, sex, birthday, address FROM testdb.userinfo WHERE Id =#{Id};
    </select>

    <insert id="insertUser" parameterType="com.dao.UserInfo" >
        INSERT INTO testdb.userinfo(name, sex, birthday, address) VALUES(#{name}, #{sex}, #{birthday}, #{address});
    </insert>

    <update id="updateUser" parameterType="com.dao.UserInfo">
        UPDATE testdb.userinfo SET name=#{name}, sex=#{sex}, birthday=#{birthday}, address= #{address} WHERE Id=#{Id};
    </update>
    
    <delete id="deleteUser" parameterType="int">
        DELETE FROM testdb.userinfo WHERE Id=#{Id};
    </delete>
</mapper>
```


### 实现mybatis demo

```java
 // 读取配置文件 mybatis-config.xml
 InputStream config = Resources.getResourceAsStream("mybatis-config.xml");
 // 根据配置文件构建SqlSessionFactory
 SqlSessionFactory ssf = new SqlSessionFactoryBuilder()
         .build(config);
 // 通过 SqlSessionFactory 创建 SqlSession
 SqlSession ss = ssf.openSession();

 // 添加一个用户
 UserInfo addmu = new UserInfo();
 addmu.setName("test");
 addmu.setSex("男");
 addmu.setBirthday(new Date());
 addmu.setAddress("杭州");
 ss.insert("com.mapper.UserInfoMapper.insertUser", addmu);

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

```





