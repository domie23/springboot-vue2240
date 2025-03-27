
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 接取用户
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jiequyonghu")
public class JiequyonghuController {
    private static final Logger logger = LoggerFactory.getLogger(JiequyonghuController.class);

    private static final String TABLE_NAME = "jiequyonghu";

    @Autowired
    private JiequyonghuService jiequyonghuService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private GonggaoService gonggaoService;//任务资讯公告
    @Autowired
    private RenwuService renwuService;//任务
    @Autowired
    private RenwuChatService renwuChatService;//任务咨询
    @Autowired
    private RenwuCollectionService renwuCollectionService;//任务收藏
    @Autowired
    private RenwuCommentbackService renwuCommentbackService;//任务评价
    @Autowired
    private RenwuOrderService renwuOrderService;//任务订单
    @Autowired
    private FabuyonghuService fabuyonghuService;//发布用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("接取用户".equals(role))
            params.put("jiequyonghuId",request.getSession().getAttribute("userId"));
        else if("发布用户".equals(role))
            params.put("fabuyonghuId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = jiequyonghuService.queryPage(params);

        //字典表数据转换
        List<JiequyonghuView> list =(List<JiequyonghuView>)page.getList();
        for(JiequyonghuView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JiequyonghuEntity jiequyonghu = jiequyonghuService.selectById(id);
        if(jiequyonghu !=null){
            //entity转view
            JiequyonghuView view = new JiequyonghuView();
            BeanUtils.copyProperties( jiequyonghu , view );//把实体数据重构到view中
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody JiequyonghuEntity jiequyonghu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jiequyonghu:{}",this.getClass().getName(),jiequyonghu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<JiequyonghuEntity> queryWrapper = new EntityWrapper<JiequyonghuEntity>()
            .eq("username", jiequyonghu.getUsername())
            .or()
            .eq("jiequyonghu_phone", jiequyonghu.getJiequyonghuPhone())
            .or()
            .eq("jiequyonghu_id_number", jiequyonghu.getJiequyonghuIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiequyonghuEntity jiequyonghuEntity = jiequyonghuService.selectOne(queryWrapper);
        if(jiequyonghuEntity==null){
            jiequyonghu.setCreateTime(new Date());
            jiequyonghu.setPassword("123456");
            jiequyonghuService.insert(jiequyonghu);
            return R.ok();
        }else {
            return R.error(511,"账户或者接取用户手机号或者接取用户身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiequyonghuEntity jiequyonghu, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,jiequyonghu:{}",this.getClass().getName(),jiequyonghu.toString());
        JiequyonghuEntity oldJiequyonghuEntity = jiequyonghuService.selectById(jiequyonghu.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(jiequyonghu.getJiequyonghuPhoto()) || "null".equals(jiequyonghu.getJiequyonghuPhoto())){
                jiequyonghu.setJiequyonghuPhoto(null);
        }

            jiequyonghuService.updateById(jiequyonghu);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<JiequyonghuEntity> oldJiequyonghuList =jiequyonghuService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        jiequyonghuService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer jiequyonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<JiequyonghuEntity> jiequyonghuList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            JiequyonghuEntity jiequyonghuEntity = new JiequyonghuEntity();
//                            jiequyonghuEntity.setUsername(data.get(0));                    //账户 要改的
//                            //jiequyonghuEntity.setPassword("123456");//密码
//                            jiequyonghuEntity.setJiequyonghuName(data.get(0));                    //接取用户姓名 要改的
//                            jiequyonghuEntity.setJiequyonghuPhone(data.get(0));                    //接取用户手机号 要改的
//                            jiequyonghuEntity.setJiequyonghuIdNumber(data.get(0));                    //接取用户身份证号 要改的
//                            jiequyonghuEntity.setJiequyonghuPhoto("");//详情和图片
//                            jiequyonghuEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            jiequyonghuEntity.setNewMoney(data.get(0));                    //余额 要改的
//                            jiequyonghuEntity.setWanchenglv(Integer.valueOf(data.get(0)));   //完成率(百分比) 要改的
//                            jiequyonghuEntity.setJiequyonghuEmail(data.get(0));                    //接取用户邮箱 要改的
//                            jiequyonghuEntity.setCreateTime(date);//时间
                            jiequyonghuList.add(jiequyonghuEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //接取用户手机号
                                if(seachFields.containsKey("jiequyonghuPhone")){
                                    List<String> jiequyonghuPhone = seachFields.get("jiequyonghuPhone");
                                    jiequyonghuPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> jiequyonghuPhone = new ArrayList<>();
                                    jiequyonghuPhone.add(data.get(0));//要改的
                                    seachFields.put("jiequyonghuPhone",jiequyonghuPhone);
                                }
                                //接取用户身份证号
                                if(seachFields.containsKey("jiequyonghuIdNumber")){
                                    List<String> jiequyonghuIdNumber = seachFields.get("jiequyonghuIdNumber");
                                    jiequyonghuIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> jiequyonghuIdNumber = new ArrayList<>();
                                    jiequyonghuIdNumber.add(data.get(0));//要改的
                                    seachFields.put("jiequyonghuIdNumber",jiequyonghuIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<JiequyonghuEntity> jiequyonghuEntities_username = jiequyonghuService.selectList(new EntityWrapper<JiequyonghuEntity>().in("username", seachFields.get("username")));
                        if(jiequyonghuEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JiequyonghuEntity s:jiequyonghuEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //接取用户手机号
                        List<JiequyonghuEntity> jiequyonghuEntities_jiequyonghuPhone = jiequyonghuService.selectList(new EntityWrapper<JiequyonghuEntity>().in("jiequyonghu_phone", seachFields.get("jiequyonghuPhone")));
                        if(jiequyonghuEntities_jiequyonghuPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JiequyonghuEntity s:jiequyonghuEntities_jiequyonghuPhone){
                                repeatFields.add(s.getJiequyonghuPhone());
                            }
                            return R.error(511,"数据库的该表中的 [接取用户手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //接取用户身份证号
                        List<JiequyonghuEntity> jiequyonghuEntities_jiequyonghuIdNumber = jiequyonghuService.selectList(new EntityWrapper<JiequyonghuEntity>().in("jiequyonghu_id_number", seachFields.get("jiequyonghuIdNumber")));
                        if(jiequyonghuEntities_jiequyonghuIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JiequyonghuEntity s:jiequyonghuEntities_jiequyonghuIdNumber){
                                repeatFields.add(s.getJiequyonghuIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [接取用户身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        jiequyonghuService.insertBatch(jiequyonghuList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }

    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        JiequyonghuEntity jiequyonghu = jiequyonghuService.selectOne(new EntityWrapper<JiequyonghuEntity>().eq("username", username));
        if(jiequyonghu==null || !jiequyonghu.getPassword().equals(password))
            return R.error("账号或密码不正确");
        String token = tokenService.generateToken(jiequyonghu.getId(),username, "jiequyonghu", "接取用户");
        R r = R.ok();
        r.put("token", token);
        r.put("role","接取用户");
        r.put("username",jiequyonghu.getJiequyonghuName());
        r.put("tableName","jiequyonghu");
        r.put("userId",jiequyonghu.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody JiequyonghuEntity jiequyonghu, HttpServletRequest request) {
//    	ValidatorUtils.validateEntity(user);
        Wrapper<JiequyonghuEntity> queryWrapper = new EntityWrapper<JiequyonghuEntity>()
            .eq("username", jiequyonghu.getUsername())
            .or()
            .eq("jiequyonghu_phone", jiequyonghu.getJiequyonghuPhone())
            .or()
            .eq("jiequyonghu_id_number", jiequyonghu.getJiequyonghuIdNumber())
            ;
        JiequyonghuEntity jiequyonghuEntity = jiequyonghuService.selectOne(queryWrapper);
        if(jiequyonghuEntity != null)
            return R.error("账户或者接取用户手机号或者接取用户身份证号已经被使用");
        jiequyonghu.setNewMoney(0.0);
        jiequyonghu.setWanchenglv(100);
        jiequyonghu.setCreateTime(new Date());
        jiequyonghuService.insert(jiequyonghu);

        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id, HttpServletRequest request) {
        JiequyonghuEntity jiequyonghu = jiequyonghuService.selectById(id);
        jiequyonghu.setPassword("123456");
        jiequyonghuService.updateById(jiequyonghu);
        return R.ok();
    }

	/**
	 * 修改密码
	 */
	@GetMapping(value = "/updatePassword")
	public R updatePassword(String  oldPassword, String  newPassword, HttpServletRequest request) {
        JiequyonghuEntity jiequyonghu = jiequyonghuService.selectById((Integer)request.getSession().getAttribute("userId"));
		if(newPassword == null){
			return R.error("新密码不能为空") ;
		}
		if(!oldPassword.equals(jiequyonghu.getPassword())){
			return R.error("原密码输入错误");
		}
		if(newPassword.equals(jiequyonghu.getPassword())){
			return R.error("新密码不能和原密码一致") ;
		}
        jiequyonghu.setPassword(newPassword);
		jiequyonghuService.updateById(jiequyonghu);
		return R.ok();
	}



    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        JiequyonghuEntity jiequyonghu = jiequyonghuService.selectOne(new EntityWrapper<JiequyonghuEntity>().eq("username", username));
        if(jiequyonghu!=null){
            jiequyonghu.setPassword("123456");
            jiequyonghuService.updateById(jiequyonghu);
            return R.ok();
        }else{
           return R.error("账号不存在");
        }
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrJiequyonghu(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        JiequyonghuEntity jiequyonghu = jiequyonghuService.selectById(id);
        if(jiequyonghu !=null){
            //entity转view
            JiequyonghuView view = new JiequyonghuView();
            BeanUtils.copyProperties( jiequyonghu , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }



    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = jiequyonghuService.queryPage(params);

        //字典表数据转换
        List<JiequyonghuView> list =(List<JiequyonghuView>)page.getList();
        for(JiequyonghuView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JiequyonghuEntity jiequyonghu = jiequyonghuService.selectById(id);
            if(jiequyonghu !=null){


                //entity转view
                JiequyonghuView view = new JiequyonghuView();
                BeanUtils.copyProperties( jiequyonghu , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody JiequyonghuEntity jiequyonghu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,jiequyonghu:{}",this.getClass().getName(),jiequyonghu.toString());
        Wrapper<JiequyonghuEntity> queryWrapper = new EntityWrapper<JiequyonghuEntity>()
            .eq("username", jiequyonghu.getUsername())
            .or()
            .eq("jiequyonghu_phone", jiequyonghu.getJiequyonghuPhone())
            .or()
            .eq("jiequyonghu_id_number", jiequyonghu.getJiequyonghuIdNumber())
//            .notIn("jiequyonghu_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiequyonghuEntity jiequyonghuEntity = jiequyonghuService.selectOne(queryWrapper);
        if(jiequyonghuEntity==null){
            jiequyonghu.setCreateTime(new Date());
            jiequyonghu.setPassword("123456");
        jiequyonghuService.insert(jiequyonghu);

            return R.ok();
        }else {
            return R.error(511,"账户或者接取用户手机号或者接取用户身份证号已经被使用");
        }
    }

}

