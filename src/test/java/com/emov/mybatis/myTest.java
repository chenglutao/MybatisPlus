package com.emov.mybatis;

import java.lang.reflect.Array;
import java.util.*;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisPlus.entity.User;
import com.mybatisPlus.mapper.UserMapper;
import com.mybatisPlus.service.IUserService;
import com.mybatisPlus.service.impl.UserServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class myTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService userService;

    /**
     * 插入
     */
    @Test
    public void inster() {
        User user = new User();
        user.setName("成路涛3");
        user.setPhone("18813024889");
        userMapper.insert(user);
    }

    /**
     * 修改
     */
    @Test
    public void update() {
        User user = new User();
        user.setId(14);
        user.setName("成路涛");
        user.setPhone("18813024881");
        user.setAge(26);
        user.setCreateTime(new Date());
        userMapper.updateById(user);
    }

    /**
     * 修改
     */
    @Test
    public void updateWrapper() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name","路");
        User user = new User();
        user.setPhone("18813024881");
        user.setAge(26);
        userMapper.update(user,updateWrapper);
    }

    /**
     * 修改
     */
    @Test
    public void updateWrapper1() {
        User whereUser = new User();
        //whereUser.setName("路");//此条件与updateWrapper.eq("name","路");二者选其一即可
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name","路");
        User user = new User();
        user.setPhone("18813024881");
        user.setAge(26);
        userMapper.update(user,updateWrapper);
    }

    /**
     * 修改
     */
    @Test
    public void updateWrapper2() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name","路").set("phone","1234S");
        userMapper.update(null,updateWrapper);
    }

    /**
     * 查询 id:1
     *     listId:123
     *     All
     */
    @Test
    public void select(){
        User user = userMapper.selectById(1);
        List<User> lists = userMapper.selectBatchIds(Arrays.asList(12,13,14));
        List<User> list = userMapper.selectList(null);
        System.out.println(user);
        System.out.println(lists);
        System.out.println(list);
    }

    /**
     * map查询
     *      name:"张路"
     */
    @Test
    public void selectMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("name","张路");
        List<User> users = userMapper.selectByMap(map);
        System.out.println(users);
    }

    /**
     * 查询
     *    姓张年龄小于30
     */
    @Test
    public void selectByWrapper(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.like("name","张").lt("age",30);
        List<User> users = userMapper.selectList(query);
        System.out.println(users);
    }

    /**
     * 查询
     *    名字为路，年龄在21-30之间，手机号码不为空
     */
    @Test
    public void selectByWrapper2(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.like("name","路").between("age", 21,30).isNotNull("phone");
        List<User> users = userMapper.selectList(query);
        System.out.println(users);
    }
    /**
     * 查询
     *    名字为路或者年龄等于30，先按照年龄排序再按id排序
     */
    @Test
    public void selectByWrapper3(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.likeLeft("name","路").or().ge("age", 30).orderByDesc("age").orderByAsc("id");
        List<User> users = userMapper.selectList(query);
        System.out.println(users);
    }

    /**
     * 查询
     *   创建时间为2019-11-14的用户
     */
    @Test
    public void selectByWrapper4(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.apply("date_format(create_time,'%Y-%m-%d') = {0}", "2019-11-14");
        List<User> users = userMapper.selectList(query);
        System.out.println(users);
    }

    /**
     * 查询
     *   名字中有为路并且姓名为成路涛，年龄不为空的用户
     */
    @Test
    public void selectByWrapper5(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.like("name","路").and(wq -> wq.eq("name","成路涛").isNotNull("age"));
        List<User> users = userMapper.selectList(query);
        System.out.println(users);
    }

    /**
     * 查询
     *   年龄为30或者手机号码不为空名字有路的用户
     */
    @Test
    public void selectByWrapper6(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.nested(wq->wq.lt("age","30").or().isNull("phone")).like("name","路");
        List<User> users = userMapper.selectList(query);
        System.out.println(users);
    }

    /**
     * 查询
     *   年龄为25,20,30,22的用户
     */
    @Test
    public void selectByWrapper7(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.in("age",Arrays.asList(25,20,30,22));
        List<User> users = userMapper.selectList(query);
        System.out.println(users);
    }

    /**
     * 查询
     *   年龄为25,20,30,22的用户取1条数据
     */
    @Test
    public void selectByWrapper8(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.in("age",Arrays.asList(25,20,30,22)).last("limit 1");//慎用last有sql注入风险
        List<User> users = userMapper.selectList(query);
        System.out.println(users);
    }

    /**
     * 查询
     *   查询名字有张年龄小于30，数据只显示id和name
     */
    @Test
    public void selectByWrapperSupper(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.select("id","name").like("name","张").lt("age",30);
        List<User> users = userMapper.selectList(query);
        System.out.println(users);
    }

    /**
     * 查询
     *   查询名字有张年龄小于30，数据不显示手机号码
     */
    @Test
    public void selectByWrapperSupper2(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.like("name","张").lt("age",30).select(User.class,info->!info.getColumn().equals("phone"));
        List<User> users = userMapper.selectList(query);
        System.out.println(users);
    }

    /**
     * 查询
     *   查询名字有张年龄小于30，数据只显示手机号码
     */
    @Test
    public void selectByWrapperSupper3(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.like("name","张").lt("age",30).select(User.class,info->info.getColumn().equals("phone"));
        List<User> users = userMapper.selectList(query);
        System.out.println(users);
    }

    @Test
    public void testCondition(){
        condition("成路涛","");//代码太多
        condition2("成路涛","");//简介代码
    }
    /**
     * 查询
     */
    private void condition(String name, String phone){
        QueryWrapper<User> query = new QueryWrapper<User>();
        if (StringUtils.isNotEmpty(name)){
            query.like("name", name);
        }
        if (StringUtils.isNotEmpty(phone)){
            query.ge("phone", phone);
        }
        List<User> users = userMapper.selectList(query);
        System.out.println(users);
    }
    private void condition2(String name, String phone){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.like(StringUtils.isNotEmpty(name),"name", name).ge(StringUtils.isNotEmpty(phone),"phone", phone);
        List<User> users = userMapper.selectList(query);
        System.out.println(users);
    }

    /**
     * 根据实体查询
     */
    @Test
    public void selectByWrapperEntity(){
        User user = new User();
        user.setName("成路涛");
        user.setAge(26);
        QueryWrapper<User> query = new QueryWrapper<User>(user);
        // query.ge("name","成路涛").like("name","路");实体或此条件二者取其一
        List<User> users = userMapper.selectList(query);
        System.out.println(users);
    }

    /**
     * 根据实体查询
     */
    @Test
    public void selectByWrapperEntity2(){
        //实体查询的是等值参数，如果想模糊查询可以实体参数上加入注解
        //@TableField(condition = SqlCondition.LIKE)
        // private String name;
        User user = new User();
        user.setName("路");
        user.setAge(26);
        QueryWrapper<User> query = new QueryWrapper<User>(user);
        List<User> users = userMapper.selectList(query);
        System.out.println(users);
    }

    @Test
    public void selectByWrapperAllEq(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "成路涛");
        //map.put("age", 26);
        //map.put("age", null);
        //query.allEq(map);
        query.allEq(map,false);
        query.allEq((k,v)->!k.equals("name"),map);
        List<User> users = userMapper.selectList(query);
        System.out.println(users);
    }

    @Test
    public void selectMaps(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        //query.like("name","成路涛");
        query.select("id").like("name","成路涛");
        List<Map<String, Object>> usersMap = userMapper.selectMaps(query);
        System.out.println(usersMap);
    }

    @Test
    public void selectObjs(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.like("name","路");
        List<Object> user =  userMapper.selectObjs(query);
        System.out.println(user);
    }

    @Test
    public void selectCount(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.like("name","路");
        Integer user =  userMapper.selectCount(query);
        System.out.println("总记录数"+user);
    }

    @Test
    public void selectOne(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.like("name","张路");
        User user =  userMapper.selectOne(query);
        System.out.println(user);
    }

    @Test
    public void selectList(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.like("name","路");
        List<User> user =  userMapper.list(query);
        System.out.println(user);
    }

    @Test
    public void selectPage(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.like("name","路");
        //Page<User> userPage = new Page<User>(1,4);
        Page<User> userPage = new Page<User>(1,10,false);
        //也可以使用自定义mappers
        //IPage<User> user =  userMapper.page(userPage, query);
        //IPage<User> user =  userMapper.selectPage(userPage, query);
        //List<User> userList = user.getRecords();
        IPage<Map<String, Object>> user =  userMapper.selectMapsPage(userPage, query);
        List<Map<String, Object>> userList = user.getRecords();
        System.out.println(user.getPages());
        System.out.println(user.getTotal());
        System.out.println(userList);
    }

    @Test
    public void deleteById(){
        userMapper.deleteById(8);
    }

    @Test
    public void deleteByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","成路涛1");
        userMapper.deleteByMap(map);
    }

    @Test
    public void deleteBatchIds(){
        userMapper.deleteBatchIds(Arrays.asList(6,9));
    }

    @Test
    public void deleteWrapper(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.eq("name","大");
        userMapper.delete(query);
    }

    @Test
    public void getOne(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.like("name","路");
        //User user = userService.getOne(query);//会报错
        User user = userService.getOne(query,false);//会警告但不会报错，返回值为1个实体对象
        //query.eq("name","小");
        //User user = userService.getOne(query);
        System.out.println(user);
    }

    @Test
    public void Batch(){
        User user = new User();
        user.setName("王五");
        user.setAge(133);
        user.setPhone("123941243");
        user.setCreateTime(new Date());

        User user1 = new User();
        user1.setName("王五1");
        user1.setAge(131);
        user1.setPhone("1239412431");
        user1.setCreateTime(new Date());
        List<User> userList = Arrays.asList(user, user1);
        userService.saveBatch(userList);
    }

    @Test
    public void Batch2(){//无则插入有则修改
        User user = new User();
        user.setName("王五");
        user.setAge(133);
        user.setPhone("123941243");
        user.setCreateTime(new Date());

        User user1 = new User();
        user1.setName("王五1");
        user1.setAge(131);
        user1.setPhone("1239412431");
        user1.setCreateTime(new Date());
        List<User> userList = Arrays.asList(user, user1);
        userService.saveOrUpdateBatch(userList);
    }

    //逻辑删除
    @Test
    public void deletedStatus(){
        userMapper.deleteById(21);
    }

    @Test
    public void list(){
        List<User> list = userMapper.selectList(null);
        System.out.println(list);
    }

    @Test
    public void update5(){//逻辑删除后只能修改有效数据
        User user = new User();
        user.setName("王五4");
        user.setAge(1111);
        user.setId(20);
        userMapper.updateById(user);
    }

    @Test
    public void select5(){//查询中排除删除标识字段
        List<User> list = userMapper.selectList(null);
        System.out.println(list);
    }

    @Test
    public void select6(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.eq("status",0);
        List<User> list = userMapper.list(query);
        System.out.println(list);
    }

    //自动填充
    @Test
    public void inster2(){
        User user = new User();
        user.setName("成路涛7");
        user.setPhone("18813024889");
        user.setAge(10);
        userMapper.insert(user);
    }

    //动态表名

}
